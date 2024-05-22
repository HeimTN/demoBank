package com.heimtn.demobank.repository;

import com.heimtn.demobank.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("select case when count(c) > 0 then true else false end from Customer c join c.email e where e = :email")
    boolean existsByEmail(@Param("email") String email);

    @Query("select case when count(c) > 0 then true else false end from Customer c join  c.phoneNumber p where p = :phoneNumber")
    boolean existsByPhoneNumber(@Param("phoneNumber") String phoneNumber);

    Optional<Customer> findCustomerByLastName(String lastName);
}
