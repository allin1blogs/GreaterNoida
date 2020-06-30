<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="projectForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateProjectForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">${department}</p>
<div class="container">
	<projectForm:form action="updatePro" id="updateProjectForm" enctype="multipart/form-data" method="post" modelAttribute="projectForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Work Circle"><h4><b>Work Circle:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="department" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                    <projectForm:input style="width: 235px; height: 35px;" path="sector" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Contractor Name"><h4><b>Contractor Name:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="contractorName" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Work Name"><h4><b>Work Name:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="workName" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="OPA/FTS No."><h4><b>OPA/FTS No.:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="opaFts" readonly="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File No."><h4><b>File No.:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="fileNo" required="true"/>
                </td>
            </tr>
             <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Category"><h4><b>Category:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="Category" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="WorkCircle"><h4><b>WorkCircle.:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="WorkCircle" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Year"><h4><b>Year:</b></h4></label><br>
                   	<projectForm:input style="width: 235px; height: 35px;" path="year" required="true"/>
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
            <tr><td colspan="3"><projectForm:hidden path="sno"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </projectForm:form>
</div>