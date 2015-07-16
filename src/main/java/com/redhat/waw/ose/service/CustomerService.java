package com.redhat.waw.ose.service;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.redhat.waw.ose.ejb.CustomerBean;
import com.redhat.waw.ose.model.Customer;

@Stateless
@Path("customers")
public class CustomerService {

	private static final Logger logger = Logger.getLogger(CustomerService.class.getName());
	
	@EJB
	private CustomerBean bean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		logger.log(Level.INFO, "getAllCustomers() service executed");
		return bean.getCustomers();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Customer find(@PathParam("id") final String id) {
		logger.log(Level.INFO, "find(" + id + ") service executed");
		return bean.findCustomer(id);
	}
	
	@GET
	@Path("/json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findJSon(@PathParam("id") final String id) {
		logger.log(Level.INFO, "findJSon(" + id + ") service executed");
		return bean.findCustomer(id);
	}
	
	//http://crm.cloudapps.osecloud.com/rest/customers/CST00000?city=Warsaw&country=Poland&firstname=Jarek&lastname=Stakun&phonenumber=123456789&postalcode=00-483&stateprovince=mazowieckie&streetaddress=Prusa 2
	
	@PUT
	//@Path("/{id}")
	@Path("/{id: CST[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer add(@PathParam("id") final String customerid, @NotNull @QueryParam("city") final String city, @QueryParam("country") final String country,
		@NotNull @QueryParam("firstname") final String firstname, @NotNull @QueryParam("lastname") final String lastname,
		@QueryParam("middlename") final String middlename, @QueryParam("phonenumber") final String phonenumber,
		@NotNull @QueryParam("postalcode") final String postalcode, @QueryParam("stateprovince") final String stateprovince,
		@NotNull @QueryParam("streetaddress") final String streetaddress, @QueryParam("streetaddress2") final String streetaddress2) {
		logger.log(Level.INFO, "add() service executed");
		Customer c = new Customer();
       	c.setCity(city);	
		c.setCountry(country);
		c.setCustomerid(customerid);
		c.setFirstname(firstname);
		c.setLastname(lastname);
		c.setMiddlename(middlename);
		c.setPhonenumber(phonenumber);
		c.setPostalcode(postalcode);
		c.setStateprovince(stateprovince);
		c.setStreetaddress(streetaddress);
		c.setStreetaddress2(streetaddress2);
		bean.save(c);
		return c;
	}
	
}
