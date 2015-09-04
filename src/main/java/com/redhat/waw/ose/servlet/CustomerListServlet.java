package com.redhat.waw.ose.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.redhat.waw.ose.ejb.CustomerBean;
import com.redhat.waw.ose.model.Customer;

/**
 * Servlet implementation class CustomerListServlet
 */
@WebServlet("/customers")
public class CustomerListServlet extends HttpServlet {
	
	private static final Logger logger = Logger.getLogger(CustomerListServlet.class.getName());
	
	private static final long serialVersionUID = 1L;
	
	@EJB
	private CustomerBean bean;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerListServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.setContentType("text/plain");
		//PrintWriter out = response.getWriter();
		List<Customer> customers = new ArrayList<Customer>();
		try {
			customers = bean.getCustomers();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		logger.log(Level.INFO, "Customer count: " + customers.size());
		
		request.setAttribute("customers", customers);
		request.getRequestDispatcher("customers.jsp").forward(request, response);
	}

}
