package org.duc.conceptualbank.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeService {

	private static final Logger LOGGER = LogManager.getLogger(AccountTypeService.class);

	@Autowired
	AccountTypeRepository accountTypeRepository;

	public void showAll() {
		List<AccountType> listAccountType = accountTypeRepository.findAll();
		for (AccountType accountType : listAccountType) {
			System.out.println(accountType.toString());
		}
	}

	public List<AccountType> getAll() {
		return accountTypeRepository.findAll();
	}

	public AccountType getById(int accountTypeCode) {
		return accountTypeRepository.findOne(accountTypeCode);
	}

	public boolean add(AccountType accountType) {
		try {
			accountTypeRepository.save(accountType);
			LOGGER.debug("Add successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}

	public boolean edit(AccountType accountType) {
		try {
			accountTypeRepository.saveAndFlush(accountType);
			LOGGER.debug("Edit successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}

	public boolean delete(int accountTypeCode) {
		try {
			accountTypeRepository.delete(accountTypeCode);
			LOGGER.debug("Delete successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}
}
