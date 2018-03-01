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

	public List<CustomerType> getAll(){
		return customerTypeRepository.findAll();
	}
	public CustomerType getById(int customerTypeCode) {
		return customerTypeRepository.findOne(customerTypeCode);
	}

	public boolean add(CustomerType customerType) {
		try {
			customerTypeRepository.save(customerType);
			System.out.println("Add successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public boolean edit(CustomerType customerType) {
		try {
			customerTypeRepository.saveAndFlush(customerType);
			System.out.println("Edit successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}

	public boolean delete(int customerTypeCode) {
		try {
			customerTypeRepository.delete(customerTypeCode);
			System.out.println("Delete successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
			return false;
		}
	}
}
