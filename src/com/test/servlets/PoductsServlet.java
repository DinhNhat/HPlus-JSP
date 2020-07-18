package com.test.servlets;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.beans.Product;
import com.test.dao.ApplicationDao;

/**
 * Servlet implementation class PoductsServlet
 */
@WebServlet("/addProducts")
public class PoductsServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		System.out.println("In do get addProducts");
		// get the HTTP session object
		HttpSession session = req.getSession();
		
		// create a cart as arraylist for the user
		List<String> cart = (ArrayList<String>) session.getAttribute("cart");
		
		if(cart == null) {
			cart = new ArrayList<>();
		}
		
		// add the selected product to the cart
		if (req.getParameter("product") != null) {
			// check if product already in cart and inform user want to add a duplicate product
			
			cart.add(req.getParameter("product"));
		}
		
		session.setAttribute("cart", cart);
		
		
		// get search criteria from search servlet
		String search = (String) session.getAttribute("search");
		
		// get the search results from DAO
		ApplicationDao dao = new ApplicationDao();
		List<Product> products = dao.searchProducts(search);
		
		// get the search results in request scope
		req.setAttribute("products", products);
		
		// forward to searchResults.jsp
		req.getRequestDispatcher("/html/searchResults.jsp").forward(req, resp);
		
	}

}
