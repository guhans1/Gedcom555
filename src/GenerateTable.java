import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class GenerateTable {

	public static int changeMonthFormatToInt(String month) {

		int monthNo = 0;

		if (month.equals("JAN")) {
			monthNo = 0;
		}
		if (month.equals("FEB")) {
			monthNo = 1;
		}
		if (month.equals("MAR")) {
			monthNo = 2;
		}
		if (month.equals("APR")) {
			monthNo = 3;
		}
		if (month.equals("MAY")) {
			monthNo = 4;
		}
		if (month.equals("JUN")) {
			monthNo = 5;
		}
		if (month.equals("JUl")) {
			monthNo = 6;
		}
		if (month.equals("AUG")) {
			monthNo = 7;
		}
		if (month.equals("SEP")) {
			monthNo = 8;
		}
		if (month.equals("OCT")) {
			monthNo = 9;
		}
		if (month.equals("NOV")) {
			monthNo = 10;
		}
		if (month.equals("DEC")) {
			monthNo = 11;
		}

		return monthNo;
	}

	// TODO actual exception handing lol
	public static ArrayList<PersonGedcom> getPeopleFromFile(File file) throws Exception {

		// ===================================================
		// Code to change file to arraylist of string lines
		// ===================================================

		// File reader and all
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> gedComStrings = new ArrayList<String>();

		String newLine;
		while ((newLine = bufferedReader.readLine()) != null) {
			gedComStrings.add(newLine);
		}

		/*
		 * for(String string : gedComStrings) { System.out.println(string); }
		 */

		fileReader.close();
		bufferedReader.close();

		// ===================================================
		// Code to begin extracting the persons
		// ===================================================

		// Here is our PEOPLE arraylist (IMPORTANT)
		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();

		// Generate list of individuals with ID only
		for (String line : gedComStrings) {
			String inLine = line; // Let's avoid any side effects
			String[] splitTokens = inLine.split("\\s+"); // Let's split our line by spaces (some regex)

			String index = splitTokens[0].trim();
			String id = splitTokens[1].trim(); // ONLY FOR INDI AND FAM
			String altTag = "";
			if (splitTokens.length > 2) {
				altTag = splitTokens[2].trim(); // ONLY VALID FOR INDI AND FAM
			}
			// Begin production of our individuals
			if (altTag.equals("INDI")) {
				PersonGedcom tempPerson = new PersonGedcom();
				id = id.replace("@", ""); // Removes @ symbol found in some variants
				tempPerson.setID(id);
				people.add(tempPerson);
			}
		}

		// Giving each person data
		for (PersonGedcom person : people) {
			String id = person.getID();

			String name = "";

			int bday = 0, bmonth = 0, byear = 0, dday = 0, dmonth = 0, dyear = 40000;

			Date birthdate = null;
			Date deathdate = null;

			String fams = "NA";
			String famc = "NA";

			String gender = "";
			String spouseID = "";
			ArrayList<String> childrenID = null;

			boolean personRecordingState = false;
			boolean birthFlag = false;
			boolean deathFlag = false;
			boolean hasDied = false;

			// Search for our INDI tag and collect all data
			for (String line : gedComStrings) {

				// Line Split
				String[] splitTokens = line.split("\\s+");

				if (personRecordingState) {
					if (splitTokens[0].trim().equals("0")) {
						// The next person has begun
						break;
					} else {
						if (splitTokens[1].trim().equals("SEX")) {
							gender = splitTokens[2].trim();
						}

						// TODO add support for middle names
						if (splitTokens[1].trim().equals("NAME")) {
							name = splitTokens[2].trim().concat(" " + splitTokens[3].trim());
						}

						if (splitTokens[1].trim().equals("SEX")) {
							gender = splitTokens[2].trim();
						}

						if (splitTokens[1].trim().equals("FAMS")) {
							fams = splitTokens[2].trim().replace("@", "");
						}

						if (splitTokens[1].trim().equals("FAMC")) {
							famc = splitTokens[2].trim().replace("@", "");
						}

						// Special code because birth/death lines split
						if (splitTokens[1].trim().equals("BIRT")) {
							birthFlag = true;
						}

						if (splitTokens[1].trim().equals("DEAT")) {
							deathFlag = true;
							hasDied = true;
						}

						if (birthFlag && splitTokens[1].trim().equals("DATE")) {
							bday = Integer.parseInt(splitTokens[2]);
							bmonth = changeMonthFormatToInt(splitTokens[3]);
							byear = Integer.parseInt(splitTokens[4]);
							birthFlag = false;
						}

						if (deathFlag && splitTokens[1].trim().equals("DATE")) {
							dday = Integer.parseInt(splitTokens[2]);
							dmonth = changeMonthFormatToInt(splitTokens[3]);
							dyear = Integer.parseInt(splitTokens[4]);
							deathFlag = false;
						}

					}
				}

				String tag = "";
				if (splitTokens.length > 2) {
					tag = splitTokens[2].trim();
				}

				// TODO some defense if INDI is the id
				if (tag.equals("INDI")) {
					String testID = splitTokens[1].trim().replace("@", "");
					if (testID.equals(id)) {
						personRecordingState = true;
					}
				}

			}

			person.setName(name);
			person.setGender(gender);
			person.setSpouseID(spouseID);
			person.setFamc(famc);
			person.setFams(fams);
			person.setHasDied(hasDied);

			// Special code for birthdays
			person.setBirthDate(byear, bmonth, bday);
			person.setDeathDate(dyear, dmonth, dday);

		}

		return people;

	}

	public static ArrayList<FamGedcom> getFamFromFile(File file) throws Exception {

		// ===================================================
		// Code to change file to arraylist of string lines
		// ===================================================

		// File reader and all
		FileReader fileReader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		ArrayList<String> gedComStrings = new ArrayList<String>();

		String newLine;
		while ((newLine = bufferedReader.readLine()) != null) {
			gedComStrings.add(newLine);
		}

		/*
		 * for(String string : gedComStrings) { System.out.println(string); }
		 */

		fileReader.close();
		bufferedReader.close();

		// ===================================================
		// Code to begin extracting the families
		// ===================================================

		// Here is our FAMILY arraylist (IMPORTANT)
		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();

		// Generate list of individuals with ID only
		for (String line : gedComStrings) {
			String inLine = line; // Let's avoid any side effects
			String[] splitTokens = inLine.split("\\s+"); // Let's split our line by spaces (some regex)

			String index = splitTokens[0].trim();
			String id = splitTokens[1].trim(); // ONLY FOR INDI AND FAM
			String altTag = "";
			if (splitTokens.length > 2) {
				altTag = splitTokens[2].trim(); // ONLY VALID FOR INDI AND FAM
			}
			// Begin production of our individuals
			if (altTag.equals("FAM")) {
				FamGedcom tempFam = new FamGedcom();
				id = id.replace("@", ""); // Removes @ symbol found in some variants
				tempFam.setFamID(id);
				families.add(tempFam);
			}
		}

		// Giving each person data
		for (FamGedcom fam : families) {
			String id = fam.getFamID();

			String husbName = "";
			String wifeName = "";
			String husbID = "";
			String wifeID = "";
			int mday = 0, mmonth = 0, myear = 0;
			int diday = 0, dimonth = 0, diyear = 0;
			ArrayList<String> children = new ArrayList<String>();

			boolean marrFlag = false;
			boolean divFlag = false;
			boolean isDivorced = false;

			boolean famRecordingState = false;
			// Search for our INDI tag and collect all data
			for (String line : gedComStrings) {

				// Line Split
				String[] splitTokens = line.split("\\s+");

				if (famRecordingState) {
					if (splitTokens[0].trim().equals("0")) {
						// The next person has begun
						break;
					} else {
						if (splitTokens[1].trim().equals("HUSB")) {
							husbID = splitTokens[2].trim().replace("@", "");
						}

						if (splitTokens[1].trim().equals("WIFE")) {
							wifeID = splitTokens[2].trim().replace("@", "");
						}
						
						if (splitTokens[1].trim().equals("CHIL")) {
							children.add(splitTokens[2].trim().replace("@", ""));
						}

						if (splitTokens[1].trim().equals("MARR")) {
							marrFlag = true;
						}

						if (marrFlag && splitTokens[1].trim().equals("DATE")) {
							mday = Integer.parseInt(splitTokens[2]);
							mmonth = changeMonthFormatToInt(splitTokens[3]);
							myear = Integer.parseInt(splitTokens[4]);
							marrFlag = false;
						}

						if (splitTokens[1].trim().equals("DIV")) {
							divFlag = true;
							isDivorced = true;
						}

						if (divFlag && splitTokens[1].trim().equals("DATE")) {
							diday = Integer.parseInt(splitTokens[2]);
							dimonth = changeMonthFormatToInt(splitTokens[3]);
							diyear = Integer.parseInt(splitTokens[4]);
							divFlag = false;
						}

					}
				}

				String tag = "";
				if (splitTokens.length > 2) {
					tag = splitTokens[2].trim();
				}

				// TODO some defense if FAM is the id
				if (tag.equals("FAM")) {
					String testID = splitTokens[1].trim().replace("@", "");
					if (testID.equals(id)) {
						famRecordingState = true;
					}
				}

			}

			fam.setHusbID(husbID);
			fam.setWifeID(wifeID);
			fam.setMarDate(myear, mmonth, mday);
			fam.setDivDate(diyear, dimonth, diday);
			fam.setDivorced(isDivorced);
			fam.setChildren(children);

		}

		return families;

	}

	public static String generatePeopleTable(ArrayList<PersonGedcom> people) {

		TableStringBuilder<PersonGedcom> t = new TableStringBuilder<PersonGedcom>();

		t.addColumn("ID", PersonGedcom::getID);
		t.addColumn("Name", PersonGedcom::getName);
		t.addColumn("Gender", PersonGedcom::getGender);
		t.addColumn("Birthday", PersonGedcom::getBirthDateAsString);
		t.addColumn("Age", PersonGedcom::getAge);
		t.addColumn("Alive", PersonGedcom::checkIfAlive);
		t.addColumn("Death", PersonGedcom::getDeathDateAsString);
		t.addColumn("Spouse", PersonGedcom::getFams);
		t.addColumn("Child", PersonGedcom::getFamc);
		// t.addColumn("Children", PersonGedcom::getChildrenID);
		// t.addColumn("Spouse", PersonGedcom::getSpouseID);

		String outString = t.createString(people);
		return outString;

	}

	public static String generateFamTable(ArrayList<FamGedcom> families) {

		TableStringBuilder<FamGedcom> t = new TableStringBuilder<FamGedcom>();

		t.addColumn("ID", FamGedcom::getFamID);
		t.addColumn("Husband", FamGedcom::getHusbID);
		t.addColumn("Wife", FamGedcom::getWifeID);
		t.addColumn("Married", FamGedcom::getMarDateAsString);
		t.addColumn("Divorced", FamGedcom::getDivDateAsString);
		t.addColumn("Children", FamGedcom::getChildrenAsString);
		
		String outString = t.createString(families);
		return outString;

	}

	public static void main(String[] args) throws Exception {

		File file = new File("/users/guhan/Desktop/proj01test.ged");
		System.out.println("Individuals");
		System.out.println(generatePeopleTable(getPeopleFromFile(file)));
		System.out.println("Families");
		System.out.println(generateFamTable(getFamFromFile(file)));
	}

}
