package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Appointment;
import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.GetAppointmentListService;
import by.htp6.hospital.service.GetPatientService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class GetPatientInfoCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		User user = (User)session.getAttribute("authorisedUser");
		String userType = user.getType();
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		GetPatientService getPatient = serviceFactory.getGetPatient();
		GetAppointmentListService getAppointmentList = serviceFactory.getGetAppointmentList();
		
		try {
			String url = "error.jsp";
			if (userType.equals("doctor")){
				url = "doctor/patientPage.jsp";
			} else if (userType.equals("nurse")) {
				url = "doctor/patientPage.jsp";
			}
			
			Patient selectedPatient = getPatient.getPatient(patientId);
			String status = request.getParameter("status");
			List<Appointment> appointents = getAppointmentList.
					getAppointmentList(selectedPatient.getId(), status);

			session.setAttribute("selectedPatient", selectedPatient);
			session.setAttribute("appointments", appointents);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} catch (ServiceException e) {
			
			String url = "error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		} 
	}

}
