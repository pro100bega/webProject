package by.htp6.hospital.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.htp6.hospital.command.Command;
import by.htp6.hospital.command.CommandProvider;

/**
 * Front controller
 * 
 * Главный контроллер 
 * 
 * @author Begench Shamuradov, 2017
 */
@WebServlet("/controller")
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public Controller() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAndExecuteCommand(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		getAndExecuteCommand(request, response);
	}
	
	private void getAndExecuteCommand(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		CommandProvider commandProvider = CommandProvider.getInstance();
		String commandName = request.getParameter("command");
		Command command = commandProvider.getCommand(commandName);
		
		command.execute(request, response);
	}

}
