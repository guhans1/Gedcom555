import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Family extends AbstractUnit {
	
	protected boolean isMarried = true;
	protected boolean isDivorced = false;
	protected String husbID = "";
	protected String wifeID = "";
	protected Date marDate;
	protected Date divDate;
	protected ArrayList<Person> children = new ArrayList<Person>();
	protected ArrayList<String> childrenIDs = new ArrayList<String>();
	
	public boolean isMarried() {
		return isMarried;
	}
	
	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
	}
	
	public boolean isDivorced() {
		return isDivorced;
	}
	
	public void setDivorced(boolean isDivorced) {
		this.isDivorced = isDivorced;
	}
	
	public String getHusbID() {
		return husbID;
	}
	
	public void setHusbID(String husbID) {
		this.husbID = husbID;
	}
	
	public String getWifeID() {
		return wifeID;
	}
	
	public void setWifeID(String wifeID) {
		this.wifeID = wifeID;
	}
	
	public Date getMarDate() {
		return marDate;
	}
	
	public String getMarDateAsString() {
		String longMarDate = marDate.toString();
		String[] tokens = longMarDate.split("\\s+");
		String marDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
		return marDate;
	}
	
	public void setMarDate(Date marDate) {
		this.marDate = marDate;
	}
	
	public void setMarDate(int iYear, int iMonth, int iDay) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(iYear, iMonth, iDay);
		this.marDate = cal2.getTime();
	}
	
	public Date getDivDate() {
		return divDate;
	}
	
	public String getDivDateAsString() {
		if (this.isDivorced()) {
			String longDivDate = divDate.toString();
			String[] tokens = longDivDate.split("\\s+");
			String divoDate = tokens[1].trim() + " " + tokens[2].trim() + " " + tokens[5].trim();
			return divoDate;
		} else {
			return "";
		}

	}
	
	public void setDivDate(Date divDate) {
		this.divDate = divDate;
	}
	
	public void setDivDate(int iYear, int iMonth, int iDay) {
		Calendar cal2 = Calendar.getInstance();
		cal2.set(iYear, iMonth, iDay);
		this.divDate = cal2.getTime();
	}
	
	public ArrayList<Person> getChildren() {
		return children;
	}
	
	public void setChildren(ArrayList<Person> children) {
		this.children = children;
	}
	
	public void addChildren(Person person) {
		children.add(person);
	}
	
	public void removeChildren(Person person) {
		children.remove(person);
	}

	public void setChildrenIDs(ArrayList<String> childrenIDs) {
		this.childrenIDs = childrenIDs;
	}
	
	public void addChildrenIDs(String child) {
		childrenIDs.add(child);
	}
	
	public ArrayList<String> getChildrenIDs() {
		return this.childrenIDs;
	}
	
	public String getChildrenIDsAsString() {
		String out = "";
		for (String child : this.childrenIDs) {
			out = out.concat(child.trim() + " ");
		}
		return out.trim();
	}
	

}
