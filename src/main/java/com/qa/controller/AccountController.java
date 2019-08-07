package com.qa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.qa.entity.Account;
import com.qa.service.AccountService;

@RestController
@RequestMapping("/account")
public class AccountController {
	
	private AccountService accountService;
	private RestTemplate restTemplate;

	@Autowired
	public AccountController(AccountService accountService, RestTemplate restTemplate) {
		this.accountService = accountService;
		this.restTemplate = restTemplate;
	}
	
	@GetMapping
	public List<Account> getAllAccounts(){
		return accountService.getAllAccounts();
	}
	
	@DeleteMapping("/{id}")
	public String deleteAccount (@PathVariable("id") Long id) {
		return accountService.deleteAccount(id);
	}
	
	@PostMapping
	public String createAccount (@RequestBody Account account) {
		return accountService.createAccount(account);
	}
	
	@PutMapping("/{id}")
	public String updateAccount (@PathVariable("id") Long id, @RequestBody Account account) {
		return accountService.updateAccount(id, account);
	}
	
	

}
