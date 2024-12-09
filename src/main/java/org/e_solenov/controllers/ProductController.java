package org.e_solenov.controllers;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.e_solenov.entities.Categories;
import org.e_solenov.entities.Models;
import org.e_solenov.entities.Products;
import org.e_solenov.services.ProductService;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import java.util.List;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Slf4j
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Path("/quantity/{id}")
    public Response getProductQuantity(@PathParam("id") long id) {
        try {
            return Response.status(Response.Status.OK)
                    .entity(productService.getProductQuantity(id)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/price/{id}")
    public Response getProductPrice(@PathParam("id") long id) {
        try {
            return Response.status(Response.Status.OK)
                    .entity(productService.getProductPrice(id)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("{id}")
    public Products findProductById(@PathParam("id") long id) {
        try {
            return productService.getProductById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/category/{id}")
    public Categories findCategoryById(@PathParam("id") long id) {
        try {
            return productService.getCategoryById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/model/{id}")
    public Models findModelById(@PathParam("id") long id) {
        try {
            return productService.getModelById(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    @Path("/product-by-category/{id}")
    public List<Products> findProductByCategoryId(@PathParam("id") long id) {
        try {
            return productService.getProductByCategoryId(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @GET
    public List<Products> findAllProducts() {
        return productService.getAllProducts();
    }

    @GET
    @Path("/category")
    public List<Categories> findAllCategories() {
        return productService.getAllCategories();
    }

    @GET
    @Path("/model")
    public List<Models> findAllModels() {
        return productService.getAllModels();
    }

    @POST
    @Transactional
    public Response addProduct(Products product) {
        return productService.addProduct(product);
    }

    @POST
    @Path("/category")
    @Transactional
    public Response addCategory(Categories category) {
        return productService.addCategory(category);
    }

    @POST
    @Path("/model")
    @Transactional
    public Response addModel(Models model) {
        return productService.addModel(model);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteProduct(@PathParam("id") Long id) {
        try {
            productService.deleteProduct(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/category/{id}")
    @Transactional
    public Response deleteCategory(@PathParam("id") Long id) {
        try {
            productService.deleteCategory(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @DELETE
    @Path("/model/{id}")
    @Transactional
    public Response deleteModel(@PathParam("id") Long id) {
        try {
            productService.deleteModel(id);
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response updateProduct(@PathParam("id") Long id, Products product) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(productService.updateProduct(id, product)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/category/{id}")
    @Transactional
    public Response updateCategory(@PathParam("id") Long id, Categories category) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(productService.updateCategory(id, category)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }

    @PUT
    @Path("/model/{id}")
    @Transactional
    public Response updateModel(@PathParam("id") Long id, Models model) {
        try {
            return Response.status(Response.Status.CREATED)
                    .entity(productService.updateModel(id, model)).build();
        } catch (EntityNotFoundException e) {
            log.error(e.getMessage(), e);
            throw new NotFoundException();
        }
    }
}
