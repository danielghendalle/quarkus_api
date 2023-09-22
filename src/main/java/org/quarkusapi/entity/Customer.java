package org.quarkusapi.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
@Entity
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
}
