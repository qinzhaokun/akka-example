package com.ebay;

public class Work {
	private final int start;
	private final int nrOfElement;
	
	public Work(int start, int nrOfElement){
		this.start = start;
		this.nrOfElement = nrOfElement;
	}
	
	public int getStart(){
		return start;
	}
	
	public int getNrOfElement(){
		return nrOfElement;
	}
}
