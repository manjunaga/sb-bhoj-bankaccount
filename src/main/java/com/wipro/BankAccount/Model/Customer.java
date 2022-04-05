package com.wipro.BankAccount.Model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

//@Entity
//@Table
//class CustomerAddress implements Serializable {
//	@Id
//	@Column
//	String sNameOfStreet;
//	@Column
//	String sCity;
//	@Column
//	String sState;
//	@Column
//	String sCountry;
//	
//	public String getsNameOfStreet() {
//		return sNameOfStreet;
//	}
//	public void setsNameOfStreet(String sNameOfStreet) {
//		this.sNameOfStreet = sNameOfStreet;
//	}
//	public String getsCity() {
//		return sCity;
//	}
//	public void setsCity(String sCity) {
//		this.sCity = sCity;
//	}
//	public String getsState() {
//		return sState;
//	}
//	public void setsState(String sState) {
//		this.sState = sState;
//	}
//	public String getsCountry() {
//		return sCountry;
//	}
//	public void setsCountry(String sCountry) {
//		this.sCountry = sCountry;
//	}
//}

@Entity
@Table
public class Customer implements  Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column
	private long accountNumber;
	@Column
	private String customerName;
	@Column
	private Date dateOfBirth;
	@Column
	String customerAddress;
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "accountNumber", nullable = false, unique = true)
	private Account account;
	
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
//	public CustomerAddress getCustomerAddress() {
//		return customerAddress;
//	}
//	public void setCustomerAddress(CustomerAddress customerAddress) {
//		CustomerAddress tempAddress = new CustomerAddress();
//		tempAddress.sNameOfStreet = customerAddress.getsNameOfStreet();
//		tempAddress.sCity = customerAddress.getsCity();
//		tempAddress.sState = customerAddress.getsState();
//		tempAddress.sCountry = customerAddress.getsCountry();
//		this.customerAddress = tempAddress;
//	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
}
