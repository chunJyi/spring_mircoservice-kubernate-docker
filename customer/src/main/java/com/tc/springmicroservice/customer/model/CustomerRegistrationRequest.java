package com.tc.springmicroservice.customer.model;

public record CustomerRegistrationRequest (
        String firstName,
        String lastName,
        String email
){

}
