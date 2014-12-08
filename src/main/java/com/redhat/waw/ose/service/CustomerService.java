package com.redhat.waw.ose.service;

import java.util.List;

import javax.ejb.EJB;
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

@Path("customers")
public class CustomerService {

	@EJB
	private CustomerBean bean;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllCustomers() {
		return bean.getCustomers();
	}
	
	@GET
	@Path("/{id}")
	@Produces(MediaType.TEXT_XML)
	public Customer find(@PathParam("id") final String id) {
		return bean.findCustomer(id);
	}
	
	@GET
	@Path("/json/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer findJSon(@PathParam("id") final String id) {
		return bean.findCustomer(id);
	}
	
	//http://dbj2ee-ose2.redhat.pl/rest/customers/CST00000?city=Warsaw&country=Poland&firstname=Jarek&lastname=Stakun&phonenumber=123456789&postalcode=00-483&stateprovince=mazowieckie&streetaddress=Prusa 2
	
	@PUT
	//@Path("/{id}")
	@Path("/{id: CST[0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer add(@PathParam("id") final String customerid, @NotNull @QueryParam("city") final String city, @QueryParam("country") final String country,
		@NotNull @QueryParam("firstname") final String firstname, @NotNull @QueryParam("lastname") final String lastname,
		@QueryParam("middlename") final String middlename, @QueryParam("phonenumber") final String phonenumber,
		@NotNull @QueryParam("postalcode") final String postalcode, @QueryParam("stateprovince") final String stateprovince,
		@NotNull @QueryParam("streetaddress") final String streetaddress, @QueryParam("streetaddress2") final String streetaddress2) {
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
