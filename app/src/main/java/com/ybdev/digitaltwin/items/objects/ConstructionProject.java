package com.ybdev.digitaltwin.items.objects;

import java.util.ArrayList;

public class ConstructionProject {
	private String id;
	private String name;
	private Double lat;
	private Double lon;
	private Double budget;
	private String startDate;
	private String dueDate;
	private ArrayList<Building> buildings;

	public ConstructionProject() {
		// Empty constructor
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLon() {
		return lon;
	}

	public void setLon(Double lon) {
		this.lon = lon;
	}

	public Double getBudget() {
		return budget;
	}

	public void setBudget(Double budget) {
		this.budget = budget;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public ArrayList<Building> getBuildings() {
		return buildings;
	}

	public void setBuildings(ArrayList<Building> buildings) {
		this.buildings = buildings;
	}

	public ConstructionProject(String id,String name, Double lat, Double lon, Double budget, String startDate, String dueDate,
			ArrayList<Building> buildings) {
		super();
		this.id = id;
		this.name = name;
		this.lat = lat;
		this.lon = lon;
		this.budget = budget;
		this.startDate = startDate;
		this.dueDate = dueDate;
		this.buildings = buildings;
	}

	@Override
	public String toString() {
		return "ConstructionProject [id=" + id + ", name= "+name+", lat=" + lat + ", lon=" + lon + ", budget=" + budget + ", startDate="
				+ startDate + ", dueDate=" + dueDate + ", buildings=" + buildings + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
