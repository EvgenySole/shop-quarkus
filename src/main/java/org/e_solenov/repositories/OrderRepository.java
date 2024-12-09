package org.e_solenov.repositories;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.e_solenov.entities.Orders;

@ApplicationScoped
public class OrderRepository implements PanacheRepository<Orders> {
}
