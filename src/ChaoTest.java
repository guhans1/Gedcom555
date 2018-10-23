//Test for list the dead.
//I create 4 people, 2 of them dead, 2 of them alive.
//I assume that the dead date is correct(It would show some invaild date)
//We may need a extra compare two arraylist function in the future.




import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import org.junit.Test;



public class ChaoTest {
	//	@Test
	public void testListDeceased(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate("01 NOV 2017");
		indi2.setBirthDate("01 NOV 2016");
		indi3.setBirthDate("01 JAN 1990");
		indi4.setBirthDate("01 JAN 1990");
		
		indi1.setDeathDate("03 NOV 2003");
		indi2.setDeathDate("03 OCT 2017");
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

		System.out.println(ReportingTool3.listRecentBirths());
		assertEquals(shouldBe,ListDeceased(people));
	}


	//	@Test
	public void testListRecentlyDeceased(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		
		indi1.setBirthDate("01 NOV 2017");
		indi2.setBirthDate("01 NOV 2016");
		indi3.setBirthDate("01 JAN 1990");
		indi4.setBirthDate("01 JAN 1990");
		
		indi1.setDeathDate("21 OCT 2003");//CHANGE THIS TO RECENTLY DATE
		indi2.setDeathDate("03 JAN 2017");//CHANGE THIS TO OTHER DATE
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
		assertEquals(shouldBe,ListDeceased(people));
	}
	//	@Test
	public void testListRecentlyBorn(){
		PersonGedcom indi1 = new PersonGedcom();
		PersonGedcom indi2 = new PersonGedcom();
		PersonGedcom indi3 = new PersonGedcom();
		PersonGedcom indi4 = new PersonGedcom();
		

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		//CHANGE THE DATE IF NEEDED!!!
		indi1.setBirthDate("30 SEP 2017");
		indi2.setBirthDate("11 JAN 2016");
		indi3.setBirthDate("11 OCT 1990");
		indi4.setBirthDate("11 JAN 1990");
		
		indi1.setDeathDate("21 OCT 2003");
		indi2.setDeathDate("03 JAN 2017");
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
		shouldBe.add(indi3);
		assertEquals(shouldBe,ListDeceased(people));
	}


//	@Test
	public void testListALiveAndSingle(){

		PersonGedcom indi1 = new PersonGedcom();//Dead and married
		PersonGedcom indi2 = new PersonGedcom();//Dead and single
		PersonGedcom indi3 = new PersonGedcom();//Alive and married
		PersonGedcom indi4 = new PersonGedcom();//Alive and single(what we want)
		

		indi1.setID("I30001");
		indi2.setID("I30002");
		indi3.setID("I30003");
		indi4.setID("I30004");
		//CHANGE THE DATE IF NEEDED!!!
		indi1.setBirthDate("30 SEP 2017");
		indi2.setBirthDate("11 JAN 2016");
		indi3.setBirthDate("11 OCT 1990");
		indi4.setBirthDate("11 JAN 1990");
		
		indi1.setDeathDate("21 OCT 2003");
		indi2.setDeathDate("03 JAN 2017");
		indi1.setHasDied(true);
		indi2.setHasDied(true);

		indi1.setMarried(true);
		indi3.setMarried(true);


		//The default is false, change the test if you change the default.

		ArrayList<PersonGedcom> people = new ArrayList<PersonGedcom>();
		people.add(indi1);
		people.add(indi2);
		people.add(indi3);
		people.add(indi4);


		ArrayList<PersonGedcom> shouldBe = new ArrayList<PersonGedcom>();
		shouldBe.add(indi4);
		assertEquals(shouldBe,ListDeceased(people));
	}

}
