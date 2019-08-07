package com.qa.controller;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.qa.entity.Account;
import com.qa.repository.AccountRepository;
import com.qa.service.AccountService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountControllerTests {
	
	@InjectMocks
	AccountController controller;
	
	@Mock
	AccountService service;
	
	public Account MOCK_OBJECT = new Account("acc1", "111111", "50");
	
	public Account MOCK_OBJECT2 = new Account("acc2", "222222", "100");

	
	@Test
	public void getAllAccounts() {
		List<Account> MOCK_LIST = new ArrayList<Account>();
		MOCK_LIST.add(MOCK_OBJECT);
		MOCK_LIST.add(MOCK_OBJECT2);
		Mockito.when(service.getAllAccounts()).thenReturn(MOCK_LIST);
		assertEquals(MOCK_LIST, controller.getAllAccounts());
		Mockito.verify(service).getAllAccounts();
	}
	
	@Test
	public void deleteAccount() {
		Mockito.when(service.deleteAccount((long) 1)).thenReturn("You have deleted");
		assertEquals(controller.deleteAccount((long) 1), "You have deleted");
	}
	
	@Test
	public void createAccount() {
		Mockito.when(service.createAccount(MOCK_OBJECT)).thenReturn("You have created");
		assertEquals(controller.createAccount(MOCK_OBJECT), "You have created");
	}
	
	@Test
	public void updateAccount() {
		Mockito.when(service.updateAccount((long) 1, MOCK_OBJECT)).thenReturn("You have updated");
		assertEquals(controller.updateAccount((long) 1, MOCK_OBJECT), "You have updated");
	}

}
