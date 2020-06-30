<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="generalForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateGeneralForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">${generalForm.department}</p>
<img src="/staticResources/images/waterMark.png" style="opacity: 30%;"/>
<div class="container">
	<generalForm:form action="updateGen" id="updateGeneralForm" enctype="multipart/form-data" method="post" modelAttribute="generalForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Allotment Letter"><h4><b>Allotment No.:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="allotmentNo" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="Sector"><h4><b>Sector No.:</b></h4></label><br>
                    <generalForm:input style="width: 235px; height: 35px;" path="sector" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Allotee Name"><h4><b>Allotee Name:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="alloteeName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Plot Size"><h4><b>Plot Size:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="plotSize" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Block No"><h4><b>Block No.:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="block" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="Scheme Code"><h4><b>Scheme Code:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="scheme" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="House/Plot"><h4><b>House/Plot No.:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="plotNo" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Master Plot"><h4><b>Master Plot No.:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="masterPlot" required="true"/>
                </td>
            </tr>
            <tr>
               	<td>
                   	<label style="color: black; font-family: cambria;" for="FTS"><h4><b>FTS No.:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="fts" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Office Order"><h4><b>Office Order:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="officeOrder" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Order Date"><h4><b>Order Date:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="orderDate" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Agenda"><h4><b>Agenda:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="agenda" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Board Meeting"><h4><b>Board Meeting:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="boardMeeting" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Meeting Date"><h4><b>Meeting Date:</b></h4></label><br>
                   	<generalForm:input style="width: 235px; height: 35px;" path="meetingDate" required="true"/>
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
            	<td><generalForm:hidden path="sno"/></td><td><generalForm:hidden path="department"/></td><td colspan="2"><generalForm:hidden path="location"/></td>
            </tr>
            <tr><td colspan="4" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </generalForm:form>
</div>