package com.test.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//@WebServlet(urlPatterns="/getServlet", initParams=@WebInitParam(name="URL", value="http://www.weatherservice.com"))
public class GetServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		ServletConfig config = getServletConfig();
		System.out.println(config.getInitParameter("URL"));
		
		ServletContext context = getServletContext();
		System.out.println(context.getInitParameter("dbURL"));
		
//		String valueParam = req.getParameter("name");
		String htmlResponse = "<html> <h3>Welcome Nathan to Servlet Tutorial!</h3> <h4>This is the customized annotation of the servlet</h4> </html>";
		String formHtml = "<html><h2>HTML Forms</h2>\r\n" + 
				"\r\n" + 
				"<form>\r\n" + 
				"  <label for=\"fname\">First name:</label><br>\r\n" + 
				"  <input type=\"text\" id=\"fname\" name=\"fname\" value=\"Nhat Dinh\"><br>\r\n" + 
				"  <label for=\"lname\">Last name:</label><br>\r\n" + 
				"  <input type=\"text\" id=\"lname\" name=\"lname\" value=\"Nguyen\"><br><br>\r\n" + 
				"  <input type=\"submit\" value=\"Submit\">\r\n" + 
				"</form> \r\n" + 
				"\r\n" + 
				"<p>If you click the \"Submit\" button, the form-data will be sent to a page called \"/action_page.php\".</p></html>";
		PrintWriter writer = resp.getWriter();
		writer.write(htmlResponse);
	}
	
}
