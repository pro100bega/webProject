package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		
		String type = request.getParameter("type");
		String name = request.getParameter("name");
		String termDate = request.getParameter("termDate");
		String termTime = request.getParameter("termTime");
		
		ServiceFactory serviceFactory  = ServiceFactory.getInstance();
		AddAppointmentService addAppointmentService = serviceFactory.getAddAppointment();
		
		try {
			addAppointmentService.addAppointment(patientId, doctorId, type, name,
					termDate, termTime);

			String url = "controller?command=GET_PATIENT_INFO&status=undone&"
					+ "patientId=" + patientId;
			response.sendRedirect(url);
		} catch (ServiceException e) {
			request.setAttribute("errorMessage", e.getMessage());
			response.sendRedirect("error.jsp");
		}
	}

}
