package com.ybdev.digitaltwin.items.objects;

public class Facility {
	private String ID;
	private boolean isReady;
	private String type;


	public Facility() {
		// Empty constructor
	}

	public Facility(String iD, boolean isReady) {
		super();
		ID = iD;
		this.isReady = isReady;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Facility{" +
				"ID='" + ID + '\'' +
				", isReady=" + isReady +
				", type='" + type + '\'' +
				'}';
	}
}
