package com.itcr.datastructures;

public class Contact {
	private long id;
	private String nameContact;
	private String description;
	private String phoneOffice;
	private String cell;
	private String mail;
	//private String service; ---> poner un fk en la tabla con el id del servicio
	
	public long getID(){
		return id;		
	}
	
	public void setID(long id){
		this.id = id;
	}
	
	public String getNameContact(){
		return nameContact;
	}
	
	public void setNameContact(String name){
		this.nameContact = name;
	}
	
	public String getDescription(){
		return description;
	}
	
	public void setDescription(String description){
		this.description = description;
	}
	
	public String getPhoneOffice(){
		return phoneOffice;
	}
	
	public void setPhoneOffice(String phone){
		this.phoneOffice = phone;
	}
	
	public String getCell(){
		return cell;
	}
	
	public void setCell(String cell){
		this.cell = cell;
	}
	
	public String getMail(){
		return mail;
		
	}
	
	public void setMail(String mail){
		this.mail = mail;
	}

	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString(){
		return nameContact + description + phoneOffice + cell + mail;
	}
}
