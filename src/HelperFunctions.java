import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class HelperFunctions {

	public void allocatePersonToFam(PersonGedcom person, ArrayList<FamGedcom> families) {
		
		// Purpose is to connect each person with family(ies)
		// Each person has famc and (current) fams
		// They may have belonged to families in the past (e.g divorced and remarried)
		
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
		//      【﻿Ｕｎｄｅｒ　ｃｏｎｓｔｒｕｃｔｉｏｎ】	      //
		//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//	
	}
	
	public static int differenceInDatesInYears(Date earlierDate, Date laterDate) {
		Calendar dod = Calendar.getInstance();
		dod.setTime(laterDate);
		Calendar dob = Calendar.getInstance();
		dob.setTime(earlierDate);
		int year1 = dod.get(Calendar.YEAR);
		int year2 = dob.get(Calendar.YEAR);
		int age = year1 - year2;
		int month1 = dod.get(Calendar.MONTH);
		int month2 = dob.get(Calendar.MONTH);
		if (month2 > month1) {
			age--;
		} else if (month1 == month2) {
			int day1 = dod.get(Calendar.DAY_OF_MONTH);
			int day2 = dob.get(Calendar.DAY_OF_MONTH);
			if (day2 > day1) {
				age--;
			}
		}
		return age;
	}
	
	public static long differenceInDatesInDays(Date earlierDate, Date laterDate) {
	    long diffInMilliseconds = laterDate.getTime() - earlierDate.getTime();
	    return TimeUnit.DAYS.convert(diffInMilliseconds,TimeUnit.MILLISECONDS);
	}
	
	
	public static int changeMonthFormatToInt(String month) {
		int monthNo = 0;
		if (month.equals("JAN")) {
			monthNo = 0;
		}
		if (month.equals("FEB")) {
			monthNo = 1;
		}
		if (month.equals("MAR")) {
			monthNo = 2;
		}
		if (month.equals("APR")) {
			monthNo = 3;
		}
		if (month.equals("MAY")) {
			monthNo = 4;
		}
		if (month.equals("JUN")) {
			monthNo = 5;
		}
		if (month.equals("JUl")) {
			monthNo = 6;
		}
		if (month.equals("AUG")) {
			monthNo = 7;
		}
		if (month.equals("SEP")) {
			monthNo = 8;
		}
		if (month.equals("OCT")) {
			monthNo = 9;
		}
		if (month.equals("NOV")) {
			monthNo = 10;
		}
		if (month.equals("DEC")) {
			monthNo = 11;
		}
		return monthNo;
	}
}
