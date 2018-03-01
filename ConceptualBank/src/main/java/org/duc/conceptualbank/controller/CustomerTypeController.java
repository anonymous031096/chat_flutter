package org.duc.conceptualbank.controller;

import java.util.List;

import org.duc.conceptualbank.entity.CustomerType;
import org.duc.conceptualbank.service.CustomerTypeService;
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
@RequestMapping(value = "/duc/customer-type")
public class CustomerTypeController {
	@Autowired
	CustomerTypeService customerTypeService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CustomerType>> getAll() {
		List<CustomerType> lCustomerType = customerTypeService.getAll();
		if (lCustomerType.isEmpty())
			return new ResponseEntity<List<CustomerType>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<CustomerType>>(lCustomerType, HttpStatus.OK);
	}

	@RequestMapping(value = "{customer_type_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CustomerType> getById(@PathVariable("customer_type_code") int id) {
		CustomerType customerType = customerTypeService.getById(id);
		if (customerType == null)
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<CustomerType>(customerType, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "customerTypeDescription") String customerTypeDescription,
			UriComponentsBuilder ucBuilder) {
		CustomerType customerType = new CustomerType(customerTypeDescription);
		customerTypeService.add(customerType);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(
				ucBuilder.path("{account_type_code}").buildAndExpand(customerType.getCustomerTypeCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{customer_type_code}", method = RequestMethod.PUT)
	public ResponseEntity<CustomerType> edit(@PathVariable("customer_type_code") int id,
			@RequestParam(value = "customerTypeDescription") String customerTypeDescription) {
		CustomerType currentCustomerType = customerTypeService.getById(id);
		if (currentCustomerType == null)
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		currentCustomerType.setCustomerTypeDescription(customerTypeDescription);

		customerTypeService.edit(currentCustomerType);
		return new ResponseEntity<CustomerType>(currentCustomerType, HttpStatus.OK);
	}

	@RequestMapping(value = "{customer_type_code}", method = RequestMethod.DELETE)
	public ResponseEntity<CustomerType> delete(@PathVariable("customer_type_code") int id) {
		CustomerType customerType = customerTypeService.getById(id);
		if (customerType == null)
			return new ResponseEntity<CustomerType>(HttpStatus.NOT_FOUND);
		customerTypeService.delete(id);
		return new ResponseEntity<CustomerType>(HttpStatus.NO_CONTENT);
	}
}
