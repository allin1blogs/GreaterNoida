<%-- 
    Document   : Create Records
    Created on : 22 June, 2020, 01:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="marketingForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateMarketingForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Marketing</p>
<div class="container">
	<marketingForm:form action="updateMarketing" id="updateMarketingForm" enctype="multipart/form-data" method="post" modelAttribute="marketingForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Lot No"><h4><b>LOT NO:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="lot_No" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="FTS NO OPA NO"><h4><b>FTS NO OPA NO:</b></h4></label><br>
                    <marketingForm:input style="width: 235px; height: 35px;" path="fts_No_Opa_No" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File No"><h4><b>File No:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="file_No" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Applicant_Name"><h4><b>Applicant Name:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="applicant_Name" required="true"/>
                </td>
               <%--  <td>
                  	<label style="color: black; font-family: cambria;" for="No Of NoteSheet"><h4><b>No Of NoteSheet:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="no_Of_NoteSheet" required="true"/>
                </td> --%>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Total No Of Pages"><h4><b>Total No Of Pages:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="Total_No_Of_Pages" required="true"/>
                </td>
            </tr>
             
            <tr>
                
                <%-- <td>
                  	<label style="color: black; font-family: cambria;" for="No Of Cros"><h4><b>No Of Cros:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="No_Of_Cros" required="true"/>
                </td> --%>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Opt Name"><h4><b>Opt Name:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="Opt_Name" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Year"><h4><b>Year:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="Year" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="Date"><h4><b>Date:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="date" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Department"><h4><b>Department:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="department" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Clerk Name"><h4><b>Clerk Name:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="clerk_Name" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Location"><h4><b>Location:</b></h4></label><br>
                   	<marketingForm:input style="width: 235px; height: 35px;" path="Location" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose File Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="file"/>
                </td>
            </tr>
            <tr><td><marketingForm:hidden path="sno"/></td><td colspan="2"><marketingForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </marketingForm:form>
</div>