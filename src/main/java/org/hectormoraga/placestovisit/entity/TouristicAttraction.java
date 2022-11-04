package org.hectormoraga.placestovisit.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.locationtech.jts.geom.Geometry;
import org.n52.jackson.datatype.jts.GeometryDeserializer;
import org.n52.jackson.datatype.jts.GeometrySerializer;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@Entity
@Table(name = "touristic_attraction")
public class TouristicAttraction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", nullable = false)
	private Integer id;
	@Column(name = "nombre", nullable = false)
	private String nombre;
	@Column(name = "ubicacion", nullable = false)
	@JsonSerialize(using = GeometrySerializer.class)
	@JsonDeserialize(using = GeometryDeserializer.class)
	private Geometry ubicacion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "country_id", nullable = false)
	@JsonBackReference
	private Country country;
	
	public TouristicAttraction(Integer id, String nombre, Geometry ubicacion, Country country) {
		this.nombre = nombre;
		this.ubicacion = ubicacion;
		this.country = country;
	}

	public TouristicAttraction() {
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

	public Geometry getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(Geometry ubicacion) {
		this.ubicacion = ubicacion;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "TouristicAttraction [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + ", country="
				+ country + "]";
	}
	
}
