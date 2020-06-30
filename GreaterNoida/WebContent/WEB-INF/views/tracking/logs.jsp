<%-- 
    Document   : Logs
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/logs.js'/>"></script>
<script type="text/javascript">
	$(document).ready(function() 
		{
		   $('#logTable').DataTable({
		       "pagingType":"full_numbers"
		    });
		});
	function logs()
	{
		if(document.getElementById('userId').value=='Select')
		{
			document.getElementById('modal').style.display='block';
			document.getElementById('contentPara').innerHTML='Select a valid  User Id!';
		}
		else
			document.getElementById('logForm').submit();
	}
</script>

<div id="modal" class="modal" style="display: ${view};">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;">
      		<span class="close" onclick="document.getElementById('modal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      		<p style="text-align: center; color: #FFFFFF;" class="h3" id="contentPara">${msg}</p>
    	</div>
  	</div>
</div>

<div align="center" class="container">
	<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Logs</p>
    <form action="logs" method="post" id="logForm">
        <table class="table" id="userTable" style="margin-left: 5%;">
            <tr>
                <td align="right"><label style="font-family: cambria;" for="username"><h4><b>Select UserId:</b></h4></label></td>
                <td>
                    <select class="form-control" style="height: 36px; width: 190px;" id="userId" name="userId" required>
                    	<option class="form-control" value="Select">Select</option>
        				<c:forEach items="${users}" var="user"><option class="form-control" value="${user}">${user}</option></c:forEach>
        			</select>
                </td>
            </tr>
            <tr><td colspan="2" align="center"><input class="btn btn-primary" style="background-color: #2510f8; color: #ffffff;" type="button" onclick="logs();" value="Search"></td></tr>
        </table>
    </form>
    <c:if test="${not empty logs}">
    	<table id="logTable" class="table display">
    		<thead>
    			<tr>
    				<th>User Id</th>
    				<th>Activity</th>
    				<th>Date</th>
    				<th>Time</th>
    				<th>MAC Id</th>
    				<th>Device Name</th>
    			</tr>
    		</thead>
    		<tbody>
    			<c:forEach items="${logs}" var="log">
    				<tr>
    					<td><c:out value="${log.userId}"/></td>
    					<td><c:out value="${log.activity}"/></td>
    					<td><c:out value="${log.date}"/></td>
    					<td><c:out value="${log.time}"/></td>
    					<td><c:out value="${log.macId}"/></td>
    					<td><c:out value="${log.deviceName}"/></td>
    				</tr>
    			</c:forEach>
    		</tbody>
    	</table>
    </c:if>
</div>