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
		if (family == null) {
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
		if (family == null) {
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

	public boolean marriageBeforeDivorce(PersonGedcom person, ArrayList<FamGedcom> families) {
		String famID = person.getFams();
		FamGedcom family = null;
		for (FamGedcom fams : families) {
			if (fams.getFamID().equals(famID)) {
				family = fams;
				break;
			}
		}
		if (family == null) {
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
				break;
			}
		}
		if (family == null) {
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
				break;
			}
		}
		if (family == null) {
			return true;
		} else if (family.isDivorced()) {
			Date divorcedDate = family.getDivDate();
			if (divorcedDate.before(deathdate)) {
				return true;
			} else {
				return false;
			}
		} else {
			return true;
		}
	}
	
	public void checkValidity(PersonGedcom person, ArrayList<FamGedcom> families) {
		if(!under150YearsOld(person)) {
			person.setValid(false);
			person.setInvalidType("Over 150 years old");
		}
		if(!birthBeforeDeath(person)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Death before Birth");
			person.setInvalidType(invalidType);
		}
		if(!birthBeforeMarriage(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Marriage before Birth");
			person.setInvalidType(invalidType);
		}
		if(!birthBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Divorce before Birth");
			person.setInvalidType(invalidType);
		}
		if(!marriageBeforeDivorce(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Divorce before Marriage");
			person.setInvalidType(invalidType);
		}
		if(!marriageBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Death before Marriage");
			person.setInvalidType(invalidType);
		}
		if(!divorceBeforeDeath(person, families)) {
			person.setValid(false);
			String invalidType = person.getInvalidType().concat("Death before Divorce");
			person.setInvalidType(invalidType);
		}
	}
	
}	
	/*
	// marriage after 14
	public boolean MarriageAfter14(PersonGedcom person, ArrayList<FamGedcom> families) {
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
			if (family.getNumberOfYearsMarried() - person.getAge() > 14) {
				return true;
			} else {
				return false;
			}
		}
	}
	
	public boolean ListDeceased(PersonGedcom person) {
		if (person.isHasDied()
		    {
			return person.getID();
		}else System.out.println("person is alive");

} */
	
	
	