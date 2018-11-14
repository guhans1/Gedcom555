import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Validity {

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

	// US02
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

	// US03
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

	// US04
	public boolean marriageBeforeDivorce(Person person) {
		boolean pass = true;
		for (Family family : person.getFamilies()) {
			if (family.isDivorced()) {
				if (family.getMarDate().after(family.getDivDate())) {
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

	// US05
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

	// US06
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

	// US07
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

	// US10
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

	// US21
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

	// US25
	public boolean uniqueFirstNamesAmongChildrenInFamily(Family family) {
		boolean pass = true;
		ArrayList<String> firstNames = new ArrayList<String>();
		ArrayList<Person> children = family.getChildren();
		for (Person child : children) {
			String childName = child.getName();
			childName = HelperFunctions.getFirstName(childName);
			firstNames.add(childName);
		}
		if (HelperFunctions.findDuplicates(firstNames).isEmpty()) {
			return pass;
		} else {
			pass = false;
			family.setValid(false);
			family.addInvalidType("US25");
			return pass;
		}
	}

	// US16
	public boolean sameLastNamesAmongMalesInFamily(Family family) {
		boolean pass = true;
		ArrayList<String> lastNames = new ArrayList<String>();
		if (!(family.getHusband().getBirthdate() == null)) {
			lastNames.add(HelperFunctions.getLastName(family.getHusband().getName()));
		}
		ArrayList<Person> children = family.getChildren();
		for (Person child : children) {
			String childName = child.getName();
			childName = HelperFunctions.getLastName(childName);
			for (String lastName : lastNames) {
				if (!lastName.equals(childName)) {
					if (child.getGender().equals("M")) {
						pass = false;
					}
				}
			}
			lastNames.add(childName);
		}
		if (pass == false) {
			family.setValid(false);
			family.addInvalidType("US16");
		}
		return pass;
	}

	// US08
	public boolean birthBeforeMarriageOfParents(Family family) {
		boolean pass = true;
		int counter = 0;
		boolean divorced = family.isDivorced();
		Date marDate = family.getMarDate();
		ArrayList<Person> children = family.getChildren();
		for (Person child : children) {
			if (child.getBirthdate().before(marDate)) {
				family.setValid(false);
				family.addInvalidType("US08");
				counter++;
				pass = false;
			}
			if (family.isDivorced()) {
				Date divDate = family.getDivDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(divDate);
				cal.add(Calendar.MONTH, +9);
				divDate = cal.getTime();
				if (child.getBirthdate().after(divDate)) {
					family.setValid(false);
					if (counter == 0) {
						family.addInvalidType("US08");
					}
					pass = false;
				}
			}
		}
		return pass;
	}

	// US09
	public boolean birthBeforeDeathOfParents(Family family) {
		boolean pass = true;
		int counter = 0;
		ArrayList<Person> children = family.getChildren();
		Person husband = family.getHusband();
		Person wife = family.getWife();
		if (wife == null) {
			return true;
		}
		for (Person child : children) {
			if (wife.isDead()) {
				if (child.getBirthdate().after(wife.getDeathdate())) {
					family.setValid(false);
					family.addInvalidType("US09");
					counter++;
					pass = false;
				}
			}
			if (husband.getBirthdate() == null) {
				return pass;
			}
			if (husband.isDead()) {
				Date husbandDeath9Months = husband.getBirthdate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(husbandDeath9Months);
				cal.add(Calendar.MONTH, +9);
				husbandDeath9Months = cal.getTime();
				if (child.getBirthdate().after(husbandDeath9Months)) {
					family.setValid(false);
					if (counter == 0) {
						family.addInvalidType("US09");
					}
					pass = false;
				}
			}

		}
		return pass;
	}

	// US12
	public boolean parentsNotTooOld(Family family) {
		boolean pass = true;
		int counter = 0;
		Person husband = family.getHusband();
		Person wife = family.getWife();
		ArrayList<Person> children = family.getChildren();
		for (Person child : children) {
			if (!(wife == null)) {
				int diff = HelperFunctions.differenceInDatesInYears(wife.getBirthdate(), child.getBirthdate());
				if (diff > 60) {
					family.setValid(false);
					counter++;
					family.addInvalidType("US12");
					pass = false;
				}
			}
			if (!(husband == null)) {
				if (!(husband.getBirthdate() == null)) {
					int diff = HelperFunctions.differenceInDatesInYears(husband.getBirthdate(), child.getBirthdate());
					if (diff > 80) {
						family.setValid(false);
						if (counter == 0) {
							family.addInvalidType("US12");
						}
						pass = false;
					}
				}
			}
		}
		return pass;
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
		uniqueFirstNamesAmongChildrenInFamily(family);
		// sameLastNamesAmongMalesInFamily(family);
		birthBeforeMarriageOfParents(family);
		birthBeforeDeathOfParents(family);
		parentsNotTooOld(family);
	}

	public void validityChecker(ArrayList<Person> people, ArrayList<Family> families) {
		uniquePersonIDs(people);
		uniqueFamilyIDs(families);
	}

}
