package com.wipro.BankAccount.Exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class BankAccountControllerAdvice {
	
	@ExceptionHandler(BankAccountException.class)
	@ResponseStatus(value=HttpStatus.NOT_FOUND)
	@ResponseBody BankAccountResponse handleResourceNotFoundException(BankAccountException bankException, HttpServletRequest httpServerReq) {
		BankAccountResponse bankAccountResponse = new BankAccountResponse();
		bankAccountResponse.setErrorMessage(bankException.getMessage());
		bankAccountResponse.setRequestURI(httpServerReq.getRequestURI());
		return bankAccountResponse;
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
	@ResponseBody BankAccountResponse handleBankAccountException(BankAccountException bankException, HttpServletRequest httpServerReq) {
		BankAccountResponse bankAccountResponse = new BankAccountResponse();
		bankAccountResponse.setErrorMessage(bankException.getMessage());
		bankAccountResponse.setRequestURI(httpServerReq.getRequestURI());
		return bankAccountResponse;
	}

}
