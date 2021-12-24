package com.tushar.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.tushar.model.T_Bank_Accounts;

@Component
public interface iBankAccountRepo extends JpaRepository<T_Bank_Accounts, Integer> {

}
