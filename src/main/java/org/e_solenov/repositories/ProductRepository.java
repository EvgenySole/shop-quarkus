package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Products;

import java.util.List;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Products> {
    public List<Products> findByCategoryId(Long id){
        return find("from Products WHERE categoryID.id = ?1", id).list();
    }
}
