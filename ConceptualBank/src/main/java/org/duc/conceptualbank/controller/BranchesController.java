package org.duc.conceptualbank.controller;

import java.util.List;

import org.duc.conceptualbank.entity.Branches;
import org.duc.conceptualbank.entity.Cities;
import org.duc.conceptualbank.service.BranchesService;
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
@RequestMapping(value = "/duc/branches")
public class BranchesController {

	@Autowired
	BranchesService branchesService;

	@Autowired
	CitiesService citiesService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Branches>> getAll() {
		List<Branches> lBranches = branchesService.getAll();
		if (lBranches.isEmpty())
			return new ResponseEntity<List<Branches>>(HttpStatus.NO_CONTENT);
		return new ResponseEntity<List<Branches>>(lBranches, HttpStatus.OK);
	}

	@RequestMapping(value = "{branch_code}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Branches> getById(@PathVariable("branch_code") int id) {
		Branches branches = branchesService.getById(id);
		if (branches == null)
			return new ResponseEntity<Branches>(HttpStatus.NOT_FOUND);
		return new ResponseEntity<Branches>(branches, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> add(@RequestParam(value = "cityCode") int cityCode,
			@RequestParam(value = "headofficeYn") String headofficeYn,
			@RequestParam(value = "branchDetails", required = false) String branchDetails,
			UriComponentsBuilder ucBuilder) {
		Cities cities = citiesService.getById(cityCode);
		if (cities == null)
			return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
		Branches branches = new Branches(cities, headofficeYn, branchDetails);
		branchesService.add(branches);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("{branch_code}").buildAndExpand(branches.getBranchCode()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{branch_code}", method = RequestMethod.PUT)
	public ResponseEntity<Branches> edit(@PathVariable("branch_code") int id,
			@RequestParam(value = "cityCode") int cityCode, @RequestParam(value = "headofficeYn") String headofficeYn,
			@RequestParam(value = "branchDetails", required = false) String branchDetails) {
		Branches currentBranches = branchesService.getById(id);
		if (currentBranches == null)
			return new ResponseEntity<Branches>(HttpStatus.NOT_FOUND);
		Cities cities = citiesService.getById(cityCode);
		if (cities == null)
			return new ResponseEntity<Branches>(HttpStatus.NOT_FOUND);

		currentBranches.setHeadofficeYn(headofficeYn);
		currentBranches.setBranchDetails(branchDetails);
		currentBranches.setCities(cities);

		branchesService.edit(currentBranches);
		return new ResponseEntity<Branches>(currentBranches, HttpStatus.OK);
	}

	@RequestMapping(value = "{branch_code}", method = RequestMethod.DELETE)
	public ResponseEntity<Branches> delete(@PathVariable("branch_code") int id) {
		Branches branches = branchesService.getById(id);
		if (branches == null)
			return new ResponseEntity<Branches>(HttpStatus.NOT_FOUND);
		branchesService.delete(id);
		return new ResponseEntity<Branches>(HttpStatus.NO_CONTENT);
	}
}
