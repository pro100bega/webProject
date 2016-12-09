package by.htp6.hospital.service.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.service.SortPatientsService;
import by.htp6.hospital.tools.comparators.ComparatorProvider;

public class SortPatients implements SortPatientsService{

	@Override
	public void sortPatientsService(List<Patient> patients, String sortBy) {
		ComparatorProvider comparatorProvider = ComparatorProvider.getInstance();
		Comparator<Patient> comparator = comparatorProvider.getComparator(sortBy);
		
		Collections.sort(patients, comparator);
	}
	
}
