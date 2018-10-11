import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class Homework4TestClass {

	// Test 1 for 1 person with correct dates
	@Test
	void test1() {
		PersonGedcom john = new PersonGedcom();
		john.setID("John");
		john.setBirthDate(1930, 12, 12);
		john.setDeathDate(1990, 10, 5);

		boolean bool;

		if (john.getBirthDate().before(john.getDeathDate())) {
			bool = true;
		} else {
			bool = false;
		}

		assertEquals(bool, true);
	}

	// Person with invalid dates (died before born)
	@Test
	void test2() {
		PersonGedcom john = new PersonGedcom();
		john.setID("John");
		john.setBirthDate(1990, 12, 12);
		john.setDeathDate(1930, 10, 5);

		boolean bool;

		if (john.getBirthDate().before(john.getDeathDate())) {
			bool = true;
		} else {
			bool = false;
		}

		assertEquals(bool, false);
	}

	// Person with birth and death on same day
	// This is a special case where it could be true and false
	// Thus, we'd need a specific time, which may not be given
	// Thus, the code assumes validity
	@Test
	void test3() {
		PersonGedcom john = new PersonGedcom();
		john.setID("John");
		john.setBirthDate(1900, 12, 12);
		john.setDeathDate(1900, 12, 12);

		boolean bool;

		if (john.getBirthDate().equals((john.getDeathDate()))) {
			bool = true;
		} else {
			bool = false;
		}

		assertEquals(bool, true);
	}

	// Person with extremely weird but valid dates
	@Test
	void test4() {
		PersonGedcom john = new PersonGedcom();
		john.setID("John");
		john.setBirthDate(1900, 12, 12);
		john.setDeathDate(3000, 10, 5); // The year 3000

		boolean bool;

		if (john.getBirthDate().before(john.getDeathDate())) {
			bool = true;
		} else {
			bool = false;
		}

		assertEquals(bool, true);
	}

	// Person with extremely close and invalid dates
	@Test
	void test5() {
		PersonGedcom john = new PersonGedcom();
		john.setID("John");
		john.setBirthDate(3000, 12, 12);
		john.setDeathDate(3000, 12, 11);

		boolean bool;

		if (john.getBirthDate().before(john.getDeathDate())) {
			bool = true;
		} else {
			bool = false;
		}

		assertEquals(bool, false);
	}

}
