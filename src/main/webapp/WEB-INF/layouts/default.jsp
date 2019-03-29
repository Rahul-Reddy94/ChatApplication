<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>  

    
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE-edge">	
  	<meta name="viewport" content="width=device-width, initial-scale=1">
  	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  	<link rel="stylesheet" href="/css/main.css">
  	
 <title><tiles:insertAttribute name="title"/> </title>
</head>

<body>


<nav class="navbar navbar-expand-lg">
  <a class="navbar-brand" href="#">Spring Boot Application</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <!-- navbar-inner container-fluid --> 
  <div class="collapse navbar-collapse navbar-inner container-fluid " id="navbarNav">
  
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="/">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="/about">About</a>
      </li>
    </ul>
    
	    <div class="dropdown show nav navbar-nav navbar-right">
		  
		  	<sec:authorize access="!isAuthenticated()">
		     <a	href="/login">Login</a>
		     <a	href="/register">Register</a>
		     
			</sec:authorize>
	  		
	  		<sec:authorize access="isAuthenticated()">
	  		 	<a href="javascript:$('#logoutForm').submit();">Logout</a>
	  		 	</sec:authorize>
	  		 	<sec:authorize access="hasRole('ROLE_ADMIN')">
			 	<a class="btn btn-secondary dropdown-toggle" href="#" role="button" id="dropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
			  		Status</a>

			  	<div class="dropdown-menu" aria-labelledby="dropdownMenuLink">
			    <a class="dropdown-item" href="/addstatus">Add Status</a>
			    <a class="dropdown-item" href="/viewstatus">View Status Updates</a>
			  </div>
			  </sec:authorize>
		</div>
	</div>
 
</nav>

<c:url var="logoutLink" value="/logout" />

<form id="logoutForm"  style="logout-styler" method="post" action="${logoutLink}">
	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>

	<div class = "container">
		<tiles:insertAttribute name="content"/>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>