package com.tushar.service;

import java.util.List;
import java.util.Optional;

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
		Optional accountDetails = repoBankAccount.findById(accNo);
		if(accountDetails.isPresent()) {
			T_Bank_Accounts account=	(T_Bank_Accounts) accountDetails.get();
			
			if("N".equalsIgnoreCase(account.getIsActive()))
				return account.getAccno()+"-Account already Deleted ";
			
			account.setIsActive("N");
			repoBankAccount.save(account);
	    	return account.getAccno()+"-Account Deleted ";
	    	
		}else {
			return accNo+"-Account Not Available ";
		}
	}

	@Override
	public String AddDestToMinusSrcMoney(Integer accNoSrc, Integer accNoDest, double amt) {
		String msg=null;
		Optional accountDetailsSrc = repoBankAccount.findById(accNoSrc);
		Optional accountDetailsDest = repoBankAccount.findById(accNoDest);
		
		if(0 >=amt) {
			return "Please enter positive amount";
		}
		
		if(accountDetailsSrc.isPresent() && accountDetailsDest.isPresent()) {
			T_Bank_Accounts accountSrc=	(T_Bank_Accounts) accountDetailsSrc.get();
			T_Bank_Accounts accountDest=	(T_Bank_Accounts) accountDetailsDest.get();
			
			if("N".equalsIgnoreCase(accountSrc.getIsActive())) {
				return accountSrc.getAccno()+" -Account already Deleted, Cant Transfer money";}
			if("N".equalsIgnoreCase(accountDest.getIsActive())) {
				return accountDest.getAccno()+" -Account already Deleted, Cant Add money";}
			
			if((accountSrc.getBalance()- amt) >0) {
			accountDest.setBalance(accountDest.getBalance()+ amt);
			accountSrc.setBalance(accountSrc.getBalance()- amt);
			List<T_Bank_Accounts > accounts = List.of(accountDest, accountSrc);
			repoBankAccount.saveAll(accounts);
			return "Money transfer successful";
			}else {
				return "Source account: Insufficient Balnace to deduct";
			}//else (accountSrc.getBalance()- amt) >0
		}//account present
		else {
			return "Account Details not available, please correct both accounts";
		}
	}//AddDestToMinusSrcMoney
		
	@Override
	public String getAccountDetails(Integer accNo) {
		Optional<T_Bank_Accounts> accountOpt = repoBankAccount.findById(accNo);
		if(accountOpt.isPresent())
		return accountOpt.get().toString();
		else 
			return accNo+"-Account details not available";
	}

}
