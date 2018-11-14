import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class Sprint2Tests {

	@Test
	public void marriageAfter14TrueTestUS10() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("M");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void marriageAfter14FalseTestUS10() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1970, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("M");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void correctGenderForRoleTrueTestUS21() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("M");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void correctGenderForRoleFalseTestUS21() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("F");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);

		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// US31
	@Test
	public void testListLivingAndSingleUS31() {

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(1960, 10, 10);
		indi2.setBirthDate(1961, 5, 5);
		indi3.setBirthDate(1960, 10, 18);
		indi4.setBirthDate(1990, 1, 1);

		indi1.setDeathDate(2017, 10, 10);
		indi2.setDeathDate(2016, 5, 5);
		indi1.setDead(true);
		indi2.setDead(true);

		Family family = new Family();
		family.setID("F1");
		family.setHusbID("I30001");
		family.setHusband(indi1);
		family.setWifeID("I30003");
		family.setWife(indi3);
		indi1.addFamily(family);
		indi3.addFamily(family);
		indi1.setFams("F1");
		indi3.setFams("F1");

		ArrayList<Family> families = new ArrayList<Family>();
		families.add(family);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi4);

		assertEquals(shouldBe, PersonQuery.listAliveAndSingle(people, families));
	}

	// US35
	@Test
	public void testListRecentlyBornUS35() {

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(1960, 10, 10);
		indi2.setBirthDate(1961, 5, 5);
		indi3.setBirthDate(1960, 10, 18);
		indi4.setBirthDate(2018, 11, 11);

		indi1.setDeathDate(2017, 10, 10);
		indi2.setDeathDate(2016, 5, 5);
		indi1.setDead(true);
		indi2.setDead(true);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi4);

		assertEquals(shouldBe, PersonQuery.listRecentBirths(people));
	}

	// US35
	@Test
	public void testListRecentDeathsUS36() {

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(1960, 10, 10);
		indi2.setBirthDate(1940, 5, 5);
		indi3.setBirthDate(1960, 10, 18);
		indi4.setBirthDate(1940, 11, 11);

		indi1.setDeathDate(2017, 10, 10);
		indi2.setDeathDate(2018, 11, 11);
		indi1.setDead(true);
		indi2.setDead(true);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi2);

		assertEquals(shouldBe, PersonQuery.listRecentDeaths(people));
	}

	// US29
	@Test
	public void testListDeceasedUS29() {

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(1960, 10, 10);
		indi2.setBirthDate(1961, 5, 5);
		indi3.setBirthDate(2011, 10, 18);
		indi4.setBirthDate(1990, 1, 1);

		indi1.setDeathDate(2017, 10, 10);
		indi2.setDeathDate(2016, 5, 5);
		indi1.setDead(true);
		indi2.setDead(true);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi1);
		shouldBe.add(indi2);

		assertEquals(shouldBe, PersonQuery.listDeceased(people));
	}

}
