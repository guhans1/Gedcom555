import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ValidityChecker {

	// Sprint 1
	// 100% works
	public boolean under150YearsOld(PersonGedcom person) {
		if (person.getAge() > 150) {
			return false;
		} else {

			return true;
		}
	}

	// Sprint 1
	// 100% works
	public boolean birthBeforeDeath(PersonGedcom person) {
		if (person.isHasDied()) {
			if (person.getBirthDate().before(person.getDeathDate())) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}

	// Sprint 1
	// V2 Fixed
	public boolean birthBeforeMarriage(PersonGedcom person, ArrayList<FamGedcom> families) {
		Date birthdate = person.getBirthDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				Date marriedDate = family.getMarDate();
				if (!birthdate.before(marriedDate)) {
					return false;
				}
			}
		}
		return true;
	}

	// Sprint 1
	// V2 Fixed
	public boolean birthBeforeDivorce(PersonGedcom person, ArrayList<FamGedcom> families) {
		Date birthdate = person.getBirthDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				if (family.isDivorced()) {
					Date divorcedDate = family.getDivDate();
					if (!birthdate.before(divorcedDate)) {
						return false;
					}
				}

			}
		}
		return true;
	}

	// Sprint 1
	// V2 Fixed
	public boolean marriageBeforeDivorce(PersonGedcom person, ArrayList<FamGedcom> families) {
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				if (family.isDivorced()) {
					Date marDate = family.getMarDate();
					Date divorcedDate = family.getDivDate();
					if (!marDate.before(divorcedDate)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// Sprint 1
	// V2 Fixed
	public boolean marriageBeforeDeath(PersonGedcom person, ArrayList<FamGedcom> families) {
		if (!person.isHasDied()) {
			return true;
		}
		Date deathdate = person.getDeathDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				Date marriedDate = family.getMarDate();
				if (!marriedDate.before(deathdate)) {
					return false;
				}
			}
		}
		return true;
	}

	// Sprint 1
	// V2 Fixed
	public boolean divorceBeforeDeath(PersonGedcom person, ArrayList<FamGedcom> families) {
		if (!person.isHasDied()) {
			return true;
		}
		Date deathdate = person.getDeathDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				if (family.isDivorced()) {
					Date divorcedDate = family.getDivDate();
					if (!divorcedDate.before(deathdate)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	// Sprint 2
	public boolean marriageAfter14YearsOld(PersonGedcom person, ArrayList<FamGedcom> families) {
		Date birthdate = person.getBirthdate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				Date marriedDate = family.getMarDate();
	
				int age = HelperFunctions.differenceInDatesInYears(birthdate, marriedDate);
				
				if (age < 14) {
					return false;
				}
			}
		}
		return true;
	}
	
	// Sprint 2
	// Dushyanth please debug this; make sure it works
	public boolean correctGenderForRole(PersonGedcom person, ArrayList<FamGedcom> families, ArrayList<PersonGedcom> people) {
		String gender = person.getGender();
		if(gender.equals("M")) {
			String famID = person.getFams();
			FamGedcom family = null;
			for (FamGedcom fams : families) {
				if (fams.getFamID().equals(famID)) {
					family = fams;
					String wifeID = family.getWifeID();
					for(PersonGedcom wife : people) {
						if(wife.getID().equals(wifeID)) {
							if(wife.getGender().equals(("M"))) {
								return false;
							}
						}
					}
				}
			}
		} else {
			String famID = person.getFams();
			FamGedcom family = null;
			for (FamGedcom fams : families) {
				if (fams.getFamID().equals(famID)) {
					family = fams;
					String husbID = family.getHusbID();
					for(PersonGedcom husb : people) {
						if(husb.getID().equals(husbID)) {
							if(husb.getGender().equals(("F"))) {
								return false;
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	// Use overloaded method, (probably) don't use this one!
	public void checkValidity(PersonGedcom person, ArrayList<FamGedcom> families) {
		if (!under150YearsOld(person)) {
			person.setValid(false);
			person.setInvalidType("| Over 150 years old");
		}
		if (!birthBeforeDeath(person)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Birth");
			person.setInvalidType(invalidType);
		}
		if (!birthBeforeMarriage(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Marriage before Birth");
			person.setInvalidType(invalidType);
		}
		if (!birthBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Divorce before Birth");
			person.setInvalidType(invalidType);
		}
		if (!marriageBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Divorce before Marriage");
			person.setInvalidType(invalidType);
		}
		if (!marriageBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Marriage");
			person.setInvalidType(invalidType);
		}
		if (!divorceBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Divorce");
			person.setInvalidType(invalidType);
		}
		if (!marriageAfter14YearsOld(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Marriage before 14");
			person.setInvalidType(invalidType);
		}
		
		String validType = person.getInvalidType();
		if (!validType.isEmpty()) {
			validType = validType.substring(1).trim();
		}
		person.setInvalidType(validType);
	}

	// Overloaded Method : Use this if unsure which method to use
	public void checkValidity(PersonGedcom person, ArrayList<FamGedcom> families, ArrayList<PersonGedcom> people) {
		if (!under150YearsOld(person)) {
			person.setValid(false);
			person.setInvalidType("| Over 150 years old");
			System.out.println("ERROR: " + "INDIVIDUAL: " + "US07: " + person.toString() + " is over 150 years old" + " (Age:" + person.getAge() + ")" );
		}
		if (!birthBeforeDeath(person)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Birth");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "INDIVIDUAL: " + "US03: " + person.toString() + " died (" + person.getDeathDateAsString() + ")" + " before birth" + " (" + person.getBirthDateAsString() + ")");
		}
		if (!birthBeforeMarriage(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Marriage before Birth");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US02: " + person.toString() + " married before being born" );
		}
		// ? Doesn't seem to have a user story
		if (!birthBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Divorce before Birth");
			person.setInvalidType(invalidType);
			// System.out.println("US02: " + person.toString() + " died before getting divorced" );
		}
		if (!marriageBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Divorce before Marriage");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US04: " + person.toString() + " divorced before getting married" );
		}
		if (!marriageBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Marriage");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US05: " + person.toString() + " died before getting married" );
		}
		if (!divorceBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Death before Divorce");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US06: " + person.toString() + " died before getting divorced" );
		}
		if (!marriageAfter14YearsOld(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Marriage before 14");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US10: " + person.toString() + " married before age 14" );
		}
		if (!correctGenderForRole(person, families, people)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("| Incorrect Gender Role");
			person.setInvalidType(invalidType);
			System.out.println("ERROR: " + "FAMILY: " + "US21: " + person.toString() + " has incorrect gender role" );
		}
		
		String validType = person.getInvalidType();
		if (!validType.isEmpty()) {
			validType = validType.substring(1).trim();
		}
		person.setInvalidType(validType);
	}

}

