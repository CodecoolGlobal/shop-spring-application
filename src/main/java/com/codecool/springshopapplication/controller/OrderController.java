package com.codecool.springshopapplication.controller;

import com.codecool.springshopapplication.exceptions.OrderNotFoundException;
import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.services.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @GetMapping("/orders/{id}")
    public Order getOrders(@PathVariable Long id){
        return service.getOrders(id)
                .orElseThrow(OrderNotFoundException::new);
    }

    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order addOrder(@RequestBody Order order){
        return service.addOrder(order);
    }

    @PutMapping("/orders/{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order){
        return service.updateOrder(id, order);
    }

    @DeleteMapping("/orders/{id}")
    @ResponseStatus(HttpStatus.OK) // optional - default set on this
    public void deleteOrder(@PathVariable long id){
        service.deleteOrder(id);
    }
}
