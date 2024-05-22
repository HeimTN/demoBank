package com.heimtn.demobank.service.impl;

import com.heimtn.demobank.dto.CustomerDTO;
import com.heimtn.demobank.model.Customer;
import com.heimtn.demobank.repository.CustomerRepository;
import com.heimtn.demobank.service.CustomerService;
import com.heimtn.demobank.util.CustomerMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerService.class);

    public CustomerServiceImpl(CustomerRepository customerRepository, CustomerMapper customerMapper){
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }
    @Override
    public boolean addCustomer(CustomerDTO customerDTO) {
        if(customerRepository.existsByEmail(customerDTO.getEmail()) && customerRepository.existsByPhoneNumber(customerDTO.getPhoneNumber())){
            Customer customer = customerMapper.mappingCustomerDtoToCustomer(customerDTO);
            LOGGER.info("Save customer: "+ customer);
            customerRepository.save(customer);
            return true;
        } else {
            LOGGER.info("Not saved. customer: "+ customerDTO);
             return false;
        }
    }

    @Override
    public boolean addEmailAuthorizeCustomer(String email) {
        if(customerRepository.existsByEmail(email)){
            Customer customer = getAuthorizationCustomer();
            List<String> result = customer.getEmail();
            result.add(email);
            LOGGER.info("Add new email: "+email+" to customer: "+customer);
            customer.setEmail(result);
            LOGGER.info("Save customer: "+ customer);
            customerRepository.save(customer);
            return true;
        } else {
            LOGGER.info("Not add email. customer: "+ getAuthorizationCustomer());
            return false;
        }
    }

    @Override
    public boolean addPhoneNumberAuthorizeCustomer(String phoneNumber) {
        if(customerRepository.existsByPhoneNumber(phoneNumber)){
            Customer customer = getAuthorizationCustomer();
            List<String> result = customer.getPhoneNumber();
            result.add(phoneNumber);
            LOGGER.info("Add new phone number: "+phoneNumber+" to customer: "+customer);
            customer.setPhoneNumber(result);
            LOGGER.info("Save customer: "+ customer);
            customerRepository.save(customer);
            return true;
        } else {
            LOGGER.info("Not add phone number. customer: "+ getAuthorizationCustomer());
            return false;
        }
    }

    @Override
    public boolean deleteEmailAuthorizeCustomer(String email) {
        Customer customer = getAuthorizationCustomer();
        if(customer.getEmail().contains(email)){
            List<String> result = customer.getEmail();
            LOGGER.info("Remove email: "+email+" to customer: "+customer);
            result.remove(email);
            customer.setEmail(result);
            LOGGER.info("Save customer: "+ customer);
            customerRepository.save(customer);
            return true;
        } else {
            LOGGER.info("Not remove email. customer: "+ getAuthorizationCustomer());
            return false;
        }
    }

    @Override
    public boolean deletePhoneNumberAuthorizeCustomer(String phoneNumber) {
        Customer customer = getAuthorizationCustomer();
        if(customer.getEmail().contains(phoneNumber)){
            List<String> result = customer.getPhoneNumber();
            LOGGER.info("Remove phone number: "+phoneNumber+" to customer: "+customer);
            result.remove(phoneNumber);
            customer.setPhoneNumber(result);
            LOGGER.info("Save customer: "+ customer);
            customerRepository.save(customer);
            return true;
        } else {
            LOGGER.info("Not remove phone number. customer id: "+ getAuthorizationCustomer());
            return false;
        }
    }


    private Customer getAuthorizationCustomer(){ //отредачить после настройки секурити
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Customer customer = customerRepository.findCustomerByLastName(authentication.getName()).orElseThrow(() -> new UsernameNotFoundException(""));
        return customer;
    }
}
