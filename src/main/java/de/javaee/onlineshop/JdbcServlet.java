package de.javaee.onlineshop;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet reads initialization parameters from the servlet context.
 */
@WebServlet("/jdbc")
public class JdbcServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletContext application = getServletContext();
		
		final String driverApplication = application.getInitParameter("driver");
		
		final String jdbcProperties = getInitParameter("jdbc_properties");
		InputStream in = application.getResourceAsStream(jdbcProperties);
		final Properties properties = new Properties();
		properties.load(in);
		
		// get servlet parameters from the properties file
		final PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>");
		out.println("<html><body>");
		out.println("<br>driver: " + properties.getProperty("driver"));
		out.println("<br>url: " + properties.getProperty("url"));
		out.println("<br>username: " + properties.getProperty("username"));
		out.println("<br>password: " + properties.getProperty("password"));
		out.println("</body></html>");
	}

}
