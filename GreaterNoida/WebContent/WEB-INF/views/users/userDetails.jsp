<%-- 
    Document   : User Details
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="userForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	var request;
	function updateUser()
	{
		mscConfirm("Are you sure, you want to update these details?",function(){
			document.getElementById('updateUserForm').submit();
		});
	}
	function removeUser()
	{
		mscConfirm("Are you sure, you want to remove this user?",function(){
	  		window.location="removeUser?userId="+document.getElementById('userId').value;
		});
	}
	function getSectors()
	{
		var department=document.getElementById('department');
		var url="getHelp?help=getSectors&key=sector&value=";
		for(var i=0;i<department.length;i++)
		{
			if(department.options[i].selected)
				url=url+department.options[i].value+",";
		}
		if(window.XMLHttpRequest)  
			request=new XMLHttpRequest();  
		else if(window.ActiveXObject)  
			request=new ActiveXObject("Microsoft.XMLHTTP");
		try
		{
			request.onreadystatechange=setSector;
			request.open("GET",url,true);  
			request.send();
		}
		catch(e)
		{}
	}
	function setSector()
	{
		var sectorMenu=document.getElementById("sector");
		var options;
		if(request.readyState==4)
		{
			var val=request.responseText.split('<@>');
			for(var i=0;i<val.length;i++)
				options=options+'<option value="'+val[i]+'">'+val[i]+"</option>";
			sectorMenu.innerHTML=options;
			if(document.getElementById("userType").value!='User' && document.getElementById('userType').value!='Select')
			{
				for(var i=0;i<sectorMenu.options.length;i++)
					sectorMenu.options[i].selected=true;
			}
		}
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
	<p class="h1" style="font-family: cambria math; text-align: center; color: #387403;">User Details</p><br>
	<userForm:form action="updateUser" id="updateUserForm" method="post" modelAttribute="userForm">
        <table class="table">
            <tr>
                <td align="center"><label style="color:black; font-family: cambria;" for="User Id"><h4><b>User Id:</b></h4></label></td>
                <td><userForm:input style="width: 250px; height: 35px;" id="userId" path="userId" autocomplete="off" readonly="true"/></td>
                <td align="center"><label style="color:black; font-family: cambria;" for="Password"><h4><b>Password:</b></h4></label></td>
                <td><userForm:password style="width: 250px; height: 35px;" path="password" id="password" showPassword="true" readonly="${read}"/></td>
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
                <td>
                    <userForm:select class="form-control" style="margin-top: 6px; height: 32px; width: 152px;" path="userType" id="userType" required="true">
                  		<userForm:option class="form-control" value="Select" label="Select"/>
                       	<userForm:option class="form-control" value="Super Administrator" label="Super Administrator"/>
                       	<userForm:option class="form-control" value="Administrator" label="Administrator"/>
                       	<userForm:option class="form-control" value="User" label="User"/>
                   </userForm:select>
                </td>
            </tr>
            <tr>
            	<td align="center"><label style="color:black; font-family: cambria;" for="Department"><h4><b>Departments:</b></h4></label></td>
            	<td>
            		<select class="form-control" style="height: 75px; width: 210px;" multiple id="department" name="department" onchange="getSectors();" required>
        				<c:forEach items="${selectedDepartments}" var="department">
                        	<option value="${department}" selected>${department}</option>
                        </c:forEach>
                        <c:forEach items="${unSelectedDepartments}" var="department">
                        	<option value="${department}">${department}</option>
                        </c:forEach>
        			</select>
            	</td>
            	<td align="center"><label style="color:black; font-family: cambria;" for="Sectors"><h4><b>Schemes:</b></h4></label></td>
            	<td>
            		<select class="form-control" style="height: 75px; width: 210px;" multiple id="sector" name="sector" required>
        				<c:forEach items="${selectedSectors}" var="sector">
                        	<option value="${sector}" selected>${sector}</option>
                        </c:forEach>
                        <c:forEach items="${unSelectedSectors}" var="sector">
                        	<option value="${sector}">${sector}</option>
                        </c:forEach>
        			</select>
            	</td>
            </tr>
            <tr>
                <td colspan="4" align="center">
                  	<label style="color:black; font-family: cambria;" for="Permissions"><h4><b>Select Permissions:</b></h4></label>
                  		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                  		<userForm:checkbox style="margin-top: 12px;" path="upload" id="upload"/>Upload Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="view" id="view"/>View Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="download" id="download"/>Download Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="print" id="print"/>Print Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="update" id="update"/>Update Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="report" id="report"/>Report Generation&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="agenda" id="agenda"/>Agenda Files&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   		<userForm:checkbox style="margin-top: 12px;" value="true" path="logs" id="logs"/>Logs
               </td>
            </tr>
            <tr><td colspan="4" align="center"><userForm:checkbox style="margin-top: 12px;" value="true" path="disable"/>Disable</td></tr>
            <tr>
            	<td colspan="2" align="center"><input class="btn btn-primary" style="background-color: #387403; color: #ffffff;" value="Update It" onclick="updateUser();"></td>
            	<td colspan="2" align="center"><input class="btn btn-primary" style="background-color: #387403; color: #ffffff;" value="Remove User" onclick="removeUser();"></td>
           	</tr>
        </table>
        <userForm:hidden path="sno"/>
    </userForm:form>
</div>