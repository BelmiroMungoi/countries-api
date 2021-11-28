package com.bbm.countries.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bbm.countries.exception.NegocioException;
import com.bbm.countries.model.Country;
import com.bbm.countries.repository.CountryRepository;

@Service
public class CountryService {

	@Autowired
	private CountryRepository countryRepository;

	public Country save(Country country) {
		Country countryExists = countryRepository.findByNome(country.getNome().trim().toUpperCase());

		if (countryExists != null && !countryExists.equals(country)) {
			throw new NegocioException("Já existe um país registado com esse nome");
		}
		
		return countryRepository.save(country);
	}
}
