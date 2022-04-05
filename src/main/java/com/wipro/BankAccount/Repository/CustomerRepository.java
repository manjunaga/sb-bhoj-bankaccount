package com.wipro.BankAccount.Repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.BankAccount.Model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
