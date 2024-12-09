package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Statuses;

@ApplicationScoped
public class StatusRepository implements PanacheRepository<Statuses> {
}
