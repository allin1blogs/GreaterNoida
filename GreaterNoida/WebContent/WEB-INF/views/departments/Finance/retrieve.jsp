<%-- 
    Document   : Retrieve Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="financeForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<script type="text/javascript">
	var request,fileId,count,currentCount;
	$(document).ready(function() 
	{
    	$('#fileTable').DataTable({
        	"pagingType":"full_numbers"
    	});
	});
	$(document).on('keydown', function(e) {
	    if(e.ctrlKey && (e.key == "p" || e.charCode == 16 || e.charCode == 112 || e.keyCode == 80) ){
	        e.cancelBubble = true;
	        e.preventDefault();
	        e.stopImmediatePropagation();
	    }  
	});
	function retrieveFiles()
	{
		var statement=document.getElementById('statement').value;
		var bankName=document.getElementById('bankName').value;
		var branchName=document.getElementById('branchName').value;
		var sector=document.getElementById('sector').value;
		var registerName=document.getElementById('registerName').value;
		var subdepartment=document.getElementById('subdepartment').value;
		if(statement=='' && bankName=='' && branchName=='' && sector=='' && registerName=='' && subdepartment=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('financeForm').submit();
	}
	function deleteFile()
	{
		window.location="delView?department=Finance";
	}
	function viewFile(pos,sno,right,bankName)
	{
		if(right==1)
		{
			var url="viewFile?id="+pos+"&sno="+sno+"&department=Finance&prFlage=null&name="+bankName;
			if(window.XMLHttpRequest)  
				request=new XMLHttpRequest();  
			else if(window.ActiveXObject)  
				request=new ActiveXObject("Microsoft.XMLHTTP");
			document.getElementById('id01').style.display='block';
			document.getElementById('div').innerHTML='<p style="margin-top: 5%; font-size: 22px; font-family: cambria; text-align: center;">Opening File...</p>';
			try
			{
				request.onreadystatechange=setFile;
				request.open("GET",url,true);  
				request.send();
			}
			catch(e)
			{}
		}
	}
	function setFile()
	{
		if(request.readyState==4)
		{
			var data=request.responseText.split('<@>');
			fileId=data[0];count=data[1];
			setPageList();
			var pages='<p style="text-align: center; font-family: cambria; font-size: 14px; color: #ffffff;">Go To Page:</p><select style="width: 50px; height: 15px;" name="notePage"><option value="Select">Select</option></select>';
			currentCount=1;
			getPage(1);
		}
	}
	function nexPre(opr)
	{
		if(opr=='pre' && currentCount!=1)
			currentCount=currentCount-1;
		if(opr=='nex' && currentCount<(count-1))
			currentCount=currentCount+1;
		getPage(currentCount);
		setPageList();
	}
	function getPage(page)
	{
		var div=document.getElementById('div');
		if(page=='self')
			page=parseInt(document.getElementById('page').value);
		currentCount=page;
		div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'@'+page+'.pdf#toolbar=0"></object>';
	}
	function setPageList()
	{
		var page=document.getElementById('page');
		for(var i=0;i<count;i++)
		{
			if(i==0)
				page.innerHTML='<option value="Select">Select</option>';
			else
				page.innerHTML=page.innerHTML+'<option value="'+i+'">'+i+'</option>'
		}
	}
	function downloadFile(pos,sno,right)
	{
		if(right==1)
			window.location="downloadFile?id="+pos+"&sno="+sno+"&department=Finance";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=Finance";
	}
	function report(sno,right,flage)
	{
		if(right==1)
			document.getElementById('reportForm').submit();
	}
	function printOut(pos,sno,right,bankName)
	{
		if(right==1)
		{
			var url="viewFile?id="+pos+"&sno="+sno+"&department=Finance&prFlage=print&name="+bankName;
			setContent('Processing...');
			if(window.XMLHttpRequest)  
				request=new XMLHttpRequest();  
			else if(window.ActiveXObject)  
				request=new ActiveXObject("Microsoft.XMLHTTP");
			try
			{
				request.onreadystatechange=setPrint;
				request.open("GET",url,true);  
				request.send();
			}
			catch(e)
			{}
		}
	}
	function setPrint()
	{
		if(request.readyState==4)
		{
			var id=request.responseText;
			document.getElementById('printDiv').innerHTML='<iframe id="pdf" src="staticResources/pdfs/'+id+'.pdf"></iframe>';
			var ifr=document.getElementById('pdf');
			document.getElementById('authModal').style.display='none';
			ifr.contentWindow.print();
		}
	}
	function singlePrint()
	{
		var contentDiv=document.getElementById('singlePrintDiv');
		contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'@'+currentCount+'@print.pdf"></iframe>';
		document.getElementById('singlePdf').contentWindow.print();
	}
	function firLas(page)
	{
		if(page=='fir')
			getPage(1);
		else
			getPage(count-1);
	}
	function setContent(content)
	{
		document.getElementById('authContentPara').innerHTML=content;
		document.getElementById('authModal').style.display='block';
	}
</script>

<div id="id01" class="base-modal" style="display: none; z-index:99999; margin: 0px; padding: 0px;">
    <a href="" style="font-size: 26px;"><span onclick="deleteFile();" style="float: right; color: red;"><b>&times;</b></span></a>
	<table style="width: 100%;">
		<tr>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 33%;" class="btn btn-primary" id="preButNote" onclick="firLas('fir');">First</button>
				<button class="btn btn-primary" onclick="nexPre('pre');">Previous</button>
				<select style="width: 70px; height: 25px;" id="page" onchange="getPage('self');"></select>
				<button class="btn btn-primary" onclick="nexPre('nex');">Next</button>
				<button class="btn btn-primary" id="preButNote" onclick="firLas('las');">Last</button>
				<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 35%;" onclick="singlePrint();">Print It</button></c:if>
			</td>
		</tr>
	</table>
    <div id="div" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:100%; height:90%;"></div>
</div>

<div id="printModel" class="base-modal" style="display: none; z-index:99999;">
    <a href="#" style="text-decoration: none; color: red; font-family: cambria; font-size: 20px;"><span onclick="deleteFile();" class="base-closebtn base-hover-red base-display-topright">X</span></a>
    <div id="printDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height:99%;"></div>
</div>

<div id="singlePrintModal" class="modal" style="display: none; z-index: 100000;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;" id="singlePrintDiv"></div>
  	</div>
</div>

<div id="authModal" class="modal" style="display: none;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;">
    		<span class="close" onclick="document.getElementById('authModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
    		<p style="text-align: center; color: #ffffff;" class="h3" id="authContentPara"></p>
    	</div>
  	</div>
</div>

<c:if test="${not empty msg}">
	<div id="modal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #387403;">
      			<span class="close" onclick="document.getElementById('modal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      			<p style="text-align: center; color: #ffffff;" class="h3" id="contentPara">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Finance</p>
<div style="margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
    <financeForm:form action="retrieveFin" id="financeForm" method="get" modelAttribute="financeForm">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Statement"><h4><b>Statement:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" path="statement" id="statement" list="statementHelp" onkeyup="getHelp('statement');"/>
                	<datalist id="statementHelp"></datalist>
                </td>
            	<td>
            		<label style="font-family: cambria;" for="Bank Name"><h4><b>Bank Name:</b></h4></label><br>
            		<financeForm:input style="width: 230px; height: 35px;" id="bankName" path="bankName" list="bankNameHelp" onkeyup="getHelp('bankName');"/>
            		<datalist id="bankNameHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Branch Name"><h4><b>Branch Name:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" id="branchName" path="branchName" list="branchNameHelp" onkeyup="getHelp('branchName');"/>
                	<datalist id="branchNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Register Name"><h4><b>Register Name:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" path="registerName" id="registerName" list="registerNameHelp" onkeyup="getHelp('registerName');"/>
                	<datalist id="registerNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" path="sector" id="sector" list="sectorHelp" onkeyup="getHelp('sector');"/>
                	<datalist id="sectorHelp"></datalist>
                </td>
            </tr>
            <tr>
             <td>
                	<label style="font-family: cambria;" for="subdepartment"><h4><b>Sub-Department:</b></h4></label><br>
                	<financeForm:select style="height: 35px; width: 200px;" path="subdepartment" id="subdepartment">
                		<financeForm:option value="Select" label="Select"/>
                  		<financeForm:options items="${sectors}"/>
                   	</financeForm:select>
                </td>
            </tr>
            <tr><td><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td></tr>
        </table>
    </financeForm:form>
</div><br>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="Finance">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
					<th></th>
					<th>Account No.</th>
					<th>Period of Statement</th>
					<th>Bank Name</th>
					<th>Branch Name</th>
					<th>Sector</th>
					<th>Register Name</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.accountNo}</td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.statement}</a></td>
						<td>${record.bankName}</td>
						<td>${record.branchName}</td>
						<td>${record.sector}</td>
						<td>${record.registerName}</td>
						<td>
							<a href="#" onclick="viewFile('${record.statement}','${record.sno}','${view}','${record.bankName}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.statement}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.statement}','${record.sno}','${print}','${record.bankName}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br><br><br>