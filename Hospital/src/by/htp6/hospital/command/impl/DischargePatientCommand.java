package by.htp6.hospital.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.DischargePatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class DischargePatientCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String finalDiagnosis = request.getParameter("finalDiagnosis");
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		DischargePatientService dischargePatientService = serviceFactory.getDischargePatient();
		try {
			dischargePatientService.dischargePatient(patientId, finalDiagnosis);
			HttpSession session = request.getSession(false);
			session.removeAttribute("patients");
			String url = "doctor/redirection.jsp";
			response.sendRedirect(url);
		} catch (ServiceException e) {
			HttpSession session = request.getSession(false);
			session.removeAttribute("patients");
			session.setAttribute("error", patientId);
			String url = "doctor/redirection.jsp";
			response.sendRedirect(url);
		}
		
		

	}

}
