package org.duc.conceptualbank.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.entity.Cities;
import org.duc.conceptualbank.repository.BranchesRepository;
import org.duc.conceptualbank.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BranchesService {

	private static final Logger LOGGER = LogManager.getLogger(BranchesService.class);
	
	@Autowired
	BranchesRepository branchesReppository;

	@Autowired
	CitiesRepository citiesReppository;

	public void showAll() {
		List<Branches> listBranches = branchesReppository.findAll();
		for (Branches branches : listBranches) {
			System.out.println(branches.toString());
		}
	}

	public Branches getOne(int branchCode) {
		return branchesReppository.getOne(branchCode);
	}

	public void add(int cityCode, String headofficeYn, String branchDetails) {
		try {
			Cities cities = citiesReppository.getOne(cityCode);
			Branches branches = new Branches(cities, headofficeYn, branchDetails);
			branchesReppository.save(branches);
			LOGGER.debug("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void edit(int branchCode, int cityCode, String headofficeYn, String branchDetails) {
		try {
			Branches branches = branchesReppository.findOne(branchCode);
			Cities cities = citiesReppository.findOne(cityCode);
			branches.setCities(cities);
			branches.setHeadofficeYn(headofficeYn);
			branches.setBranchDetails(branchDetails);
			branchesReppository.saveAndFlush(branches);
			LOGGER.debug("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void delete(int branchCode) {
		try {
			Branches branches = branchesReppository.findOne(branchCode);
			branchesReppository.delete(branches);
			LOGGER.debug("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}
}
