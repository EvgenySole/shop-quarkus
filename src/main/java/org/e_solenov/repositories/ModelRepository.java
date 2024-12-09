package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Models;

@ApplicationScoped
public class ModelRepository implements PanacheRepository<Models> {
}
