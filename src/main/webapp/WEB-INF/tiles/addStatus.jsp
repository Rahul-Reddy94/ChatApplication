<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix = "form"  uri="http://www.springframework.org/tags/form" %>
    
    
<div class="row">
		<div class="col-md-8 col-md-offset-2">
	  <div class="card" >
	    <div class="card-header"> 	
	    	<div class="card-title"> Add a Status Update</div>
	    	</div>
	    <div class="card-body">
<!-- 			<div class="card-title">THis is Panel Body</div>
	 -->
	 	<form:form modelAttribute="update">
	 		 	<div class="errorsz" style="color:red">
	 				<form:errors path="text"/>
	 			</div>
	 		<div class="form-group">
<%-- 	 		Here Path is the Text field
	 		and 
	 		If We dont want to 'ID' to get updated then we should set it to 'HIDDEN'
	 		
	 		 ******* <form:input path="id" type="hidden"/> **********--%>
	 			<form:textarea path="text" name="text" rows="10" cols="50"></form:textarea>
	 		</div>
	 			<input type="submit" name ="submit" value="Add Status">
	 	</form:form>
	 	
	 	</div>
	  </div>
	  	  <div class="card" >
	    <div class="card-header">
	    	<div class="card-title"><fmt:formatDate type="both" dateStyle = "long" timeStyle = "long" value="${latestStatusUpdate.added}"/></div>
	    	<%-- <c:out value="${latestStatusUpdate.added}"/> --%>
	    </div>
	    <div class="card-body">
<!-- 			<div class="card-title">THis is Panel Body</div>
	 -->		    	<div class="card-title"><c:out value="${latestStatusUpdate.text}"/></div>
	 	 
	 	</div>
	 	
	 	<div class="card-footer">
	 		<div class="card-title">
<%-- 	 			Request StatusUpdate Attribute: <%= request.getAttribute("update") %> <br/>
 --%>	 			JSP Object : <%= this %><br/>
	 			JSP Object : <%= this.getClass() %><br/>
	 		</div>
	 	</div>
	  </div>
	</div>	
</div>
		
<!-- <div class="alert alert-primary" role="alert">
  A simple primary alert with <a href="#" class="alert-link">an example link</a>. Give it a click if you like.
</div>
 -->