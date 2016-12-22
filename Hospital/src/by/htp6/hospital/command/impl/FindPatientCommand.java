package by.htp6.hospital.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.htp6.hospital.bean.Patient;
import by.htp6.hospital.bean.User;
import by.htp6.hospital.command.Command;
import by.htp6.hospital.service.FindPatientService;
import by.htp6.hospital.service.GetPatientsCountService;
import by.htp6.hospital.service.exception.ServiceException;
import by.htp6.hospital.service.factory.ServiceFactory;

public class FindPatientCommand implements Command {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("authorisedUser");
		String userType = user.getType();
		
		String orderBy = request.getParameter("orderBy");;
		if (orderBy == null){
			orderBy = (String)session.getAttribute("orderBy");
		}

		String requestCurrentPage = request.getParameter("currentPage");
		int currentPage;
		if (requestCurrentPage == null){
			String sessionCurrentPage = ""+session.getAttribute("currentPage");
			currentPage = (sessionCurrentPage == null) 
					? 1	: Integer.parseInt(sessionCurrentPage);
		} else {
			currentPage = Integer.parseInt(requestCurrentPage);
		}
		
		String requestPatientsPerPage = request.getParameter("patientsPerPage");
		int patientsPerPage;
		if (requestPatientsPerPage == null) {
			String sessionPatientsPerPage = 
					""+session.getAttribute("patientsPerPage");
			patientsPerPage = (sessionPatientsPerPage == null) 
					? 10 : Integer.parseInt(sessionPatientsPerPage);
		} else {
			patientsPerPage = Integer.parseInt(requestPatientsPerPage);
		}
		
		String searchData = request.getParameter("searchData");
		if (searchData == null || "".equals(searchData)) {
			String url = "controller?command=GET_PATIENT_LIST";
				response.sendRedirect(url);
		} else {
			ServiceFactory serviceFactory = ServiceFactory.getInstance();
			FindPatientService findPatientService = serviceFactory.getFindPatient();
			GetPatientsCountService getPatientsCount = serviceFactory.getGetPatientsCount();

			try {
				int patientsCount = 0;
				int offset = patientsPerPage * (currentPage - 1);
				List<Patient> patients = null;
				String url = "error.jsp";
				if (userType.equals("doctor")){
					int doctorId = user.getId();
					patientsCount = getPatientsCount.getPatientsCountByDoctorId(searchData, 
							doctorId);
					patients = findPatientService.findPatientsByDoctorId(searchData, doctorId,
							offset, patientsPerPage, orderBy);
					url = "doctor/foundPatients.jsp";
				} else if (userType.equals("nurse")) {
					patientsCount = getPatientsCount.getPatientsCount(searchData);
					patients = findPatientService.findPatients(searchData, offset,
							patientsPerPage, orderBy);
					url = "nurse/foundPatients.jsp";
				}

				request.setAttribute("foundPatients", patients);
				session.setAttribute("currentPage", currentPage);
				session.setAttribute("patientsPerPage", patientsPerPage);
				request.setAttribute("patientsCount", patientsCount);
				request.setAttribute("searchData", searchData);
				if (orderBy != null){
					session.setAttribute("orderBy", orderBy);
				}
				
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			} catch (ServiceException e) {
				String url = "error.jsp";
				RequestDispatcher dispatcher = request.getRequestDispatcher(url);
				dispatcher.forward(request, response);
			}
		}
	}
}
