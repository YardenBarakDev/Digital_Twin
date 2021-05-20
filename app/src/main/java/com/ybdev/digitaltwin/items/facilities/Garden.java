package com.ybdev.digitaltwin.items.facilities;


import com.ybdev.digitaltwin.items.objects.Facility;

public class Garden extends Facility {
	private Double length;
	private Double width;
	private int numOfPlants;
	private Double maintenanceCost;

	public Garden() {
		// EmptyConstructor
	}

	public Garden(String id, boolean isReady, Double length, Double width, int numOfPlants, Double maintenanceCost) {
		super(id, isReady);
		this.length = length;
		this.width = width;
		this.numOfPlants = numOfPlants;
		this.maintenanceCost = maintenanceCost;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public int getNumOfPlants() {
		return numOfPlants;
	}

	public void setNumOfPlants(int numOfPlants) {
		this.numOfPlants = numOfPlants;
	}

	public Double getMaintenanceCost() {
		return maintenanceCost;
	}

	public void setMaintenanceCost(Double maintenanceCost) {
		this.maintenanceCost = maintenanceCost;
	}

	@Override
	public String toString() {
		return "Garden [length=" + length + ", width=" + width + ", numOfPlants=" + numOfPlants + ", maintenanceCost="
				+ maintenanceCost + "]";
	}

	
}
