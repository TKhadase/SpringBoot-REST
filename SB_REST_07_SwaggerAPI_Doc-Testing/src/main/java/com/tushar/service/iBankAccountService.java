package com.tushar.service;

import com.tushar.exception.BankAccountException;
import com.tushar.model.Accounts_res;
import com.tushar.model.T_Bank_Accounts;

public interface iBankAccountService {
	
	public Accounts_res  getAllAccountDetails() throws BankAccountException;
	public Accounts_res  createOrUpdateAccount(T_Bank_Accounts account, String opsType) throws BankAccountException ;
	public Accounts_res deleteAccount(Integer accNo) throws BankAccountException;
	public Accounts_res  AddDestToMinusSrcMoney(Integer accNoSrc,  Integer accNoDest, double amt) throws BankAccountException;
	public Accounts_res  getAccountDetails(Integer accNo) throws BankAccountException;

}
