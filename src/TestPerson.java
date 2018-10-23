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

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1830, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarried(true);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Guy is less than 150 years old GOOD!
	@Test
	void lessThan150YearsOldTest2() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1940, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Birth before death GOOD!
	@Test
	void birthBeforeDeathTest3() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Birth after Death BAD!
	@Test
	void birthBeforeDeathTest4() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1995, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Birth BAD!
	@Test
	void birthBeforeMarriageTest5() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1940, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Birth GOOD!
	@Test
	void birthBeforeMarriageTest6() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce before Birth BAD!
	@Test
	void birthBeforeDivorceTest7() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1920, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Birth before Divorce GOOD!
	@Test
	void birthBeforeDivorceTest8() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1980, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Marriage before Divorce GOOD!
	@Test
	void birthBeforeDivorceTest9() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1990, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce before Marriage BAD!
	@Test
	void MarriageBeforeDivorceTest10() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1989, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Marriage before Death GOOD!
	@Test
	void MarriageBeforeDeathTest11() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1991, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Marriage after Death BAD!
	@Test
	void MarriageBeforeDeathTest12() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(2001, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1989, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	// Divorce before Death GOOD!
	@Test
	void DivorceBeforeDeathTest13() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1990, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1991, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	// Divorce after Death BAD!
	@Test
	void DivorceBeforeDeathTest14() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1980, 10, 5);
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(2000, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}
<<<<<<< HEAD

	// Marriage after 14 GOOD!
=======
	
	//Differenece between birth date and marriage date is greater than 14 years GOOD!
>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366
	@Test
	void MarriageAfter14Test15() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
<<<<<<< HEAD
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
=======
		john.setBirthDate(1930, 12, 12);
		john.setDeathDate(1990, 10, 5);
>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366
		john.setHasDied(true);

		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
<<<<<<< HEAD
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setDivorced(true);
		johnsfamily.setDivDate(1990, 10, 10);
=======
		johnsfamily.setMarDate(1955, 10, 10);
>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

<<<<<<< HEAD
	// Marriage after 14 BAD!
	@Test
	void CorrectGenderForRoleTest17() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);
		john.setGender("M");
		john.setFams("F1");
		
		PersonGedcom jane = new PersonGedcom();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setHasDied(true);
		jane.setGender("F");
		jane.setFams("F1");
		
		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(john);
		people.add(jane);
		
		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
		johnsfamily.setMarDate(1960, 10, 10);
		johnsfamily.setMarried(true);
		johnsfamily.setHusbID("I1");
		johnsfamily.setWifeID("I2");
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families, people);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}
	
	// BAD!
	void CorrectGenderForRoleTest18() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(2000, 10, 5);
		john.setHasDied(true);
		john.setGender("M");
		john.setFams("F1");
		
		PersonGedcom jane = new PersonGedcom();
		jane.setID("I2");
		jane.setBirthDate(1950, 12, 12);
		jane.setDeathDate(2000, 10, 5);
		jane.setHasDied(true);
		jane.setGender("F");
		jane.setFams("F1");
		
		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(john);
		people.add(jane);
		
=======
	//Difference between birth date and marriage date is less than 14 years BAD!
	@Test
	void MarriageAfter14Test16() {

		PersonGedcom john = new PersonGedcom();
		john.setID("I1");
		john.setBirthDate(1930, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setHasDied(true);

>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366
		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		String famID = john.getFams();
		johnsfamily.setFamID(famID);
<<<<<<< HEAD
		johnsfamily.setMarDate(1960, 10, 10);
		johnsfamily.setMarried(true);
		johnsfamily.setHusbID("I1");
		johnsfamily.setWifeID("I2");
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families, people);
=======
		johnsfamily.setMarDate(1940, 10, 10);
		families.add(johnsfamily);

		ValidityChecker vc = new ValidityChecker();
		vc.checkValidity(john, families);
>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}
<<<<<<< HEAD
	
	
	
	
	
	
=======

>>>>>>> aa3eb538018de734e84ed722aa7b419ce7649366
}
