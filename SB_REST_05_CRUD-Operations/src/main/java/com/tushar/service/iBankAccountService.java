package com.tushar.service;

import com.tushar.model.T_Bank_Accounts;

public interface iBankAccountService {
	
	public T_Bank_Accounts createOrUpdateAccount(T_Bank_Accounts account);
	public String deleteAccount(Integer accNo);
	public String  AddDestToMinusSrcMoney(Integer accNoSrc,  Integer accNoDest, double amt);
	public String  getAccountDetails(Integer accNo);

}
