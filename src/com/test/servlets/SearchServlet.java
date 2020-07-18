package com.test.servlets;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// collect search string from the form
		String searchString = req.getParameter("search");
		
		req.getSession().setAttribute("search", searchString);
		// call DAO layer and get all products for search criteria
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(searchString);
		
		// write the products data back to the client browser
//		String page = getHTMLString(req.getServletContext().getRealPath("/html/searchResults.html"), products);
//		resp.getWriter().write(page);
		req.setAttribute("products", products);
		req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);
	}
	
	public String getHTMLString(String filePath, List<Product> products) throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		String line = "";
		StringBuffer buffer = new StringBuffer();
		while((line=reader.readLine()) != null) {
			buffer.append(line);
		}
		reader.close();
		String page = buffer.toString();
		
		page = MessageFormat.format(page, products.get(0).getPath(), products.get(1).getPath(), products.get(2).getPath(),
				products.get(0).getName(), products.get(1).getName(), products.get(2).getName(), 0);
		
		return page;
	}
	
}
