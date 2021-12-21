package com.tushar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	}//createNewAccount
	
	@GetMapping("/find/{accId}")
	public ResponseEntity<String> getAccountDetails(@PathVariable("accId") Integer accNo){
		System.out.println("BankAccountController.getAccountDetails(): "+accNo);
		try {
			return new ResponseEntity(serviceBankAccount.getAccountDetails(accNo), HttpStatus.OK);
		}	
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Unable to Process request", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}//getAccountDetails
	
	@PatchMapping("/transferBalance/{srcAccNo}/{destAccNo}/{amt}")
	public ResponseEntity<String> balanceTransfer(@PathVariable Integer srcAccNo, 
																								@PathVariable Integer destAccNo, 
																								@PathVariable Integer amt){
		System.out.println("BankAccountController.balanceTransfer()::srcAccNo "+srcAccNo+", destAccNo "+destAccNo+", amt "+amt);
		try {
			return new ResponseEntity(serviceBankAccount.AddDestToMinusSrcMoney(srcAccNo,destAccNo, amt ), HttpStatus.OK);
		}	
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity("Unable to Process request", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
	}//balanceTransfer
	
		@PutMapping("/close/{accNo}")
		public ResponseEntity<String> closedAccount(@PathVariable Integer accNo){
			System.out.println("BankAccountController.balanceTransfer(): "+accNo);
			try {
				return new ResponseEntity(serviceBankAccount.deleteAccount(accNo), HttpStatus.OK);
			}	
			catch(Exception e) {
				e.printStackTrace();
				return new ResponseEntity("Unable to Process request", HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}//closedAccount
		
	}//class