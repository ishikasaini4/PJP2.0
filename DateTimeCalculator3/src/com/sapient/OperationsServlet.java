package com.sapient;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/OperationsServlet")
public class OperationsServlet extends HttpServlet {
	private DateTimeBO bo;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OperationsServlet() {
		this.bo = new DateTimeBO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String selectedOperation = request.getParameter("operation");
		String res = "";

		switch (selectedOperation) {
		case "1":
			res = this.bo.add(request.getParameter("date1"), request.getParameter("date2"));
			break;
		case "2":
			res = this.bo.subtract(request.getParameter("date1"), request.getParameter("date2"));
			break;
		case "3":
			res = this.bo.minusNDays(request.getParameter("date1"), Integer.parseInt(request.getParameter("days")));
			break;
		case "4":
			res = this.bo.minusNWeeks(request.getParameter("date1"), Integer.parseInt(request.getParameter("weeks")));
			break;
		case "5":
			res = this.bo.minusNMonths(request.getParameter("date1"), Integer.parseInt(request.getParameter("months")));
			break;
		case "6":
			res = this.bo.getDayOfTheWeek(request.getParameter("date1"));
			break;
		case "7":
			res = this.bo.getWeekNumber(request.getParameter("date1"));
			break;
		case "8":
			res = this.bo.NLPToDate(request.getParameter("phrase"));
			break;
		}

		request.setAttribute("operation", request.getTitle());
		request.setAttribute("result", res);
		request.getRequestDispatcher("Result.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
