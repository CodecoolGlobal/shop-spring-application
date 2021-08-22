package com.codecool.springshopapplication.services;

import com.codecool.springshopapplication.exceptions.OrderNotFoundException;
import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.repositories.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public List<Order> getOrders() {
        return  repository.findAll();
    }

    public Optional<Order> getOrders(Long id) {
        return repository.findById(id);
    }

    public Order addOrder(Order order) {
        return repository.save(order);
    }

    public Order updateOrder(long id,Order order) {
        Optional<Order> optionalOrder = repository.findById(id);

        Order updateOrder = optionalOrder.map(o -> {
            o.setTitle(order.getTitle());
            return o;
        }).orElse(order);
        return repository.save(updateOrder);
    }

    public void deleteOrder(long id) {
        Optional<Order> optionalOrder = repository.findById(id);

        if(optionalOrder.isPresent()) {
            repository.delete(optionalOrder.get());
        } else {
            throw new OrderNotFoundException();
        }
    }
}
