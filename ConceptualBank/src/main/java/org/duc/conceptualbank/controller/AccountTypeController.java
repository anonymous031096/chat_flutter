package org.duc.conceptualbank.controller;

import java.util.List;

import org.duc.conceptualbank.entity.AccountType;
import org.duc.conceptualbank.service.AccountTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/duc/account-type")
public class AccountTypeController {

	@Autowired
	AccountTypeService accountTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<AccountType>> getAll() {
		List<AccountType> lAccountType = accountTypeService.getAll();
		if (lAccountType.isEmpty())
			return new ResponseEntity<List<AccountType>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<AccountType>>(lAccountType, HttpStatus.OK);
	}

	@RequestMapping(value = "{account_type_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AccountType> getById(@PathVariable("account_type_code") int id) {
		AccountType accountType = accountTypeService.getById(id);
		if (accountType == null)
			return new ResponseEntity<AccountType>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<AccountType>(accountType, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "accountTypeDescription") String accountTypeDescription,
			UriComponentsBuilder ucBuilder) {
		AccountType accountType = new AccountType(accountTypeDescription);
		accountTypeService.add(accountType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("{account_type_code}").buildAndExpand(accountType.getAccountTypeCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{account_type_code}", method = RequestMethod.PUT)
	public ResponseEntity<AccountType> edit(@PathVariable("account_type_code") int id,
			@RequestParam(value = "accountTypeDescription") String accountTypeDescription) {
		AccountType currentAccountType = accountTypeService.getById(id);
		if (currentAccountType == null)
			return new ResponseEntity<AccountType>(HttpStatus.NOT_FOUND);
		currentAccountType.setAccountTypeDescription(accountTypeDescription);

		accountTypeService.edit(currentAccountType);
		return new ResponseEntity<AccountType>(currentAccountType, HttpStatus.OK);
	}

	@RequestMapping(value = "{account_type_code}", method = RequestMethod.DELETE)
	public ResponseEntity<AccountType> delete(@PathVariable("account_type_code") int id) {
		AccountType accountType = accountTypeService.getById(id);
		if (accountType == null)
			return new ResponseEntity<AccountType>(HttpStatus.NOT_FOUND);
		accountTypeService.delete(id);
		return new ResponseEntity<AccountType>(HttpStatus.NO_CONTENT);
	}
}
