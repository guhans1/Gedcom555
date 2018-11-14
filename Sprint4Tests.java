import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

public class Sprint4Tests {

	@Test
	public void birthBeforeMarriageOfParentsTrueTestUS08() {
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

		Person jeff = new Person();
		jeff.setID("I2");
		jeff.setBirthDate(1983, 12, 12);
		jeff.setDeathDate(2000, 10, 5);
		jeff.setDead(true);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.birthBeforeMarriageOfParents(family);

		Boolean valid = family.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void birthBeforeMarriageOfParentsFalseTestUS08() {
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

		Person jeff = new Person();
		jeff.setID("I2");
		jeff.setBirthDate(1978, 12, 12);
		jeff.setDeathDate(2000, 10, 5);
		jeff.setDead(true);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.birthBeforeMarriageOfParents(family);

		Boolean valid = family.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void parentsNotTooOldFalseTestUS12() {
		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1900, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("M");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");

		Person jeff = new Person();
		jeff.setID("I2");
		jeff.setBirthDate(1990, 12, 12);
		jeff.setDeathDate(2000, 10, 5);
		jeff.setDead(true);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.parentsNotTooOld(family);

		Boolean valid = family.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void parentsNotTooOldTrueTestUS12() {
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

		Person jeff = new Person();
		jeff.setID("I2");
		jeff.setBirthDate(1981, 12, 12);
		jeff.setDeathDate(2000, 10, 5);
		jeff.setDead(true);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.parentsNotTooOld(family);

		Boolean valid = family.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void birthBeforeDeathOfParentsTrueTestUS09() {
		Person john = new Person();
		john.setID("I1");
		john.setName("john smith");
		john.setBirthDate(1950, 12, 12);
		john.setGender("M");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setGender("F");

		Person jeff = new Person();
		jeff.setID("I2");
		john.setName("jeff smith");
		jeff.setBirthDate(1981, 12, 12);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.birthBeforeDeathOfParents(family);

		Boolean valid = family.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void birthBeforeDeathOfParentsFalseTestUS09() {
		Person john = new Person();
		john.setID("I1");
		john.setName("john smith");
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

		Person jeff = new Person();
		jeff.setID("I2");
		john.setName("jeff smith");
		jeff.setBirthDate(2001, 12, 12);
		jeff.setGender("M");

		Family family = new Family();
		family.setHusbID("I1");
		family.setHusband(john);
		family.setWifeID("I2");
		family.setWife(jane);
		family.setMarDate(1980, 10, 10);
		family.addChildren(jeff);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.birthBeforeDeathOfParents(family);

		Boolean valid = family.isValid();
		assertEquals(valid, false);
	}

	// US38
	@Test
	public void testListBirthdaysUS38() {

		Person indi1 = new Person();
		Person indi2 = new Person();
		Person indi3 = new Person();
		Person indi4 = new Person();

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");

		indi1.setBirthDate(1960, 4, 4);
		indi2.setBirthDate(2017, 10, 20);
		indi3.setBirthDate(2011, 2, 2);
		indi4.setBirthDate(1990, 1, 1);

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);

		ArrayList<Person> shouldBe = new ArrayList<Person>();
		shouldBe.add(indi2);

		assertEquals(shouldBe, PersonQuery.upcomingBirthdays(people));
	}
	
	// US29
		@Test
		public void testListAnniversariesUS39() {

			Person indi1 = new Person();
			Person indi2 = new Person();
			Person indi3 = new Person();
			Person indi4 = new Person();

			indi1.setID("I30001");
			indi2.setID("I30002");
			indi3.setID("I30003");
			indi4.setID("I30004");

			indi1.setBirthDate(1960, 4, 4);
			indi2.setBirthDate(1990, 10, 20);
			indi3.setBirthDate(2011, 2, 2);
			indi4.setBirthDate(1990, 1, 1);
			
			Family fam = new Family();
			fam.setHusband(indi1);
			fam.setWife(indi2);
			fam.setMarDate(2017, 10, 20);

			ArrayList<Person> people = new ArrayList<Person>();
			people.add(indi1);
			people.add(indi2);
			people.add(indi3);
			people.add(indi4);
			
			ArrayList<Family> families = new ArrayList<Family>();
			families.add(fam);

			ArrayList<Family> shouldBe = new ArrayList<Family>();
			shouldBe.add(fam);

			assertEquals(shouldBe, PersonQuery.upcomingAnniversaries(families));
		}
		
		@Test
		public void correspondingEntriesTestUS26() {
			Person indi1 = new Person();
			Person indi2 = new Person();
			Person indi3 = new Person();
			Person indi4 = new Person();

			indi1.setID("I30001");
			indi2.setID("I30002");
			indi3.setID("I30003");
			indi4.setID("I30004");

			indi1.setBirthDate(1960, 4, 4);
			indi2.setBirthDate(1990, 10, 20);
			indi3.setBirthDate(2011, 2, 2);
			indi4.setBirthDate(1990, 1, 1);
			
			Family fam = new Family();
			fam.setHusband(indi1);
			fam.setWife(indi2);
			fam.setMarDate(2017, 10, 20);

			ArrayList<Person> people = new ArrayList<Person>();
			people.add(indi1);
			people.add(indi2);
			people.add(indi3);
			people.add(indi4);
			
			ArrayList<Family> families = new ArrayList<Family>();
			families.add(fam);

			ArrayList<Family> shouldBe = new ArrayList<Family>();
			shouldBe.add(fam);
			
			HelperFunctions.allocatePersonToFam(people, families);

			assertEquals(shouldBe, families);
		}

}
