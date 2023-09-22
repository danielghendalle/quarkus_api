package org.quarkusapi.controller;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.quarkusapi.entity.Customer;
import org.quarkusapi.service.CustomerService;

import java.util.ArrayList;
import java.util.List;

@Path("/customers")
public class CustomerController {
    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Customer> retrieveCustomers() {
        return customerService.findAllCustomers();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Customer findCustomer(@PathParam("id") Long id) {
        return customerService.findCustomerById(id);
    }

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addCustomer(Customer customer) {
        customerService.addCustomer(customer);
        return Response.status(Response.Status.OK).entity("Cliente adicionado com sucesso").build();
    }

    @PUT
    @Path("/{id}")
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateCustomer(@PathParam("id") Long id, Customer customer) {
        return customerService.updateCustomer(id, customer);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response deleteCustomer(@PathParam("id") Long id) {
        customerService.deleteCustomer(id);
        return Response.status(Response.Status.OK).entity("Cliente excluído com sucesso").build();
    }
}
