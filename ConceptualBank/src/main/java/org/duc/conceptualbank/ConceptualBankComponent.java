package org.duc.conceptualbank;

import org.duc.conceptualbank.service.AccountTypeService;
import org.duc.conceptualbank.service.AccountsService;
import org.duc.conceptualbank.service.BranchesService;
import org.duc.conceptualbank.service.CitiesService;
import org.duc.conceptualbank.service.CustomerTypeService;
import org.duc.conceptualbank.service.CustomersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConceptualBankComponent implements CommandLineRunner {

	@Autowired
	CitiesService citiesService;

	@Autowired
	BranchesService branchesService;

	@Autowired
	AccountTypeService accountTypeService;

	@Autowired
	CustomersService customersService;
	
	@Autowired
	CustomerTypeService customerTypeService;
	
	@Autowired
	AccountsService accountService;

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
//----------CITIES----------
		citiesService.add("Vinh");
		citiesService.edit(13, "Da Nang");
		citiesService.delete(14);
		citiesService.showAll();
		
//----------BRANCHES----------
		branchesService.add(13, "Chi nhanh Cau Giay", "");
		branchesService.edit(14, 13, "Chi nhanh Dong Da", "");
		branchesService.delete(14);
		branchesService.showAll();
		
//----------ACCOUNT TYPE----------
		accountTypeService.add("tdfgdfgfdg");
		accountTypeService.edit(3, "asdfghjk");
		accountTypeService.delete(3);
		accountTypeService.showAll();
		
//----------CUSTOMER TYPE----------
		customerTypeService.add("qwertyu");
		customerTypeService.edit(3, "asdfghjkl");
		customerTypeService.delete(3);
		customerTypeService.showAll();
		
//----------CUSTOMERS----------
		customersService.add(2, "Le Huynh Duc", "Nghe An", "187458628", "01694395934", "");
		customersService.edit(4, 1, "Duc Le Huynh", "Nghe An", "187458628", "01694395934", "");
		customersService.delete(3);
		customersService.openAccount(4, 12, 1);
		customersService.useAccount(4, 7);
		customersService.showAccounts(4);
		customersService.showAll();
		

	}
}
