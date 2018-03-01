package org.duc.conceptualbank.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.Branches;
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

	public List<Branches> getAll(){
		return branchesReppository.findAll();
	}
	public Branches getById(int branchCode) {
		return branchesReppository.getOne(branchCode);
	}

	public boolean add(Branches branches) {
		try {
			branchesReppository.save(branches);
			LOGGER.debug("Add successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
			return false;
		}
	}

	public boolean edit(Branches branches) {
		try {
			branchesReppository.saveAndFlush(branches);
			LOGGER.debug("Edit successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}

	public boolean delete(int branchCode) {
		try {
			Branches branches = branchesReppository.findOne(branchCode);
			branchesReppository.delete(branches);
			LOGGER.debug("Delete successfully");
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e);
			return false;
		}
	}
}
