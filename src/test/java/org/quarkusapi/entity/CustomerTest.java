package org.quarkusapi.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CustomerTest {

    @Test
    void testCustomerConstructorAndGetters() {
        Long id = 1L;
        String name = "John";
        String lastname = "Doe";
        Integer age = 30;
        String email = "john.doe@example.com";
        Customer customer = new Customer(id, name, lastname, age, email);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(lastname, customer.getLastname());
        assertEquals(age, customer.getAge());
        assertEquals(email, customer.getEmail());
    }

    @Test
    void testCustomerSetter() {
        Customer customer = new Customer();

        Long id = 1L;
        String name = "John";
        String lastname = "Doe";
        Integer age = 30;
        String email = "john.doe@example.com";

        customer.setId(id);
        customer.setName(name);
        customer.setLastname(lastname);
        customer.setAge(age);
        customer.setEmail(email);

        assertEquals(id, customer.getId());
        assertEquals(name, customer.getName());
        assertEquals(lastname, customer.getLastname());
        assertEquals(age, customer.getAge());
        assertEquals(email, customer.getEmail());
    }

    @Test
    void testCustomerNoArgsConstructor() {
        Customer customer = new Customer();

        assertNotNull(customer);

        assertNull(customer.getId());
        assertNull(customer.getName());
        assertNull(customer.getLastname());
        assertNull(customer.getAge());
        assertNull(customer.getEmail());
    }
}
