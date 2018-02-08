package org.duc.conceptualbank.repository;

import org.duc.conceptualbank.entity.Cities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends JpaRepository<Cities, Integer> {

}
