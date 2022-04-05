package com.wipro.BankAccount.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.BankAccount.Model.Account;
import com.wipro.BankAccount.Model.Customer;
import com.wipro.BankAccount.Repository.AccountRepository;
import com.wipro.BankAccount.Repository.CustomerRepository;

@Service
public class BankAccountServiceImpl {
	@Autowired
	AccountRepository accountRepo;
	@Autowired
	CustomerRepository customerRepo;
	
	public Account addAccount(Account account) {
		Account accountAdded = accountRepo.save(account);
		return accountAdded;
	}

	public Account findAccount(long accountNumber) {
		Account foundAccount = new Account();
		Optional<Account> bFound = accountRepo.findById(accountNumber);
		if (bFound.isPresent()) {
			foundAccount = accountRepo.findById(accountNumber).get();
		}
		return foundAccount;
	}

	public List<Account> getAllAccounts() {
		List<Account> accountList = new ArrayList<Account>();
		accountRepo.findAll().forEach(eachAccount -> accountList.add(eachAccount));
		return accountList;
	}

	public List<Customer> getAllCustomers() {
		List<Customer> customerList = new ArrayList<Customer>();
		customerRepo.findAll().forEach(eachCustomer -> customerList.add(eachCustomer));
		return customerList;
	}

	public Account updateAccount(Account account) {
		Account accountToUpdate = accountRepo.save(account);
		return accountToUpdate;
	}

	public void deleteAccount(long accountNumber) {
		accountRepo.deleteById(accountNumber);
	}

	public String transferFunds(long accountNumberFrom, long accountNumberTo, double amountToTransfer) {
		String transferFundStatus = new String();
		Account fromAccount = accountRepo.findById(accountNumberFrom).get();
		Account toAccount = accountRepo.findById(accountNumberTo).get();
		if (fromAccount.getAccountBalance() < amountToTransfer) {
			transferFundStatus = "INSUFFICIENT_FUNDS";
			return transferFundStatus;
		}
		else {
			double toAccountNewBalance = toAccount.getAccountBalance() + amountToTransfer;
			double fromAccountNewBalance = fromAccount.getAccountBalance() - amountToTransfer;
			fromAccount.setAccountBalance(fromAccountNewBalance);
			toAccount.setAccountBalance(toAccountNewBalance);
			accountRepo.save(fromAccount);
			accountRepo.save(toAccount);
			transferFundStatus = "SUCCESS";
		}
		return transferFundStatus;
	}

	public Account getBalanceOf(long accountNumber) {
		Account account = accountRepo.findById(accountNumber).get();
		return account;
	}

}
