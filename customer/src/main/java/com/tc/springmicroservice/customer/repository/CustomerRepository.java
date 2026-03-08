package com.tc.springmicroservice.customer.repository;

import com.tc.springmicroservice.customer.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {


}
