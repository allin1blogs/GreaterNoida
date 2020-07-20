<%-- 
    Document   : Create Records
    Created on : 22 June, 2020, 01:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="HealthForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateHealthForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Health</p>
<div class="container">
	<HealthForm:form action="updateHealth" id="updateHealthForm" enctype="multipart/form-data" method="post" modelAttribute="HealthForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="category"><h4><b>Category:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="category" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="contractorName"><h4><b>Contractor Name:</b></h4></label><br>
                    <HealthForm:input style="width: 235px; height: 35px;" path="contractorName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Department"><h4><b>Department:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="department" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Applicant_Name"><h4><b>File No:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="fileNo" readonly="true"/>
                </td>
            <td>
                   	<label style="color: black; font-family: cambria;" for="scheme"><h4><b>scheme:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="scheme" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="opa_fts"><h4><b>OPA/FTS:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="opa_fts" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="sector"><h4><b>Sector:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="sector" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="workName"><h4><b>Work Name:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="workName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="year"><h4><b>Year:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="year" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="contrName"><h4><b>Contr Name:</b></h4></label><br>
                   	<HealthForm:input style="width: 235px; height: 35px;" path="contrName" required="true"/>
                </td>
                 <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose NoteSheet Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose Correspondence Page:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="correspondence"/>
                </td>
            </tr>
			<tr>
            	
            </tr>
            <tr><td><HealthForm:hidden path="sno"/></td><td colspan="2"><HealthForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </HealthForm:form>
</div>