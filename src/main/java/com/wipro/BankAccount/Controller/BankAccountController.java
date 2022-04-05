package com.wipro.BankAccount.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.BankAccount.Exception.BankAccountException;
import com.wipro.BankAccount.Model.Account;
import com.wipro.BankAccount.Model.Customer;
//import com.wipro.BankAccount.Model.Customer;
import com.wipro.BankAccount.Service.AccountObjectWrapper;
import com.wipro.BankAccount.Service.BankAccountServiceImpl;

@RestController
public class BankAccountController {
	
	@Autowired
	BankAccountServiceImpl bankAccountService;
		
	@Value("${welcome-message}")
	public String sWelcomeMessage;
	
	@GetMapping("/")
	public String welcome() {
		return sWelcomeMessage;
	}
	
	@PostMapping("/addAccount")
	public ResponseEntity<Account> addAccount(@RequestBody Account account) throws BankAccountException {
		ResponseEntity<Account> responseEntity = null;
		
		Account accountFound = bankAccountService.findAccount(account.getAccountNumber());
		if (accountFound != null && accountFound.getAccountType() == null) {
			Account newAccount = bankAccountService.addAccount(account);
			responseEntity = new ResponseEntity<Account>(newAccount, HttpStatus.CREATED);
		} else {
			throw new BankAccountException("Bank Account Number " + account.getAccountNumber() + " already Exists");
		}
		
		return responseEntity;
	}
	
	@GetMapping("/findAccount/{accountNumber}")
	public ResponseEntity<Account> findAccount(@PathVariable long accountNumber) throws BankAccountException {
		Account accountFound = bankAccountService.findAccount(accountNumber);
		if (accountFound != null && accountFound.getAccountType() == null) {
			throw new BankAccountException("Bank Account Number " + accountNumber + " is not found");
		}
		
		return new ResponseEntity<Account>(accountFound, HttpStatus.FOUND);
	}
	
	@GetMapping("/getAllAccounts")
	public ResponseEntity<List<Account>> getAllAccounts() throws BankAccountException {
		List<Account> accountList = bankAccountService.getAllAccounts();
		if (accountList.size() == 0) 
			throw new BankAccountException("Bank Accounts List is Empty");
		
		return new ResponseEntity<List<Account>>(accountList, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCustomers")
	public ResponseEntity<List<Customer>> getAllCustomers() throws BankAccountException{
		List<Customer> customerList = bankAccountService.getAllCustomers();
		if (customerList.size() == 0) 
			throw new BankAccountException("Bank Accounts Customer List is Empty");
		
		return new ResponseEntity<List<Customer>>(customerList, HttpStatus.OK);
	}
	
	@PutMapping("/updateAccount")
	public ResponseEntity<Account> updateAccount(@RequestBody Account account) throws BankAccountException {
		Account accountFound = bankAccountService.findAccount(account.getAccountNumber());
		if (accountFound != null && accountFound.getAccountType() == null) {
			throw new BankAccountException("Bank Account Number " + account.getAccountNumber() + " is not found. Nothing to Update.");
		} else {
			Account accountToUpdate = bankAccountService.updateAccount(account);
			return new ResponseEntity<Account>(accountToUpdate, HttpStatus.OK);
		}
	}
	
	@DeleteMapping("deleteAccount/{accountNumber}")
	public ResponseEntity<Account> deleteAccount(@PathVariable long accountNumber) throws BankAccountException {
		Account accountFound = bankAccountService.findAccount(accountNumber);
		if (accountFound != null && accountFound.getAccountType() == null) {
			throw new BankAccountException("Bank Account Number " + accountNumber + " is not found. So Nothing to Delete");
		} else {
			bankAccountService.deleteAccount(accountNumber);
			return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
		}
	}
	
	@PostMapping("transferFunds")
	public ResponseEntity<String>  transferFunds(@RequestBody AccountObjectWrapper accountWrapper) throws BankAccountException {
		String returnStatus = new String();
		Double zeroValue = 0.0;
		long accountNumberFrom = accountWrapper.getAccountNumberFrom();
		long accountNumberTo = accountWrapper.getAccountNumberTo();
		double amountToTransfer = accountWrapper.getAmountToTransfer();
		
		Account accountNumberFromFound = bankAccountService.findAccount(accountNumberFrom);
		if (accountNumberFromFound != null && accountNumberFromFound.getAccountType() == null) {
			returnStatus = "ID_MISMATCH";
		}
		
		Account accountNumberToFound = bankAccountService.findAccount(accountNumberTo);
		if (accountNumberToFound != null && accountNumberToFound.getAccountType() == null) {
			returnStatus = "ID_MISMATCH";
		}
		
		if (!(returnStatus.equals("ID_MISMATCH"))) {
			if (Double.compare(amountToTransfer, zeroValue) == 0) { 
				throw new BankAccountException("Amount to Transfer is Zero. So Nothing to Transfer");
	        } 
			else if (Double.compare(amountToTransfer, zeroValue) < 0) { 
				throw new BankAccountException("Amount to Transfer is Less than Zero. Not Possible. So Nothing to Transfer");
	        } 
			else if (Double.compare(amountToTransfer, zeroValue) > 0) { 
				returnStatus= bankAccountService.transferFunds(accountNumberFrom, accountNumberTo, amountToTransfer);
	        } 
		}
		
		return new ResponseEntity<String>(returnStatus, HttpStatus.OK); 	
	} 
	
	@GetMapping("getAccountBalance/{accountNumber}")
	public ResponseEntity<Account> getBalanceOf(@PathVariable long accountNumber) throws BankAccountException {
		Account accountFound = bankAccountService.findAccount(accountNumber);
		if (accountFound != null && accountFound.getAccountType() == null) {
			throw new BankAccountException("Bank Account Number " + accountNumber + " is not found");
		} else {
			bankAccountService.getBalanceOf(accountNumber);
			return new ResponseEntity<Account>(accountFound, HttpStatus.FOUND);
		}
	}
}
