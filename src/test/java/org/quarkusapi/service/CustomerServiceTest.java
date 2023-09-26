package org.quarkusapi.service;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.quarkusapi.entity.Customer;
import org.quarkusapi.repository.CustomerRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class CustomerServiceTest {

    @InjectMocks
    private CustomerService customerService;

    @Mock
    private CustomerRepository customerRepository;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAllCustomers() {
        Customer customer1 = new Customer(1L, "João", "Silva", 30, "joao.silva@example.com");
        Customer customer2 = new Customer(2L, "Bob", "Doe", 20, "bob.doe@example.com");
        List<Customer> mockCustomers = Arrays.asList(customer1, customer2);

        Mockito.when(customerRepository.listAll()).thenReturn(mockCustomers);

        List<Customer> customers = customerService.findAllCustomers();

        assertEquals(2, customers.size());
        assertEquals(customer1.getName(), customers.get(0).getName());
        assertEquals(customer2.getName(), customers.get(1).getName());
    }

    @Test
    void testAddCustomer() {
        Customer newCustomer = new Customer(1L, "Maria", "Pereira", 35, "maria.pereira@example.com");

        Mockito.doNothing().when(customerRepository).persist(newCustomer);

        customerService.addCustomer(newCustomer);
    }

    @Test
    void testFindCustomerById() {
        Customer customer = new Customer(4L, "Alice", "Smith", 25, "alice.smith@example.com");

        when(customerRepository.findById(4L)).thenReturn(customer);

        Customer foundCustomer = customerService.findCustomerById(4L);

        assertNotNull(foundCustomer);
        assertEquals(customer.getName(), foundCustomer.getName());
    }

    @Test
    void testUpdateCustomer() {
        Customer customer = new Customer(5L, "David", "Johnson", 40, "david.johnson@example.com");
        Customer updatedCustomer = new Customer(5L, "Emily", "Davis", 28, "emily.davis@example.com");

        when(customerRepository.findById(5L)).thenReturn(customer);

        Response response = customerService.updateCustomer(5L, updatedCustomer);

        assertEquals(Status.OK.getStatusCode(), response.getStatus());
    }

    @Test
    void testUpdateCustomerNotFound() {
        Long nonExistentCustomerId = 6L;
        Customer updatedCustomer = new Customer(nonExistentCustomerId, "Emily", "Davis", 28, "emily.davis@example.com");

        when(customerRepository.findById(nonExistentCustomerId)).thenReturn(null);

        Response response = customerService.updateCustomer(nonExistentCustomerId, updatedCustomer);

        assertEquals(Status.NOT_FOUND.getStatusCode(), response.getStatus());
        assertEquals("Cliente não encontrado", response.getEntity());
    }

    @Test
    @Transactional
    void testUpdateCustomerBadRequest() {
        Customer customer = new Customer(7L, "John", "Doe", 30, "john.doe@example.com");
        Customer updatedCustomer = new Customer(null, "Maria", "Pereira", 35, "maria.pereira@example.com");

        when(customerRepository.findById(7L)).thenReturn(customer);

        Response response = customerService.updateCustomer(7L, updatedCustomer);

        assertEquals(Status.BAD_REQUEST.getStatusCode(), response.getStatus());
        assertEquals("ID do cliente na URL e no corpo da solicitação não correspondem.", response.getEntity());
    }

    @Test
    void testDeleteCustomer() {
        Customer customer = new Customer(9L, "Alice", "Smith", 25, "alice.smith@example.com");

        when(customerRepository.findById(9L)).thenReturn(customer);
        doNothing().when(customerRepository).delete(customer);

        customerService.deleteCustomer(9L);

        verify(customerRepository, times(1)).delete(customer);
    }

}
