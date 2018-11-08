import java.util.ArrayList;

public class AbstractUnit {
	
	protected String ID = "NA";
	protected boolean valid = true;
	protected ArrayList<String> invalidType = new ArrayList<String>();

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public ArrayList<String> getInvalidType() {
		return invalidType;
	}

	public void setInvalidType(ArrayList<String> lol) {
		invalidType = lol;
	}
	
	public void addInvalidType(String s) {
		invalidType.add(s);
	}

	public boolean isValid() {
		if(invalidType.isEmpty()) {
			return true;
		}
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
		
}
