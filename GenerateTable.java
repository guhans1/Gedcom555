import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class GenerateTable {
	
	public static String generatePeopleTable(ArrayList<Person> people) {

		TableStringBuilder<Person> t = new TableStringBuilder<Person>();

		t.addColumn("ID", Person::getID);
		t.addColumn("Name", Person::getName);
		t.addColumn("Gender", Person::getGender);
		t.addColumn("Birthday", Person::getBirthDateAsString);
		t.addColumn("Age", Person::getAge);
		t.addColumn("Alive", Person::isAlive);
		t.addColumn("Death", Person::getDeathDateAsString);
		t.addColumn("Current Spouse of", Person::getFams);
		t.addColumn("Spouses", Person::getSpousesIDs);
		t.addColumn("Child of", Person::getFamc);
		t.addColumn("Children", Person::getChildrenIDs);
		t.addColumn("Valid", Person::isValid);
		t.addColumn("Invalid Type", Person::getInvalidType);

		String outString = t.createString(people);
		return outString;

	}

	public static String generateFamTable(ArrayList<Family> families) {

		TableStringBuilder<Family> t = new TableStringBuilder<Family>();

		t.addColumn("ID", Family::getID);
		t.addColumn("Husband", Family::getHusbID);
		t.addColumn("Wife", Family::getWifeID);
		t.addColumn("Married", Family::getMarDateAsString);
		t.addColumn("Divorced", Family::getDivDateAsString);
		t.addColumn("Children", Family::getChildrenIDsAsString);

		String outString = t.createString(families);
		return outString;

	}
	
	public static void main(String[] args) throws Exception {
		
		File file = new File("/users/guhan/Desktop/proj05test.ged");
		ArrayList<Person> people = GedcomScraper.getPeopleFromFile(file);
		ArrayList<Family> families = GedcomScraper.getFamFromFile(file);
		HelperFunctions.allocatePersonToFam(people, families);
		HelperFunctions.addChildrenToFamilies(people, families);
		HelperFunctions.addHusbandToFamilies(people, families);
		HelperFunctions.addWifeToFamilies(people, families);
		
		Validity vc = new Validity();
		for (Person person : people) {
			vc.validityChecker(person);
		}
		for (Family family : families) {
			vc.validityChecker(family);
		}
		vc.validityChecker(people, families);
		
		System.out.println(GenerateErrorMessage.generateMsg(people, families));
		
		System.out.println("Individuals");
		System.out.println(generatePeopleTable(people));
		System.out.println("Families");
		System.out.println(generateFamTable(families));
		
		System.out.println("List Deceased US29");
		System.out.println(generatePeopleTable(PersonQuery.listDeceased(people)));
				
		System.out.println("List Living And Married US30");
		System.out.println(generatePeopleTable(PersonQuery.listLivingMarried(people)));
		
		System.out.println("List Living Single US31");
		System.out.println(generatePeopleTable(PersonQuery.listAliveAndSingle(people, families)));
		
		System.out.println("List Recent Births US35");
		System.out.println(generatePeopleTable(PersonQuery.listRecentBirths(people)));
		
		System.out.println("List Recent Deaths US36");
		System.out.println(generatePeopleTable(PersonQuery.listRecentDeaths(people)));
		
		System.out.println(("List Upcoming Birthdays US38"));
		System.out.println(generatePeopleTable(PersonQuery.upcomingBirthdays(people)));
		
		System.out.println("List Upcoming Anniversaries US39");
		System.out.println(generateFamTable(PersonQuery.upcomingAnniversaries(families)));
		
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter("GedcomOutput.txt"));
		writer.write(GenerateErrorMessage.generateMsg(people, families));
		writer.write(System.lineSeparator());
		writer.write("Individuals");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(people));
		writer.write(System.lineSeparator());
		writer.write("Families");
		writer.write(System.lineSeparator());
		writer.write(generateFamTable(families));
		writer.write(System.lineSeparator());
		writer.write("List Deceased US29");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.listDeceased(people)));
		writer.write(System.lineSeparator());
		writer.write("List Living And Married US30");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.listLivingMarried(people)));
		writer.write(System.lineSeparator());
		writer.write("List Living Single US31");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.listAliveAndSingle(people, families)));
		writer.write(System.lineSeparator());
		writer.write("List Recent Births US35");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.listRecentBirths(people)));
		writer.write(System.lineSeparator());
		writer.write("List Recent Deaths US36");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.listRecentDeaths(people)));
		writer.write(System.lineSeparator());
		writer.write("List Upcoming Birthdays US38");
		writer.write(System.lineSeparator());
		writer.write(generatePeopleTable(PersonQuery.upcomingBirthdays(people)));
		writer.write(System.lineSeparator());
		writer.write("List Upcoming Anniversaries US39");
		writer.write(System.lineSeparator());
		writer.write(generateFamTable(PersonQuery.upcomingAnniversaries(families)));
		writer.close();
	}

}
