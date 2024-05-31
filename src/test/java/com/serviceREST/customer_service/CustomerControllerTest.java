package com.serviceREST.customer_service.controller;

import com.serviceREST.customer_service.model.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void testGetCustomerInfoValid() throws Exception {
        mockMvc.perform(get("/api/customers/C/23445322")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("Juan"))
                .andExpect(jsonPath("$.middleName").value("Carlos"))
                .andExpect(jsonPath("$.lastName").value("Perez"))
                .andExpect(jsonPath("$.secondLastName").value("Gomez"));
    }

    @Test
    public void testGetCustomerInfoInvalidType() throws Exception {
        mockMvc.perform(get("/api/customers/X/23445322")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Invalid document type"));
    }

    @Test
    public void testGetCustomerInfoNotFound() throws Exception {
        mockMvc.perform(get("/api/customers/C/00000000")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().string("Customer not found"));
    }
}