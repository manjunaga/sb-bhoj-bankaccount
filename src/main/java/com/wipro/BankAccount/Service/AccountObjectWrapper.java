package com.wipro.BankAccount.Service;

public class AccountObjectWrapper {
	private long accountNumberFrom;
	private long accountNumberTo;
	private double amountToTransfer;
	
	public long getAccountNumberFrom() {
		return accountNumberFrom;
	}
	
	public void setAccountNumberFrom(long accountNumberFrom) {
		this.accountNumberFrom = accountNumberFrom;
	}
	
	public long getAccountNumberTo() {
		return accountNumberTo;
	}
	
	public void setAccountNumberTo(long accountNumberTo) {
		this.accountNumberTo = accountNumberTo;
	}
	
	public double getAmountToTransfer() {
		return amountToTransfer;
	}
	
	public void setAmountToTransfer(double amountToTransfer) {
		this.amountToTransfer = amountToTransfer;
	}
}