package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.PatientTransmitorService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class DisplayPatientsCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("tut2");
		HttpSession session = request.getSession(false);
		
		// if patient list does not exist in session scope
		if (session.getAttribute("patients") == null){
			try {
				int doctorId = Integer.parseInt(request.getParameter("doctorId"));
				ServiceFactory serviceFactory = ServiceFactory.getInstance();
				PatientTransmitorService patientTransmitor = serviceFactory.getPatientTransmitor();
				List<Patient> patients = patientTransmitor.transmitPatient(doctorId);
				session.setAttribute("patients", patients);
				String url = "doctor/doctorPanel.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			} 
			// If something gone wrong with getting patient list
			catch (ServiceException e) {
				String url = "error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		}
		// if patient list already exists in session scope
		else {
			String url = "doctor/doctorPanel.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
	}

}
