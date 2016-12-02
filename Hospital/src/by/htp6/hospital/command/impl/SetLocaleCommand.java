package by.htp6.hospital.command.impl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import by.htp6.hospital.command.Command;

public class SetLocaleCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String locale = request.getParameter("localeName");
		String redirect = request.getParameter("redirect");
		HttpSession session = request.getSession(true);		
		switch(locale){
			case "ru_RU":
				session = request.getSession(true);
				session.setAttribute("localeName", "ru_RU");
				response.sendRedirect(redirect);
				break;
			case "en_US":
				session = request.getSession(true);
				session.setAttribute("localeName", "en_US");
				response.sendRedirect(redirect);
				break;
		}
	}

}
