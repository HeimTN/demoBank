package com.heimtn.demobank.util;

import com.heimtn.demobank.dto.CustomerDTO;
import com.heimtn.demobank.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapper {
    public Customer mappingCustomerDtoToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        List<String> temp = new ArrayList<>();
        temp.add(customerDTO.getEmail());
        customer.setEmail(temp);
        temp.clear();
        temp.add(customerDTO.getPhoneNumber());
        customer.setPhoneNumber(temp);
        customer.setFirstName(customerDTO.getFirstName());
        customer.setLastName(customerDTO.getLastName());
        customer.setMiddleName(customerDTO.getMiddleName());
        customer.setBalance(customerDTO.getBalance());
        return customer;
    }
}
