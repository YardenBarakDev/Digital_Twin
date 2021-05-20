package com.ybdev.digitaltwin.items.facilities;


import com.ybdev.digitaltwin.items.objects.Facility;

public class BombShelter extends Facility {

	private Double height;
	private Double length;
	private double width;
	private int capacity;

	public BombShelter() {
		// Empty constructor
	}

	public BombShelter(String id, boolean isReady, Double height, Double length, double width, int capacity) {
		super(id, isReady);
		this.height = height;
		this.length = length;
		this.width = width;
		this.capacity = capacity;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	public Double getLength() {
		return length;
	}

	public void setLength(Double length) {
		this.length = length;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	@Override
	public String toString() {
		return "BombShelter [height=" + height + ", length=" + length + ", width=" + width + ", capacity=" + capacity
				+ "]";
	}

}
