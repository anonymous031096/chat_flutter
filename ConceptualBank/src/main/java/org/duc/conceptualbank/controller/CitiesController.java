package org.duc.conceptualbank.controller;

import java.util.List;

import org.duc.conceptualbank.entity.Cities;
import org.duc.conceptualbank.service.CitiesService;
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
@RequestMapping(value = "/duc/cities")
public class CitiesController {

	@Autowired
	CitiesService citiesService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Cities>> listAllCities() {
		List<Cities> lCities = citiesService.getAll();
		if (lCities.isEmpty())
			return new ResponseEntity<List<Cities>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Cities>>(lCities, HttpStatus.OK);
	}

	@RequestMapping(value = "/{city_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Cities> getById(@PathVariable("city_code") int id) {
		Cities cities = citiesService.getById(id);
		if (cities == null)
			return new ResponseEntity<Cities>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Cities>(cities, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "cityName") String cityName, UriComponentsBuilder ucBuilder) {
		Cities cities = new Cities(cityName);
		citiesService.add(cities);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{city_code}").buildAndExpand(cities.getCityCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/{city_code}", method = RequestMethod.PUT)
	public ResponseEntity<Cities> edit(@PathVariable("city_code") int id,
			@RequestParam(value = "cityName") String cityName) {
		Cities cCities = citiesService.getById(id);
		if (cCities == null)
			return new ResponseEntity<Cities>(HttpStatus.NOT_FOUND);
		cCities.setCityName(cityName);
		citiesService.edit(cCities);
		return new ResponseEntity<Cities>(cCities, HttpStatus.OK);
	}

	@RequestMapping(value = "/{city_code}", method = RequestMethod.DELETE)
	public ResponseEntity<Cities> delete(@PathVariable("city_code") int id) {
		Cities cities = citiesService.getById(id);
		if (cities == null)
			return new ResponseEntity<Cities>(HttpStatus.NOT_FOUND);
		citiesService.delete(id);
		return new ResponseEntity<Cities>(HttpStatus.NO_CONTENT);
	}
}
