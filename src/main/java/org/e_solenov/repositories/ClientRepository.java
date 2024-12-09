package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Clients;

@ApplicationScoped
public class ClientRepository implements PanacheRepository<Clients> {
}
