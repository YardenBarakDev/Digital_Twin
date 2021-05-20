package com.ybdev.digitaltwin.items.facilities;


import com.ybdev.digitaltwin.items.objects.Facility;

public class Lobby extends Facility {
	private String guard;
	private int numOfMailBoxes;

	public Lobby() {
		// Empty Constructor
	}

	public Lobby(String id, boolean isReady, String guard, int numOfMailBoxes) {
		super(id, isReady);
		this.guard = guard;
		this.numOfMailBoxes = numOfMailBoxes;
	}

	public String getGuard() {
		return guard;
	}

	public void setGuard(String guard) {
		this.guard = guard;
	}

	public int getNumOfMailBoxes() {
		return numOfMailBoxes;
	}

	public void setNumOfMailBoxes(int numOfMailBoxes) {
		this.numOfMailBoxes = numOfMailBoxes;
	}

	@Override
	public String toString() {
		return "Lobby [guard=" + guard + ", numOfMailBoxes=" + numOfMailBoxes + "]";
	}
	

}
