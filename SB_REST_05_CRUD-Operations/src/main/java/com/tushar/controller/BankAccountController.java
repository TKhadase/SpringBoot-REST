package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tushar.model.T_Bank_Accounts;
import com.tushar.service.iBankAccountService;

@RestController
@RequestMapping("/Accounts")
public class BankAccountController {
	
	@Autowired
	private iBankAccountService serviceBankAccount;
	
	@PostMapping("/newAccount")
	public ResponseEntity<String> createNewAccount(@RequestBody T_Bank_Accounts account){
		System.out.println("BankAccountController.createNewAccount():: "+account);
		try {
			return new ResponseEntity("Account Created with AccNo: "+serviceBankAccount.createOrUpdateAccount(account).getAccno(), HttpStatus.CREATED);
		}	
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Unable to Process request", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
