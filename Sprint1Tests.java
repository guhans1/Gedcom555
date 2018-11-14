import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import org.junit.Test;

public class Sprint1Tests {

	@Test
	public void lessThan150YearsOldFalseTest() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1830, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void lessThan150YearsOldTrueTest() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1940, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void birthBeforeDeathTrueTest() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void birthBeforeDeathFalseTest() {

		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1995, 12, 12);
		john.setDeathDate(1990, 10, 5);
		john.setDead(true);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void birthBeforeMarriageFalseTest() {

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
		family.setMarDate(1920, 10, 10);

		john.addFamily(family);

		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void birthBeforeMarriageTrueTest() {

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
	public void marriageBeforeDivorceFalseTest() {

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
		family.setDivorced(true);
		family.setDivDate(1970, 10, 10);
		
		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		
		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}

	@Test
	public void marriageBeforeDivorceTrueTest() {

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
		family.setDivorced(true);
		family.setDivDate(1990, 10, 10);

		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void marriageBeforeDeathTrueTest() {

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
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
	}

	@Test
	public void marriageBeforeDeathFalseTest() {

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
		family.setMarDate(2005, 10, 10);

		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
	}
	
	@Test
	public void divorceBeforeDeathFalseTest() {
		
		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1989, 10, 5);
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
		family.setDivorced(true);
		family.setDivDate(1990, 10, 10);

		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, false);
		
	}
	
	@Test
	public void divorceBeforeDeathTrueTest() {
		
		Person john = new Person();
		john.setID("I1");
		john.setBirthDate(1950, 12, 12);
		john.setDeathDate(1991, 10, 5);
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
		family.setDivorced(true);
		family.setDivDate(1990, 10, 10);

		john.addFamily(family);
		jane.addFamily(family);
		Validity vc = new Validity();
		vc.validityChecker(john);

		Boolean valid = john.isValid();
		assertEquals(valid, true);
		
	}

}
