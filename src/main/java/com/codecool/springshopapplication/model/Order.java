package com.codecool.springshopapplication.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;

    @Column
    private String title;

    @OneToMany(mappedBy="order")
    @JsonManagedReference
    private Set<Product> orderedProducts = new HashSet<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Set<Product> getOrderedProducts() {
        return orderedProducts;
    }

    public void setOrderedProducts(Set<Product> orderedProducts) {
        this.orderedProducts = orderedProducts;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id == order.id && Objects.equals(title, order.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title);
    }
}
