<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ attribute name="statusPageKey" required = "true" type=" org.springframework.data.domain.Page" %>
<%@ attribute name="url" required = "true"%>

<c:set var="block" value=""/>

<c:if test="${statusPageKey.totalPages != 1}">
	<div class="pagination">
		<c:forEach var="pageNumber" begin="1" end="${statusPageKey.totalPages}">
			
			<c:choose>
			
				<c:when test="${pageNumber - 1 != statusPageKey.number}">
					<a href="${url}?p=${pageNumber}"> <c:out value="${pageNumber}"/> </a>
				</c:when>
				
				 <c:otherwise>
					<strong><c:out value=" ${pageNumber} "/></strong>
				</c:otherwise>
				
			</c:choose> 
			
			<c:if test="${pageNumber != statusPageKey.totalPages}">
				&nbsp;|&nbsp;
			</c:if>
		</c:forEach>
	</div>
</c:if>