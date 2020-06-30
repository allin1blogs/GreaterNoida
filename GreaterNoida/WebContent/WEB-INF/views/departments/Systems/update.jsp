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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Finance</p>
<div class="container">
	<financeForm:form action="updateFin" id="updateFinanceForm" enctype="multipart/form-data" method="post" modelAttribute="financeForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Pos"><h4><b>Period of Statement:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="statement" readonly="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                    <financeForm:input style="width: 235px; height: 35px;" path="sector" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Bank Name"><h4><b>Bank Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="bankName" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Branch Name"><h4><b>Branch Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="branchName" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="Account No."><h4><b>Account No.:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="accountNo" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="Register Name"><h4><b>Register Name:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="registerName" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Category"><h4><b>Category:</b></h4></label><br>
                   	<financeForm:input style="width: 235px; height: 35px;" path="category" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose File Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="file"/>
                </td>
            </tr>
            <tr><td><financeForm:hidden path="sno"/></td><td colspan="2"><financeForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </financeForm:form>
</div>