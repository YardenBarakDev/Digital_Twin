package com.ybdev.digitaltwin.items.objects;

public class Room {

	enum roomType {
		SHOES_ROOM, BEDROOM, LIVING_ROOM, CLOSET_ROOM, UTILITIES_ROOM, TOILET, SHOWER, KITCHEN, SAFE_ROOM
	}

	private String ID;
	private Double length;
	private Double width;
	private Double height;
	private int numOfWindows;
	private boolean airConditioner;
	private boolean isRoomReady;
	private roomType type;
	//{"ID":"1234" , "length":1.0 , "width":1.0 , "height":1.0 ,"numOfWindows":1 ,"airConditioner" : true , "isRoomReady" : true ,"type" : NULL}

	public Room() {
		// Empty constructor
	}

	public Room(String iD, Double length, Double width, Double height, int numOfWindows, boolean airConditioner,
			boolean isRoomReady, roomType type) {
		super();
		ID = iD;
		this.length = length;
		this.width = width;
		this.height = height;
		this.numOfWindows = numOfWindows;
		this.airConditioner = airConditioner;
		this.isRoomReady = isRoomReady;
		this.type = type;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
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

	public int getNumOfWindows() {
		return numOfWindows;
	}

	public void setNumOfWindows(int numOfWindows) {
		this.numOfWindows = numOfWindows;
	}

	public boolean isAirConditioner() {
		return airConditioner;
	}

	public void setAirConditioner(boolean airConditioner) {
		this.airConditioner = airConditioner;
	}

	public boolean isRoomReady() {
		return isRoomReady;
	}

	public void setRoomReady(boolean isRoomReady) {
		this.isRoomReady = isRoomReady;
	}

	public roomType getType() {
		return type;
	}

	public void setType(roomType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Room [ID=" + ID + ", length=" + length + ", width=" + width + ", height=" + height + ", numOfWindows="
				+ numOfWindows + ", airConditioner=" + airConditioner + ", isRoomReady=" + isRoomReady + ", type="
				+ type + "]";
	}

}
