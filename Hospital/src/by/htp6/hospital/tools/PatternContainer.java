package by.htp6.hospital.tools;

import java.util.regex.Pattern;

public class PatternContainer {
	//User name format pattern
	public static final Pattern usernamePattern = 
			Pattern.compile("[0-9a-zA-Z_]{6,20}");
	
	//Password format pattern
	public static final Pattern passwordPattern = 
			Pattern.compile("[0-9a-zA-Z]{6,20}");
	
	//Diagnosis format pattern
	public static final Pattern diagnosisPattern =
			Pattern.compile("^[А-Яа-я \\-]{1,20}$|^[A-Za-z \\-]{1,50}$");
	
	//Patient name format pattern
	public static final Pattern patientNamePattern = 
			Pattern.compile("^[А-Яа-я]{1,20}$|^[A-Za-z]{1,20}$");
	
}
