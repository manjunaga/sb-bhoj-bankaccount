package com.wipro.BankAccount.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.wipro.BankAccount.Model.Account;
import com.wipro.BankAccount.Model.Customer;
import com.wipro.BankAccount.Repository.AccountRepository;
import com.wipro.BankAccount.Repository.CustomerRepository;

@RunWith(MockitoJUnitRunner.class)
class BankAccountServiceImplTest {
	
	@InjectMocks
	BankAccountServiceImpl bankAccountService;
	
	@Mock
	AccountRepository accountRepo;
	
	@Mock
	CustomerRepository customerRepo;

	@Test
	void testAddAccount() throws Exception {
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
		bankAccountService.addAccount(acct);
		verify(accountRepo, times(1)).save(acct);
	}

	@Test
	void testFindAccount() throws Exception {
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
		
		when(accountRepo.findById((long) 1).get()).thenReturn(acct);
		Account Actualacct = bankAccountService.findAccount(1);
		assertEquals(1, Actualacct.getAccountNumber());
		assertEquals("Checking", Actualacct.getAccountType());
		assertEquals(1000.0, Actualacct.getAccountBalance());
		assertEquals(customer1, Actualacct.getCustomer());
	}

	@Test
	void testGetAllAccounts() throws Exception {
		List<Account> accountList = new ArrayList<Account>();
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct1 = new Account();
		acct1.setAccountNumber(1);
		acct1.setAccountType("Checking");
		acct1.setAccountBalance(1000.0);
		acct1.setCustomer(customer1);
		
		Customer customer2 = new Customer();
		customer2.setAccountNumber(2);
		customer2.setCustomerName("Varun"); 
		customer2.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1973-12-12"));
		customer2.setCustomerAddress("GTRoad");
		
		Account  acct2 = new Account();
		acct2.setAccountNumber(2);
		acct2.setAccountType("Checking");
		acct2.setAccountBalance(2000.0);
		acct2.setCustomer(customer2);
		
		Customer customer3 = new Customer();
		customer3.setAccountNumber(3);
		customer3.setCustomerName("Mehta"); 
		customer3.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-22"));
		customer3.setCustomerAddress("ValleyRoad");
		
		Account  acct3 = new Account();
		acct3.setAccountNumber(3);
		acct3.setAccountType("Checking");
		acct3.setAccountBalance(300.0);
		acct3.setCustomer(customer3);
		
		accountList.add(acct1);
		accountList.add(acct2);
		accountList.add(acct3);
		
		when(accountRepo.findAll()).thenReturn(accountList);
		
		List<Account> actualAccountList = bankAccountService.getAllAccounts();
		assertEquals(3, actualAccountList.size());
		verify(accountRepo, times(1)).findAll();
	}

	@Test
	void testGetAllCustomers() throws Exception {
		List<Account> accountList = new ArrayList<Account>();
		List<Customer> customerList = new ArrayList<Customer>();
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct1 = new Account();
		acct1.setAccountNumber(1);
		acct1.setAccountType("Checking");
		acct1.setAccountBalance(1000.0);
		acct1.setCustomer(customer1);
		
		Customer customer2 = new Customer();
		customer2.setAccountNumber(2);
		customer2.setCustomerName("Varun"); 
		customer2.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1973-12-12"));
		customer2.setCustomerAddress("GTRoad");
		
		Account  acct2 = new Account();
		acct2.setAccountNumber(2);
		acct2.setAccountType("Checking");
		acct2.setAccountBalance(2000.0);
		acct2.setCustomer(customer2);
		
		Customer customer3 = new Customer();
		customer3.setAccountNumber(3);
		customer3.setCustomerName("Mehta"); 
		customer3.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("2003-09-22"));
		customer3.setCustomerAddress("ValleyRoad");
		
		Account  acct3 = new Account();
		acct3.setAccountNumber(3);
		acct3.setAccountType("Checking");
		acct3.setAccountBalance(300.0);
		acct3.setCustomer(customer3);
		
		accountList.add(acct1);
		accountList.add(acct2);
		accountList.add(acct3);
		
		when(customerRepo.findAll()).thenReturn(customerList);
		
		List<Customer> actualCustomerList = bankAccountService.getAllCustomers();
		assertEquals(3, actualCustomerList.size());
		verify(customerRepo, times(1)).findAll();
	}

	@Test
	void testUpdateAccount() throws Exception {
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
		bankAccountService.addAccount(acct);
		verify(accountRepo, times(1)).save(acct);
	}

	@Test
	void testDeleteAccount() {
		bankAccountService.deleteAccount(1);
		verify(accountRepo, times(1)).deleteById((long) 1);
	}

	@Test
	void testTransferFunds() throws Exception {
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1993-10-1"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct1 = new Account();
		acct1.setAccountNumber(1);
		acct1.setAccountType("Checking");
		acct1.setAccountBalance(1000.0);
		acct1.setCustomer(customer1);
		
		Customer customer2 = new Customer();
		customer2.setAccountNumber(2);
		customer2.setCustomerName("Varun"); 
		customer2.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1973-12-12"));
		customer2.setCustomerAddress("GTRoad");
		
		Account  acct2 = new Account();
		acct2.setAccountNumber(2);
		acct2.setAccountType("Checking");
		acct2.setAccountBalance(2000.0);
		acct2.setCustomer(customer2);
		
		when(accountRepo.findById((long) 1).get()).thenReturn(acct1);
		Account Actualacct1 = bankAccountService.findAccount(1);
		assertEquals(1, Actualacct1.getAccountNumber());
		assertEquals("Checking", Actualacct1.getAccountType());
		assertEquals(1000.0, Actualacct1.getAccountBalance());
		assertEquals(customer1, Actualacct1.getCustomer());
		
		when(accountRepo.findById((long) 2).get()).thenReturn(acct2);
		Account Actualacct2 = bankAccountService.findAccount(2);
		assertEquals(2, Actualacct2.getAccountNumber());
		assertEquals("Checking", Actualacct2.getAccountType());
		assertEquals(1000.0, Actualacct2.getAccountBalance());
		assertEquals(customer2, Actualacct2.getCustomer());
		
		String returnStatus = bankAccountService.transferFunds(Actualacct1.getAccountNumber(), Actualacct2.getAccountNumber(), 500.0);
		assertEquals(returnStatus, "SUCCESS");
	}

	@Test
	void testGetBalanceOf() throws Exception {
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
		
		when(accountRepo.findById((long) 1).get()).thenReturn(acct);
		Account Actualacct = bankAccountService.findAccount(1);
		assertEquals(1, Actualacct.getAccountNumber());
		assertEquals("Checking", Actualacct.getAccountType());
		assertEquals(1000.0, Actualacct.getAccountBalance());
		assertEquals(customer1, Actualacct.getCustomer());
	}
}
