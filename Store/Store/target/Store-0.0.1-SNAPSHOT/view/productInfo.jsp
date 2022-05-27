<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "fn" uri = "http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Product Information</title>
</head>
<body>
<h3>List of available products of ${brandName} in the store:-</h3>
	<c:forEach var="product" items="${productList}">
		Product Name: 	  ${product.productName} <br>
		Product Category: ${product.category} <br>
		<strong>--------------------------------------------</strong><br>
	</c:forEach>
</body>
</html>