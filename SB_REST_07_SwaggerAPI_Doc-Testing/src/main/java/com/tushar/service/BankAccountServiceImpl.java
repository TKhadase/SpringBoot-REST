package com.tushar.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tushar.exception.BankAccountException;
import com.tushar.model.Accounts_res;
import com.tushar.model.T_Bank_Accounts;
import com.tushar.repo.iBankAccountRepo;

@Service("bankAccountServiceImpl")
public class BankAccountServiceImpl implements iBankAccountService {

	@Autowired
	private iBankAccountRepo repoBankAccount;
	
	@Override
	public Accounts_res createOrUpdateAccount(T_Bank_Accounts account, String opsType) throws BankAccountException {
		System.out.println("BankAccountServiceImpl.createOrUpdateAccount():: "+account);
		Accounts_res accRes = null;
		String sucessMsg="Create" ;
		if("U".equalsIgnoreCase(opsType)) {
			sucessMsg="Update";
		}
		try {
			T_Bank_Accounts acc = repoBankAccount.save(account);
			accRes = new Accounts_res("success", "Account :"+acc.getAccno()+", "+sucessMsg+"d successfully.", LocalDateTime.now());
			accRes.setAccounts(List.of(acc));
			System.out.println(accRes);
		} catch (Exception e) {
			throw new BankAccountException( "Failed to "+sucessMsg+" account");
		}
		return accRes;
	}//createOrUpdateAccount

	@Override
	public Accounts_res deleteAccount(Integer accNo)  throws BankAccountException{
		Accounts_res accRes = null;
		Optional accountDetails = repoBankAccount.findById(accNo);
		if(accountDetails.isPresent()) {
			T_Bank_Accounts account=	(T_Bank_Accounts) accountDetails.get();
			
			if("N".equalsIgnoreCase(account.getIsActive())) {
				throw new BankAccountException( account.getAccno()+"-Account already Deleted ");
			}
			account.setIsActive("N");
			repoBankAccount.save(account);
			accRes = new Accounts_res("success",account.getAccno()+"-Account Deleted ", LocalDateTime.now());
			return accRes;
	    	
		}else {
			throw new BankAccountException(accNo+"-Account Not Available ");
		}
	}//deleteAccount

	@Override
	public Accounts_res AddDestToMinusSrcMoney(Integer accNoSrc, Integer accNoDest, double amt) throws BankAccountException {
		String msg=null;
		Accounts_res accRes = null;
		Optional accountDetailsSrc = repoBankAccount.findById(accNoSrc);
		Optional accountDetailsDest = repoBankAccount.findById(accNoDest);
		
		if(0 >=amt) {
			throw new BankAccountException("Please enter positive amount");
		}
		
		if(accountDetailsSrc.isPresent() && accountDetailsDest.isPresent()) {
			T_Bank_Accounts accountSrc=	(T_Bank_Accounts) accountDetailsSrc.get();
			T_Bank_Accounts accountDest=	(T_Bank_Accounts) accountDetailsDest.get();
			
			if("N".equalsIgnoreCase(accountSrc.getIsActive())) {
				throw new BankAccountException(accountSrc.getAccno()+" -Account already Deleted, Cant Transfer money");
				}
			else if("N".equalsIgnoreCase(accountDest.getIsActive())) {
				throw new BankAccountException( accountDest.getAccno()+" -Account already Deleted, Cant add money");
				}
			
			if((accountSrc.getBalance()- amt) >0) {
			accountDest.setBalance(accountDest.getBalance()+ amt);
			accountSrc.setBalance(accountSrc.getBalance()- amt);
			List<T_Bank_Accounts > accounts = List.of(accountDest, accountSrc);
			repoBankAccount.saveAll(accounts);
			accRes = new Accounts_res("success", "Money transfer successful", LocalDateTime.now(), List.of( accountDest));
			return accRes;
			}else {
				throw new BankAccountException( "Source account: Insufficient Balnace to deduct");
			}//else (accountSrc.getBalance()- amt) >0
		}//account present
		else {
			throw new BankAccountException( "Account Details not available, please correct both accounts");
		}
	}//AddDestToMinusSrcMoney
		
	@Override
	public Accounts_res getAccountDetails(Integer accNo)  throws BankAccountException{
		Accounts_res accRes=null;
		Optional<T_Bank_Accounts> accountOpt = repoBankAccount.findById(accNo);
		if(accountOpt.isPresent()) {
			accRes = new Accounts_res("success", "Account Found", LocalDateTime.now(), List.of( accountOpt.get()));
			return accRes ;
		}
		else 
		throw new BankAccountException( accNo+" :Account not Found");
	}//getAccountDetails

	@Override
	public Accounts_res getAllAccountDetails() throws BankAccountException {
		Accounts_res accRes=null;
		List<T_Bank_Accounts> accounts =repoBankAccount.findAll();
		accRes = new Accounts_res("success", "Accounts Found", LocalDateTime.now(),accounts );
		return accRes ;
	}

}//class
