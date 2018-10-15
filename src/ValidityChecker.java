import java.util.ArrayList;
import java.util.Date;

public class ValidityChecker {

	public boolean under150YearsOld(PersonGedcom person) {
		if (person.getAge() > 150) {
			return false;
		} else {

			return true;
		}
	}

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

	public boolean birthBeforeMarriage(PersonGedcom person, ArrayList<FamGedcom> families) {
		Date birthdate = person.getBirthDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family.equals(null)) {
			return true;
		} else {
			Date marriedDate = family.getMarDate();
			if (birthdate.before(marriedDate)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean birthBeforeDivorce(PersonGedcom person, ArrayList<FamGedcom> families) {
		Date birthdate = person.getBirthDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family.equals(null)) {
			return true;
		} else if (!family.isDivorced()) {
			return true;
		} else {
			Date divorcedDate = family.getDivDate();
			if (birthdate.before(divorcedDate)) {
				return true;
			} else {
				return false;
			}
		}
	}

	public boolean MarriageBeforeDivorce(PersonGedcom person, ArrayList<FamGedcom> families) {
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family.equals(null)) {
			return true;
		} else if (!family.isDivorced()) {
			return true;
		} else {
			Date marriedDate = family.getMarDate();
			Date divorcedDate = family.getDivDate();
			if (marriedDate.before(divorcedDate)) {
				return true;
			} else {
				return false;
			}
		}

	}

	public boolean MarriageBeforeDeath(PersonGedcom person, ArrayList<FamGedcom> families) {
		if(!person.isHasDied()) {
			return true;
		}
		Date deathdate = person.getDeathDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family.equals(null)) {
			return true;
		} else {
			Date marriedDate = family.getMarDate();
			if (marriedDate.before(deathdate)) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean DivorceBeforeDeath(PersonGedcom person, ArrayList<FamGedcom> families) {
		if(!person.isHasDied()) {
			return true;
		}
		Date deathdate = person.getDeathDate();
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family.equals(null)) {
			return true;
		} else if(family.isDivorced()){
			Date marriedDate = family.getMarDate();
			Date divorcedDate = family.getDivDate();
			if (marriedDate.before(deathdate)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	

}
