import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class GedcomScraper {

	public static ArrayList<Person> getPeopleFromFile(File file) throws Exception {

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
		ArrayList<Person> people = new ArrayList<Person>();

		// Generate list of individuals with ID only
		for (String line : gedComStrings) {
			String inLine = line; // Let's avoid any side effects
			String[] splitTokens = inLine.split("\\s+"); // Let's split our line by spaces (some regex)

			@SuppressWarnings("unused")
			String index = splitTokens[0].trim();
			String id = splitTokens[1].trim(); // ONLY FOR INDI AND FAM
			String altTag = "";
			if (splitTokens.length > 2) {
				altTag = splitTokens[2].trim(); // ONLY VALID FOR INDI AND FAM
			}
			// Begin production of our individuals
			if (altTag.equals("INDI")) {
				Person tempPerson = new Person();
				id = id.replace("@", ""); // Removes @ symbol found in some variants
				tempPerson.setID(id);
				people.add(tempPerson);
			}
		}

		// Giving each person data
		for (Person person : people) {
			String id = person.getID();

			String name = "";

			int bday = 0, bmonth = 0, byear = 0, dday = 0, dmonth = 0, dyear = 40000;

			String fams = "NA";
			String famc = "NA";

			String gender = "";

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
							bmonth = HelperFunctions.changeMonthFormatToInt(splitTokens[3]);
							byear = Integer.parseInt(splitTokens[4]);
							birthFlag = false;
						}

						if (deathFlag && splitTokens[1].trim().equals("DATE")) {
							dday = Integer.parseInt(splitTokens[2]);
							dmonth = HelperFunctions.changeMonthFormatToInt(splitTokens[3]);
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
			person.setFamc(famc);
			person.setFams(fams);
			person.setDead(hasDied);

			// Special code for birthdays/deathdays
			person.setBirthDate(byear, bmonth, bday);
			person.setDeathDate(dyear, dmonth, dday);

		}

		return people;

	}

	public static ArrayList<Family> getFamFromFile(File file) throws Exception {

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
		ArrayList<Family> families = new ArrayList<Family>();

		// Generate list of individuals with ID only
		for (String line : gedComStrings) {
			String inLine = line; // Let's avoid any side effects
			String[] splitTokens = inLine.split("\\s+"); // Let's split our line by spaces (some regex)

			@SuppressWarnings("unused")
			String index = splitTokens[0].trim();
			String id = splitTokens[1].trim(); // ONLY FOR INDI AND FAM
			String altTag = "";
			if (splitTokens.length > 2) {
				altTag = splitTokens[2].trim(); // ONLY VALID FOR INDI AND FAM
			}
			// Begin production of our individuals
			if (altTag.equals("FAM")) {
				Family tempFam = new Family();
				id = id.replace("@", ""); // Removes @ symbol found in some variants
				tempFam.setID(id);
				families.add(tempFam);
			}
		}

		// Giving each person data
		for (Family fam : families) {
			String id = fam.getID();

			String husbID = "";
			String wifeID = "";
			int mday = 0, mmonth = 0, myear = 0;
			int diday = 0, dimonth = 0, diyear = 0;
			ArrayList<String> children = new ArrayList<String>();

			boolean marrFlag = false;
			boolean divFlag = false;
			boolean isDivorced = false;
			boolean isMarried = false;

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
							isMarried = true;
						}

						if (marrFlag && splitTokens[1].trim().equals("DATE")) {
							mday = Integer.parseInt(splitTokens[2]);
							mmonth = HelperFunctions.changeMonthFormatToInt(splitTokens[3]);
							myear = Integer.parseInt(splitTokens[4]);
							marrFlag = false;
						}

						if (splitTokens[1].trim().equals("DIV")) {
							divFlag = true;
							isDivorced = true;
							isMarried = false;
						}

						if (divFlag && splitTokens[1].trim().equals("DATE")) {
							diday = Integer.parseInt(splitTokens[2]);
							dimonth = HelperFunctions.changeMonthFormatToInt(splitTokens[3]);
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
			fam.setChildrenIDs(children);
			fam.setMarried(isMarried);
		}

		return families;
	}
}
