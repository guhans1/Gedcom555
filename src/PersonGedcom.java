import java.lang.Math.*;
import java.util.*;
import java.io.*;
import java.text.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PersonGedcom {
	
	// TO WHOM IT MAY CONCERN:
	// There's a lot of functionality here,
	// useful, useless, and duplicated
	// should work but needs refactoring

	// INDI variables
	protected String ID = "NA";
	protected String name = "NA";
	protected Date birthdate;
	protected Date deathdate;
	protected boolean alive; // TODO remove boolean alive (deprecated by hasDied)
	protected int age = 9000; // If you see a 9000 year old: means getAge() doesn't work
	protected String gender = "NA"; // Could be boolean? Anyways it works
	protected boolean hasDied = false;
	protected String fams = "NA";
	protected String famc = "NA";
	protected String familyID = "NA";
	protected boolean valid = true;
	protected String invalidType = "";

	// Currently Unused
	// However some of them have getters/setters
	protected Date married;
	protected Date divorced;
	protected String husbandID = "NA";
	protected String husbandName = "NA";
	protected String wifeID = "NA";
	protected String wifeName = "NA";
	protected ArrayList<String> childrenID = new ArrayList<String>();
	protected String spouseID = "NA";
	// End unused variables
	
	public boolean isHasDied() {
		return hasDied;
	}

	public void setHasDied(boolean hasDied) {
		this.hasDied = hasDied;
	}
	
	// Deprecated, DONT USE
	public boolean isAlive() {
		if (this.deathdate != null) {
			alive = false;
			return alive;
		} else {
			alive = true;
			return alive;
		}
	}
	 
	public boolean checkIfAlive() {
		if (this.isHasDied()) {
			return false;
		} else {
			return true;
		}

	}

	public String getFams() {
		return fams;
	}

	public void setFams(String fams) {
		this.fams = fams;
	}

	public String getFamc() {
		return famc;
	}

	public void setFamc(String famc) {
		this.famc = famc;
	}

	public String getID() {
		return ID;
	}
	
	public void setID(String ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSpouseID() {
		return spouseID;
	}

	public void setSpouseID(String spouseID) {
		this.spouseID = spouseID;
	}

	public ArrayList<String> getChildrenID() {
		return childrenID;
	}

	public void addChild(String childID) {
		childrenID.add(childID);
	}

	public void removeChild(String childID) {
		childrenID.remove(childID);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getBirthDate() {
		return birthdate;
	}
	
	// Outputs Mar 10 1930
	public String getBirthDateAsString() {
		String longBirthDate = birthdate.toString();
		String[] tokens = longBirthDate.split("\\s+");
		String birthDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
		return birthDate;
	}
	
	public void setBirthDate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public void setBirthDate(int iYear, int iMonth, int iDay) {
		Calendar cal = Calendar.getInstance();
		cal.set(iYear, iMonth, iDay);
		this.birthdate = cal.getTime();
	}
	
	public Date getDeathDate() {
		if (!this.checkIfAlive()) {
			return deathdate;
		} else {
			return null;
		}
	}
	
	public String getDeathDateAsString() {
		if (!this.checkIfAlive()) {
			String longDeathDate = deathdate.toString();
			String[] tokens = longDeathDate.split("\\s+");
			String deathDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
			return deathDate;
		} else {
			return "";
		}

	}
	
	public void setDeathDate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public void setDeathDate(int iYear, int iMonth, int iDay) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(iYear, iMonth, iDay);
		this.deathdate = cal2.getTime();
	}
	
	// TODO check if leap years function properly in getAge()
	public int getAge() {
		if (this.isHasDied()) {
			return getAgeIfDead();
		} else {
			return getAgeIfAlive();
		}
	}
	
	// 2 functions for age from Alive and Dead
	// They both do similar calculations except:
	// Alive is now - birth
	// Dead is death - birth
	public int getAgeIfAlive() {
		
		// NOTE: This code is a bit messy but its a simple calculation
		Calendar now = Calendar.getInstance();
		Calendar dob = Calendar.getInstance();
		dob.setTime(birthdate);
		int year1 = now.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		int age = year1 - year2;
		int month1 = now.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);
		if (month2 > month1) {
			age--;
		} else if (month1 == month2) {
			int day1 = now.get(Calendar.DAY_OF_MONTH);
			int day2 = dob.get(Calendar.DAY_OF_MONTH);
			if (day2 > day1) {
				age--;
			}
		}

		return age;

	}

	public int getAgeIfDead() {

		Calendar dod = Calendar.getInstance();
		dod.setTime(deathdate);
		Calendar dob = Calendar.getInstance();
		dob.setTime(birthdate);
		int year1 = dod.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		int age = year1 - year2;
		int month1 = dod.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);
		if (month2 > month1) {
			age--;
		} else if (month1 == month2) {
			int day1 = dod.get(Calendar.DAY_OF_MONTH);
			int day2 = dob.get(Calendar.DAY_OF_MONTH);
			if (day2 > day1) {
				age--;
			}
		}

		return age;

	}

	// Since getAge calculates age, function is unused
	// Code is kept for testing purposes
	public void setAge(int age) {
		this.age = age;
	}

	public String getFamilyID() {
		return familyID;
	}

	public void setFamilyID(String familyID) {
		this.familyID = familyID;
	}
	
	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	public String getInvalidType() {
		return invalidType;
	}

	public void setInvalidType(String invalidType) {
		this.invalidType = invalidType;
	}

	@Override
	public String toString() {
		return ID;
	}

	// ===========================================
	// For family purposes (UNUSED)
	// ===========================================

	public Date getMarried() {
		return married;
	}

	public void setMarried(int iYear, int iMonth, int iDay) {
		Calendar cal3 = Calendar.getInstance();
		cal3.set(iYear, iMonth, iDay);
		this.married = cal3.getTime();
	}

	public Date getDivorced() {
		return divorced;
	}

	public void setDivorced(int iYear, int iMonth, int iDay) {
		Calendar cal4 = Calendar.getInstance();
		cal4.set(iYear, iMonth, iDay);
		this.divorced = cal4.getTime();
	}

	public String getHusbandID() {
		return husbandID;
	}

	public void setHusbandID(String husbandID) {
		this.husbandID = husbandID;
	}

	public String getHusbandName() {
		return husbandName;
	}

	public void setHusbandName(String husbandName) {
		this.husbandName = husbandName;
	}

	public String getWifeID() {
		return wifeID;
	}

	public void setWifeID(String wifeID) {
		this.wifeID = wifeID;
	}

	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}
	

}
