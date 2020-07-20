<%-- 
    Document   : Update Records
    Created on : 08 7, 2020, 05:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="UEForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateUEForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">UE</p>
<div class="container">
	<UEForm:form action="updateUE" id="updateUEForm" enctype="multipart/form-data" method="POST" modelAttribute="UEForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Contractor_Name"><h4><b>Contractor Name:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="contractor_Name" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="File_Number"><h4><b>File Number:</b></h4></label><br>
                    <UEForm:input style="width: 235px; height: 35px;" path="File_Number" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="name_Of_Work"><h4><b>Name Of Work:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="name_Of_Work" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="No_Of_Cros"><h4><b>No Of Cros:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="no_Of_Cros" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="Year."><h4><b>Year:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="year" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="category"><h4><b>CATEGORY:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="category" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="date"><h4><b>DATE:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="date" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="line_no"><h4><b>LINE NO:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="line_no" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="opa_fts"><h4><b>OPA/FTS:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="opa_fts" readonly="true"/>
                </td>
                </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="SECTOR"><h4><b>SECTOR:</b></h4></label><br>
                   	<UEForm:input style="width: 235px; height: 35px;" path="sector" required="true"/>
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
            <tr><td><UEForm:hidden path="sno"/></td><td><UEForm:hidden path="location"/></td><td><UEForm:hidden path="department"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </UEForm:form>
</div>