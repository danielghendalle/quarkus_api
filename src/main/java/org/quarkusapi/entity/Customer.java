package org.quarkusapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
@Table(name = "CUSTOMER")
@Data
@NoArgsConstructor
public class Customer{

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String lastname;

    private Integer age;

    private String email;

    public Customer(Long id, String name, String lastname, Integer age, String email) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.age = age;
        this.email = email;
    }
}
