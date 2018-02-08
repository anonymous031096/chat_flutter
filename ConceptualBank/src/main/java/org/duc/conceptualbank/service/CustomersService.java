package org.duc.conceptualbank.service;

import java.util.List;
import java.util.Set;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.entity.Accounts;
import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.entity.CustomerType;
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
		for(Accounts account : accountses) {
			System.out.println(account.toString());
		}
	}
	public Customers getOne(int customerNr) {
		return customersRepository.getOne(customerNr);
	}

	public void openAccount(int customerNr, int branchCode, int accountTypeCode) {
		Branches branches = branchesRepository.getOne(branchCode);
		AccountType accountType = accountTypeRepository.getOne(accountTypeCode);
		Accounts account = new Accounts(accountType, branches);
		accountRepository.save(account);
		Customers customer = customersRepository.getOne(customerNr);
		customer.getAccountses().add(account);
		customersRepository.saveAndFlush(customer);
	}
	
	public void useAccount(int customerNr, int accountNr) {
		Accounts account = accountRepository.getOne(accountNr);
		Customers customer = customersRepository.getOne(customerNr);
		customer.getAccountses().add(account);
		customersRepository.saveAndFlush(customer);
	}
	public void add(int customerTypeCode, String name, String address, String postCode, String phoneNr,
			String customerDetails) {
		try {
			CustomerType customerType = customerTypeRepository.getOne(customerTypeCode);
			Customers customers = new Customers(customerType, name, address, postCode, phoneNr, customerDetails);
			customersRepository.save(customers);
			LOGGER.debug("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void edit(int customerNr, int customerTypeCode, String name, String address, String postCode,
			String phoneNr, String customerDetails) {
		try {
			CustomerType customerType = customerTypeRepository.getOne(customerTypeCode);
			Customers customer = customersRepository.findOne(customerNr);
			customer.setCustomerType(customerType);
			customer.setName(name);
			customer.setAddress(address);
			customer.setPostCode(postCode);
			customer.setPhoneNr(phoneNr);
			customer.setCustomerDetails(customerDetails);
			customersRepository.saveAndFlush(customer);
			LOGGER.debug("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void delete(int accountNr) {
		try {
			customersRepository.delete(accountNr);
			LOGGER.debug("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}
}
