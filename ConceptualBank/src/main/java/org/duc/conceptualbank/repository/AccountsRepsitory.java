package org.duc.conceptualbank.repository;

import org.duc.conceptualbank.entity.Accounts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountsRepsitory extends JpaRepository<Accounts, Integer> {

}
