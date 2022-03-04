package com.ittest.blog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.AUTO;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String username;
    private String password;
}
