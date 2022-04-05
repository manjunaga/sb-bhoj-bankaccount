package com.wipro.BankAccount.Repository;

import org.springframework.data.repository.CrudRepository;

import com.wipro.BankAccount.Model.Account;

public interface AccountRepository extends CrudRepository<Account, Long>{
	
}
