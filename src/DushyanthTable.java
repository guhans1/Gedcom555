import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

public class DushyanthTable {

	public static void main(String[] args) {

		List<Person> persons = new ArrayList<Person>();

		for (int i = 0; i < 10; i++) {
			persons.add(new Person());
		}

		TableStringBuilder<Person> t = new TableStringBuilder<Person>();
		t.addColumn("ID", Person::getID);
		t.addColumn("Married", Person::getMarried);
		t.addColumn("Divorced", Person::getDivorced);
		t.addColumn("Husband ID", Person::getHusbandID);
		t.addColumn("Husband Name", Person::getHusbandName);
		t.addColumn("Wife ID", Person::getWifeID);
		t.addColumn("Wife Name", Person::getWifeName);
		t.addColumn("Children", Person::getChildren);
		String s = t.createString(persons);
		System.out.println(s);

	}

}


class Person

{

	private String ID;

	private String Married;

	private String Divorced;

	private String HusbandID;

	private String HusbandName;

	private String WifeID;

	private String WifeName;

	private String Children;

	Person()

	{

		ID = ("1");

		Married = ("1990-2-19");

		Divorced = ("NA");

		HusbandID = ("I01");

		HusbandName = ("Joe/Smith");

		WifeID = ("I07");

		WifeName = ("Jennifer/Smith");

		Children = ("{I26}, {I19}");

	}

	String getID()

	{

		return ID;

	}

	String getMarried()

	{

		return Married;

	}

	String getDivorced()

	{

		return Divorced;

	}

	String getHusbandID()

	{

		return HusbandID;

	}

	String getHusbandName()

	{

		return HusbandName;

	}

	String getWifeID()

	{

		return WifeID;

	}

	String getWifeName()

	{

		return WifeName;

	}

	String getChildren()

	{

		return Children;

	}

}
