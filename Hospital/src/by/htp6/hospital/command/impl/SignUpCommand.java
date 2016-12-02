package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.LogUpService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class SignUpCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String userType = request.getParameter("userType");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		LogUpService logUpUserService = serviceFactory.getLogUpUser();
		
		User user = null;
		try {
			user = logUpUserService.logUp(username, password, userType);
			request.setAttribute("user", user);
			
			String url = "WEB-INF/signUpSuccess.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			String url = "signUp.jsp";
			request.setAttribute("error", "true");
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
				
	}
	
}
