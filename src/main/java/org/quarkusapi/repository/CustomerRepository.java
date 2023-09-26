package org.quarkusapi.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.quarkusapi.entity.Customer;

import java.util.List;

@ApplicationScoped
public class CustomerRepository implements PanacheRepository<Customer> {

    public List<Customer> findAllCustomers() {
        return listAll();
    }

}
