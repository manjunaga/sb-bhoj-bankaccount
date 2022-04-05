package com.wipro.BankAccount.Model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import com.wipro.BankAccount.Repository.AccountRepository;

@Component
public class AccountHealthIndicator implements HealthIndicator{
	
	@Autowired
	private AccountRepository accountRepo;
	private static final int MAX = 2;
	
	public AccountHealthIndicator() {

	}
	
	public AccountHealthIndicator(AccountRepository acctRepo) {
		super();
		this.accountRepo = acctRepo;
	}

	@Override
	public Health health() {
		Health.Builder builder = new Health.Builder();
		if (accountRepo.count() < MAX) {
			System.out.println("accountRepo count" + accountRepo.count());
			builder.up();
		}
		else {
			builder.down();
		}
		return builder.build();
	}

}
