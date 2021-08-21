package com.codecool.springshopapplication.controller;

import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return List.of();
    }

    @GetMapping("/orders/{id}")
    public Order getOrders(@PathVariable String id){
        var mockOrder = new Order();

        mockOrder.setId(1);
        mockOrder.setTitle("Test order");

        return mockOrder;
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order Order){
        // add Order
        Order.setId(11);
        return Order;
    }

    @PutMapping("/orders/{id}")
    public void updateOrder(@PathVariable long id, @RequestBody Order Order){
        // update Order
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK) // optional - default set on this
    public void deleteOrder(@PathVariable long id){
        // delete Order
    }
}
