package com.ybdev.digitaltwin.items.objects;

public class Apartment {

	public enum apartmentOrientation {
		EAST, WEST, NORTH, SOUTH
	}
	private String parentID;
	private String ID;
	private boolean isVilla;
	private Double length;
	private Double width;

	public String getParentID() {
		return parentID;
	}

	public void setParentID(String parentID) {
		parentID = parentID;
	}

	private Double height;
	private int numOfRooms;
	private Double price;
	private int numOfShowers;
	private int numOfToilets;
	private boolean haveBalcony;
	private boolean haveSeaView;
	private apartmentOrientation orientation;
	private boolean isCentralAirCon;
	private boolean haveChimney;
	private boolean isApartmentReady;
	
	public Apartment() {
		//Empty constructor
	}
	
	
	public Apartment(String iD, String parentID,boolean isVilla, Double length, Double width, Double height, int numOfRooms,
			Double price, int numOfShowers, int numOfToilets, boolean haveBalcony, boolean haveSeaView,
			apartmentOrientation orientation, boolean isCentralAirCon, boolean haveChimney, boolean isApartmentReady) {
		super();
		ID = iD;
		this.parentID=parentID;
		this.isVilla = isVilla;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numOfRooms = numOfRooms;
		this.price = price;
		this.numOfShowers = numOfShowers;
		this.numOfToilets = numOfToilets;
		this.haveBalcony = haveBalcony;
		this.haveSeaView = haveSeaView;
		this.orientation = orientation;
		this.isCentralAirCon = isCentralAirCon;
		this.haveChimney = haveChimney;
		this.isApartmentReady = isApartmentReady;
	}


	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public boolean isVilla() {
		return isVilla;
	}
	public void setVilla(boolean isVilla) {
		this.isVilla = isVilla;
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
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public int getNumOfRooms() {
		return numOfRooms;
	}
	public void setNumOfRooms(int numOfRooms) {
		this.numOfRooms = numOfRooms;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public int getNumOfShowers() {
		return numOfShowers;
	}
	public void setNumOfShowers(int numOfShowers) {
		this.numOfShowers = numOfShowers;
	}
	public int getNumOfToilets() {
		return numOfToilets;
	}
	public void setNumOfToilets(int numOfToilets) {
		this.numOfToilets = numOfToilets;
	}
	public boolean isHaveBalcony() {
		return haveBalcony;
	}
	public void setHaveBalcony(boolean haveBalcony) {
		this.haveBalcony = haveBalcony;
	}
	public boolean isHaveSeaView() {
		return haveSeaView;
	}
	public void setHaveSeaView(boolean haveSeaView) {
		this.haveSeaView = haveSeaView;
	}
	public apartmentOrientation getOrientation() {
		return orientation;
	}
	public void setOrientation(apartmentOrientation orientation) {
		this.orientation = orientation;
	}
	public boolean isCentralAirCon() {
		return isCentralAirCon;
	}
	public void setCentralAirCon(boolean isCentralAirCon) {
		this.isCentralAirCon = isCentralAirCon;
	}
	public boolean isHaveChimney() {
		return haveChimney;
	}
	public void setHaveChimney(boolean haveChimney) {
		this.haveChimney = haveChimney;
	}
	public boolean isApartmentReady() {
		return isApartmentReady;
	}
	public void setApartmentReady(boolean isApartmentReady) {
		this.isApartmentReady = isApartmentReady;
	}


	@Override
	public String toString() {
		return "Apartment [ID=" + ID + ", isVilla=" + isVilla + ", length=" + length + ", width=" + width + ", height="
				+ height + ", numOfRooms=" + numOfRooms + ", price=" + price + ", numOfShowers=" + numOfShowers
				+ ", numOfToilets=" + numOfToilets + ", haveBalcony=" + haveBalcony + ", haveSeaView=" + haveSeaView
				+ ", orientation=" + orientation + ", isCentralAirCon=" + isCentralAirCon + ", haveChimney="
				+ haveChimney + ", isApartmentReady=" + isApartmentReady + "]";
	}
	
	
	

}
