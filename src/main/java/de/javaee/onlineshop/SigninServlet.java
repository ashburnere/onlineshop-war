package de.javaee.onlineshop;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Reads the data from the signin form and checks if a customer for the entered data 
 * exists.
 */
@WebServlet("/signin")
public class SigninServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// compare the entered credentials with the known customer
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		
		// create the customer from the session
//		final Customer customer = (Customer)request.getSession().getAttribute("customer");
		
		// read the credentials from the cookies and create the customer
		String cookieMail = null;
		String cookiePassword = null;
		Cookie[] cookies = request.getCookies();
		for (Cookie c : cookies) {
			if ("email".equals(c.getName())) {
				cookieMail = c.getValue();
			} else if("password".equals(c.getName())) {
				cookiePassword = c.getValue();
			}
			
			if (cookieMail != null && cookiePassword != null) {
				break;
			}
		}

		final PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		if (email.equals(cookieMail) && password.equals(cookiePassword)) {
		} else {
			out.println("E-Mail ("+ cookieMail +") oder Passwort ("+ cookiePassword +") falsch!");
		}
		out.println("</body></html>");
		
	}

}
