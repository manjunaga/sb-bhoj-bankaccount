package com.wipro.BankAccount.Controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wipro.BankAccount.Model.Account;
import com.wipro.BankAccount.Model.Customer;
import com.wipro.BankAccount.Service.BankAccountServiceImpl;
@RunWith(SpringRunner.class)
@WebMvcTest
class BankAccountControllerTest {
	
	@Autowired
	MockMvc mockMvc;
	
	@MockBean
	private BankAccountServiceImpl bankService;

	@Test
	void testWelcome() throws Exception {
		RequestBuilder requestBuild = MockMvcRequestBuilders.get("/").accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuild).andReturn();
		System.out.println(mvcResult.getResponse().getContentAsString());
		assertEquals("Welcome to TryBank Development", mvcResult.getResponse().getContentAsString());
	}

	@Test
	void testAddAccount() throws Exception {
		Customer customer1 = new Customer();
		customer1.setAccountNumber(11);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct = new Account();
		acct.setAccountNumber(11);
		acct.setAccountType("Checking");
		acct.setAccountBalance(1000.0);
		acct.setCustomer(customer1);
		
		ObjectMapper mapper = new ObjectMapper();
	      //Converting the Object to JSONString
	      String jsonString = mapper.writeValueAsString(acct);
		
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post("/addAccount")
		         .contentType(org.springframework.http.MediaType.APPLICATION_JSON_VALUE)
		         .content(jsonString)).andReturn();
		int status = mvcResult.getResponse().getStatus();
	    assertEquals(404, status);
	}

	@Test
	void testFindAccount() throws Exception {
		Customer customer1 = new Customer();
		customer1.setAccountNumber(1);
		customer1.setCustomerName("Raj"); 
		customer1.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse("1990-01-01"));
		customer1.setCustomerAddress("MGRoad");
		
		Account  acct = new Account();
		acct.setAccountNumber(1);
		acct.setAccountType("Checking");
		acct.setAccountBalance(1000.0);
		acct.setCustomer(customer1);
		
		when(bankService.findAccount(1)).thenReturn(acct);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/findAccount/{accountNumber}",1).accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult mvcResult = mockMvc.perform(requestBuilder).andExpect(status().isOk())
							  .andExpect((ResultMatcher) content().json("{\"accountNumber\":1,\"accountType\":\"Checking\",\"accountBalance\":1000.0,\"customer\":{\"accountNumber\":1,\"customerName\":\"Raj\",\"dateOfBirth\":\"1990-01-01\",\"customerAddress\":\"MGRoad\"}}"))
							  .andReturn();
		System.out.println("mvnREsult"  + mvcResult.getResponse().getContentAsString());
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
		accountList.add(acct1);
		
		when(bankService.getAllAccounts()).thenReturn(accountList);
		
		RequestBuilder request = MockMvcRequestBuilders.get("/getAllAccounts").accept(org.springframework.http.MediaType.APPLICATION_JSON);
		MvcResult mvcResult =  (MvcResult) mockMvc.perform(request).andExpect(status().isOk());
								//.andExpect(MockMvcResultMatchers.jsonPath("$").exists())
								//.andExpect(MockMvcResultMatchers.jsonPath("$[*].accountNumber").isNotEmpty());
		System.out.println("mvnREsult"  + mvcResult.getResponse().getContentAsString());
	}
}
