<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>    
    
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
      <li class="nav-item">
        <a class="nav-link" href="/admin">Admin Area</a>
      </li>
    </ul>
    
    <ul class="nav navbar-nav navbar-right">
               <li class="nav-item"> <a class="nav-link" href="/addstatus">Add Status</a></li>
               <li class="nav-item"> <a class="nav-link" href="/viewstatus">View Status</a></li>
               
           </ul>
    </div>
 
</nav>	

	<div class = "container">
		<tiles:insertAttribute name="content"/>
	</div>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>