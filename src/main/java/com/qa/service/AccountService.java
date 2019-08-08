package com.qa.service;

import java.util.List;

import com.qa.entity.Account;

public interface AccountService {
	
	public List<Account> getAllAccounts();
	
	public String deleteAccount(Long id);
	
	public String createAccount(Account account);
	
	public String updateAccount(Long id, Account account);
	

}
