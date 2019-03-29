<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="jwp" %>

<c:url var="url" value="/viewstatus"/>


<div class="row">
	<div class="col-md-8 col-md-offset-2">
	
		<jwp:pagination url="${url}" statusPageKey="${statusPageKey}"/>
	
		<c:forEach items="${statusPageKey.content}" var="list" varStatus="status">
		
			<c:url var="editLink" value="/editstatus?id=${list.id}"/>
			<c:url var="deleteLink" value="/deletestatus?id=${list.id}"/>
		
		     <div class="card" >
			    <div class="card-header">
			    	<div class="card-title"><fmt:formatDate type="both" dateStyle = "long" timeStyle = "long" value="${list.added}"/></div>
			    </div>
			    <div class="card-body">
					<div class="card-title">
						${list.text}
					<div class="edit-links pull-right">
						<a href="${editLink}">Edit</a> || <a onclick="return confirm('Do you want to delete the Update ? ')" href="${deleteLink}">Delete</a>
						
					</div>
					</div>
			 	</div>
			 </div>
		</c:forEach>
		
	</div>
</div>

	<%--View Status here
	now page number is : ${param.p}
	
	 <c:forEach items="${statusPageKey}" var="listMap" varStatus="status">
	    <tr>
	        <td>, ${listMap.text}</td>
	    </tr>
	</c:forEach> --%>