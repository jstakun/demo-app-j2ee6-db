package com.redhat.waw.ose.ejb;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.redhat.waw.ose.model.Customer;

/**
 * Session Bean implementation class CustomerBean
 */
@Stateless
@LocalBean
public class CustomerBean {

	@PersistenceContext(unitName="dbj2ee")
	private EntityManager em;
		
	public List<Customer> getCustomers() {
		List<Customer> customers = em.createQuery("from Customer", Customer.class).getResultList();
		return customers;
	}
	
	public Customer findCustomer(String id) {
		return em.find(Customer.class, id);
	}
	
	public Customer save(Customer c) {
		em.persist(c);
		em.flush();
		return c;
	}
	
	public Customer update(Customer c) {
		em.merge(c);
		em.flush();
		return c;
	}
}
