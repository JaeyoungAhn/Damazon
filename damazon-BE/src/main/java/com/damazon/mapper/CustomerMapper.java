package com.damazon.mapper;

import com.damazon.dto.request.CustomerRequest;
import com.damazon.dto.response.CustomerResponse;
import com.damazon.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerMapper {
    Customer customerRequestToCustomer(CustomerRequest customerRequest);

    CustomerResponse customerToCustomerResponse(Customer customer);
}