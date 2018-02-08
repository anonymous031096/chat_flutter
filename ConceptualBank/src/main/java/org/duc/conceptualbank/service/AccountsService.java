package org.duc.conceptualbank.service;

import java.util.List;

import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.entity.Accounts;
import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.repository.AccountsRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	@Autowired
	AccountsRepsitory accountsRepository;

	public void showAll() {
		List<Accounts> listAccounts = accountsRepository.findAll();
		for (Accounts accounts : listAccounts) {
			System.out.println(accounts.toString());
		}
	}

	public Accounts getOne(int accountsCode) {
		return accountsRepository.findOne(accountsCode);
	}

	public void add(AccountType accountType, Branches branches) {
		try {
			Accounts accounts = new Accounts(accountType, branches);
			accountsRepository.save(accounts);
			System.out.println("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void edit(int accountNr, AccountType accountType, Branches branches) {
		try {
			Accounts accounts = accountsRepository.findOne(accountNr);
			accounts.setAccountType(accountType);
			accounts.setBranches(branches);
			accountsRepository.saveAndFlush(accounts);
			System.out.println("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void delete(int accountNr) {
		try {
			accountsRepository.delete(accountNr);
			System.out.println("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
