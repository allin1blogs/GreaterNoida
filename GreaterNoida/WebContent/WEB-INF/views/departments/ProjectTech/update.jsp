<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="ptForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateptForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">ProjectTech</p>
<div class="container">
	<ptForm:form action="updateProjectTech" id="updateptForm" enctype="multipart/form-data" method="post" modelAttribute="ProjectTechForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="BP No."><h4>OPA/FTS:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" path="opaFts" readonly="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Allotment No"><h4>SECTOR NAME:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" path="sector" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Allotee Name"><h4>CATEGORY:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" class="in" path="category" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Plot Size"><h4>NAME OF WORK:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" path="name_Of_Work" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: #345c65;" class="lb" for="Sector"><h4>CONTRACTOR NAME:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" path="contractor_Name" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="Block No"><h4>DEPARTMENT:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" class="in" path="department" readonly="true"/>
                </td>
                <td>
                  	<label style="color: #345c65;" class="lb" for="Scheme Code"><h4>FILE NUMBER:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" class="in" path="fileNumber" required="true"/>
                </td>
                <td>
                   	<label style="color: #345c65;" class="lb" for="House/Plot"><h4>YEAR:</h4></label><br>
                   	<ptForm:input style="width: 235px; height: 35px;" type="text" class="in" path="year" required="true"/>
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
            <tr><td colspan="2"><ptForm:hidden path="sno"/></td><td colspan="2"><ptForm:hidden path="location"/></td></tr>
            <tr><td colspan="4" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </ptForm:form>
</div>