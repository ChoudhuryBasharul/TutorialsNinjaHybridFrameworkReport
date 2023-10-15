package timeStampAndDateDemo;

import java.util.Date;

public class timeAndDate {

	public static void main(String[] args) {
		
		// This is Method Chaining concept.
		
		 Date date = new Date();
		 System.out.println(date.toString().replace(" ", "_").replace(":", "_"));
		 
		 
		// This is the full code below for above line code
		
//		String dateText = date.toString();
//		String dateTextWithoutSpaces = dateText.replace(" ", "_");
//		String dateTextWithoutSpacesAndColon = dateTextWithoutSpaces.replace(":", "_");
//		
//		System.out.println(dateTextWithoutSpacesAndColon);
		 

	}

}
