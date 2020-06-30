<%-- 
    Document   : User Details For CEO
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function updateUser()
	{
		mscConfirm("Confirm these details before proceeding?",function(){
		  	document.getElementById('updateUserForm').submit();
		});
	}
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #387403;">
      			<span class="close" onclick="document.getElementById('myModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      			<p style="text-align: center; color: #FFFFFF;" class="h3">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<div class="container">
	<p class="h1" style="font-family: cambria math; text-align: center; color: #387403;">CEO Details</p><br>
	<userForm:form action="updateUser" id="updateUserForm" method="post" modelAttribute="userForm">
        <table class="table">
            <tr>
                <td align="center"><label style="color:black; font-family: cambria;" for="User Id"><h4><b>User Id:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" id="userId" path="userId" autocomplete="off" readonly="true"/></td>
                <td align="center"><label style="color:black; font-family: cambria;" for="Password"><h4><b>Password:</b></h4></label></td>
                <td><userForm:password style="width: 250px; height: 35px;" path="password" id="password" showPassword="true" required="true"/></td>
            </tr>
            <tr>
              	<td align="center"><label style="color:black; font-family: cambria;" for="Mobile No."><h4><b>Mobile No.:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="mobileNo" id="mobileNo" required="true"/></td>
                <td align="center"><label style="color:black; font-family: cambria;" for="Email Id"><h4><b>Email Id:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="emailId" id="emailId" required="true"/></td>
            </tr>
            <tr>
                <td align="center"><label style="color:black; font-family: cambria;" for="First Name"><h4><b>First Name:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="firstName" id="firstName" required="true"/></td>
                <td align="center"><label style="color: black; font-family: cambria;" for="Last Name"><h4><b>Last Name:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="lastName" id="lastName" required="true"/></td>
            </tr>
            <tr>
            	<td align="center"><label style="color: black; font-family: cambria;" for="EmployeeId"><h4><b>Employee Id:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="employeeId" id="employeeId" required="true"/></td>
                <td align="center"><label style="color: black; font-family: cambria;" for="User Type"><h4><b>User Type:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" path="userType" id="userType" readonly="true"/></td>
            </tr>
            <tr><td colspan="4"><userForm:hidden path="sno"/></td></tr>
            <tr><td colspan="4" align="center"><input class="btn btn-primary" style="background-color: #387403; color: #ffffff;" value="Update It" onclick="updateUser();"></td></tr>
        </table>
        <div style="display: none;">
        	<userForm:checkbox style="margin-top: 12px;" path="upload" id="upload"/>Upload Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
              		<userForm:checkbox style="margin-top: 12px;" value="true" path="view" id="view"/>View Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="download" id="download"/>Download Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="print" id="print"/>Print Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="update" id="update"/>Update Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="report" id="report"/>Report Generation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="agenda" id="agenda"/>Agenda Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="logs" id="logs"/>Logs
        </div>
    </userForm:form>
</div>