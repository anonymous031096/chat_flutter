package org.duc.conceptualbank.service;

import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.entity.Accounts;
import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.entity.Customers;
import org.duc.conceptualbank.repository.AccountTypeRepository;
import org.duc.conceptualbank.repository.AccountsRepsitory;
import org.duc.conceptualbank.repository.BranchesRepository;
import org.duc.conceptualbank.repository.CustomerTypeRepository;
import org.duc.conceptualbank.repository.CustomersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomersService {

	private static final Logger LOGGER = LogManager.getLogger(CustomersService.class);

	@Autowired
	CustomersRepository customersRepository;

	@Autowired
	AccountsRepsitory accountRepository;

	@Autowired
	CustomerTypeRepository customerTypeRepository;

	@Autowired
	BranchesRepository branchesRepository;

	@Autowired
	AccountTypeRepository accountTypeRepository;

	public void showAll() {
		List<Customers> listCustomers = customersRepository.findAll();
		for (Customers customers : listCustomers) {
			System.out.println(customers.toString());
		}
	}

	public void showAccounts(int customerNr) {
		Customers customer = customersRepository.getOne(customerNr);
		Set<Accounts> accountses = customer.getAccountses();
		for (Accounts account : accountses) {
			System.out.println(account.toString());
		}
	}

	public List<Customers> getAll() {
		return customersRepository.findAll();
	}

	public Customers getById(int customerNr) {
		return customersRepository.getOne(customerNr);
	}

	public boolean openAccount(int customerNr, int branchCode, int accountTypeCode) {
		try {
			Branches branches = branchesRepository.getOne(branchCode);
			AccountType accountType = accountTypeRepository.getOne(accountTypeCode);
			Accounts account = new Accounts(accountType, branches);
			accountRepository.save(account);
			Customers customer = customersRepository.getOne(customerNr);
			customer.getAccountses().add(account);
			customersRepository.saveAndFlush(customer);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean useAccount(int customerNr, int accountNr) {
		try {
			Accounts account = accountRepository.getOne(accountNr);
			Customers customer = customersRepository.getOne(customerNr);
			customer.getAccountses().add(account);
			customersRepository.saveAndFlush(customer);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public boolean add(Customers customer) {
		try {
			customersRepository.save(customer);
			LOGGER.debug("Add successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	public boolean edit(Customers customer) {
		try {
			customersRepository.saveAndFlush(customer);
			LOGGER.debug("Edit successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	public boolean delete(int accountNr) {
		try {
			customersRepository.delete(accountNr);
			LOGGER.debug("Delete successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return false;
		}
	}
}
