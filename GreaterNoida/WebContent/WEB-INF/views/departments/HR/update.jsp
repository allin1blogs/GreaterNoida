<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="HRForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateHRForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">HR</p>
<div class="container">
	<HRForm:form action="updateEM" id="updateHRForm" enctype="multipart/form-data" method="post" modelAttribute="HRForm">
        <table class="table">
            <tr>
                <td>
                    <label style="color: black; font-family: cambria;" for="line_No"><h4><b>Line No:</b></h4></label><br>
                    <HRForm:input style="width: 235px; height: 35px;" path="line_No" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="fileNo"><h4><b>File No:</b></h4></label><br>
                   	<HRForm:input style="width: 235px; height: 35px;" path="fileNo" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="opaFts"><h4><b>Opa/Fts No.:</b></h4></label><br>
                   	<HRForm:input style="width: 235px; height: 35px;" path="opaFts" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="fileCode"><h4><b>File Code:</b></h4></label><br>
                   	<HRForm:input style="width: 235px; height: 35px;" path="fileCode" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="year"><h4><b>Year:</b></h4></label><br>
                   	<HRForm:input style="width: 235px; height: 35px;" path="year" readonly="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="file_Subject"><h4><b>File Subject:</b></h4></label><br>
                   	<HRForm:input style="width: 235px; height: 35px;" path="file_Subject" required="true"/>
                </td>
            </tr>
            <tr>
            	
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose NoteSheet Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose Correspondence Page:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="correspondence"/>
                </td>
            </tr>
            <tr><td colspan="3"><HRForm:hidden path="sno"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </HRForm:form>
</div>