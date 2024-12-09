package org.e_solenov.services;

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
import org.e_solenov.entities.Categories;
import org.e_solenov.entities.Models;
import org.e_solenov.entities.Products;
import org.e_solenov.repositories.CategoryRepository;
import org.e_solenov.repositories.ModelRepository;
import org.e_solenov.repositories.ProductRepository;

import java.util.List;
import java.util.Map;

@ApplicationScoped
@Slf4j
public class ProductService {

    @Inject
    ProductRepository productRepository;
    @Inject
    ModelRepository modelRepository;
    @Inject
    CategoryRepository categoryRepository;

    @CacheResult(cacheName = "productQuantity")
    public Map<String, Integer> getProductQuantity(Long id) {
        log.info("Getting product quantity by id: {}", id);
        return Map.of("quantity", getProductById(id).getQuantity());
    }

    @CacheResult(cacheName = "productPrice")
    public Map<String, Double> getProductPrice(Long id) {
        log.info("Getting product price by id: {}", id);
        return Map.of("price", getProductById(id).getPrice());
    }

    @CacheResult(cacheName = "allProducts")
    public List<Products> getAllProducts() {
        log.info("Getting all products");
        return productRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "allCategories")
    public List<Categories> getAllCategories() {
        log.info("Getting all categories");
        return categoryRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "allModels")
    public List<Models> getAllModels() {
        log.info("Getting all models");
        return modelRepository.findAll().stream().toList();
    }

    @CacheResult(cacheName = "product")
    public Products getProductById(Long id) {
        log.info("Getting product by id: {}", id);
        return productRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Product not found by id " + id));
    }

    @CacheResult(cacheName = "category")
    public Categories getCategoryById(Long id) {
        log.info("Getting category by id: {}", id);
        return categoryRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Category not found by id " + id));
    }

    @CacheResult(cacheName = "model")
    public Models getModelById(Long id) {
        log.info("Getting model by id: {}", id);
        return modelRepository.findByIdOptional(id).orElseThrow(()
                -> new EntityNotFoundException("Model not found by id " + id));
    }

    @CacheResult(cacheName = "productByCategory")
    public List<Products> getProductByCategoryId(long id) {
        log.info("Getting products by category id: {}", id);
        return productRepository.findByCategoryId(id).stream().toList();
    }

    @Transactional
    @CacheInvalidate(cacheName = "allProducts")
    @CacheInvalidateAll(cacheName = "productByCategory")
    public Response addProduct(Products product) {
        log.info("Adding new product: {}", product);
        try {
            getProductById(product.getId());
        } catch (EntityNotFoundException e) {
            productRepository.getEntityManager().merge(product);
            return Response.status(Response.Status.CREATED).entity(product).build();
        }
        log.error("Adding new product failed, id already exists: {}", product.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "allCategories")
    public Response addCategory(Categories category) {
        log.info("Adding new category: {}", category);
        try {
            getProductById(category.getId());
        } catch (EntityNotFoundException e) {
            categoryRepository.getEntityManager().merge(category);
            return Response.status(Response.Status.CREATED).entity(category).build();
        }
        log.error("Adding new category failed, id already exists: {}", category.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "allModels")
    public Response addModel(Models model) {
        log.info("Adding new model: {}", model);
        try {
            getProductById(model.getId());
        } catch (EntityNotFoundException e) {
            modelRepository.getEntityManager().merge(model);
            return Response.status(Response.Status.CREATED).entity(model).build();
        }
        log.error("Adding new model failed, id already exists: {}", model.getId());
        return Response.status(Response.Status.CONFLICT).build();
    }

    @Transactional
    @CacheInvalidate(cacheName = "product")
    @CacheInvalidate(cacheName = "productQuantity")
    @CacheInvalidate(cacheName = "productPrice")
    @CacheInvalidateAll(cacheName = "allProducts")
    @CacheInvalidateAll(cacheName = "productByCategory")
    public void deleteProduct(Long id) {
        log.info("Deleting product by id: {}", id);
        getProductById(id);
        productRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "category")
    @CacheInvalidateAll(cacheName = "allCategories")
    public void deleteCategory(Long id) {
        log.info("Deleting category by id: {}", id);
        getCategoryById(id);
        categoryRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "model")
    @CacheInvalidateAll(cacheName = "allModels")
    public void deleteModel(Long id) {
        log.info("Deleting model by id: {}", id);
        getModelById(id);
        modelRepository.deleteById(id);
    }

    @Transactional
    @CacheInvalidate(cacheName = "product")
    @CacheInvalidate(cacheName = "productQuantity")
    @CacheInvalidate(cacheName = "productPrice")
    @CacheInvalidateAll(cacheName = "allProducts")
    @CacheInvalidateAll(cacheName = "productByCategory")
    public Products updateProduct(@CacheKey Long id, Products product) {
        Products productByIdToUpdate = getProductById(id);
        log.info("Updating product by id: {}", id);
        if (product.getCategoryID() != null) {
            productByIdToUpdate.setCategoryID(product.getCategoryID());
        }
        if (product.getModelNumber() != null) {
            productByIdToUpdate.setModelNumber(product.getModelNumber());
        }
        if (product.getProductImage() != null) {
            productByIdToUpdate.setProductImage(product.getProductImage());
        }
        if (product.getPrice() != 0.0) {
            productByIdToUpdate.setPrice(product.getPrice());
        }
        if (product.getDescription() != null) {
            productByIdToUpdate.setDescription(product.getDescription());
        }
        if (product.getProductionDate() != null) {
            productByIdToUpdate.setProductionDate(product.getProductionDate());
        }
        productRepository.getEntityManager().merge(productByIdToUpdate);
        return product;
    }

    @Transactional
    @CacheInvalidate(cacheName = "category")
    @CacheInvalidateAll(cacheName = "allCategories")
    public Categories updateCategory(@CacheKey Long id, Categories category) {
        Categories categoryByIdToUpdate = getCategoryById(id);
        log.info("Updating category by id: {}", id);
        if (category.getCategoryName() != null) {
            categoryByIdToUpdate.setCategoryName(category.getCategoryName());
        }
        if (category.getDescription() != null) {
            categoryByIdToUpdate.setDescription(category.getDescription());
        }
        categoryRepository.getEntityManager().merge(categoryByIdToUpdate);
        return category;
    }

    @Transactional
    @CacheInvalidate(cacheName = "model")
    @CacheInvalidateAll(cacheName = "allModels")
    public Models updateModel(@CacheKey Long id, Models model) {
        Models modelByIdToUpdate = getModelById(id);
        log.info("Updating model by id: {}", id);
        if (model.getModelName() != null) {
            modelByIdToUpdate.setModelName(model.getModelName());
        }
        if (model.getParameters() != null) {
            modelByIdToUpdate.setParameters(model.getParameters());
        }
        modelRepository.getEntityManager().merge(modelByIdToUpdate);
        return model;
    }
}
