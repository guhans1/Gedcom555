import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;

public class ChaoTest {
	
	@Test
	public void testMaleLastNames() {
		Family fam1 = new Family();
		Family fam2 = new Family();
		
		fam1.getHusbName("Daddy /Bean/");
		fam2.getHusbName("Daddy /Bean/");
		
		PersonGedcom child1 = new PersonGedcom();
		PersonGedcom child2 = new PersonGedcom();
		child1.setName("Irvin /Bean/");
		child2.setName("Irvin /Other/");
		child1.setGender("M");
		child2.setGender("M");

		ParseGEDCOMFile.indiList.put(20001, child1);
		ParseGEDCOMFile.indiList.put(20002, child2);
		
		fam1.setChildren(new ArrayList<String>(Arrays.asList("F01")));
		fam2.setChildren(new ArrayList<String>(Arrays.asList("F02")));


		assertEquals(true, ReportingTool.MaleLastNames(fam1));
		assertEquals(false, ReportingTool.MaleLastNames(fam2));

	}
	@Test
	public void testFewerThan15Siblings(){
	  Family family1 = new Family();
	  ArrayList<String> children1 = new ArrayList<String>();
	  children1.add("Kid1");
	  children1.add("Kid2");
	  children1.add("Kid3");
	  children1.add("Kid4");
	  family1.setChildren(children1);

	  ArrayList<String> children2 = new ArrayList<String>();
	  children2.add("Kid1");
	  children2.add("Kid2");
	  children2.add("Kid3");
	  children2.add("Kid4");
	  children2.add("Kid5");
	  children2.add("Kid6");
	  children2.add("Kid7");
	  children2.add("Kid8");
	  children2.add("Kid9");
	  children2.add("Kid10");
	  children2.add("Kid11");
	  children2.add("Kid12");
	  children2.add("Kid13");
	  children2.add("Kid14");
	  children2.add("Kid15");
	  family2.setChildren(children2);

	  assertEquals(true, ReportingTool.FewerThan15Siblings(family1));
	  assertEquals(false, ReportingTool.FewerThan15Siblings(family2));
	}
	

	
}
