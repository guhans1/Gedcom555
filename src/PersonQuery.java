import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PersonQuery {

	public static ArrayList<PersonGedcom> listDeceased(ArrayList<PersonGedcom> people) {
		ArrayList<PersonGedcom> deadPeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (person.isHasDied()) {
				deadPeople.add(person);
			}
		}
		return deadPeople;
	}

	public static ArrayList<PersonGedcom> listRecentBirths(ArrayList<PersonGedcom> people) {
		ArrayList<PersonGedcom> recentlyBornPeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (true) {
				Date birthdate = person.getBirthDate();
				if (HelperFunctions.differenceInDatesInDays(birthdate, Calendar.getInstance().getTime()) < 30) {
					recentlyBornPeople.add(person);
				}
			}
		}
		return recentlyBornPeople;
	}

	public static ArrayList<PersonGedcom> listRecentDeaths(ArrayList<PersonGedcom> people) {
		ArrayList<PersonGedcom> recentlyDeadPeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (person.isHasDied()) {
				Date deathdate = person.getDeathDate();
				if (HelperFunctions.differenceInDatesInDays(deathdate, Calendar.getInstance().getTime()) < 30) {
					recentlyDeadPeople.add(person);
				}
			}
		}
		return recentlyDeadPeople;
	}

	// Fixed
	public static ArrayList<PersonGedcom> listAliveAndSingle(ArrayList<PersonGedcom> people, ArrayList<FamGedcom> families) {
		ArrayList<PersonGedcom> alivePeople = new ArrayList<PersonGedcom>();
		ArrayList<PersonGedcom> aliveAndSinglePeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (!person.isHasDied()) {
				alivePeople.add(person);
			}
		}
		for (PersonGedcom person : alivePeople) {
			boolean isSingle = true;
			String famID = person.getFams();
			FamGedcom family = null;
			for (FamGedcom fams : families) {
				if (fams.getFamID().equals(famID)) {
					family = fams;
					if (!family.isDivorced()) {
						isSingle = false;
					}
				}
			}
			if(isSingle) {
				aliveAndSinglePeople.add(person);
			}
		}
		return aliveAndSinglePeople;
	}

}
