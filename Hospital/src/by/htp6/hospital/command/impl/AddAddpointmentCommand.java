package by.htp6.hospital.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.AddAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class AddAddpointmentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		User user = (User)session.getAttribute("authorisedUser");
		int doctorId = user.getId();
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String type = request.getParameter("procedureType");
		String name = request.getParameter("procedureName");
		
		ServiceFactory serviceFactory  = ServiceFactory.getInstance();
		AddAppointmentService addAppointmentService = serviceFactory.getAddAppointment();
		
		try {
			addAppointmentService.addAppointment(patientId, doctorId, type, name);
			Patient selectedPatient = (Patient)session.getAttribute("selectedPatient");
			int selectedPatientId = selectedPatient.getId();
			String url = "controller?command=GET_APPOINTMENTS&patientId=" + selectedPatientId;
			response.sendRedirect(url);
		} catch (ServiceException e) {
			response.sendRedirect("error.jsp");
		}
	}

}