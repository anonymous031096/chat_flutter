package org.duc.conceptualbank.service;

import java.util.List;

import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.repository.AccountTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountTypeService {

	@Autowired
	AccountTypeRepository accountTypeRepository;

	public void showAll() {
		List<AccountType> listAccountType = accountTypeRepository.findAll();
		for (AccountType accountType : listAccountType) {
			System.out.println(accountType.toString());
		}
	}

	public AccountType getOne(int accountTypeCode) {
		return accountTypeRepository.findOne(accountTypeCode);
	}

	public void add(String accountTypeDescription) {
		try {
			AccountType accountType = new AccountType(accountTypeDescription);
			accountTypeRepository.save(accountType);
			System.out.println("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void edit(int accountTypeCode, String accountTypeDescription) {
		try {
			AccountType accountType = accountTypeRepository.findOne(accountTypeCode);
			accountType.setAccountTypeDescription(accountTypeDescription);
			accountTypeRepository.saveAndFlush(accountType);
			System.out.println("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void delete(int accountTypeCode) {
		try {
			accountTypeRepository.delete(accountTypeCode);
			System.out.println("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
