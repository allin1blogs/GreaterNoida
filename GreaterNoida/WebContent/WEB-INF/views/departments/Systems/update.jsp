<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="SystemForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateSystemForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Systems</p>
<div class="container">
	<SystemForm:form action="updateSys" id="updateSystemForm" enctype="multipart/form-data" method="post" modelAttribute="SystemForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="lotNo"><h4><b>LOT NO:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="lotNo" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="Sector"><h4><b>Clerk Name:</b></h4></label><br>
                    <SystemForm:input style="width: 235px; height: 35px;" path="clerk_Name" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="file_No"><h4><b>file No:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="file_No" readonly="true"/>
                </td>
            </tr>
              <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="lotNo"><h4><b>Year:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="Year" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="Opt_Name"><h4><b>Opt Name:</b></h4></label><br>
                    <SystemForm:input style="width: 235px; height: 35px;" path="Opt_Name" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Total_No_Of_Pages"><h4><b>Total No Of Pages:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="Total_No_Of_Pages" required="true"/>
                </td>
            </tr>
             <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="date"><h4><b>Date:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="date" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="No_Of_Cros"><h4><b>Total No Of Cros:</b></h4></label><br>
                    <SystemForm:input style="width: 235px; height: 35px;" path="No_Of_Cros" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="no_Of_NoteSheet"><h4><b>Total No Of Notesheet:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="no_Of_NoteSheet" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="optFts"><h4><b>Opt/Fts No.:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="optFts" readonly="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="subjectName."><h4><b>Subject Name:</b></h4></label><br>
                   	<SystemForm:input style="width: 235px; height: 35px;" path="subjectName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose NoteSheet Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                </tr>
                <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose Correspondence Page:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="correspondence"/>
                </td>
            </tr>
            <tr><td><SystemForm:hidden path="sno"/></td><td><SystemForm:hidden path="location"/><td><SystemForm:hidden path="department"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </SystemForm:form>
</div>