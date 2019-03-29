<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>



<div class="row">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="homepage-status"> 
			${homePageStatusUpdateKey.text}
		</div>
		</div>
</div>
	  <div class="card" >
	    <div class="card-header">
	    	<div class="card-title"><fmt:formatDate type="both" dateStyle = "long" timeStyle = "long" value="${homePageStatusUpdateKey.added}"/></div>
	    </div>
	    <div class="card-body">
	 		    	<div class="card-title"><c:out value="${homePageStatusUpdateKey.text}"/></div>
	 	 
	 	</div>
	 	
	 	<div class="card-footer">
	 		<div class="card-title">
<%-- 	 			Request StatusUpdate Attribute: <%= request.getAttribute("update") %> <br/>
 --%>	 			JSP Object : <%= this %><br/>
	 			JSP Object : <%= this.getClass() %><br/>
	 		</div>
	 	</div>
	  </div>

    
    
    