package com.ybdev.digitaltwin.items.facilities;


import com.ybdev.digitaltwin.items.objects.Facility;

public class SwimmingPool extends Facility {
	private Double length;
	private Double width;
	private Double depth;
	private String lifeGuard;
	private Double waterVolume;
	private int numOfChairs;
	private int numOfBeds;

	public SwimmingPool() {
		// Empty constructor
	}

	public SwimmingPool(String iD, boolean isReady, Double length, Double width, Double depth, String lifeGuard,
			Double waterVolume, int numOfChairs, int numOfBeds) {
		super(iD, isReady);
		this.length = length;
		this.width = width;
		this.depth = depth;
		this.lifeGuard = lifeGuard;
		this.waterVolume = waterVolume;
		this.numOfChairs = numOfChairs;
		this.numOfBeds = numOfBeds;
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

	public Double getDepth() {
		return depth;
	}

	public void setDepth(Double depth) {
		this.depth = depth;
	}

	public String getLifeGuard() {
		return lifeGuard;
	}

	public void setLifeGuard(String lifeGuard) {
		this.lifeGuard = lifeGuard;
	}

	public Double getWaterVolume() {
		return waterVolume;
	}

	public void setWaterVolume(Double waterVolume) {
		this.waterVolume = waterVolume;
	}

	public int getNumOfChairs() {
		return numOfChairs;
	}

	public void setNumOfChairs(int numOfChairs) {
		this.numOfChairs = numOfChairs;
	}

	public int getNumOfBeds() {
		return numOfBeds;
	}

	public void setNumOfBeds(int numOfBeds) {
		this.numOfBeds = numOfBeds;
	}

}
