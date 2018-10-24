import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class ListTest {
	
	@Test
	public void testListDeceased(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		
		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate(2017, 10, 10);
		indi2.setBirthDate(2016, 5, 5);
		indi3.setBirthDate(1990, 1, 1);
		indi4.setBirthDate(1990, 1, 1);
		
		indi1.setDeathDate(2003, 11, 1);
		indi2.setDeathDate(2017, 10, 2);
		indi1.setHasDied(true);
		indi2.setHasDied(true);
		//The default is false, change the test if you change the default.

		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);


		ArrayList<PersonGedcom> shouldBe = new ArrayList<PersonGedcom>();
		shouldBe.add(indi1);
		shouldBe.add(indi2);

		assertEquals(shouldBe,PersonQuery.listDeceased(people));
	}
	
	@Test
	public void testListRecentlyDeceased(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		
		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate(2017, 10, 10);
		indi2.setBirthDate(2016, 5, 5);
		indi3.setBirthDate(1990, 1, 1);
		indi4.setBirthDate(1990, 1, 1);
		
		indi1.setDeathDate(2003, 11, 1);
		indi2.setDeathDate(2018, 10, 17); // WARNING TEST WILL STOP WORKING AFTER 1 MONTH!!!
		indi1.setHasDied(true);
		indi2.setHasDied(true);
		//The default is false, change the test if you change the default.

		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);


		ArrayList<PersonGedcom> shouldBe = new ArrayList<PersonGedcom>();
		shouldBe.add(indi2);

		assertEquals(shouldBe,PersonQuery.listRecentDeaths(people));
	}
	
	@Test
	public void testListRecentlyBorn(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		
		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate(2017, 10, 10);
		indi2.setBirthDate(2016, 5, 5);
		indi3.setBirthDate(2018, 10, 10);
		indi4.setBirthDate(1990, 1, 1);
		
		indi1.setDeathDate(2003, 11, 1);
		indi2.setDeathDate(2018, 10, 17); // WARNING TEST WILL STOP WORKING AFTER 1 MONTH!!!
		indi1.setHasDied(true);
		indi2.setHasDied(true);
		//The default is false, change the test if you change the default.

		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);


		ArrayList<PersonGedcom> shouldBe = new ArrayList<PersonGedcom>();
		ArrayList<PersonGedcom> persontest = new ArrayList<PersonGedcom>();
		persontest = PersonQuery.listRecentBirths(people);
		shouldBe.add(indi3);
		
		String out = new String();
		
		for(PersonGedcom person : persontest) {
			out = out.concat(person.toString());
		}

		System.out.println(out + "!");
		assertEquals(shouldBe,PersonQuery.listRecentBirths(people));
	}
	
	@Test
	public void testListAliveAndSingle(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		
		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate(2017, 10, 10);
		indi2.setBirthDate(2016, 5, 5);
		indi3.setBirthDate(2018, 10, 18);
		indi4.setBirthDate(1990, 1, 1);
		
		indi1.setDeathDate(2003, 11, 1);
		indi2.setDeathDate(2018, 10, 17); // WARNING TEST WILL STOP WORKING AFTER 1 MONTH!!!
		indi1.setHasDied(true);
		indi2.setHasDied(true);
		//The default is false, change the test if you change the default.

		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);
		
		ArrayList<FamGedcom> families = new ArrayList<FamGedcom>();
		FamGedcom johnsfamily = new FamGedcom();
		johnsfamily.setFamID("F1");
		johnsfamily.setMarDate(1970, 10, 10);
		johnsfamily.setMarried(true);
		johnsfamily.setHusbID("I30001");
		johnsfamily.setWifeID("I30002");
		families.add(johnsfamily);

		ArrayList<PersonGedcom> shouldBe = new ArrayList<PersonGedcom>();
		shouldBe.add(indi3);
		shouldBe.add(indi4);
		
		assertEquals(shouldBe,PersonQuery.listAliveAndSingle(people, families));
	}
	
	
	
	
	
	

}
