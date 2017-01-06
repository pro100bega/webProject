package by.htp6.hospital.command.impl;

import java.io.IOException;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.GetUserListService;
import by.htp6.hospital.service.GetUsersCountService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class GetUserListCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);

		User user = (User) session.getAttribute("authorisedUser");
		String userType = user.getType();

		String requestCurrentPage = request.getParameter("currentPage");
		int currentPage;
		if (requestCurrentPage == null) {
			String sessionCurrentPage = "" + session.getAttribute("currentPage");
			currentPage = (sessionCurrentPage == null) 
					? 1 
					: Integer.parseInt(sessionCurrentPage);
		} else {
			currentPage = Integer.parseInt(requestCurrentPage);
		}

		String requestUsersPerPage = request.getParameter("usersPerPage");
		int usersPerPage;
		if (requestUsersPerPage == null) {
			String sessionUsersPerPage = "" + 
		session.getAttribute("usersPerPage");
			usersPerPage = (sessionUsersPerPage == null) 
					? 10 
					: Integer.parseInt(sessionUsersPerPage);
		} else {
			usersPerPage = Integer.parseInt(requestUsersPerPage);
		}

		ServiceFactory serviceFactory = ServiceFactory.getInstance();
		GetUsersCountService getUsersCount = serviceFactory.getGetUsersCount();
		GetUserListService getUserList = serviceFactory.getGetUserList();

		try {
			int usersCount = getUsersCount.getUsersCount();
			int offset = usersPerPage * (currentPage - 1);
			List<User> users = getUserList.getUserList(userType, offset, 
					usersPerPage);
		
			session.setAttribute("currentPage", currentPage);
			session.setAttribute("usersPerPage", usersPerPage);
			request.setAttribute("users", users);
			request.setAttribute("usersCount", usersCount);
			
			String url = "administrator/users.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);
			
		} catch (ServiceException e) {
			String url = "error";
			response.sendRedirect(url);
		}
		
	}

}
