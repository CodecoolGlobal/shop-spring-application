package com.codecool.springshopapplication.controller;

import com.codecool.springshopapplication.exceptions.OrderNotFoundException;
import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.services.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonPath;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.BDDMockito.given;


import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(OrderController.class)
class OrderControllerTest {
    @MockBean
    private OrderService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnOrdersWhenAsked() throws Exception {
        given(service.getOrders()).willReturn(List.of());
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", is(List.of())))
                .andReturn();

    }

    @Test
    void shouldReturnOrderByIdWhenAvailable() throws Exception {
        Order order = new Order();
        order.setId(1);
        order.setTitle("test title");
        given(service.getOrders(any())).willReturn(java.util.Optional.of(order));

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is((int) order.getId())))
                .andExpect(jsonPath("$.title", is(order.getTitle())))
                .andReturn();

    }

    @Test
    void shouldThrowExceptionWhenCalledUnavailableOrder() throws Exception {
        given(service.getOrders(any())).willThrow(OrderNotFoundException.class);

        mockMvc.perform(get("/orders/1"))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andReturn();
    }

    @Test
    void addOrder() {
    }

    @Test
    void updateOrder() {
    }

    @Test
    void deleteOrder() {
    }
}