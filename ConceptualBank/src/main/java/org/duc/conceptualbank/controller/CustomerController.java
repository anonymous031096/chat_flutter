package org.duc.conceptualbank.controller;

import java.util.List;

import org.duc.conceptualbank.entity.CustomerType;
import org.duc.conceptualbank.entity.Customers;
import org.duc.conceptualbank.service.CustomerTypeService;
import org.duc.conceptualbank.service.CustomersService;
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
@RequestMapping(value = "/duc/customers")
public class CustomerController {

	@Autowired
	CustomersService customersService;

	@Autowired
	CustomerTypeService customerTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Customers>> getAll() {
		List<Customers> lCustomers = customersService.getAll();
		if (lCustomers.isEmpty())
			return new ResponseEntity<List<Customers>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Customers>>(lCustomers, HttpStatus.OK);
	}

	@RequestMapping(value = "{customerNr}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customers> getById(@PathVariable("customerNr") int id) {
		Customers customer = customersService.getById(id);
		if (customer == null)
			return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Customers>(customer, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "customerTypeCode") int customerTypeCode,
			@RequestParam(value = "name") String name, @RequestParam(value = "address") String address,
			@RequestParam(value = "postCode") String postCode, @RequestParam(value = "phoneNr") String phoneNr,
			@RequestParam(value = "customerDetails") String customerDetails, UriComponentsBuilder ucBuilder) {
		CustomerType customerType = customerTypeService.getById(customerTypeCode);
		if (customerType == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		Customers customer = new Customers(customerType, name, address, postCode, phoneNr, customerDetails);
		customersService.add(customer);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{customerNr}").buildAndExpand(customer.getCustomerNr()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	// ?customerNr={customerNr}&branchCode={branchCode}&accountTypeCode={accountTypeCode}
	// @RequestMapping(value = "customers", method = RequestMethod.PUT)
	// public ResponseEntity<Void> openAccount(@RequestParam("customerNr") int
	// customerNr,
	// @RequestParam("branchCode") int branchCode, @RequestParam("accountTypeCode")
	// int accountTypeCode) {
	// customersService.openAccount(customerNr, branchCode, accountTypeCode);
	// return new ResponseEntity<Void>(HttpStatus.OK);
	// }
	@RequestMapping(value = "{customerNr}", method = RequestMethod.PUT)
	public ResponseEntity<Customers> edit(@PathVariable("customerNr") int id,
			@RequestParam(value = "customerTypeCode") int customerTypeCode, @RequestParam(value = "name") String name,
			@RequestParam(value = "address") String address, @RequestParam(value = "postCode") String postCode,
			@RequestParam(value = "phoneNr") String phoneNr,
			@RequestParam(value = "customerDetails") String customerDetails) {
		Customers currentCustomer = customersService.getById(id);
		if (currentCustomer == null)
			return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
		CustomerType customerType = customerTypeService.getById(customerTypeCode);
		if (customerType == null)
			return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
		currentCustomer.setCustomerType(customerType);
		currentCustomer.setName(name);
		currentCustomer.setAddress(address);
		currentCustomer.setPostCode(postCode);
		currentCustomer.setPhoneNr(phoneNr);
		currentCustomer.setCustomerDetails(customerDetails);

		customersService.edit(currentCustomer);
		return new ResponseEntity<Customers>(currentCustomer, HttpStatus.OK);
	}

	@RequestMapping(value = "{customerNr}", method = RequestMethod.DELETE)
	public ResponseEntity<Customers> delete(@PathVariable("customerNr") int id) {
		Customers customer = customersService.getById(id);
		if (customer == null)
			return new ResponseEntity<Customers>(HttpStatus.NOT_FOUND);
		customersService.delete(id);
		return new ResponseEntity<Customers>(HttpStatus.NO_CONTENT);
	}
}
