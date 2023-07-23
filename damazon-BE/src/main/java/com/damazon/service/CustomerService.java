package com.damazon.service;

import com.damazon.dto.request.CustomerRequest;
import com.damazon.dto.response.CustomerResponse;
import com.damazon.exception.NotFoundException;
import com.damazon.mapper.CustomerMapper;
import com.damazon.model.Customer;
import com.damazon.repository.CustomCustomerRepository;
import com.damazon.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;
    private final CustomCustomerRepository customCustomerRepository;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper, CustomCustomerRepository customCustomerRepository) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
        this.customCustomerRepository = customCustomerRepository;
    }

    @Transactional
    public List<CustomerResponse> getAllCustomers() {
        return customerRepository.findAllByDeletedAtIsNull().stream()
                .map(customerMapper::customerToCustomerResponse)
                .collect(Collectors.toList());
    }

    @Transactional
    public CustomerResponse getCustomerById(Integer id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));
        return customerMapper.customerToCustomerResponse(customer);
    }

    @Transactional
    public CustomerResponse createCustomer(CustomerRequest customerRequest) {
        Customer customer = customerMapper.customerRequestToCustomer(customerRequest);
        customer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponse(customer);
    }

    @Transactional
    public CustomerResponse updateCustomer(Integer id, CustomerRequest customerRequest) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Customer not found with id: " + id));

        customer.setName(customerRequest.name());
        customer.setEmail(customerRequest.email());
        customer.setAddress(customerRequest.address());

        customer = customerRepository.save(customer);
        return customerMapper.customerToCustomerResponse(customer);
    }

    @Transactional
    public CustomerResponse deleteCustomer(Integer id) {
        if (!customerRepository.existsById(id)) {
            throw new NotFoundException("Customer not found with id: " + id);
        }
        Customer customer = customCustomerRepository.softDelete(id);
        return customerMapper.customerToCustomerResponse(customer);
    }
}
