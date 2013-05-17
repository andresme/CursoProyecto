package com.itcr.datastructures;

public class Service {
	private long id;
	private String name;
	private String information;
	private String website;

	public long getID(){
		return id;
	}

	public void setID(long id){
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public String getWeb_Site() {
		return website;
	}

	public void setWeb_Site(String website) {
		this.website = website;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return name + information + website;
	}

}

