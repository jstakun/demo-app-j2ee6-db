<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Top customers</title>
</head>
<body>
   <h3>Top customers list</h3>
   <table border="1">
      <tr>
		  <th>Customer Id</th>
		  <th>First name</th>
		  <th>Last name</th>
		  <th>Country</th>
		  <th>City</th>
      </tr>
      <c:forEach var="customer" items="${requestScope.customers}">
        <tr>
          <td>${customer.customerid}</td>
          <td>${customer.firstname}</td>
          <td>${customer.lastname}</td>
          <td>${customer.country}</td>
          <td>${customer.city}</td>
        </tr>
      </c:forEach>
    </table>
    <br/>
    Table has ${fn:length(requestScope.customers)} rows
</body>
</html>
