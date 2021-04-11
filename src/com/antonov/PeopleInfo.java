package com.antonov;

import java.util.Date;

public class PeopleInfo {

	private String name;
	private String surname;
	private String patronymic;
	private Date entryDate;
	private boolean isEntry;
	private boolean isGuest;
	private String pathToImage;

	
	public boolean isGuest() {
		return isGuest;
	}

	public void setGuest(boolean isGuest) {
		this.isGuest = isGuest;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPatronymic() {
		return patronymic;
	}

	public void setPatronymic(String patronymic) {
		this.patronymic = patronymic;
	}

	public Date getEntryDate() {
		return entryDate;
	}

	public void setEntryDate(Date entryDate) {
		this.entryDate = entryDate;
	}

	public boolean isEntry() {
		return isEntry;
	}

	public void setEntry(boolean isEntry) {
		this.isEntry = isEntry;
	}

	public String getPathToImage() {
		return pathToImage;
	}

	public void setPathToImage(String pathToImage) {
		this.pathToImage = pathToImage;
	}

	@Override
	public String toString() {
		return "PeopleInfo [name=" + name + ", surname=" + surname + ", patronymic=" + patronymic + ", entryDate="
				+ entryDate + ", isEntry=" + isEntry + ", isGuest=" + isGuest + ", pathToImage=" + pathToImage + "]";
	}
	
	

}
