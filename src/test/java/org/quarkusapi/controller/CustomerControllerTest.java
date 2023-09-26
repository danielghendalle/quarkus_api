package org.quarkusapi.controller;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.quarkusapi.controller.CustomerController;
import org.quarkusapi.entity.Customer;
import org.quarkusapi.service.CustomerService;
import org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;


@QuarkusTest
class CustomerControllerTest {
    @Inject
    CustomerController customerController;

    @InjectMock
    CustomerService customerService;

    Customer customer = new Customer();

    @Test
    void retrieveCustomers() {
        List<Customer> customers = new ArrayList<>();
        customers.add(new Customer(1L, "João", "Silva", 30, "joao.silva@example.com"));
        customers.add(new Customer(2L, "Bob", "Doe", 20, "bob.doe@example.com"));

        when(customerService.findAllCustomers()).thenReturn(customers);

        List<Customer> customers1 = customerController.retrieveCustomers();

        assertEquals(2, customers1.size());
        assertEquals("João", customers1.get(0).getName());
        assertEquals("Bob", customers1.get(1).getName());

        Mockito.verify(customerService, Mockito.times(1)).findAllCustomers();
    }

    @Test
    void findCustomer() {
        Long customerId = 1L;
        Customer mockCustomer = new Customer(customerId, "Maria", "Pereira", 35, "maria.pereira@example.com");
        when(customerService.findCustomerById(customerId)).thenReturn(mockCustomer);

        Customer retrievedCustomer;
        retrievedCustomer = customerController.findCustomer(customerId);

        assertEquals("Maria", retrievedCustomer.getName());
    }

    @Test
    void addCustomer() {
        Customer newCustomer = new Customer(3L, "Alice", "Smith", 28, "alice.smith@example.com");

        customerController.addCustomer(newCustomer);

        Mockito.verify(customerService, Mockito.times(1)).addCustomer(newCustomer);
    }

    @Test
    void updateCustomer() {
        Long customerId = 1L;
        Customer updatedCustomer = new Customer(customerId, "Maria", "Pereira", 35, "maria.pereira@example.com");

        Response mockResponse = Response.status(Response.Status.OK).entity("Cliente atualizado com sucesso").build();
        when(customerService.updateCustomer(customerId, updatedCustomer)).thenReturn(mockResponse);

        Response response = customerController.updateCustomer(customerId, updatedCustomer);

        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals("Cliente atualizado com sucesso", response.getEntity());
    }

    @Test
    void deleteCustomer() {
        // Simule o cliente a ser excluído
        Long customerId = 1L;

        // Simule o comportamento de exclusão no CustomerService
        customerController.deleteCustomer(customerId);

        Mockito.verify(customerService, Mockito.times(1)).deleteCustomer(customerId);
    }
}