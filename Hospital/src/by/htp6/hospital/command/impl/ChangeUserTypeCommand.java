package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.ChangeUserTypeService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class ChangeUserTypeCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		
		User user = (User)session.getAttribute("authorisedUser");
		String authorisedUserType = user.getType();
		
		String newType = request.getParameter("newType");
		int userId = Integer.parseInt(request.getParameter("id"));
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		ChangeUserTypeService changeUserType = serviceFactory.getChangeUserType();
		
		try {
			changeUserType.changeUserType(authorisedUserType, newType, userId);
			
			String url = "controller?command=GET_USER_LIST";
			response.sendRedirect(url);
			
		} catch (ServiceException e) {
			String url = "error.jsp";
			response.sendRedirect(url);
		}
	}

}
