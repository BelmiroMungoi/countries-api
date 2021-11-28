package com.bbm.countries.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bbm.countries.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long>{

	@Query("select c from Country c where upper(trim(c.nome)) = ?1")
	Country findByNome(String nome);
}
