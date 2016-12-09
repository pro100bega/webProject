package by.htp6.hospital.tools.comparators;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import by.htp6.hospital.bean.Patient;

public class ComparatorProvider {
	private static final ComparatorProvider instance = new ComparatorProvider();
	
	private Map<String, Comparator<Patient>> comparators = new HashMap<>();
	
	private ComparatorProvider(){
		comparators.put("name", new PatientNameComparator());
		comparators.put("surname", new PatientSurnameComparator());
		comparators.put("diagnosis", new PatientDiagnosisComparator());
	}
	
	public static ComparatorProvider getInstance(){
		return instance;
	}
	
	public Comparator<Patient> getComparator(String sortBy){
		Comparator<Patient> comparator;
		comparator = comparators.get(sortBy);
		return comparator;
	}
}
