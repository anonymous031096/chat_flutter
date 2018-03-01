package org.duc.conceptualbank.controller;

import org.duc.conceptualbank.entity.Customers;
import org.duc.conceptualbank.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping(value = "/duc/account")
public class AccountController {

	@Autowired
	CustomersService customersService;

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> openAccount(@RequestParam(value = "customerNr") int customerNr,
			@RequestParam(value = "branchCode") int branchCode,
			@RequestParam(value = "accountTypeCode") int accountTypeCode, UriComponentsBuilder ucBuilder) {
		Customers customer = customersService.getById(customerNr);
		if (customer == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		customersService.openAccount(customerNr, branchCode, accountTypeCode);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{customerNr}").buildAndExpand(customer.getCustomerNr()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
}
