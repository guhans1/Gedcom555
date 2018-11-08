import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class PersonQuery {

	public static ArrayList<Person> listDeceased(ArrayList<Person> people) {
		ArrayList<Person> deadPeople = new ArrayList<Person>();
		for (Person person : people) {
			if (person.isDead()) {
				deadPeople.add(person);
			}
		}
		return deadPeople;
	}

	public static ArrayList<Person> listRecentBirths(ArrayList<Person> people) {
		ArrayList<Person> recentlyBornPeople = new ArrayList<Person>();
		for (Person person : people) {
			if (true) {
				Date birthdate = person.getBirthdate();
				if (HelperFunctions.differenceInDatesInDays(birthdate, Calendar.getInstance().getTime()) < 30) {
					recentlyBornPeople.add(person);
				}
			}
		}
		return recentlyBornPeople;
	}

	public static ArrayList<Person> listRecentDeaths(ArrayList<Person> people) {
		ArrayList<Person> recentlyDeadPeople = new ArrayList<Person>();
		for (Person person : people) {
			if (person.isDead()) {
				Date deathdate = person.getDeathdate();
				if (HelperFunctions.differenceInDatesInDays(deathdate, Calendar.getInstance().getTime()) < 30) {
					recentlyDeadPeople.add(person);
				}
			}
		}
		return recentlyDeadPeople;
	}

	// Fixed
	public static ArrayList<Person> listAliveAndSingle(ArrayList<Person> people, ArrayList<Family> families) {
		ArrayList<Person> alivePeople = new ArrayList<Person>();
		ArrayList<Person> aliveAndSinglePeople = new ArrayList<Person>();
		for (Person person : people) {
			if (!person.isDead()) {
				alivePeople.add(person);
			}
		}
		for (Person person : alivePeople) {
			boolean isSingle = true;
			String famID = person.getFams();
			Family family = null;
			for (Family fams : families) {
				if (fams.getID().equals(famID)) {
					family = fams;
					if (!family.isDivorced()) {
						isSingle = false;
					}
				}
			}
			if (isSingle) {
				aliveAndSinglePeople.add(person);
			}
		}
		return aliveAndSinglePeople;
	}

	public static ArrayList<Person> listLivingMarried(ArrayList<Person> people) {
		ArrayList<Person> aliveAndMarriedPeople = new ArrayList<Person>();
		for (Person person : people) {
			if ((!person.isDead())) {
				ArrayList<Family> families = person.getFamilies();
				for (Family family : families) {
					if (family.isMarried()) {
						aliveAndMarriedPeople.add(person);
					}
				}

			}
		}
		return aliveAndMarriedPeople;
	}

}
