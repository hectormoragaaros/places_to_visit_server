package org.hectormoraga.placestovisit.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "country")
public class Country {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "alpha2code", nullable = false)
	private String alpha2Code;
	@Column(name = "alpha3code", nullable = false)
	private String alpha3Code;
	@JsonManagedReference
	@OneToMany(mappedBy = "country",
			fetch = FetchType.LAZY, 
			orphanRemoval = true,
			cascade = CascadeType.ALL)
	private List<TouristicAttraction> touristicAttractions = new ArrayList<>();

	public Country(Integer id, String nombre, String alpha2Code, String alpha3Code) {
		this.id = id;
		this.nombre = nombre;
		this.alpha2Code = alpha2Code;
		this.alpha3Code = alpha3Code;
	}
	
	public Country() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getAlpha2Code() {
		return alpha2Code;
	}

	public void setAlpha2Code(String alpha2Code) {
		this.alpha2Code = alpha2Code;
	}

	public String getAlpha3Code() {
		return alpha3Code;
	}

	public void setAlpha3Code(String alpha3Code) {
		this.alpha3Code = alpha3Code;
	}

	public List<TouristicAttraction> getTouristicAttractions() {
		return touristicAttractions;
	}

	public void setTouristicAttractions(List<TouristicAttraction> touristicAttractions) {
		this.touristicAttractions = touristicAttractions;
	}
	
	@Override
	public String toString() {
		return "Country [id=" + id + ", nombre=" + nombre + ", alpha2Code=" + alpha2Code + ", alpha3Code=" + alpha3Code
				+ ", touristicAttractions=" + touristicAttractions + "]";
	}
}
