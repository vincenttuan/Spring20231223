package com.mvc.psi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mvc.psi.model.po.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
