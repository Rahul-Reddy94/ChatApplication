<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix = "c"  uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    <%@ taglib prefix = "form"  uri="http://www.springframework.org/tags/form" %>
    
    
<div class="row">

	<div class="col-md-8 col-md-offset-2">
	  
	  			 
			 <div class="errorsz">
 	 			<form:errors path="editProfile.*"/>
	 		</div>  
			
	  
	  <div class="card" >
	    
	    <div class="card-header"> 	
	    	<div class="card-title"> Edit your About Information</div>
	    </div>
	    
	    <div class="card-body">
	    

	 	
	 	<form:form modelAttribute="editProfile">
	 		 
<%-- 			<form:input path="id" type="hidden"/>
			<form:input path="user" type="hidden"/>
			 --%>

	 		<div class="form-group">
	 			<form:textarea path="about" name="text" rows="10" cols="50"></form:textarea>
	 		</div>
	 		
	 		<input type="submit" name ="submit" value="Save Status">
	 	
	 	</form:form>
	 	
	 	</div>
	  </div>
	</div>	
</div>

  <script src='https://cloud.tinymce.com/5/tinymce.min.js?apiKey=your_API_key'></script>
  <script>
/*   tinymce.init({
    selector: 'textarea',
    plugins: "link"
  }); */
  </script>
  
  	 	<%--	 <div class="errorsz">
 	 			<form:errors path="text"/>
	 			<form:errors path="id"/>
	 			<form:errors path="added"/>
	 		</div> --%>