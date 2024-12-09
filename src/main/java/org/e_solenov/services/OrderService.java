package org.e_solenov.services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheInvalidateAll;
import io.quarkus.cache.CacheKey;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.e_solenov.entities.*;
import org.e_solenov.repositories.ClientRepository;
import org.e_solenov.repositories.OrderRepository;
import org.e_solenov.repositories.StatusRepository;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;

@ApplicationScoped
@Slf4j
public class OrderService {
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    @Inject
    OrderRepository orderRepository;
    @Inject
    StatusRepository statusRepository;
    @Inject
    ClientRepository clientRepository;

    public OrderService() {
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    public Map<String, Double> getProductPriceById(long id) {
        return Map.of("price" ,sendRequest(id, "products").getFirst().getPrice());
    }

    public Map<String, Integer> getProductQuantityById(long id) {
        return Map.of("quantity" ,sendRequest(id, "products").getFirst().getQuantity());
    }

    public Products getProductById(long id) {
        return sendRequest(id, "products").getFirst();
    }

    public List<Products> getProductByCategoryId(long id) {
        return sendRequest(id, "products/product-by-category");
    }

    public List<Products> getAllProducts() {
        return sendRequest(-1, "products");
    }

    private List<Products> sendRequest(long id, String... args) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/" + args[0] + "/" + id)).GET().build();
        String infoLog = String.format("Getting %s by id: %d", args[0], id);
        if (id == -1){
            request = HttpRequest.newBuilder()
                    .uri(URI.create("http://localhost:8080/" + args[0])).GET().build();
            infoLog = String.format("Getting all %s", args[0]);
        }
        try {
            log.info(infoLog);
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() == 404) {
                throw new EntityNotFoundException(args[0] + " not found by id " + id);
            }
            if (id == -1 || Objects.equals(args[0], "products/product-by-category")) {
                return objectMapper.readValue(response.body(), new TypeReference<>() {});
            }
            return Collections.singletonList(objectMapper.readValue(response.body(), Products.class));
        } catch (IOException | InterruptedException e) {
            log.error("Fail to fetch {} from products service", args[0], e);
            throw new RuntimeException("Fail to fetch response" ,e);
        }
    }

    @CacheResult(cacheName = "allOrders")
    public List<Orders> getAllOrders() {
        log.info("Getting all orders");
        return orderRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "allStatuses")
    public List<Statuses> getAllStatuses() {
        log.info("Getting all statuses");
        return statusRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "allClients")
    public List<Clients> getAllClients() {
        log.info("Getting all clients");
        return clientRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "order")
    public Orders getOrderById(long id) {
        log.info("Getting order by id: {}", id);
        return orderRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Order not found by id " + id));
    }

    @CacheResult(cacheName = "status")
    public Statuses getStatusById(long id) {
        log.info("Getting status by id: {}", id);
        return statusRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Status not found by id " + id));
    }

    @CacheResult(cacheName = "client")
    public Clients getClientById(long id) {
        log.info("Getting client by id: {}", id);
        return clientRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found by id " + id));
    }

    @Transactional
    @CacheInvalidate(cacheName = "allOrders")
    public Response addOrder(Orders order) {
        log.info("Adding new order: {}", order);
        try {
            getOrderById(order.getId());
        } catch (EntityNotFoundException e) {
            Products product;
            double orderFullPrice = 0.0;
            List<Integer> orderDetailsToDelete = new ArrayList<>();
            log.info("Adding order details");
            for (int i = 0; i < order.getOrderDetailsList().size(); i++){
                try {
                    product = getProductById(order.getOrderDetailsList().get(i).getProductID());
                    if (order.getOrderDetailsList().get(i).getQuantity() > product.getQuantity()){
                        order.getOrderDetailsList().get(i).setQuantity(product.getQuantity());
                    }
                    order.getOrderDetailsList().get(i).setUnitPrice(product.getPrice());
                    orderFullPrice += order.getOrderDetailsList().get(i).getQuantity() *
                            order.getOrderDetailsList().get(i).getUnitPrice();
                } catch (EntityNotFoundException e2) {
                    log.error("Getting product info failed, id: {}",
                            order.getOrderDetailsList().get(i).getProductID());
                    orderDetailsToDelete.add(i);
                }
            }
            for (int i = orderDetailsToDelete.size() - 1; i >= 0; i--){
                order.getOrderDetailsList().remove((int)orderDetailsToDelete.get(i));
            }
            order.setOrderPrice(orderFullPrice);
            orderRepository.getEntityManager().merge(order);
            return Response.status(Response.Status.CREATED).entity(order).build();
        }
        log.error("Adding new order failed, id already exists: {}", order.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "allStatuses")
    public Response addStatus(Statuses status) {
        log.info("Adding new status: {}", status);
        try {
            getStatusById(status.getId());
        } catch (EntityNotFoundException e) {
            statusRepository.getEntityManager().merge(status);
            return Response.status(Response.Status.CREATED).entity(status).build();
        }
        log.error("Adding new status failed, id already exists: {}", status.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "allClients")
    public Response addClient(Clients client) {
        log.info("Adding new client: {}", client);
        try {
            getClientById(client.getId());
        } catch (EntityNotFoundException e) {
            clientRepository.getEntityManager().merge(client);
            return Response.status(Response.Status.CREATED).entity(client).build();
        }
        log.error("Adding new client failed, id already exists: {}", client.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "order")
    @CacheInvalidateAll(cacheName = "allOrders")
    public void deleteOrder(Long id) {
        log.info("Deleting order by id: {}", id);
        orderRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Order not found by id " + id));
        orderRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "status")
    @CacheInvalidateAll(cacheName = "allStatuses")
    public void deleteStatus(Long id) {
        log.info("Deleting status by id: {}", id);
        statusRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Status not found by id " + id));
        statusRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "client")
    @CacheInvalidateAll(cacheName = "allClients")
    public void deleteClient(Long id) {
        log.info("Deleting client by id: {}", id);
        clientRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found by id " + id));
        clientRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "order")
    @CacheInvalidateAll(cacheName = "allOrders")
    public Orders updateOrder(@CacheKey Long id, Orders order) {
        Orders orderByIdToUpdate = orderRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Order not found by id " + id));
        log.info("Updating order by id: {}", id);
        if (order.getOrderDate() != null) {
            orderByIdToUpdate.setOrderDate(order.getOrderDate());
        }
        if (order.getShipDate() != null) {
            orderByIdToUpdate.setShipDate(order.getShipDate());
        }
        if (order.getOrderPrice() != 0.0) {
            orderByIdToUpdate.setOrderPrice(order.getOrderPrice());
        }
        if (order.getClientID() != null) {
            orderByIdToUpdate.setClientID(order.getClientID());
        }
        if (order.getStatusID() != null) {
            orderByIdToUpdate.setStatusID(order.getStatusID());
        }
        if (order.getOrderDetailsList() != null) {
            orderByIdToUpdate.setOrderDetailsList(order.getOrderDetailsList());
        }
        orderRepository.getEntityManager().merge(orderByIdToUpdate);
        return order;
    }

    @Transactional
    @CacheInvalidate(cacheName = "status")
    @CacheInvalidateAll(cacheName = "allStatuses")
    public Statuses updateStatus(@CacheKey Long id, Statuses status) {
        Statuses statusByIdToUpdate = statusRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Status not found by id " + id));
        log.info("Updating status by id: {}", id);
        if (status.getStatusName() != null) {
            statusByIdToUpdate.setStatusName(status.getStatusName());
        }
        statusRepository.getEntityManager().merge(statusByIdToUpdate);
        return statusByIdToUpdate;
    }

    @Transactional
    @CacheInvalidate(cacheName = "client")
    @CacheInvalidateAll(cacheName = "allClients")
    public Clients updateClient(@CacheKey Long id, Clients client) {
        Clients clientByIdToUpdate = clientRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Client not found by id " + id));
        log.info("Updating client by id: {}", id);
        if (client.getName() != null) {
            clientByIdToUpdate.setName(client.getName());
        }
        if (client.getAddress() != null) {
            clientByIdToUpdate.setAddress(client.getAddress());
        }
        if (client.getEmail() != null) {
            clientByIdToUpdate.setEmail(client.getEmail());
        }
        if (client.getPhoneNumber() != null) {
            clientByIdToUpdate.setPhoneNumber(client.getPhoneNumber());
        }
        clientRepository.getEntityManager().merge(clientByIdToUpdate);
        return clientByIdToUpdate;
    }
}
