package com.sapient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/CalculatorServlet")
public class CalculatorServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String choice = "choice";

	public CalculatorServlet() {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String selected = request.getParameter(choice);
		String nextPage = "Menu.jsp" ;
		
		switch(selected) {
		case "1":nextPage = "Add.jsp"; break;
		case "2":nextPage = "Subtract.jsp"; break;
		case "3":nextPage = "MinusDays.jsp"; break;
		case "4":nextPage = "MinusWeeks,jsp"; break;
		case "5":nextPage = "MinusMonths,jsp"; break;
		case "6":nextPage = "DayOfWeek.jsp"; break;
		case "7":nextPage = "WeekOfYear"; break;
		case "8":nextPage = "NLPToDate.jsp"; break;
		}
		
		
		request.getRequestDispatcher(nextPage).forward(request, response);
	}
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
