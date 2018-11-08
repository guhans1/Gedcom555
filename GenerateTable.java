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
		t.addColumn("Spouse", Person::getFams);
		t.addColumn("Child", Person::getFamc);
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
		
		File file = new File("/users/guhan/Desktop/proj02test.ged");
		ArrayList<Person> people = GedcomScraper.getPeopleFromFile(file);
		ArrayList<Family> families = GedcomScraper.getFamFromFile(file);
		HelperFunctions.allocatePersonToFam(people, families);
		
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
		
		System.out.println("List Living Single US31");
		System.out.println(generatePeopleTable(PersonQuery.listAliveAndSingle(people, families)));
		
		System.out.println("List Recent Births US35");
		System.out.println(generatePeopleTable(PersonQuery.listRecentBirths(people)));
		
		System.out.println("List Recent Deaths US36");
		System.out.println(generatePeopleTable(PersonQuery.listRecentDeaths(people)));
		
		System.out.println("List Living And Married US30");
		System.out.println(generatePeopleTable(PersonQuery.listLivingMarried(people)));
		
		BufferedWriter writer = null;
		writer = new BufferedWriter(new FileWriter("GedcomOutput.txt"));
		writer.write(GenerateErrorMessage.generateMsg(people, families));
		writer.write("\n");
		writer.write("Individuals");
		writer.write("\n");
		writer.write(generatePeopleTable(people));
		writer.write("\n");
		writer.write("Families");
		writer.write("\n");
		writer.write(generateFamTable(families));
		writer.write("\n");
		writer.write("List Deceased US29");
		writer.write("\n");
		writer.write(generatePeopleTable(PersonQuery.listDeceased(people)));
		writer.write("\n");
		writer.write("List Living Single US31");
		writer.write("\n");
		writer.write(generatePeopleTable(PersonQuery.listAliveAndSingle(people, families)));
		writer.write("\n");
		writer.write("List Recent Births US35");
		writer.write("\n");
		writer.write(generatePeopleTable(PersonQuery.listRecentBirths(people)));
		writer.write("\n");
		writer.write("List Recent Deaths US36");
		writer.write("\n");
		writer.write(generatePeopleTable(PersonQuery.listRecentDeaths(people)));
		writer.write("\n");
		writer.write("List Living And Married US30");
		writer.write("\n");
		writer.write(generatePeopleTable(PersonQuery.listLivingMarried(people)));
		writer.write("\n");
		writer.close();
	}

}
