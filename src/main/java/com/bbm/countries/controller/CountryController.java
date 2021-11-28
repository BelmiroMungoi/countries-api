package com.bbm.countries.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bbm.countries.model.Country;
import com.bbm.countries.repository.CountryRepository;
import com.bbm.countries.service.CountryService;

@RestController
@RequestMapping("/country")
public class CountryController {
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private CountryService countryService;

	@PostMapping("/")
	public ResponseEntity<Country> saveCountry(@Valid @RequestBody Country country){
		
		Country countries = countryService.save(country);
		
		return new ResponseEntity<Country>(countries, HttpStatus.CREATED);
	}
	
	@GetMapping("/")
	public ResponseEntity<List<Country>> findAllCountries(){
		
		List<Country> countries = countryRepository.findAll();
		
		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}
	
	@GetMapping("/{sort}")
	public ResponseEntity<List<Country>> findAllCountriesWithSort(@PathVariable("sort") String sort){
		
		List<Country> countries = countryRepository.findAll(Sort.by(sort));
		
		return new ResponseEntity<List<Country>>(countries, HttpStatus.OK);
	}
	
	@PutMapping("/")
	public ResponseEntity<?> updateCountry(@Valid @RequestBody Country country){
		
		if (country.getId() == null) {
			return new ResponseEntity<String>("País não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		Country countries = countryService.save(country);
		
		return new ResponseEntity<Country>(countries, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCountry(@PathVariable("id") Long id){
		
		if (!countryRepository.existsById(id)) {
			return new ResponseEntity<String>("País não encontrado!", HttpStatus.NOT_FOUND);
		}
		
		countryRepository.deleteById(id);
		
		return new ResponseEntity<String>("País Deletado Com Sucesso!", HttpStatus.OK);
	}
	
}
