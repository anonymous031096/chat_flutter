package org.duc.conceptualbank.service;

import java.util.List;

import org.duc.conceptualbank.entity.CustomerType;
import org.duc.conceptualbank.repository.CustomerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerTypeService {

	@Autowired
	CustomerTypeRepository customerTypeRepository;

	public void showAll() {
		List<CustomerType> listCustomerType = customerTypeRepository.findAll();
		for (CustomerType customerType : listCustomerType) {
			System.out.println(customerType.toString());
		}
	}

	public CustomerType getOne(int customerTypeCode) {
		return customerTypeRepository.findOne(customerTypeCode);
	}

	public void add(String customerTypeDescription) {
		try {
			CustomerType customerType = new CustomerType(customerTypeDescription);
			customerTypeRepository.save(customerType);
			System.out.println("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void edit(int customerTypeCode, String customerTypeDescription) {
		try {
			CustomerType customerType = customerTypeRepository.findOne(customerTypeCode);
			customerType.setCustomerTypeDescription(customerTypeDescription);
			customerTypeRepository.saveAndFlush(customerType);
			System.out.println("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}

	public void delete(int customerTypeCode) {
		try {
			customerTypeRepository.delete(customerTypeCode);
			System.out.println("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
	}
}
