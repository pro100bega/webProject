package by.htp6.hospital.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.BookUserService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class BookUserCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
				
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		BookUserService bookUserService = serviceFactory.getBookUser();
		
		User user = null;
		try {
			user = bookUserService.book(username, password);
			request.setAttribute("user", user);
			
			String url = "WEB-INF/bookSuccess.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			String url = "error.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
		}
				
	}
	
}