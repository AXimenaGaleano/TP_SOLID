package com.example.pokedex.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Identificador único en la base de datos

    private String name;
    private String type;
    private double height;
    private double weight;

    // Otros atributos y métodos de la clase Pokemon

    // Constructor vacío necesario para JPA
    public Pokemon() {
    }

    public Pokemon(String name, String type, double height, double weight) {
        this.name = name;
        this.type = type;
        this.height = height;
        this.weight = weight;
    }

    // Getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

}
