<%-- 
    Document   : Update Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="financeForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateFinanceForm').submit();
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Finance(${financeForm.subdepartment})</p>
<c:choose>
	<c:when test="${financeForm.subdepartment =='Bank Statement'  || financeForm.subdepartment=='Loan'}" >
<div class="container">
	<financeForm:form action="updateFin" id="updateFinanceForm" enctype="multipart/form-data" method="POST" modelAttribute="financeForm">
        
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="bankName"><h4><b>Bank Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="bankName" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="branchName"><h4><b>Branch Name:</b></h4></label><br>
                    <financeForm:input style="width: 235px; height: 35px;" path="branchName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="fileNo"><h4><b>FILE NAME:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="fileNo" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="statement"><h4><b>Period of Statement:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="statement" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="periodOfYear"><h4><b>Period of Year.:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="periodOfYear" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="patrawaliSankya"><h4><b>PATRAWALI SANKYA:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="patrawaliSankya" required="true"/>
                </td>
            </tr>
             <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="clerkName"><h4><b>Clerk Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="clerkName" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="registerName"><h4><b>Register Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="registerName" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="subdepartment"><h4><b>Sub Department:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="subdepartment" readonly="true"/>
                </td>
            </tr>
            <tr>
            <td>
                   	<label style="color: black; font-family: cambria;" for="accountNo"><h4><b>Account No.:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="accountNo" readonly="true"/>
                </td>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="subject"><h4><b>SUBJECT:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="subject" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose NoteSheet Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                </tr>
		            <tr>
		                <td>
		                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose Correspondence Page:</b></h4></label><br>
		                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file"  name="correspondence"/>
		                </td>
	                </tr>
            <tr><td><financeForm:hidden path="sno"/></td><td colspan="2"><financeForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </financeForm:form>
</div>
</c:when>
	<c:otherwise>
	<div class="container">
	<financeForm:form action="updateFin" id="updateFinanceForm" enctype="multipart/form-data" method="post" modelAttribute="financeForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="fileNo"><h4><b>FILE NO:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="fileNo" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="codeNo"><h4><b>CODE NO:</b></h4></label><br>
                    <financeForm:input style="width: 235px; height: 35px;" path="codeNo" readonly="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="year"><h4><b>YEAR :</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="year" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="subject"><h4><b>SUBJECT:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="subject" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="designation"><h4><b>DESIGNATION:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="designation" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="fileType"><h4><b>TYPE OF FILE:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="fileType" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="subdepartment"><h4><b>Sub Department:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="subdepartment" readonly="true"/>
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
            <tr><td><financeForm:hidden path="sno"/></td><td colspan="2"><financeForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </financeForm:form>
</div>
	</c:otherwise>
	</c:choose>