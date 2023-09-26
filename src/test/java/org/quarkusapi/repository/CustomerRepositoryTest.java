package org.quarkusapi.repository;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.quarkusapi.entity.Customer;

import jakarta.inject.Inject;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@QuarkusTest
public class CustomerRepositoryTest {

    @Inject
    private CustomerRepository customerRepository;

    @Test
    public void testFindAllCustomers() {
        // Act
        List<Customer> actualCustomers = customerRepository.findAllCustomers();

        // Assert
        assertNotNull(actualCustomers);
        // You can add additional assertions here based on the expected behavior of findAllCustomers
    }
}
