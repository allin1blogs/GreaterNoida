

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="base-bar" style="margin-top: 5px; width: 100%; background-color: #387403;">
	<a href="${pageContext.request.contextPath}/home" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Home</b></a>
	<a href="${pageContext.request.contextPath}/users" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Users</b></a>
	<c:if test="${not empty departments}">
		<div class="base-dropdown-hover">
    		<button class="menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" id="dropCha" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; color: #FFFFFF;"><b>Departments</b></button>
   			<div style="background-color: #387403;" onmouseover="document.getElementById('dropCha').style.backgroundColor='#ffffff';document.getElementById('dropCha').style.color='#387403'" onmouseout="document.getElementById('dropCha').style.backgroundColor='#387403';document.getElementById('dropCha').style.color='#FFFFFF'" class="base-dropdown-content base-bar-block base-card-4">
    			<c:forEach items="${departments}" var="department">
    				<c:if test="${department!='Abadi 6%'}">
    					<a href="${pageContext.request.contextPath}/retrieve?department=${department}" class="base-bar-item" style="padding-top: 3px; margin-top: 0px; color: #FFFFFF; text-decoration: none; font-family: cambria;" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'"><b>${department}</b></a>
    				</c:if>
    				<c:if test="${department=='Abadi 6%'}">
    					<a href="${pageContext.request.contextPath}/retrieve?department=Abadi 6%25" class="base-bar-item" style="padding-top: 3px; margin-top: 0px; color: #FFFFFF; text-decoration: none; font-family: cambria;" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'"><b>${department}</b></a>
    				</c:if>
    			</c:forEach>
    		</div>
    	</div>
	</c:if>
	<c:if test="${not empty upload && user!='CEO'}">
		<a href="${pageContext.request.contextPath}/uploadBulk" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Upload</b></a>
	</c:if>
	<c:if test="${not empty agenda || user=='CEO'}">
		<a href="${pageContext.request.contextPath}/agenda" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Agenda</b></a>
	</c:if>
	<c:if test="${not empty track || user=='CEO'}">
		<a href="${pageContext.request.contextPath}/logs" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Logs</b></a>
	</c:if>
    <div style="float: right;">
    	<a href="#" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>User Id: &nbsp;<i>${user}</i></b></a>
        <a href="${pageContext.request.contextPath}/logout" class="base-bar-item menu-bar base-btn" onmouseover="style.backgroundColor='#FFFFFF';style.color='#387403'" onmouseout="style.backgroundColor='#387403';style.color='#FFFFFF'" style="font-size: 22px; font-family: cambria math; background-color: #387403; box-shadow: none; text-decoration: none; color: #FFFFFF;"><b>Logout</b></a>
    </div>
</div>