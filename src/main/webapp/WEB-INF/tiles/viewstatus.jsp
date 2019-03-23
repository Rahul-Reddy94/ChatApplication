<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:url var="url" value="/viewstatus"/>
<div class="row">
	<div class="col-md-8 col-md-offset-2">
	
	<div class="pagination">
		<c:forEach var="pageNumber" begin="1" end="${statusPageKey.totalPages}">
			<c:choose>
			<c:when test="${pageNumber - 1 != statusPageKey.number}">
			
				<a href="${url}?p=${pageNumber}"><c:out value="  ${pageNumber}"  />  </a>
			</c:when>
			 <c:otherwise>
				<strong><c:out value=" ${pageNumber} "/></strong>
			</c:otherwise>
		</c:choose> 
		<c:if test="${pageNumber != statusPageKey.totalPages}">&nbsp;|&nbsp;
		</c:if>
		</c:forEach>
	</div>
	
		<c:forEach items="${statusPageKey.content}" var="list" varStatus="status">
		     <div class="card" >
			    <div class="card-header">
			    	<div class="card-title"><fmt:formatDate type="both" dateStyle = "long" timeStyle = "long" value="${list.added}"/></div>
			    </div>
			    <div class="card-body">
					<div class="card-title"><c:out value = "${list.text}"/></div>
			 	</div>
			 	</div>
		</c:forEach>
	</div>
	</div>
</body>
</html>

	<%--View Status here
	now page number is : ${param.p}
	
	 <c:forEach items="${statusPageKey}" var="listMap" varStatus="status">
	    <tr>
	        <td>, ${listMap.text}</td>
	    </tr>
	</c:forEach> --%>