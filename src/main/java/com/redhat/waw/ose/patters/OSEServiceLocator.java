package com.redhat.waw.ose.patters;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OSEServiceLocator {
	private static OSEServiceLocator me;
	private InitialContext context = null;
	private static final String oseDbJndiName = "java:jboss/datasources/PostgreSQLDS";
	private Map<String, Object> cache;

	private OSEServiceLocator() throws ServiceLocatorException {
		try {
			context = new InitialContext();
			cache = Collections.synchronizedMap(new HashMap<String, Object>());
		} catch (NamingException ne) {
			throw new ServiceLocatorException(ne);
		}
	}

	public static OSEServiceLocator getInstance() throws ServiceLocatorException {
		if (me == null) {
			me = new OSEServiceLocator();
		}
		return me;
	}

	public DataSource getOseDataSource() throws ServiceLocatorException {
		DataSource dataSource = null;
		try {
			if (cache.containsKey(oseDbJndiName)) {
				dataSource = (DataSource) cache.get(oseDbJndiName);
			} else {
				dataSource = (DataSource) context.lookup(oseDbJndiName);
				cache.put(oseDbJndiName, dataSource);
			}
		} catch (NamingException nex) {
			throw new ServiceLocatorException(nex);
		} catch (Exception ex) {
			throw new ServiceLocatorException(ex);
		}
		return dataSource;
	}

	private class ServiceLocatorException extends Exception {

		private static final long serialVersionUID = 1L;

		public ServiceLocatorException(Exception e) {
			super(e);
		}
	}
}
