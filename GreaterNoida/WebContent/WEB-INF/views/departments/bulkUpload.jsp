<%-- 
    Document   : Bulk Upload
    Created on : 03 Dec, 2017, 05:32:32 PM
    Author     : Swapril Tyagi
    Updated By  : Ashok Khatri
     Updated By      : Preeti Rani 
    
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function upload()
	{
		if(document.getElementById('oracle').checked)
		{
			mscConfirm("Have checked all details before uploading records with Oracle Check?",function(){
			  	document.getElementById('uploadForm').submit();
			});	
		}
		else
		{
			mscConfirm("Have checked all details before proceeding without Oracle Check?",function(){
			  	document.getElementById('uploadForm').submit();
			});
		}
	}
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #FFFC9F;">
      			<span class="close" onclick="document.getElementById('myModal').style.display='none'" style="color: black;">&times;</span>
      			<p style="text-align: center; color: black;" class="h3">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<p class="h1" style="font-family: cambria; text-align:center; color: #387403;">Bulk Upload</p>
<br><br>
<div class="container">
	<form action="${pageContext.request.contextPath}/uploadBulk" id="uploadForm" enctype="multipart/form-data" method="post">
        <table class="table">
            <tr>
                <td align="center"><label for="File Location"><h4><b>Enter Pdf File Location:</b></h4></label></td>
                <td><input style="width: 250px; height: 35px;" name="pdfLocation" required></td>
                <td><label for="Csv File"><h4><b>Select Csv File:</b></h4></label></td>
                <td><input style="width: 250px; height: 35px; margin-top: 7px;" type="file" name="csv" accept=".csv" required></td>
            </tr>
            <tr>
                <td align="center"><label for="Departments"><h4><b>Select Department:</b></h4></label></td>
                <td>
                    <select class="form-control" style="height: 36px; width: 210px;" name="department" required>
                    	<option class="form-control" value="Select">Select</option>
        				<c:forEach items="${departments}" var="department">
                        	<c:if test="${department!='Planning'}"><option class="form-control" value="${department}">${department}</option></c:if>
                        	<c:if test="${department=='Planning'}">
                        		<option class="form-control" value="Planning(Industry)">Planning(Industry)</option>
                        		<option class="form-control" value="Planning(Residential)">Planning(Residential)</option>
                        		<option class="form-control" value="Planning(Institutional)">Planning(Institutional)</option>
                        	</c:if>
                        	<c:if test="${department=='Finance'}">
                        		<option class="form-control" value="Finance_Bank_Statement">Finance(Bank Statement)</option>
                        		<option class="form-control" value="Finance_Direct_Salary">Finance(Direct Salary)</option>
                        		<option class="form-control" value="Finance_Deputation_Salary">Finance(Deputation Salary)</option>
                        		<option class="form-control" value="Finance_Labor_Cass">Finance(Labor Cass)</option>
                        		<option class="form-control" value="Finance_Costing">Finance(Costing)</option>
                        		<option class="form-control" value="Finance_Loan">Finance(Loan)</option>
                        		<option class="form-control" value="Finance_tax">Finance(tax)</option>
                        		<option class="form-control" value="Finance_TDS">Finance(TDS)</option>
                        	</c:if>
                        </c:forEach>
        			</select>
                </td>
            </tr>
            <tr><td colspan="4" align="center"><input type="checkbox" name="oracle" value="yes" id="oracle"><b>Check with Oracle</b></td></tr>
            <tr><td colspan="4" align="center"><input class="btn btn-primary" style="background-color: #387403; color: #ffffff;" value="Upload" onclick="upload();"></td></tr>
        </table>
    </form>
    <c:if test="${not empty err}">
    	<div id="span" align="center"><p style="text-align: center; color: red;" class="h3">${msg}</p></div>
        <div align="center" id="span">
            <a style="color: red;" href="${pageContext.request.contextPath}/errFile" class="h4">Click here to see Detail...</a>
        </div>
    </c:if>
</div>
<br><br><br>