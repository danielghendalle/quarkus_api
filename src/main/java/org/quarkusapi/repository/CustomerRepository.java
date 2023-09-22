package org.quarkusapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.quarkusapi.entity.Customer;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {
}
