package by.htp6.hospital.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.AssignProcedureService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class AssignProcedureCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		User user = (User)session.getAttribute("authorisedUser");
		int doctorId = user.getId();
		int patientId = Integer.parseInt(request.getParameter("patientId"));
		String type = request.getParameter("procedureType");
		String name = request.getParameter("procedureName");
		
		ServiceFactory serviceFactory  = ServiceFactory.getInstance();
		AssignProcedureService assignProcedurService = serviceFactory.getAssignProcedure();
		
		try {
			assignProcedurService.assignProcedure(patientId, doctorId, type, name);
			String url = "doctor/redirection.jsp";
			response.sendRedirect(url);
		} catch (ServiceException e) {
			
		}
	}

}
