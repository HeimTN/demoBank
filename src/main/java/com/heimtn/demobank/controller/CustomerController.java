package com.heimtn.demobank.controller;

import com.heimtn.demobank.dto.CustomerDTO;
import com.heimtn.demobank.service.CustomerService;
import com.heimtn.demobank.service.impl.CustomerServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerServiceImpl customerService){
        this.customerService = customerService;
    }

    @PostMapping("/add_dto")
    public ResponseEntity<?> addCustomerFromDTO(@RequestBody CustomerDTO customerDTO){
        boolean result = customerService.addCustomer(customerDTO);
        if(result){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/add_data")
    public ResponseEntity<?> addCustomerFromData(@RequestParam @NotNull String email,
                                                 @RequestParam @NotNull String phoneNumber,
                                                 @RequestParam @NotNull String firstName,
                                                 @RequestParam @NotNull String lastName,
                                                 @RequestParam @NotNull String middleName,
                                                 @RequestParam @NotNull int balance){
        if(balance < 0){
            return ResponseEntity.badRequest().build();
        }
        boolean result = customerService.addCustomer(new CustomerDTO(email,phoneNumber,firstName,lastName,middleName,balance));
        if(result){
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/add_email")
    public ResponseEntity<?> addEmailToCustomer(@RequestParam @NotNull String email){
        boolean result = customerService.addEmailAuthorizeCustomer(email);
        if(result){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/add_phone_number")
    public ResponseEntity<?> addPhoneNumberToCustomer(@RequestParam @NotNull String phoneNumber){
        boolean result = customerService.addPhoneNumberAuthorizeCustomer(phoneNumber);
        if(result){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/delete_email")
    public ResponseEntity<?> deleteEmailToCustomer(@RequestParam @NotNull String email){
        boolean result = customerService.deleteEmailAuthorizeCustomer(email);
        if(result){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/update/delete_phone_number")
    public ResponseEntity<?> deletePhoneNumberToCustomer(@RequestParam @NotNull String phoneNumber){
        boolean result = customerService.deletePhoneNumberAuthorizeCustomer(phoneNumber);
        if(result){
            return ResponseEntity.ok().build();
        } else{
            return ResponseEntity.badRequest().build();
        }
    }
}
