package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Categories;

@ApplicationScoped
public class CategoryRepository implements PanacheRepository<Categories> {
}
