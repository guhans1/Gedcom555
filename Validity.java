import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Validity {

	// US01 UNFINISHED

	public boolean birthBeforeMarriage(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (!person.getBirthdate().before(family.getMarDate())) {
				family.setValid(false);
				family.addInvalidType("US02");
				person.setValid(false);
				person.addInvalidType("US02");
				pass = false;
			}
		}
		return pass;
	}

	// Sprint 1
	// 100% works
	public boolean birthBeforeDeath(Person person) {
		if (person.isDead()) {
			if (person.getBirthdate().before(person.getDeathdate())) {
				return true;
			} else {
				person.setValid(false);
				person.addInvalidType("US03");
				return false;
			}
		} else {
			return true;
		}
	}

	public boolean marriageBeforeDivorce(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (family.isDivorced()) {
				if (!family.getMarDate().before(family.getDivDate())) {
					family.setValid(false);
					family.addInvalidType("US04");
					person.setValid(false);
					person.addInvalidType("US04");
					pass = false;
				}
			}
		}
		return pass;
	}

	public boolean marriageBeforeDeath(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (person.isDead()) {
				if (!family.getMarDate().before(person.getDeathdate())) {
					family.setValid(false);
					family.addInvalidType("US05");
					person.setValid(false);
					person.addInvalidType("US05");
					pass = false;
				}
			}
		}
		return pass;
	}

	public boolean divorceBeforeDeath(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (person.isDead() && family.isDivorced()) {
				if (!family.getDivDate().before(person.getDeathdate())) {
					family.setValid(false);
					family.addInvalidType("US06");
					person.setValid(false);
					person.addInvalidType("US06");
					pass = false;
				}
			}
		}
		return pass;
	}

	public boolean under150YearsOld(Person person) {
		if (person.getAge() > 150) {
			person.setValid(false);
			person.addInvalidType("US07");
			return false;
		} else {
			return true;
		}
	}

	// US08 UNFINISHED
	// US09 UNFINISHED

	public boolean marriageAfter14(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			int age = HelperFunctions.differenceInDatesInYears(person.getBirthdate(), family.getMarDate());
			if (age < 14) {
				family.setValid(false);
				family.addInvalidType("US10");
				person.setValid(false);
				person.addInvalidType("US10");
				pass = false;
			}
		}
		return pass;
	}

	// US11 to US20 Unfinished

	public boolean correctGenderForRole(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (person.getGender().equals("M")) {
				if (!family.getHusbID().equals(person.getID())) {
					family.setValid(false);
					family.addInvalidType("US21");
					person.setValid(false);
					person.addInvalidType("US21");
					pass = false;
				}
			}
			if (person.getGender().equals("F")) {
				if (!family.getWifeID().equals(person.getID())) {
					family.setValid(false);
					family.addInvalidType("US21");
					person.setValid(false);
					person.addInvalidType("US21");
					pass = false;
				}
			}
		}
		return pass;
	}

	// US22
	public boolean uniquePersonIDs(ArrayList<Person> people) {
		List<String> ids = new ArrayList<String>();
		boolean bool = true;
		for (Person person : people) {
			ids.add(person.getID());
		}
		ids = HelperFunctions.findDuplicates(ids);
		for (String s : ids) {
			bool = false;
			for (Person person : people) {
				if (person.getID().equals(s)) {
					person.addInvalidType("US22");
					person.setValid(false);
				}
			}
		}
		return bool;
	}

	// US22
	public boolean uniqueFamilyIDs(ArrayList<Family> families) {
		List<String> ids = new ArrayList<String>();
		boolean bool = true;
		for (Family person : families) {
			ids.add(person.getID());
		}
		ids = HelperFunctions.findDuplicates(ids);
		for (String s : ids) {
			bool = false;
			for (Family person : families) {
				if (person.getID().equals(s)) {
					person.addInvalidType("US22");
					person.setValid(false);
				}
			}
		}
		return bool;
	}

	// US15
	public boolean lessThan15Siblings(Family family) {
		if (family.getChildrenIDs().size() > 14) {
			family.setValid(false);
			family.addInvalidType("US15");
			return false;
		}
		return true;
	}

	// US11
	public boolean noBigamy(Person person) {
		ArrayList<Family> families = person.getFamilies();
		int counter = 0;
		for (Family family : families) {
			if (family.isMarried()) {
				counter++;
			}
		}
		if (counter > 1) {
			person.setValid(false);
			person.addInvalidType("US11");
			return false;
		} else {
			return true;
		}
	}

	// US01
	public boolean datesBeforeCurrentDate(Person person) {
		Date today = Calendar.getInstance().getTime();
		if (!person.getBirthdate().before(today)) {
			person.setValid(false);
			person.addInvalidType("US01");
			return false;
		}
		if (person.isDead()) {
			if (!person.getDeathdate().before(today)) {
				person.setValid(false);
				person.addInvalidType("US01");
				return false;
			}
		}
		return true;
	}

	// US01
	public boolean datesBeforeCurrentDate(Family person) {
		Date today = Calendar.getInstance().getTime();
		if (!person.getMarDate().before(today)) {
			person.setValid(false);
			person.addInvalidType("US01");
			return false;
		}
		if (person.isDivorced()) {
			if (!person.getDivDate().before(today)) {
				person.setValid(false);
				person.addInvalidType("US01");
				return false;
			}
		}
		return true;
	}

	public void validityChecker(Person person) {
		birthBeforeMarriage(person);
		birthBeforeDeath(person);
		marriageBeforeDivorce(person);
		marriageBeforeDeath(person);
		divorceBeforeDeath(person);
		under150YearsOld(person);
		marriageAfter14(person);
		correctGenderForRole(person);
		noBigamy(person);
		datesBeforeCurrentDate(person);
	}

	public void validityChecker(Family family) {
		lessThan15Siblings(family);
		datesBeforeCurrentDate(family);
	}

	public void validityChecker(ArrayList<Person> people, ArrayList<Family> families) {
		uniquePersonIDs(people);
		uniqueFamilyIDs(families);
	}

}
