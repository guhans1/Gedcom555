import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.Test;

public class Sprint3Tests {

	// US11
	@Test
	public void noBigamyFalseTestUS11() {
		ArrayList<Family> families = new ArrayList<Family>();
		Family f1 = new Family();
		f1.setMarDate(1980, 10, 10);
		f1.setMarried(true);
		families.add(f1);
		Family f2 = new Family();
		f2.setMarDate(1981, 10, 10);
		f2.setMarried(true);
		families.add(f2);
		Person p1 = new Person();
		p1.setFamilies(families);
		Validity vc = new Validity();
		Boolean bool = vc.noBigamy(p1);
		assertEquals(bool, false);
	}

	// US11
	@Test
	public void noBigamyTrueTestUS11() {
		ArrayList<Family> families = new ArrayList<Family>();
		Family f1 = new Family();
		f1.setMarDate(1980, 10, 10);
		f1.setMarried(true);
		families.add(f1);
		Family f2 = new Family();
		f2.setMarDate(1981, 10, 10);
		f2.setMarried(false);
		families.add(f2);
		Person p1 = new Person();
		p1.setFamilies(families);
		Validity vc = new Validity();
		Boolean bool = vc.noBigamy(p1);
		assertEquals(bool, true);
	}

	// US01
	@Test
	public void noDatesBeforeCurrDateTest1US01() {
		Person p1 = new Person();
		p1.setBirthDate(3000, 6, 2);
		p1.setDeathDate(3001, 6, 2);
		Validity vc = new Validity();
		Boolean bool = vc.datesBeforeCurrentDate(p1);
		assertEquals(bool, false);
	}

	// US01
	@Test
	public void noDatesBeforeCurrDateTest2US01() {
		Person p1 = new Person();
		p1.setBirthDate(2000, 6, 2);
		p1.setDeathDate(2001, 6, 2);
		Validity vc = new Validity();
		Boolean bool = vc.datesBeforeCurrentDate(p1);
		assertEquals(bool, true);
	}

	public void noDatesBeforeCurrDateTest3US01() {
		Family p1 = new Family();
		p1.setMarDate(2000, 6, 2);
		p1.setDivDate(2001, 6, 2);
		Validity vc = new Validity();
		Boolean bool = vc.datesBeforeCurrentDate(p1);
		assertEquals(bool, true);
	}

	public void noDatesBeforeCurrDateTest4US01() {
		Family p1 = new Family();
		p1.setMarDate(2000, 6, 2);
		p1.setDivDate(3005, 6, 2);
		Validity vc = new Validity();
		Boolean bool = vc.datesBeforeCurrentDate(p1);
		assertEquals(bool, false);
	}

	// US27
	@Test
	public void testIfPersonHasAgeUS27() {
		boolean bool;
		Person p1 = new Person();
		p1.setBirthDate(1970, 10, 10);
		if (p1.getAge() > -1) {
			bool = true;
		} else {
			bool = false;
		}
		assertEquals(bool, true);
	}

	// US22
	@Test
	public void uniqueIDsTest1US22() {
		ArrayList<Person> people = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setID("I1");
		people.add(p1);
		Person p2 = new Person();
		p2.setID("I2");
		people.add(p2);
		Validity vc = new Validity();
		Boolean bool = vc.uniquePersonIDs(people);
		assertEquals(bool, true);
	}

	// US22
	@Test
	public void uniqueIDsTest2US22() {
		ArrayList<Person> people = new ArrayList<Person>();
		Person p1 = new Person();
		p1.setID("I1");
		people.add(p1);
		Person p2 = new Person();
		p2.setID("I1");
		people.add(p2);
		Validity vc = new Validity();
		Boolean bool = vc.uniquePersonIDs(people);
		assertEquals(bool, false);
	}

	// US22
	@Test
	public void uniqueIDsTest3US22() {
		ArrayList<Family> people = new ArrayList<Family>();
		Family p1 = new Family();
		p1.setID("I1");
		people.add(p1);
		Family p2 = new Family();
		p2.setID("I2");
		people.add(p2);
		Validity vc = new Validity();
		Boolean bool = vc.uniqueFamilyIDs(people);
		assertEquals(bool, true);
	}

	// US22
	@Test
	public void uniqueIDsTest4US22() {
		ArrayList<Family> people = new ArrayList<Family>();
		Family p1 = new Family();
		p1.setID("I1");
		people.add(p1);
		Family p2 = new Family();
		p2.setID("I1");
		people.add(p2);
		Validity vc = new Validity();
		Boolean bool = vc.uniqueFamilyIDs(people);
		assertEquals(bool, false);
	}

	// US15
	@Test
	public void lessThan15SiblingsTrueTestUS15() {
		Family family = new Family();
		family.addChildrenIDs("1");
		family.addChildrenIDs("2");
		family.addChildrenIDs("3");
		family.addChildrenIDs("4");
		family.addChildrenIDs("5");
		Validity vc = new Validity();
		Boolean bool = vc.lessThan15Siblings(family);
		assertEquals(bool, true);
	}

	// US15
	@Test
	public void lessThan15SiblingsFalseTestUS15() {
		Family family = new Family();
		family.addChildrenIDs("1");
		family.addChildrenIDs("2");
		family.addChildrenIDs("3");
		family.addChildrenIDs("4");
		family.addChildrenIDs("5");
		family.addChildrenIDs("6");
		family.addChildrenIDs("7");
		family.addChildrenIDs("8");
		family.addChildrenIDs("9");
		family.addChildrenIDs("10");
		family.addChildrenIDs("11");
		family.addChildrenIDs("12");
		family.addChildrenIDs("13");
		family.addChildrenIDs("14");
		family.addChildrenIDs("15");
		family.addChildrenIDs("16");
		Validity vc = new Validity();
		Boolean bool = vc.lessThan15Siblings(family);
		assertEquals(bool, false);
	}

	// US30
	@Test
	public void testListAliveAndMarriedUS30() {

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsFamily = new Family();
		johnsFamily.setID("F1");
		johnsFamily.setMarDate(1970, 10, 10);
		johnsFamily.setMarried(true);
		johnsFamily.setHusbID("I30001");
		johnsFamily.setWifeID("I30002");
		families.add(johnsFamily);

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.addFamily(johnsFamily);
		indi2.addFamily(johnsFamily);

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(2017, 10, 10);
		indi2.setBirthDate(2016, 5, 5);
		indi3.setBirthDate(2018, 10, 18);
		indi4.setBirthDate(1990, 1, 1);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi1);
		shouldBe.add(indi2);

		assertEquals(shouldBe, PersonQuery.listLivingMarried(people));
	}

}
