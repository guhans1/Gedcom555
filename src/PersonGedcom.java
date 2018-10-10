import java.lang.Math.*;
import java.util.*;
import java.io.*;
import java.text.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class PersonGedcom {

	// INDI variables
	protected String ID = "NA";
	protected String name = "NA";
	protected Date birthdate;
	protected Date deathdate;
	protected boolean alive;
	protected int age = 1000;
	protected String gender = "NA";
	protected String spouseID = "NA";
	protected ArrayList<String> childrenID = new ArrayList<String>();
	
	protected String fams = "NA";
	protected String famc = "NA";

	// FAM variables
	protected String familyID = "NA";
	
	
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

	protected Date married;
	protected Date divorced;
	protected String husbandID = "NA";
	protected String husbandName = "NA";
	protected String wifeID = "NA";
	protected String wifeName = "NA";
	// TODO Family Children functionality

	// ===========================================
	// for individual purposes
	// ===========================================

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
		// TODO age functionality
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public String getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthDate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getDeathdate() {
		return deathdate;
	}

	public void setDeathDate(Date deathdate) {
		this.deathdate = deathdate;
	}

	// No set method, calculates directly from deathdate
	public boolean isAlive() {
		if (this.deathdate != null) {
			alive = false;
			return alive;
		} else {
			alive = true;
			return alive;
		}
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

	public void setBirthDate(int iYear, int iMonth, int iDay) {
		Calendar cal = Calendar.getInstance();
		cal.set(iYear, iMonth, iDay);
		this.birthdate = cal.getTime();
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

	public String getBirthDateAsString() {
		return birthdate.toString();
	}

	public void setDeathDate(int iYear, int iMonth, int iDay) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(iYear, iMonth, iDay);
		this.deathdate = cal2.getTime();
	}

	public Date getDeathDate() {
		return deathdate;
	}

	// TODO fix deathdate hack
	public String getDeathDateAsString() {
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(deathdate);
		int year = cal2.get(Calendar.YEAR);
		if (year > 3000) {
			return "";
		} else {
			return deathdate.toString();
		}

	}

	// TODO fix deathdate hack 2
	public boolean checkIfAlive() {
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(deathdate);
		int year = cal2.get(Calendar.YEAR);
		if (year > 3000) {
			return true;
		} else {
			return false;
		}

	}

	// TODO fix deathdate hack 3
	public int getAgeHack() {
	
		Calendar now = Calendar.getInstance();

		Calendar cald = Calendar.getInstance();
		cald.setTime(this.deathdate);
		int dyear = cald.get(Calendar.YEAR);

		Calendar calb = Calendar.getInstance();
		calb.setTime(this.birthdate);
		
		

		if (dyear > 3000) {
			int year1 = now.get(Calendar.YEAR);
			int year2 = calb.get(Calendar.YEAR);
			int age = year1 - year2;
			int month1 = now.get(Calendar.MONTH);
			int month2 = calb.get(Calendar.MONTH);
			if (month2 > month1) {
				age--;
			} else if (month1 == month2) {
				int day1 = now.get(Calendar.DAY_OF_MONTH);
				int day2 = calb.get(Calendar.DAY_OF_MONTH);
				if (day2 > day1) {
					age--;
				}
			}

		} else {
			int year1 = cald.get(Calendar.YEAR);
			int year2 = calb.get(Calendar.YEAR);
			int age = year1 - year2;
			int month1 = cald.get(Calendar.MONTH);
			int month2 = calb.get(Calendar.MONTH);
			if (month2 > month1) {
				age--;
			} else if (month1 == month2) {
				int day1 = cald.get(Calendar.DAY_OF_MONTH);
				int day2 = calb.get(Calendar.DAY_OF_MONTH);
				if (day2 > day1) {
					age--;
				}
			}
		}

		return age;
	}
	
	// TODO refactor this horrific code in getAgeIfAlive
	public int getAgeIfAlive() {
		
		if(this.checkIfAlive()) {
			Calendar now = Calendar.getInstance();
			Calendar calb = Calendar.getInstance();
			calb.setTime(this.birthdate);
			int year1 = now.get(Calendar.YEAR);
			int year2 = calb.get(Calendar.YEAR);
			int age = year1 - year2;
			int month1 = now.get(Calendar.MONTH);
			int month2 = calb.get(Calendar.MONTH);
			if (month2 > month1) {
				age--;
			} else if (month1 == month2) {
				int day1 = now.get(Calendar.DAY_OF_MONTH);
				int day2 = calb.get(Calendar.DAY_OF_MONTH);
				if (day2 > day1) {
					age--;
				}
			}
			return age;
		} else {
			Calendar calb = Calendar.getInstance();
			calb.setTime(this.birthdate);
			Calendar cald = Calendar.getInstance();
			cald.setTime(this.deathdate);
			
			
			int year1 = cald.get(Calendar.YEAR);
			int year2 = calb.get(Calendar.YEAR);
			int age = year1 - year2;
			int month1 = cald.get(Calendar.MONTH);
			int month2 = calb.get(Calendar.MONTH);
			if (month2 > month1) {
				age--;
			} else if (month1 == month2) {
				int day1 = cald.get(Calendar.DAY_OF_MONTH);
				int day2 = calb.get(Calendar.DAY_OF_MONTH);
				if (day2 > day1) {
					age--;
				}
			}
			return age;
		}
	}
	

	public String getFamilyID() {
		return familyID;
	}

	public void setFamilyID(String familyID) {
		this.familyID = familyID;
	}

	// ===========================================
	// For family purposes
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

	@Override
	public String toString() {
		return ID;

	}

}