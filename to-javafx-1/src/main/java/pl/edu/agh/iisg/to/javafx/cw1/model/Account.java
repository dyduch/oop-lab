package pl.edu.agh.iisg.to.javafx.cw1.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account {

	private String name;
	
	private BigInteger balance;
	
	private List<Transaction> transactions;

	public Account(String name) {
		this.name = name;
		this.balance = BigInteger.ZERO;
		this.transactions = new ArrayList<>();
	}

	public final String getName() {
		return name;
	}

	public final BigInteger getBalance() {
		return balance;
	}
	
	public final List<Transaction> getTransactions() {
		return transactions;
	}
	
	public final void addTransaction(Transaction transaction) {
		this.transactions.add(transaction);
	}
		
}
