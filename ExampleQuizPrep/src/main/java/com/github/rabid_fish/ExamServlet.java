package com.github.rabid_fish;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ExamServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String actionString = request.getParameter("action");
		ExamAction action = ExamAction.valueOf(actionString);
		switch (action) {
		case BEGIN_EXAM:
			handeBeginExam(request, response);
			break;
		case DISPLAY_QUESTION:
			handleExamAnswer(request, response);
			break;
		case NAVIGATE_QUESTION:
			handleExamNavigate(request, response);
			break;
		case DISPLAY_SUMMARY:
			handleSummary(request, response);
			break;
		default:
			throw new RuntimeException("You tried to do bad stuff, go away");
		}
	}
	
	void handeBeginExam(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(true);
		
		String file = request.getParameter("file");
		Problem[] problems = new ExamDao().listProblems("/json/" + file);
		session.setAttribute("problems", problems);
		
		String[] guesses = new String[problems.length];
		session.setAttribute("guesses", guesses);
		
		response.sendRedirect("problemView.jsp?index=0");
	}

	void handleExamAnswer(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String indexString = request.getParameter("index");
		int index = Integer.parseInt(indexString);
		
		HttpSession session = request.getSession(false);
		String[] guesses = (String[]) session.getAttribute("guesses");
		guesses[index] = request.getParameter("guess");
		session.setAttribute("guesses", guesses);
		
		int nextIndex = index + 1;
		Problem[] problems = (Problem[]) session.getAttribute("problems");
		if (nextIndex > problems.length - 1) {
			response.sendRedirect("servlet?action=" + ExamAction.DISPLAY_SUMMARY.toString());
		} else {
			response.sendRedirect("problemView.jsp?index=" + nextIndex);
		}
	}
	
	void handleExamNavigate(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String indexString = request.getParameter("index");
		int index = Integer.parseInt(indexString);
		response.sendRedirect("problemView.jsp?index=" + index);
	}
	
	void handleSummary(HttpServletRequest request, HttpServletResponse response) throws IOException {

		HttpSession session = request.getSession(false);
		Problem[] problems = (Problem[]) session.getAttribute("problems");
		String[] guesses = (String[]) session.getAttribute("guesses");
		
		boolean[] corrects = new boolean[problems.length];
		int correctTotal = 0;
		for (int i = 0; i < problems.length; i++) {
			
			if (guesses[i] == null) {
				guesses[i] = "";
			}
			
			corrects[i] = problems[i].getAnswer().equals(guesses[i]);
			if (corrects[i]) {
				correctTotal++;
			}
		}
		
		session.setAttribute("corrects", corrects);
		session.setAttribute("correctTotal", new Integer(correctTotal));
		session.setAttribute("guesses", guesses);
		
		response.sendRedirect("problemSummary.jsp");
	}

}
