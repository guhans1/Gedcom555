import static org.junit.jupiter.api.Assertions.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.jupiter.api.Test;

class PersonDeprecated {

	private String ID;
	private Calendar birthDate = new GregorianCalendar();
	private Calendar deathDate = new GregorianCalendar();
	
	
	public PersonDeprecated(String IDi) {
		// Shell code for unique people in the system
		// In the final system, they would be stored as objects with values
		ID = IDi;
		
		
		
		
		
		deathDate = null;
	}


	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Calendar getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Calendar birthDate) {
		this.birthDate = birthDate;
	}

	public Calendar getDeathDate() {
		return deathDate;
	}

	public void setDeathDate(Calendar deathDate) {
		this.deathDate = deathDate;
	}

	public boolean checkBornBeforeDied() {

		// For any given person's unique ID, checks validity of m/d
		return true;
	}

}
