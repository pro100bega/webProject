package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.constant.ParameterName;
import by.htp6.hospital.constant.Url;
import by.htp6.hospital.service.PerformAppointmentService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

/**
 * Команда выполнения назначения
 * 
 * Command designed to perform appointment
 * 
 * @author Begench Shamuradov, 2017
 */
public class PerformAppointmentCommand implements Command{
	
	private static final String APPOINTMENTS_DISPLAY_COMMAND =
			"controller?command=GET_APPOINTMENTS&status=undone&patientId=";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		User user = (User)session.getAttribute(ParameterName.AUTHORISED_USER);
		String userType = user.getType();
		int appointmentId = Integer.parseInt(request.getParameter(ParameterName.ID));
		String appointmentType = request.getParameter(ParameterName.TYPE);
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		PerformAppointmentService performAppointment = serviceFactory.getPerformAppointment();
		try {
			performAppointment.performAppointment(appointmentId, userType, appointmentType);
			Patient selectedPatient = (Patient)session.getAttribute(ParameterName.SELECTED_PATIENT);
			int selectedPatientId = selectedPatient.getId();
			String url = APPOINTMENTS_DISPLAY_COMMAND + selectedPatientId;
			response.sendRedirect(url);
		} catch (ServiceException e) {

			response.sendRedirect(Url.ERROR);
		}
	}

}
