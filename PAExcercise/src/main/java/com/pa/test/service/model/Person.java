package com.pa.test.service.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	
	private String id; 
	private String placeOfBirth;
	private float height;
	private String familyName;
	private String givenName;
	private String middleNames;
	private String twitterId;
	private Date dateOfBirth;
	private Date dateOfDeath;
	
	
	/**
	 * Constructor
	 * @param id
	 * @param placeOfBirth
	 * @param height
	 * @param familyName
	 * @param givenName
	 * @param middleNames
	 * @param twitterId
	 * @param dateOfBirth
	 * @param dateOfDeath
	 */
	public Person(String id, String placeOfBirth, float height,
			String familyName, String givenName, String middleNames,
			String twitterId, Date dateOfBirth, Date dateOfDeath) {
		super();
		this.id = id;
		this.placeOfBirth = placeOfBirth;
		this.height = height;
		this.familyName = familyName;
		this.givenName = givenName;
		this.middleNames = middleNames;
		this.twitterId = twitterId;
		this.dateOfBirth = dateOfBirth;
		this.dateOfDeath = dateOfDeath;
	}

	public Person() {
	}
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the placeOfBirth
	 */
	public String getPlaceOfBirth() {
		return placeOfBirth;
	}
	/**
	 * @param placeOfBirth the placeOfBirth to set
	 */
	public void setPlaceOfBirth(String placeOfBirth) {
		this.placeOfBirth = placeOfBirth;
	}
	/**
	 * @return the height
	 */
	public float getHeight() {
		return height;
	}
	/**
	 * @param height the height to set
	 */
	public void setHeight(float height) {
		this.height = height;
	}
	/**
	 * @return the familyName
	 */
	public String getFamilyName() {
		return familyName;
	}
	/**
	 * @param familyName the familyName to set
	 */
	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}
	/**
	 * @return the givenName
	 */
	public String getGivenName() {
		return givenName;
	}
	/**
	 * @param givenName the givenName to set
	 */
	public void setGivenName(String givenName) {
		this.givenName = givenName;
	}
	/**
	 * @return the middleNames
	 */
	public String getMiddleNames() {
		return middleNames;
	}
	/**
	 * @param middleNames the middleNames to set
	 */
	public void setMiddleNames(String middleNames) {
		this.middleNames = middleNames;
	}
	/**
	 * @return the twitterId
	 */
	public String getTwitterId() {
		return twitterId;
	}
	/**
	 * @param twitterId the twitterId to set
	 */
	public void setTwitterId(String twitterId) {
		this.twitterId = twitterId;
	}
	/**
	 * @return the dateOfBirth
	 */
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	/**
	 * @param dateOfBirth the dateOfBirth to set
	 */
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	/**
	 * @return the dateOfDeath
	 */
	public Date getDateOfDeath() {
		return dateOfDeath;
	}
	/**
	 * @param dateOfDeath the dateOfDeath to set
	 */
	public void setDateOfDeath(Date dateOfDeath) {
		this.dateOfDeath = dateOfDeath;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dateOfBirth == null) ? 0 : dateOfBirth.hashCode());
		result = prime * result
				+ ((dateOfDeath == null) ? 0 : dateOfDeath.hashCode());
		result = prime * result
				+ ((familyName == null) ? 0 : familyName.hashCode());
		result = prime * result
				+ ((givenName == null) ? 0 : givenName.hashCode());
		result = prime * result + Float.floatToIntBits(height);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((middleNames == null) ? 0 : middleNames.hashCode());
		result = prime * result
				+ ((placeOfBirth == null) ? 0 : placeOfBirth.hashCode());
		result = prime * result
				+ ((twitterId == null) ? 0 : twitterId.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (dateOfBirth == null) {
			if (other.dateOfBirth != null)
				return false;
		} else if (!dateOfBirth.equals(other.dateOfBirth))
			return false;
		if (dateOfDeath == null) {
			if (other.dateOfDeath != null)
				return false;
		} else if (!dateOfDeath.equals(other.dateOfDeath))
			return false;
		if (familyName == null) {
			if (other.familyName != null)
				return false;
		} else if (!familyName.equals(other.familyName))
			return false;
		if (givenName == null) {
			if (other.givenName != null)
				return false;
		} else if (!givenName.equals(other.givenName))
			return false;
		if (Float.floatToIntBits(height) != Float.floatToIntBits(other.height))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (middleNames == null) {
			if (other.middleNames != null)
				return false;
		} else if (!middleNames.equals(other.middleNames))
			return false;
		if (placeOfBirth == null) {
			if (other.placeOfBirth != null)
				return false;
		} else if (!placeOfBirth.equals(other.placeOfBirth))
			return false;
		if (twitterId == null) {
			if (other.twitterId != null)
				return false;
		} else if (!twitterId.equals(other.twitterId))
			return false;
		return true;
	}
}