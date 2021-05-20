package com.ybdev.digitaltwin.items.facilities;

import com.ybdev.digitaltwin.items.objects.Facility;

import java.util.Arrays;


public class Elevator extends Facility {
	private int capacity;
	private int[] floors;
	private Double maxWeight;
	private boolean isShabbat;
	private Double height;
	private Double width;
	private Double length;

	public Elevator() {
		// Empty constructor
	}

	public Elevator(String id, boolean isReady, int capacity, int[] floors, Double maxWeight, boolean isShabbat,
			Double height, Double width, Double length) {
		super(id, isReady);
		this.capacity = capacity;
		this.floors = floors;
		this.maxWeight = maxWeight;
		this.isShabbat = isShabbat;
		this.height = height;
		this.width = width;
		this.length = length;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public int[] getFloors() {
		return floors;
	}

	public void setFloors(int[] floors) {
		this.floors = floors;
	}

	public Double getMaxWeight() {
		return maxWeight;
	}

	public void setMaxWeight(Double maxWeight) {
		this.maxWeight = maxWeight;
	}

	public boolean isShabbat() {
		return isShabbat;
	}

	public void setShabbat(boolean isShabbat) {
		this.isShabbat = isShabbat;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getWidth() {
		return width;
	}

	public void setWidth(Double width) {
		this.width = width;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "Elevator [capacity=" + capacity + ", floors=" + Arrays.toString(floors) + ", maxWeight=" + maxWeight
				+ ", isShabbat=" + isShabbat + ", height=" + height + ", width=" + width + ", length=" + length + "]";
	}

}
