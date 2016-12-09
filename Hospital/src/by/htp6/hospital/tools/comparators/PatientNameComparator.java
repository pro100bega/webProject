package by.htp6.hospital.tools.comparators;

import java.util.Comparator;

import by.htp6.hospital.bean.Patient;

public class PatientNameComparator implements Comparator<Patient>{
	@Override
	public int compare(Patient o1, Patient o2) {
		return o1.getName().compareTo(o2.getName());
	}
}
