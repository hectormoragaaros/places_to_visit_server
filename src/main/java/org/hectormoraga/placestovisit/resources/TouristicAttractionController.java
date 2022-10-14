package org.hectormoraga.placestovisit.resources;

import java.util.List;
import java.util.Optional;

import org.hectormoraga.placestovisit.entity.Country;
import org.hectormoraga.placestovisit.entity.TouristicAttraction;
import org.hectormoraga.placestovisit.repository.TouristicAttractionRepository;
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
public class TouristicAttractionController {
	
	@Autowired
	private TouristicAttractionRepository touristicAttractionService;
	
	public TouristicAttractionController(TouristicAttractionRepository touristicAttractionService) {
		this.touristicAttractionService = touristicAttractionService;
	}

	//Create TouristicAttraction object
	@PostMapping("/touristic_attractions")
	public TouristicAttraction createTouristicAttraction(@RequestBody TouristicAttraction touristicAttraction) {
		touristicAttraction.setId(0);
		TouristicAttraction theCity = touristicAttractionService.save(touristicAttraction);
		
		return theCity;
	}
	
	//Read: single TouristicAttraction by Id
	@GetMapping("/touristic_attractions/{id}")
	public TouristicAttraction findTouristicAttractionById(@PathVariable int id) {
		Optional<TouristicAttraction> theTouristicAttraction = touristicAttractionService.findById(id);
		
		if (!theTouristicAttraction.isPresent()) {
			throw new RuntimeException("Attraccion turistica no encontrada, id - " + id);
		}
		
		return theTouristicAttraction.get();
	}

	//Read: list all TouristicAttractions
	@GetMapping("/touristic_attractions")
	public List<TouristicAttraction> findAllTouristicAttractions() {
		return touristicAttractionService.findAll();
	}

	//Read: list data of country associated to touristic attraction
	@GetMapping("/touristic_attractions/{id}/country")
	public Country findCountryData(@PathVariable int id) {
		Optional<TouristicAttraction> theTouristicAttraction = touristicAttractionService.findById(id);
		
		if (!theTouristicAttraction.isPresent()) {
			throw new RuntimeException("Atracción Turística no existe, id - " + id);
		}

		return theTouristicAttraction.get().getCountry();
	}
	
	//Update TouristicAttraction
	@PutMapping("/touristic_attractions")
	public void updateCiudad(@RequestBody TouristicAttraction touristicAttraction) {
		if (touristicAttraction.getId()!=null) {
			touristicAttractionService.save(touristicAttraction);			
		} else {
			throw new RuntimeException("Atracción Turística no existe, no se puede actualizar!");
		}
	}
	
	//Delete Country by id
	@DeleteMapping("/touristic_attractions/{id}")
	public String deleteCity(@PathVariable int id) {
		Optional<TouristicAttraction> theTouristicAttractionService = touristicAttractionService.findById(id);
		
		if (theTouristicAttractionService.isPresent()) {
			touristicAttractionService.deleteById(id);			

			return "Deleted Successfully";
		} else {
			throw new RuntimeException("Atracción Turística no encontrada: id - " + id);
		}	
	}
}
