package com.serviceREST.customer_service.controller;

import com.serviceREST.customer_service.model.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {

    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @GetMapping("/{type}/{number}")
    public ResponseEntity<?> getCustomerInfo(@PathVariable String type, @PathVariable String number) {
        logger.info("Request received: type={}, number={}", type, number);

        if (!type.equals("C") && !type.equals("P")) {
            logger.warn("Invalid document type: {}", type);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid document type");
        }

        if (type.equals("C") && number.equals("23445322")) {
            Customer customer = new Customer();
            customer.setFirstName("Juan");
            customer.setMiddleName("Carlos");
            customer.setLastName("Perez");
            customer.setSecondLastName("Gomez");
            customer.setPhone("123456789");
            customer.setAddress("Calle 123 #45-67");
            customer.setCity("Bogotá");
            logger.info("Customer found: {}", customer);
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        } else {
            logger.warn("Customer not found: type={}, number={}", type, number);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        logger.error("Internal server error", e);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal server error");
    }
}