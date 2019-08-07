package com.qa.entity;

public class SentAccount {

    //private Long id;

    private String name;

    private String accountNumber;
    
    private String prize;
    

    public SentAccount(Account account){
        this.name = account.getName();
        this.accountNumber = account.getAccountNumber();
        this.prize = account.getPrize();
    }
    
    public SentAccount(){}

//	public Long getId() {
//		return id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {

		return "name: " + name + ", accountNumber: " + accountNumber + ", prize: " + prize;
	}

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}




}