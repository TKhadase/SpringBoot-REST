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

import com.tushar.model.Accounts_res;
import com.tushar.model.T_Bank_Accounts;
import com.tushar.service.iBankAccountService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/Accounts")
@Api("All about Bank Accounts Ops.")
public class BankAccountController {

	@Autowired
	private iBankAccountService serviceBankAccount;

	@PostMapping("/newAccount")
	@ApiOperation("For Account Opening")
	public ResponseEntity<Accounts_res> createNewAccount(@RequestBody T_Bank_Accounts account) throws Exception {
		System.out.println("BankAccountController.createNewAccount():: " + account);
		return new ResponseEntity(serviceBankAccount.createOrUpdateAccount(account), HttpStatus.CREATED);
	}// createNewAccount

	
	@GetMapping("/find/{accId}")
	public ResponseEntity<Accounts_res> getAccountDetails(@PathVariable("accId") Integer accNo) throws Exception {
		System.out.println("BankAccountController.getAccountDetails(): " + accNo);
		return new ResponseEntity(serviceBankAccount.getAccountDetails(accNo), HttpStatus.OK);
	}// getAccountDetails

	
	@PatchMapping("/transferBalance/{srcAccNo}/{destAccNo}/{amt}")
	public ResponseEntity<Accounts_res> balanceTransferAcc2Acc(@PathVariable Integer srcAccNo,
			@PathVariable Integer destAccNo, @PathVariable Integer amt) throws Exception {
		System.out.println("BankAccountController.balanceTransfer()::srcAccNo " + srcAccNo + ", destAccNo " + destAccNo
				+ ", amt " + amt);
		return new ResponseEntity(serviceBankAccount.AddDestToMinusSrcMoney(srcAccNo, destAccNo, amt), HttpStatus.OK);
	}// balanceTransfer

	
	@PutMapping("/close/{accNo}")
	public ResponseEntity<Accounts_res> closedAccount(@PathVariable Integer accNo) throws Exception {
		System.out.println("BankAccountController.balanceTransfer(): " + accNo);
		return new ResponseEntity(serviceBankAccount.deleteAccount(accNo), HttpStatus.OK);
	}// closedAccount

	
}// class