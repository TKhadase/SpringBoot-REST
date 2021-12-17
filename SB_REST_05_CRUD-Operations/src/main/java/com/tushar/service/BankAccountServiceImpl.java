package com.tushar.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.model.T_Bank_Accounts;
import com.tushar.repo.iBankAccountRepo;

@Service("bankAccountServiceImpl")
public class BankAccountServiceImpl implements iBankAccountService {

	@Autowired
	private iBankAccountRepo repoBankAccount;
	
	@Override
	public T_Bank_Accounts createOrUpdateAccount(T_Bank_Accounts account) {
		System.out.println("BankAccountServiceImpl.createOrUpdateAccount():: "+account);
		return repoBankAccount.save(account);
	}

	@Override
	public String deleteAccount(Integer accNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String addMoney(Integer accNo, double amt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String minusMoney(Integer accNo, double amt) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String AddDestToMinusSrcMoney(Integer accNoSrc, Integer accNoDest, double amt) {
		// TODO Auto-generated method stub
		return null;
	}

}
