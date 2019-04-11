<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
    
<div class="row">

	<div class="col-md-8 col-md-offset-2">
	  
		<div class="card" >		    
		    <div class="card-body">
		    	<c:out value="${errorMessage}"/>
		    			    	<c:out value="${errorMessage}"/>
		    	
<%-- 		    	<c:forEach var="element" items="${exceptionTrace}">
		    		<c:out value="${element}"/>
		    	</c:forEach> --%>
		    	
		    </div>
	   </div>
	</div>
</div>
	    
</body>
</html>