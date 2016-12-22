package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class PerformAppointmentCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		User user = (User)session.getAttribute("authorisedUser");
		String userType = user.getType();
		int appointmentId = Integer.parseInt(request.getParameter("id"));
		String appointmentType = request.getParameter("type");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PerformAppointmentService performAppointment = serviceFactory.getPerformAppointment();
		try {
			performAppointment.performAppointment(appointmentId, userType, appointmentType);
			Patient selectedPatient = (Patient)session.getAttribute("selectedPatient");
			int selectedPatientId = selectedPatient.getId();
			String url = "controller?command=GET_APPOINTMENTS&status=undone&patientId=" + selectedPatientId;
			response.sendRedirect(url);
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
