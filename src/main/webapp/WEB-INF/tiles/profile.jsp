<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<c:url var="img" value="/img"/>
<c:url var="edit" value="/edit-profile-about"/>

<div class="row">
	<div class="col-md-10 col-md-offset-1">			
		<div class="profile-about">
			
			<div class="profile-image">
				<img alt="" src="${img}/compress-image.jpg"/>
			</div>
			
			<div class="profile-text">
			
			<c:choose>
			<c:when test="${profile.about == null}">
				Click edit to add story of yours
			</c:when>
			<c:otherwise>		
			${profile.about}
			</c:otherwise>
			</c:choose>
			</div>
		</div>
		
		<div class="edit">
			<a href="${edit}">Edit Profile</a>
		</div>
	</div>
</div>