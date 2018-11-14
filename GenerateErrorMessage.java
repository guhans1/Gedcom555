import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GenerateErrorMessage {
	
	public static String generateMsg(Person person) {
		ArrayList<String> invalidTypes = person.getInvalidType();
		String message = "";
		Set<String> hs = new HashSet<>();
		hs.addAll(invalidTypes);
		invalidTypes.clear();
		invalidTypes.addAll(hs);
		for(String error : invalidTypes) {
			if(error.equals("US01")) {
				message = message.concat("US01: Person " + person.getID() + " has birth date and/or death date after current date" + System.lineSeparator());
			}
			if(error.equals("US02")) {
				message = message.concat("US02: Person " + person.getID() + " marriage date is before birth date on " + person.getBirthDateAsString() + System.lineSeparator());
			}
			if(error.equals("US03")) {
				message = message.concat("US03: Person " + person.getID() + " death date on " + person.getDeathDateAsString() + " before birth date on " + person.getBirthDateAsString() + System.lineSeparator());
			}
			if(error.equals("US04")) {
				message = message.concat("US04: Person " + person.getID() + " divorce date before marriage date" + System.lineSeparator());
			}
			if(error.equals("US05")) {
				message = message.concat("US05: Person " + person.getID() + " death date on " + person.getDeathDateAsString() + " before marriage date" + System.lineSeparator());
			}
			if(error.equals("US06")) {
				message = message.concat("US06: Person " + person.getID() + " death date on " + person.getDeathDateAsString() + " before divorce date" + System.lineSeparator());
			}
			if(error.equals("US07")) {
				message = message.concat("US07: Person " + person.getID() + " is age: " + person.getAge() + " which is over 150 years old" + System.lineSeparator());
			}
			if(error.equals("US10")) {
				message = message.concat("US10: Person " + person.getID() + " has marriage date before the age of 14" + System.lineSeparator());
			}
			if(error.equals("US11")) {
				message = message.concat("US11: Person " + person.getID() + " is married multiple times (bigamy/polygamy)" + System.lineSeparator());
			}
			if(error.equals("US21")) {
				message = message.concat("US21: Person " + person.getID() +  " is gender " + person.getGender() +  " which is the wrong gender in at least one of their families" + System.lineSeparator());
			}
			if(error.equals("US22")) {
				message = message.concat("US22: Person " + person.getID() + " ID duplicated in list of people" + System.lineSeparator());
			}
			
			
		}
		return message;	
	}
	
	public static String generateMsg(Family family) {
		ArrayList<String> invalidTypes = family.getInvalidType();
		String message = "";
		Set<String> hs = new HashSet<>();
		hs.addAll(invalidTypes);
		invalidTypes.clear();
		invalidTypes.addAll(hs);
		for(String error : invalidTypes) {
			if(error.equals("US01")) {
				message = message.concat("US01: Family " + family.getID() + " has marriage date and/or divorce date after current date" + System.lineSeparator());
			}
			if(error.equals("US15")) {
				message = message.concat("US15: Family " + family.getID() + " has " + family.getChildrenIDs().size() +  " children which is more than 14" + System.lineSeparator());
			}
//			if(error.equals("US16")) {
//				message = message.concat("US16: Family " + family.getID() + " has males with different last names" + System.lineSeparator());
//			}
			if(error.equals("US25")) {
				message = message.concat("US25: Family " + family.getID() + " has multiple children with the same first name" + System.lineSeparator());
			}
			if(error.equals("US08")) {
				message = message.concat("US08: Family " + family.getID() + " has children born before the marriage of parents or 9 months after the divorce of parents" + System.lineSeparator());
			}
			if(error.equals("US09")) {
				message = message.concat("US09: Family " + family.getID() + " has children born 9 months after the death of the father or born after the death of the mother" + System.lineSeparator());
			}
			if(error.equals("US12")) {
				message = message.concat("US12: Family " + family.getID() + " has father's birthdate more than 80 years before child's birthdate or mother's birthdate more than 60 years before child's birthdate " + System.lineSeparator());
			}
		}
		return message;	
	}
	
	public static String generateMsg(ArrayList<Person> people,ArrayList<Family> families) {
		String output = "";
		for(Person person : people) {
			output = output.concat(generateMsg(person));
		}
		for(Family family : families) {
			output = output.concat(generateMsg(family));
		}
		return output;
	}

}
