package org.hectormoraga.placestovisit.resources;

import java.util.List;
import java.util.Optional;

import org.hectormoraga.placestovisit.entity.Country;
import org.hectormoraga.placestovisit.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class CountryController {

	@Autowired
	private CountryRepository countryService;
	
	//Create Country object
	@PostMapping("/countries")
	public Country crearCountry(@RequestBody Country country) {
		country.setId(0);
		
		return countryService.save(country);
	}
	
	//Read: single element by Id
	@GetMapping("/countries/{id}")
	public Country findCountryById(@PathVariable int id) {
		Optional<Country> theCity = countryService.findById(id);
		
		if (!theCity.isPresent()) {
			throw new RuntimeException("Ciudad no encontrada, id - " + id);
		}
		
		return theCity.get();
	}

	//Read: list all countries
	@GetMapping("/countries")
	public List<Country> findAllCountries() {
		return countryService.findAll();
	}

	//Update Country
	@PutMapping("/countries")
	public void updateCountry(@RequestBody Country country) {
		if (country.getId()!=null) {
			countryService.save(country);	
		} else {
			throw new RuntimeException("Ciudad no existe!");
		}
	}
	
	//Delete Country by id
	@DeleteMapping("/countries/{id}")
	public String deleteCountry(@PathVariable int id) {
		countryService.deleteById(id);
		
		 return "Deleted Successfully";
	}
}
