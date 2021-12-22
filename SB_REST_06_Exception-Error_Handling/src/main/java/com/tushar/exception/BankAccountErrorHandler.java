package com.tushar.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tushar.model.Error_res;

@RestControllerAdvice
public class BankAccountErrorHandler {

	@ExceptionHandler(BankAccountException.class)
	public ResponseEntity<Error_res> handleBankAccountError(BankAccountException exp){
		Error_res res = new Error_res("Failed", ""+exp.getMessage(), LocalDateTime.now());
		System.out.println("BankAccountErrorHandler.handleBankAccountError():"+res);
		return new ResponseEntity<Error_res>(res, HttpStatus.OK);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Error_res> handleAllError(Exception exp){
		Error_res res = new Error_res("Failed", ""+exp.getMessage(), LocalDateTime.now());
		System.out.println("BankAccountErrorHandler.handleAllError():"+res);
		return new ResponseEntity<Error_res>(res, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
