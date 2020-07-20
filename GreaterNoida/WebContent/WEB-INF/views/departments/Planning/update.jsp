<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="planningForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updatePlanningForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Planning - ${planningForm.subDepartment}</p>
<div class="container">
	<planningForm:form action="updatePlan" id="updatePlanningForm" enctype="multipart/form-data" method="post" modelAttribute="planningForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="BP No."><h4>BP No.:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" path="bpNo" readonly="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Allotment No"><h4>Allotment No.:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" path="allotmentNo" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Allotee Name"><h4>Applicant Name:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="applicantName" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="plotSize"><h4>PlotSize:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="plotSize" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="Sector"><h4>Sector:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" path="sector" readonly="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="blockNo"><h4>Block No.:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="blockNo" required="true"/>
                </td>
                <td>
                  	<label style="color: #345c65;" class="lb" for="Scheme Code"><h4>File Type:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="fileType" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="House/Plot"><h4>House/Plot No.:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="plotNo" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="DOA"><h4>DOA:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" path="doa" required="true"/>
                </td>
               	<td>
                   	<label style="color: #345c65;" class="lb" for="DOC"><h4>DOC:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="doc" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="FTS"><h4>FTS No.:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="fts" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Category"><h4>Category:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="category" readonly="true"/>
                </td>
            </tr>
            <tr>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="policyNo"><h4>Policy No:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="policyNo" required="true"/>
                </td>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="subject"><h4>Subject:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="subject" required="true"/>
                </td>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="subject"><h4>Sub-Department:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="subDepartment" readonly="true"/>
                </td>
                </tr>
            <tr>
             <tr>
             <td>
                   	<label style="color: #345c65;" class="lb" for="bn_no"><h4>Bn No:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="bn_no" readonly="true"/>
                </td>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="year"><h4>Year:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="year" required="true"/>
                </td>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="clerk_Name"><h4>Clerk Name:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="clerk_Name" required="true"/>
                </td>
                 <td>
                   	<label style="color: #345c65;" class="lb" for="fileNo"><h4>File No:</h4></label><br>
                   	<planningForm:input style="width: 235px; height: 35px;" type="text" class="in" path="fileNo" required="true"/>
                </td>
                </tr>
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="File"><h4>Choose NoteSheet Pages:</h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="File"><h4>Choose Correspondence Page:</h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="correspondence"/>
                </td>
            </tr>
            <tr><td ><planningForm:hidden path="sno"/></td><td><planningForm:hidden path="location"/></td></tr>
            <tr><td colspan="4" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </planningForm:form>
</div>