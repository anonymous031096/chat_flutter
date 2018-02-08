package org.duc.conceptualbank.service;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.duc.conceptualbank.entity.Cities;
import org.duc.conceptualbank.repository.BranchesRepository;
import org.duc.conceptualbank.repository.CitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CitiesService {

	private static final Logger LOGGER = LogManager.getLogger(CitiesService.class);

	@Autowired
	CitiesRepository citiesReppository;

	@Autowired
	BranchesRepository branchesRepository;

	public void showAll() {
		List<Cities> listCities = citiesReppository.findAll();
		for (Cities cities : listCities) {
			System.out.println(cities.toString());
		}
	}

	public Cities getOne(int cityCode) {
		return citiesReppository.getOne(cityCode);
	}

	public void add(String cityName) {
		Cities cities = new Cities(cityName);
		try {
			citiesReppository.save(cities);
			LOGGER.debug("Add successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void edit(int cityCode, String cityName) {
		try {
			Cities cities = citiesReppository.getOne(cityCode);
			cities.setCityName(cityName);
			citiesReppository.saveAndFlush(cities);
			LOGGER.debug("Edit successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}

	public void delete(int cityCode) {
		try {
			citiesReppository.delete(cityCode);
			LOGGER.debug("Delete successfully");
		} catch (Exception e) {
			// TODO: handle exception
			LOGGER.error(e.getMessage());
		}
	}
}
