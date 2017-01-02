package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.LogInService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class SignInCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LogInService loginUserService = serviceFactory.getLoginUser();
		
		User user = null;
		try {
			user = loginUserService.logIn(username, password);
			String url = null;
			switch(user.getType()){
			case "doctor":
				url = "controller?command=GET_PATIENT_LIST" +
						"&currentPage=1&patientsPerPage=13";
				break;
			case "admin":
				url = "controller?command=GET_ADMIN_INFO";
				break;
			case "nurse":
				url = "controller?command=GET_PATIENT_LIST" + 
						"&currentPage=1&patientsPerPage=13";
				break;
			}
			session.setAttribute("authorisedUser", user);
			
			response.sendRedirect(url);
			
		} catch (ServiceException e) {
			String url = "index.jsp";
			request.setAttribute("error", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
		
		
	}
	
}
