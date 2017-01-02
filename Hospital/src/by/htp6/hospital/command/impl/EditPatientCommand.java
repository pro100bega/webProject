package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.EditPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class EditPatientCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		Patient selectedPatient = (Patient)session.getAttribute("selectedPatient");
		int patientId = selectedPatient.getId();
		
		String name = request.getParameter("name");
		String surname = request.getParameter("surname");
		String sex = request.getParameter("sex");
		String birthDate = request.getParameter("birthDate");
		String diagnosis = request.getParameter("diagnosis");
		String note = request.getParameter("note");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		EditPatientService editPatient = serviceFactory.getEditPatient();
		
		try {
			editPatient.editPatitnt(patientId, name, surname, sex, birthDate, diagnosis, note);
			response.sendRedirect("controller?command=GET_PATIENT_INFO&"
					+ "status=undone&patientId="+patientId);
		} catch (ServiceException e) {
			response.sendRedirect("doctor/error.jsp");
		}
	}

}
