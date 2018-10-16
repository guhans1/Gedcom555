import java.util.ArrayList;

public class PersonQuery {

	public ArrayList<PersonGedcom> listDeceased(ArrayList<PersonGedcom> people) {
		ArrayList<PersonGedcom> deadPeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (person.isHasDied()) {
				deadPeople.add(person);
			}
		}
		return deadPeople;
	}

	public void listAliveAndSingle(ArrayList<PersonGedcom> people, ArrayList<FamGedcom> families) {
		ArrayList<PersonGedcom> alivePeople = new ArrayList<PersonGedcom>();
		ArrayList<PersonGedcom> aliveAndSinglePeople = new ArrayList<PersonGedcom>();
		for (PersonGedcom person : people) {
			if (!person.isHasDied()) {
				alivePeople.add(person);
			}
		}
		for (PersonGedcom person : people) {
			String famID = person.getFams();
			FamGedcom family = null;
			boolean hasFamily = false;
			for (FamGedcom fams : families) {
				if (fams.getFamID().equals(famID)) {
					family = fams;
					hasFamily = true;
					break;
				}
			}
			if(hasFamily) {
				if(family.isMarried()) {
					// do nothing
				} else {
					aliveAndSinglePeople.add(person);
				}
			} else {
				aliveAndSinglePeople.add(person);
			}
		}

	}

}
