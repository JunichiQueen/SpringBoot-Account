package com.qa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.qa.entity.Account;
import com.qa.entity.SentAccount;
import com.qa.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {
	
	private AccountRepository repository;
	
	private RestTemplate restTemplate;
	
    @Autowired
    private JmsTemplate jmsTemplate;

	@Autowired
	public AccountServiceImpl(AccountRepository repository, RestTemplate restTemplate) {
		this.repository=repository;
		this.restTemplate=restTemplate;
	}
	
	@Override
	public List<Account> getAllAccounts(){
		return repository.findAll();
	}
	
	@Override
	public String deleteAccount(Long id){
		repository.deleteById(id);
		return "You have deleted";
	}
	
	@Override
	public String createAccount(Account account) {
		
		ResponseEntity<String> getRandNumber = restTemplate.exchange("http://localhost:8081/numgen/getNumber", HttpMethod.GET, null, String.class);
		account.setAccountNumber(getRandNumber.getBody());
		String randNo = getRandNumber.getBody();
		ResponseEntity<String> getPrize = restTemplate.exchange("http://localhost:8082/prizegen/getPrize/" + randNo, HttpMethod.GET, null, String.class);
		account.setPrize(getPrize.getBody());
		repository.save(account);
		System.out.println(account);
		sendToQueue(account);
		return getPrize.getBody();
	}
	
	@Override
	public String updateAccount(Long id, Account account) {
		Optional<Account> oldAccount = repository.findById(id);
		
		if (oldAccount != null) {
			repository.deleteById(id);
			account.setId(id);
			repository.save(account);
			return "You have updated";
		} else {
			return "You have not updated";
		}
		
	}
	
    private void sendToQueue(Account account){
        SentAccount accountToStore =  new SentAccount(account);
        System.out.println(accountToStore);
        jmsTemplate.convertAndSend("AccountQueue", accountToStore);
    }


}
