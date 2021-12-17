package com.tushar.service;

import com.tushar.model.T_Bank_Accounts;

public interface iBankAccountService {
	
	public T_Bank_Accounts createOrUpdateAccount(T_Bank_Accounts account);
	public String deleteAccount(Integer accNo);
	public String  addMoney(Integer accNo, double amt);
	public String  minusMoney(Integer accNo, double amt);
	public String  AddDestToMinusSrcMoney(Integer accNoSrc,  Integer accNoDest, double amt);

}
