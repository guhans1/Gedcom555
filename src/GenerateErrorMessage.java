import java.util.ArrayList;

public class GenerateErrorMessage {
	
	public static String generateMsg(Person person) {
		ArrayList<String> invalidTypes = person.getInvalidType();
		String message = "";
		for(String error : invalidTypes) {
			if(error.equals("US22")) {
				message = message.concat("US22: Person " + person.getID() + " duplicated in list of people" + "\n");
			}
			if(error.equals("US11")) {
				message = message.concat("US11: Person " + person.getID() + " is married multiple times" + "\n");
			}
			if(error.equals("US01")) {
				message = message.concat("US01: Person " + person.getID() + " has dates after current date \n");
			}
		}
		return message;	
	}
	
	public static String generateMsg(Family family) {
		ArrayList<String> invalidTypes = family.getInvalidType();
		String message = "";
		for(String error : invalidTypes) {
			if(error.equals("US15")) {
				message = message.concat("US15: Family " + family.getID() + " has " + family.getChildrenIDs().size() +  " children which is more than 14" + "\n");
			}
			if(error.equals("US01")) {
				message = message.concat("US01: Family " + family.getID() + " has dates after current date \n");
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
