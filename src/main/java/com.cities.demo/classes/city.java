package com.cities.demo.classes;

import javax.persistence.*;

@Entity
@Table(name = "Cities")
public class city {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	@Column(name = "city", length = 255)
	String city;
	@Column(name = "city_ascii", length = 255)
	String city_ascii;
	@Column(name = "lat", length = 255)
	String lat;
	@Column(name = "lng", length = 255)
	String lng;
	@Column(name = "country", length = 255)
	String country;
	@Column(name = "iso2", length = 255)
	String iso2;
	@Column(name = "iso3", length = 255)
	String iso3;
	@Column(name = "admin_name", length = 255)
	String admin_name;
	@Column(name = "capital", length = 255)
	String capital;
	@Column(name = "population", length = 255)
	String population;
	@Column(name = "city_id", length = 255)
	String city_id;

	public city() {
	}

	public city(int id, String city, String city_ascii, String lat, String lng, String country, String iso2, String iso3, String admin_name, String capital, String population, String city_id) {
		this.id = id;
		this.city = city;
		this.city_ascii = city_ascii;
		this.lat = lat;
		this.lng = lng;
		this.country = country;
		this.iso2 = iso2;
		this.iso3 = iso3;
		this.admin_name = admin_name;
		this.capital = capital;
		this.population = population;
		this.city_id = city_id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_ascii() {
		return city_ascii;
	}

	public void setCity_ascii(String city_ascii) {
		this.city_ascii = city_ascii;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getIso2() {
		return iso2;
	}

	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}

	public String getIso3() {
		return iso3;
	}

	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}

	public String getAdmin_name() {
		return admin_name;
	}

	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}

	public String getCapital() {
		return capital;
	}

	public void setCapital(String capital) {
		this.capital = capital;
	}

	public String getPopulation() {
		return population;
	}

	public void setPopulation(String population) {
		this.population = population;
	}

	public String getCity_id() {
		return city_id;
	}

	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}

	@Override
	public String toString() {
		return "City{" + "id=" + id + ", country=" + country + ", city=" + city + ", population=" + population + '}';
	}
}
