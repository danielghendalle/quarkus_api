package org.quarkusapi.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.core.Response;
import org.quarkusapi.entity.Customer;
import org.quarkusapi.repository.CustomerRepository;

import java.util.List;

@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerRepository customerRepository;


    public List<Customer> findAllCustomers() {
        return customerRepository.findAll().list();
    }

    public void addCustomer(Customer customer) {
        customerRepository.persist(customer);
    }

    public Customer findCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    @Transactional
    public Response updateCustomer(Long id, Customer updatedCustomer) {
        try {
            Customer existingCustomer = findCustomerById(id);
            if (existingCustomer == null) {
                return Response.status(Response.Status.NOT_FOUND).entity("Cliente não encontrado").build();
            }

            if (!existingCustomer.getId().equals(updatedCustomer.getId())) {
                return Response.status(Response.Status.BAD_REQUEST).entity("ID do cliente na URL e no corpo da solicitação não correspondem.").build();
            }

            existingCustomer.setName(updatedCustomer.getName());
            existingCustomer.setLastname(updatedCustomer.getLastname());
            existingCustomer.setAge(updatedCustomer.getAge());
            existingCustomer.setEmail(updatedCustomer.getEmail());

            customerRepository.persist(existingCustomer);

            return Response.status(Response.Status.OK).entity("Cliente atualizado com sucesso").build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Erro ao atualizar o cliente: " + e.getMessage()).build();
        }
    }

    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id);
        if (customer != null) {
            customerRepository.delete(customer);
        }
    }


}
