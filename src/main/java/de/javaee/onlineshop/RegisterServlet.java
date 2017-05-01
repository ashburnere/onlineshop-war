package de.javaee.onlineshop;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.javaee.onlineshop.model.Customer;

/**
 * Creates Customer object from the data entered in the register form and
 * saves the Customer object in the session with key "customer".
 * <br>
 * Forwards to index.html.
 */
@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		
		// create a customer object and save it
		final String email = request.getParameter("email");
		final String password = request.getParameter("password");
		
		final Customer customer = new Customer(email, password);
		
		// saves the customer in the session
//		final HttpSession session = request.getSession();
//		session.setAttribute("customer", customer);
		
		// saves the customer in a cookie
		final Cookie customerMail = new Cookie("email", email);
		response.addCookie(customerMail);
		final Cookie customerPassword = new Cookie("password", password);
		response.addCookie(customerPassword);
		
		// forward to the index page
		final RequestDispatcher dispatcher = 
				request.getRequestDispatcher("index.html");
		dispatcher.forward(request, response);
		
	}

}
