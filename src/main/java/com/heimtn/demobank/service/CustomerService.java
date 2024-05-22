package com.heimtn.demobank.service;


import com.heimtn.demobank.dto.CustomerDTO;

public interface CustomerService {

    boolean addCustomer(CustomerDTO customerDTO);

    boolean addEmailAuthorizeCustomer(String email);

    boolean addPhoneNumberAuthorizeCustomer(String phoneNumber);

    boolean deleteEmailAuthorizeCustomer(String email);

    boolean deletePhoneNumberAuthorizeCustomer(String phoneNumber);
}
