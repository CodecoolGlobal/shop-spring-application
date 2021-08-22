package com.codecool.springshopapplication.controller;

import com.codecool.springshopapplication.model.Order;
import com.codecool.springshopapplication.repositories.OrderRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


import java.util.ArrayList;
import java.util.List;

@SpringBootTest // Runs spring
@AutoConfigureMockMvc // Set mock MVC request manager
class OrderControllerIntegrationTest {
    // Integrity tests

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    private void setUp() {
        Order order1 = new Order();
        order1.setTitle("Daily shopping");

        Order order2 = new Order();
        order2.setTitle("Monthly shopping");

        orderRepository.saveAll(List.of(order1, order2));
    }

    @AfterEach
    private void tearDown() {
        orderRepository.deleteAll();
    }

    @Test
    void shouldBeOkAfterAddingOrder() throws Exception {
        Order order = new Order();
        order.setTitle("Monthly shopping");

        mockMvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsBytes(order))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title", is(order.getTitle())))
                .andReturn();

        // CONTROVERSIAL if we would repository.findAll()
        String contentAsString = mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        List<Order> orders = objectMapper.readValue(contentAsString, new TypeReference<List<Order>>() {
        });

        assertThat(orders)
                .hasSize(3);
    }

}
