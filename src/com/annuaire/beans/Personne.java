package com.annuaire.beans;

public class Personne {
	private String 	firstName 	= "",
					lastName 	= "",	
					telNum 		= "", 
					adress 		= "", 
					email 		= "", 
					comment 	= "",
					age 		= "";

	
	public Personne(String firstName, String lastName, String telNum, String adress, String email, String comment, String age) {
		this.firstName 	= firstName;
		this.lastName 	= lastName;
		this.telNum 	= telNum; 
		this.adress 	= adress;
		this.email 		= email; 
		this.comment 	= comment;
		this.age 		= age;
	}

	public Personne() {	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getTelNum() {
		return telNum;
	}
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	public String getAdress() {
		return adress;
	}
	public void setAdress(String adress) {
		this.adress = adress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
}
