package pl.edu.agh.iisg.to.javafx.cw1.model.generator;

import java.math.BigInteger;
import java.time.LocalDate;

import pl.edu.agh.iisg.to.javafx.cw1.model.Account;
import pl.edu.agh.iisg.to.javafx.cw1.model.Category;
import pl.edu.agh.iisg.to.javafx.cw1.model.Transaction;

public class DataGenerator {

	public final static Account generateAccountData() {
		Account account = new Account("Personal account");
		Category income = new Category("Income");
		Category groceries = new Category("Groceries");
		Category fuel = new Category("Fuel");
		
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 24), "My company", income, BigInteger.valueOf(1000)));
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 25), "Greenrocesr's", groceries, BigInteger.valueOf(973)));
		account.addTransaction(new Transaction(LocalDate.of(2015, 8, 26), "Petrol station", fuel, BigInteger.valueOf(727)));
		
		return account;
	}
}
