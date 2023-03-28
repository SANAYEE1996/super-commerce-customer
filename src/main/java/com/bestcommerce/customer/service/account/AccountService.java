package com.bestcommerce.customer.service.account;

import com.bestcommerce.customer.domain.Customer;
import com.bestcommerce.customer.dto.CustomerDto;
import com.bestcommerce.customer.repository.domain.CustomerRepository;
import com.bestcommerce.customer.repository.support.CustomerRepositorySupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    private final CustomerRepository customerRepository;

    private final CustomerRepositorySupport customerRepositorySupport;

    public AccountService(CustomerRepository customerRepository, CustomerRepositorySupport customerRepositorySupport){
        this.customerRepository = customerRepository;
        this.customerRepositorySupport = customerRepositorySupport;
    }

    public Boolean isUsableEmail(String cu_email){
        return !customerRepository.existsByCuEmail(cu_email);
    }

    public void save(Customer customer){
        if(!customerRepository.existsByCuEmail(customer.getCuEmail())){
            customerRepository.save(customer);
            return;
        }
        log.error("Duplicated email");
        throw new RuntimeException("Duplicated Email !");
    }

    public Customer getOneCustomerInfo(long id){
        return customerRepository.findById(id).orElseGet(Customer::new);
    }

    public Customer getOneCustomerInfo(String cu_email){
        return customerRepository.findByCuEmail(cu_email);
    }

    public void deleteOneCustomer(String cu_email){
        customerRepository.deleteCustomerByCuEmail(cu_email);
    }

    public void updateCustomer(CustomerDto customerDto){
        if(customerRepository.existsById(customerDto.getCustomerId())){
            customerRepositorySupport.updateCustomer(customerDto);
            return;
        }
        log.error("Not exists Customer Id");
        throw new RuntimeException("Not exists Customer Id !");
    }
}
