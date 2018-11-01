import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ReportingTool {
    public static boolean MaleLastNames(Family family) {// If the person is a MALE and doesn't share the name with the family, return false.
		for (int i = 0; i < family.getChildren().size(); i++) {
			PersonGedcom  child = getThePersonByTheId(family.getChildren().get(i));//I didn't find the function in the src, please change the getThePersonByTheId() function name for me!!!!!!!!!! 
			if (child.getGender().equals("M")) {
				String fatherLastName = family.getHusbName().split(" ")[1];
				String sonLastName = child.getName().split(" ")[1];
				if(!fatherLastName.equals(sonLastName)){
					return false;
				}
			}
		}
		return true;
	}

	public static boolean FewerThan15Siblings (Family family) {
		if(family==Null){
			System.out.println("FewerThan15Siblings() read a null family");
			return false;
		}
		return family.getChildren().size()<15;

	}

}
