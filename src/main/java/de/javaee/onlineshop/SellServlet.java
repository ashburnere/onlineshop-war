package de.javaee.onlineshop;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class SellServlet
 */
@WebServlet("/sell")
@MultipartConfig(maxFileSize = 1024 * 1024 * 10, // max. file size of 10 MB
		maxRequestSize = 1024 * 1024 * 30, // max. request size of 30 MB
		fileSizeThreshold = 1024 * 1024, // threshold after which files will be written to disk
		location = "/home/erik/tmp/upload-files") // folder where the files are stored
public class SellServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// reads out text and data from sell page
		final PrintWriter out = response.getWriter();
		final String title = request.getParameter("title");
		final String description = request.getParameter("description");
		final String price = request.getParameter("price");
		final Part part = request.getPart("foto");

		OutputStream os = null;
		InputStream is = null;
		try {
			String path = "/home/erik/tmp/" + part.getSubmittedFileName();
			File file = new File(path);
			os = new FileOutputStream(file);
			byte[] b = new byte[1024];
			int i = 0;
			is = part.getInputStream();
			while ((i = is.read(b)) != -1) {
				os.write(b, 0, i);

			}
		} catch (Exception e) {
			throw new ServletException(e.getMessage());
		}

		out.println("<!DOCTYPE HTML><html><body><br>");
		out.println("<br>Hochgeladener Artikel: " + title);
		out.println("<br>Beschreibung: " + description);
		out.println("<br>Preise: " + price);
		out.println("<br>Bild: " + part.getSubmittedFileName());
//		out.println("Content-Type: " + part.getContentType() + "<br>");
//		out.println("Size: " + part.getSize() + "<br>");
//		out.println("Name: " + part.getName() + "<br>");
		
		Logger.getLogger(SellServlet.class.getName()).warning(
				"processing request " + 
						request.getSession().getId() + 
						"in sell servlet: " + this.hashCode());
	}

}
