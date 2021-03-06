import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import static java.lang.Math.*;

public class TestPerson {

	// REMEMBER: You need to give enough dummy info for the tests to work
	// The validity checker needs a marriage date at least to work

	// Check all our sprint 1 user stories:
	// Less than 150 years old, Tests: 1, 2
	// Birth before Death, Tests: 3, 4
	// Birth before Marriage, Tests: 5,6
	// Birth before Divorce, Tests: 7,8
	// Marriage before Divorce, Tests: 9,10
	// Marriage before Death, Tests: 11,12
	// Divorce before Death, Tests: 13,14
	// Marriage after 13, Tests: 15, 16

	// Guy is more than 150 years old BAD!
	@Test
	void lessThan150YearsOldTest1() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1830, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarried(true);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Guy is less than 150 years old GOOD!
	@Test
	void lessThan150YearsOldTest2() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1940, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Birth before death GOOD!
	@Test
	void birthBeforeDeathTest3() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Birth after Death BAD!
	@Test
	void birthBeforeDeathTest4() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1995, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Birth BAD!
	@Test
	void birthBeforeMarriageTest5() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1940, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Birth GOOD!
	@Test
	void birthBeforeMarriageTest6() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce before Birth BAD!
	@Test
	void birthBeforeDivorceTest7() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1920, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Birth before Divorce GOOD!
	@Test
	void birthBeforeDivorceTest8() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1980, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Marriage before Divorce GOOD!
	@Test
	void birthBeforeDivorceTest9() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1990, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce before Marriage BAD!
	@Test
	void MarriageBeforeDivorceTest10() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1989, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Death GOOD!
	@Test
	void MarriageBeforeDeathTest11() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1991, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Marriage after Death BAD!
	@Test
	void MarriageBeforeDeathTest12() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(2001, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1989, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Divorce before Death GOOD!
	@Test
	void DivorceBeforeDeathTest13() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1991, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce after Death BAD!
	@Test
	void DivorceBeforeDeathTest14() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1980, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(2000, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage after 14 GOOD!
	@Test
	void MarriageAfter14Test15() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1990, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Marriage after 14 GOOD!
	@Test
	void MarriageAfter14Test16() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1990, 10, 10);
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage after 14 BAD!
	@Test
	void CorrectGenderForRoleTest17() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("M");
		john.setFams("F1");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");
		jane.setFams("F1");

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(john);
		people.add(jane);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		johnsfamily.setMarried(true);
		johnsfamily.setHusbID("I1");
		johnsfamily.setWifeID("I2");
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// BAD!
	@Test
	void CorrectGenderForRoleTest18() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setDead(true);
		john.setGender("F");
		john.setFams("F1");

		Person jane = new Person();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setDead(true);
		jane.setGender("F");
		jane.setFams("F1");

		ArrayList<Person> people = new ArrayList<Person>();
		people.add(john);
		people.add(jane);

		ArrayList<Family> families = new ArrayList<Family>();
		Family johnsfamily = new Family();
		String famID = john.getFams();
		johnsfamily.setID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		johnsfamily.setMarried(true);
		johnsfamily.setHusbID("I1");
		johnsfamily.setWifeID("I2");
		families.add(johnsfamily);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

}
