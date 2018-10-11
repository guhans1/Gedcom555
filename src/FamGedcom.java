import java.util.*;

public class FamGedcom {

	protected String famID = "";
	protected boolean isMarried = true;
	protected boolean isDivorced = false;
	protected String husbID = "";
	protected String husbName = "";
	protected String wifeID = "";
	protected String wifeName = "";
	protected ArrayList<String> children;
	protected Date marDate;
	protected Date divDate;

	public String getFamID() {
		return famID;
	}

	public void setFamID(String famID) {
		this.famID = famID;
	}

	public boolean isMarried() {
		return isMarried;
	}

	public String getHusbName() {
		return husbName;
	}

	public void setHusbName(String husbName) {
		this.husbName = husbName;
	}

	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName) {
		this.wifeName = wifeName;
	}

	public void setMarried(boolean isMarried) {
		this.isMarried = isMarried;
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

	public ArrayList<String> getChildren() {
		return children;
	}

	public String getChildrenAsString() {
		String out = "";
		for (String child : this.children) {
			out = out.concat(child.trim() + " ");
		}
		return out.trim();
	}

	public void setChildren(ArrayList<String> children) {
		this.children = children;
	}

	public void addChildren(String id) {
		this.children.add(id);
	}

	public void removeChildren(String id) {
		this.children.remove(id);
	}

}
