package org.duc.conceptualbank.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.entity.Accounts;
import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.repository.AccountsRepsitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountsService {

	private static final Logger LOGGER = LogManager.getLogger(AccountsService.class);
	
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

	public boolean add(AccountType accountType, Branches branches) {
		try {
			Accounts accounts = new Accounts(accountType, branches);
			accountsRepository.save(accounts);
			LOGGER.debug("Add successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}

	public boolean edit(int accountNr, AccountType accountType, Branches branches) {
		try {
			Accounts accounts = accountsRepository.findOne(accountNr);
			accounts.setAccountType(accountType);
			accounts.setBranches(branches);
			accountsRepository.saveAndFlush(accounts);
			LOGGER.debug("Edit successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}

	public boolean delete(int accountNr) {
		try {
			accountsRepository.delete(accountNr);
			LOGGER.debug("Delete successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			LOGGER.error(e);
			return false;
		}
	}
}
