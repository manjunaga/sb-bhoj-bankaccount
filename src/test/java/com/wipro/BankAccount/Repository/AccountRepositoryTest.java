package com.wipro.BankAccount.Repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.wipro.BankAccount.Model.Account;
import com.wipro.BankAccount.Model.Customer;
import com.wipro.BankAccount.Service.BankAccountServiceImpl;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {
	
	@InjectMocks
	BankAccountServiceImpl bankAccountService;
	
	@Autowired
	AccountRepository accountRepo;

	@Test
	void testSave() {
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(100.0);
		accountRepo.save(acct);
		Assert.assertNotNull(acct.getAccountNumber());
		
		List<Account> accountList = new ArrayList<Account>();
		accountRepo.findAll().forEach(eachAccount -> accountList.add(eachAccount));
		assertEquals(1, accountList.size());
	}

	@Test
	void testFindById() throws Exception {		
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(1000.0);
		acct.setCustomer(customer1);
		accountRepo.save(acct);
		
		Account bFound = accountRepo.findById((long)1).get();
		assertNotNull(bFound);
	}

	@Test
	void testFindAll() {
		List<Account> accountList = new ArrayList<Account>();
		assertEquals(0, accountList.size());
		
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(100.0);
		accountRepo.save(acct);
		
		List<Account> actualAccountList = (List<Account>) accountRepo.findAll();
		assertEquals(1, actualAccountList.size());
	}

	@Test
	void testDeleteById() throws Exception {
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(1000.0);
		acct.setCustomer(customer1);
		accountRepo.save(acct);
		
		accountRepo.deleteById((long) 1);
	}

	@Test
	void testDelete() throws Exception {
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(1000.0);
		acct.setCustomer(customer1);
		accountRepo.save(acct);
		
		accountRepo.deleteById((long) 1);
	}

}
