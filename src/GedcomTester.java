import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class GedcomTester {

	// If the tag is valid itself and has a valid index, returns true
	public static boolean isValidTag(String index, String tag) {

		if (tag.equals("HEAD") || tag.equals("NOTE") || tag.equals("TRLR")) {
			if (index.equals("0")) {
				return true;
			}
		}

		if (tag.equals("FAMC") || tag.equals("FAMS")) {
			if (index.equals("1")) {
				return true;
			}
		}

		if (tag.equals("SEX")) {
			if (index.equals("1")) {
				if (true) {
					return true;
				}
			}
		}

		if (tag.equals("BIRT") || tag.equals("DEAT")) {
			if (index.equals("1")) {
				if (true) {
					return true;
				}
			}
		}

		if (tag.equals("MARR") || tag.equals("DIV")) {
			if (index.equals("1")) {
				if (true) {
					return true;
				}
			}
		}

		if (tag.equals("DATE")) {
			if (index.equals("2")) {
				return true;
			}
		}

		if (tag.equals("NAME")) {
			if (index.equals("1")) {
				return true;
			}
		}

		if (tag.equals("HUSB") || tag.equals("WIFE") || tag.equals("CHIL")) {
			if (index.equals("1")) {
				return true;
			}
		}

		// Special case addressed elsewhere
		if (tag.equals("INDI") || tag.equals("FAM") ) {
			if (index.equals("0")) {
				return true;
			}
		}

		return false;
	}

	// Helper function that converts from T/F to Y/N
	public static String stringPrinterYN(boolean bool) {
		if (bool) {
			return "Y";
		} else {
			return "N";
		}
	}

	// 1. Reads file into String ArrayList
	// 2. Outputs input line with -->
	// 3. Finds validity of line
	// 3. Outputs edited output line with <--
	public static void main(String[] args) {
		try {
			// File file = new File("/users/guhan/Desktop/proj02test.ged");
			File file = new File("/users/guhan/Desktop/proj01test.ged");
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			ArrayList<String> gedComStrings = new ArrayList<String>();

			String newLine;
			while ((newLine = bufferedReader.readLine()) != null) {
				gedComStrings.add(newLine);
			}
			fileReader.close();

			// Legacy code, not currently used
			ArrayList<String> validTags = new ArrayList<String>();
			validTags.add("INDI");
			validTags.add("NAME");
			validTags.add("SEX");
			validTags.add("BIRT");
			validTags.add("DEAT");
			validTags.add("FAMC");
			validTags.add("FAM");
			validTags.add("FAMS");
			validTags.add("MARR");
			validTags.add("HUSB");
			validTags.add("WIFE");
			validTags.add("DIV");
			validTags.add("DATE");
			validTags.add("HEAD");
			validTags.add("TRLR");
			validTags.add("NOTE");
			// Legacy code ends here

			for (String strings : gedComStrings) {

				String inputString = strings;
				String validString = strings;

				inputString = "--> " + inputString;
				System.out.println(inputString);

				String[] splitTokens = validString.split("\\s+");

				String index = splitTokens[0].trim();
				String tag = splitTokens[1].trim();

				// Special code for INDI and FAM since they are the third part of string
				if (splitTokens.length > 2 && index.equals("0")
						&& (splitTokens[2].trim().equals("FAM") || splitTokens[2].trim().equals("INDI"))) {
					String yn = stringPrinterYN(isValidTag(index, splitTokens[2]));
					validString = splitTokens[0] + "|" + splitTokens[2] + "|" + yn + "|" + splitTokens[1]; // IMPORTANT
					validString = "<-- " + validString;
					System.out.println(validString);
				} else {

					String yn = stringPrinterYN(isValidTag(index, tag));

					splitTokens[1] = "|" + splitTokens[1] + "|" + yn + "|";
					String tempString = Arrays.toString(splitTokens);
					tempString = tempString.substring(1, tempString.length() - 1).replace(",", "");

					// Hotfix to get rid of the space between index and tag to conform to specs
					tempString = tempString.substring(0, 1) + tempString.substring(2);

					validString = "<-- " + tempString;
					System.out.println(validString);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
