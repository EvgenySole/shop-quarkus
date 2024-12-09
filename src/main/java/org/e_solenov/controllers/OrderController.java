package org.e_solenov.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.e_solenov.entities.Clients;
import org.e_solenov.entities.Orders;
import org.e_solenov.entities.Products;
import org.e_solenov.entities.Statuses;
import org.e_solenov.services.OrderService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;
import java.util.Map;

@Path("/orders")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Slf4j
public class OrderController {

    @Inject
    OrderService orderService;

    @GET
    @Path("/get-product-price-by-id/{id}")
    public Map<String, Double> findProductPriceById(@PathParam("id") long id) {
        try {
            return orderService.getProductPriceById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/get-product-quantity-by-id/{id}")
    public Map<String, Integer> findProductQuantityById(@PathParam("id") long id) {
        try {
            return orderService.getProductQuantityById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/get-product-by-id/{id}")
    public Products findProductById(@PathParam("id") long id) {
        try {
            return orderService.getProductById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/product-by-category/{id}")
    public List<Products> findProductByCategoryId(@PathParam("id") long id) {
        try {
            return orderService.getProductByCategoryId(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/get-products/")
    public List<Products> getProducts() {
        return orderService.getAllProducts();
    }

    @GET
    @Path("{id}")
    public Orders findOrderById(@PathParam("id") long id) {
        int a1[] = {6,7,8};
        int a2[] = {6,7,8};
        System.out.println(a1 == a2);
        try {
            return orderService.getOrderById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/status/{id}")
    public Statuses findStatusById(@PathParam("id") long id) {
        try {
            return orderService.getStatusById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/client/{id}")
    public Clients findClientById(@PathParam("id") long id) {
        try {
            return orderService.getClientById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    public List<Orders> findAllOrders() {
        return orderService.getAllOrders();
    }

    @GET
    @Path("/status")
    public List<Statuses> findAllStatuses() {
        return orderService.getAllStatuses();
    }

    @GET
    @Path("/client")
    public List<Clients> findAllClients() {
        return orderService.getAllClients();
    }

    @POST
    @Transactional
    public Response addOrder(Orders order) {
        return orderService.addOrder(order);
    }

    @POST
    @Path("/status")
    @Transactional
    public Response addStatus(Statuses status) {
        return orderService.addStatus(status);
    }

    @POST
    @Path("/client")
    @Transactional
    public Response addClient(Clients client) {
        return orderService.addClient(client);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteOrder(@PathParam("id") Long id) {
        try {
            orderService.deleteOrder(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/status/{id}")
    @Transactional
    public Response deleteStatus(@PathParam("id") Long id) {
        try {
            orderService.deleteStatus(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/client/{id}")
    @Transactional
    public Response deleteClient(@PathParam("id") Long id) {
        try {
            orderService.deleteClient(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateOrder(@PathParam("id") Long id, Orders order) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(orderService.updateOrder(id, order)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/status/{id}")
    @Transactional
    public Response updateStatus(@PathParam("id") Long id, Statuses status) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(orderService.updateStatus(id, status)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/client/{id}")
    @Transactional
    public Response updateClient(@PathParam("id") Long id, Clients client) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(orderService.updateClient(id, client)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }
}
