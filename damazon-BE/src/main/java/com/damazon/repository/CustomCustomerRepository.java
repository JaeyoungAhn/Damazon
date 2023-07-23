package com.damazon.repository;

import com.damazon.model.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class CustomCustomerRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Customer softDelete(Integer id) {
        Customer customer = entityManager.find(Customer.class, id);
        if (customer != null) {
            customer.markAsDeleted();
        }
        return customer;
    }
}
