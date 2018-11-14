import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Person extends AbstractUnit {

	protected String name = "NA";
	protected Date birthdate;
	protected Date deathdate;
	protected int age = 9000;
	protected String gender = "NA";
	protected boolean dead = false;
	protected boolean alive = true;
	protected String fams = "NA"; // ID of Current Family (Legacy Code)
	protected String famc = "NA";
	protected ArrayList<Family> families = new ArrayList<Family>();
	protected ArrayList<Person> children = new ArrayList<Person>();
	protected ArrayList<Person> spouses = new ArrayList<Person>();
	protected ArrayList<String> childrenIDs = new ArrayList<String>();
	protected ArrayList<String> spousesIDs = new ArrayList<String>();
	
	// ============================================
	// NAME/GENDER
	// ============================================
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	// ============================================
	// END SECTION
	// ============================================

	
	
	// ============================================
	// AGE RELATED CODE
	// ============================================
	public Date getBirthdate() {
		return birthdate;
	}

	// Outputs Mar 10 1930
	public String getBirthDateAsString() {
		String longBirthDate = birthdate.toString();
		String[] tokens = longBirthDate.split("\\s+");
		String birthDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
		return birthDate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public void setBirthDate(int iYear, int iMonth, int iDay) {
		Calendar cal = Calendar.getInstance();
		cal.set(iYear, iMonth, iDay);
		this.birthdate = cal.getTime();
	}

	public Date getDeathdate() {
		return deathdate;
	}

	public String getDeathDateAsString() {
		if (this.isDead()) {
			String longDeathDate = deathdate.toString();
			String[] tokens = longDeathDate.split("\\s+");
			String deathDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
			return deathDate;
		} else {
			return "";
		}
	}

	public void setDeathdate(Date deathdate) {
		this.deathdate = deathdate;
	}

	public void setDeathDate(int iYear, int iMonth, int iDay) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(iYear, iMonth, iDay);
		this.deathdate = cal2.getTime();
	}

	// SET AGE SHOULD ONLY BE USED FOR DEBUGGING/ NOT FOR PRODUCTION CODE
	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		if (this.isDead()) {
			return getAgeIfDead();
		} else {
			return getAgeIfAlive();
		}
	}

	public int getAgeIfAlive() {
		return HelperFunctions.differenceInDatesInYears(birthdate, Calendar.getInstance().getTime());
	}

	public int getAgeIfDead() {
		return HelperFunctions.differenceInDatesInYears(birthdate, deathdate);
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}
	
	public boolean isAlive() {
		return !dead;
	}

	// Don't use (Only for debugging)
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	
	// ============================================
	// END SECTION
	// ============================================

	
	
	

	// ============================================
	// FAMILY SECTION
	// ============================================
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

	public ArrayList<Family> getFamilies() {
		return families;
	}

	public Family getCurrentFamily() {
		for(Family family : families) {
			if(!family.isDivorced()) {
				return family;
			}
		}
		return null;
	}
	
	public void setFamilies(ArrayList<Family> families) {
		this.families = families;
	}

	public void addFamily(Family family) {
		families.add(family);
	}
	
	public void removeFamily(Family family) {
		families.remove(family);
	}

	
	public ArrayList<Person> getChildren() {
		this.setChildren();
		return children;
	}
	
	// Provides children from all marriages

	public void setChildren() {
		for(Family family : families) {
			for(Person child : family.getChildren()) {
				children.add(child);
			}
		}
	}
	
	public ArrayList<String> getChildrenIDs() {
		this.setChildren();
		for(Person person : children) {
			childrenIDs.add(person.getID());
		}
		Set<String> hs = new HashSet<>();
		hs.addAll(childrenIDs);
		childrenIDs.clear();
		childrenIDs.addAll(hs);
		return childrenIDs;
	}
	
	public ArrayList<Person> getSpouses() {
		this.setSpouses();
		return spouses;
	}
	
	// Provides children from all marriages

	public void setSpouses() {
		for(Family family : families) {
			if(this.getGender().equals("M")) {
				spouses.add(family.getWife());
			} else {
				spouses.add(family.getHusband());
			}
		}
	}
	
	public ArrayList<String> getSpousesIDs() {
		this.setSpouses();
		for(Person person : spouses) {
			spousesIDs.add(person.getID());
		}
		Set<String> hs = new HashSet<>();
		hs.addAll(spousesIDs);
		spousesIDs.clear();
		spousesIDs.addAll(hs);
		return spousesIDs;
	}
	// ============================================
	// END SECTION
	// ============================================
}
