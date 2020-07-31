<%-- 
    Document   : Retrieve Records
    Created on : 27 7, 2020, 05:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="financeForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<fmt:setLocale value = "en"/>
<fmt:setBundle basename="resources.application" var="storeText" scope="request"/>
<fmt:message bundle="${storeText}" var="financeSubdepartment" key="FINANCE_SUBDEPARTMENT" />
<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<script type="text/javascript">
	var request,fileId,noteCount,corrCount,currentNote,currentCorr,currentSno,currentAlloteeName,printLr,sno,bankName,subdepartment;
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
		var accountNo=document.getElementById('accountNo').value;
		var codeNo=document.getElementById('codeNo').value;
		var subdepartment=document.getElementById('subdepartment').value;
		if(subdepartment=='Select'){
			setContent('First Select Sub-department Parameters!');
		}
		else{
		if(statement=='' && bankName=='' && branchName=='' && sector==''  && subdepartment=='Select' && registerName=='' && accountNo==''  && codeNo=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('financeForm').submit();
		}
	}
	function deleteFile()
	{
		window.location="delView?department=Finance";
	}
	function viewFile(pos,sno,right,bankName)
	{
              currentSno=sno;currentAlloteeName=bankName;
		if(right==1)
		{
			var url="viewFile?id="+pos+"&sno="+sno+"&department=Finance&prFlage=null&name="+bankName;
			if(window.XMLHttpRequest)  
				request=new XMLHttpRequest();  
			else if(window.ActiveXObject)  
				request=new ActiveXObject("Microsoft.XMLHTTP");
			document.getElementById('id01').style.display='block'; 
			var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
			document.getElementById('noteDiv').innerHTML='<p style="margin-top: 5%; font-size: 22px; font-family: cambria; text-align: center;">Opening Notesheet...</p>';
			if(subDepartemnt != 'Bank Statement')
			document.getElementById('corrDiv').innerHTML='<p style="margin-top: 5%; font-size: 22px; font-family: cambria; text-align: center;">Opening Correspondence...</p>';
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
			fileId=data[0];
			noteCount=data[1];
			var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
			if(subDepartemnt != 'Bank Statement')
			corrCount=data[2];
			setPageList();
			var pages='<p style="text-align: center; font-family: cambria; font-size: 14px; color: #ffffff;">Go To Page:</p><select style="width: 50px; height: 15px;" name="notePage"><option value="Select">Select</option></select>';
			currentNote=1;currentCorr=1;
			getPage('noteDiv',1);

			var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
			if(subDepartemnt != 'Bank Statement')
			getPage('corrDiv',1);
		}
	}
	function nexPre(type,opr)
	{
		if(type=='noteDiv')
		{
			if(opr=='pre' && currentNote!=1)
				currentNote=currentNote-1;
			if(opr=='nex' && currentNote<(noteCount-1))
				currentNote=currentNote+1;
			getPage(type,currentNote);
		}
		else
		{
			if(opr=='pre' && currentCorr!=1)
				currentCorr=currentCorr-1;
			if(opr=='nex' && currentCorr<(corrCount-1))
				currentCorr=currentCorr+1;
			getPage(type,currentCorr);
		}
		setPageList();
	}
	function getPage(type,page)
	{
		var div=document.getElementById(type);
		if(page=='self')
		{
			page=parseInt(document.getElementById(type).value);
			if(type=='notePage')
				div=document.getElementById('noteDiv');
			else{
				var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
				if(subDepartemnt != 'Bank Statement')
				div=document.getElementById('corrDiv');
				}
				
		}
		if(type=='noteDiv' || type=='notePage')
		{
			currentNote=page;
			var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
			if(subDepartemnt == 'Bank Statement')
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'@'+page+'.pdf#toolbar=0"></object>';
			else
				div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'L@'+page+'L.pdf#toolbar=0"></object>';
				
		}
		else
		{
			var subDepartemnt = '<c:out value="${financeForm.subdepartment}"/>';
			if(subDepartemnt != 'Bank Statement'){
				currentCorr=page;
				div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'R@'+page+'R.pdf#toolbar=0"></object>';
			}
			
		}
	}
	function setPageList()
	{
		var notePage=document.getElementById('notePage');
		var corrPage=document.getElementById('corrPage');
		for(var i=0;i<noteCount;i++)
		{
			if(i==0)
				notePage.innerHTML='<option value="Select">Select</option>';
			else
				notePage.innerHTML=notePage.innerHTML+'<option value="'+i+'">'+i+'</option>'
		}
		for(var i=0;i<corrCount;i++)
		{
			if(i==0)
				corrPage.innerHTML='<option value="Select">Select</option>';
			else
				corrPage.innerHTML=corrPage.innerHTML+'<option value="'+i+'">'+i+'</option>'
		}
	}
	function downloadFile(pos,sno,right,statement)
	{
		if(right==1)
			window.location="downloadFile?id="+pos+"&sno="+sno+"&department=Finance"+"&statement="+statement+"&subdepartment="+'<c:out value="${financeForm.subdepartment}"/>';
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=Finance";
	}
	function report(sno,right,flage,subdepartment)
	{
		if(right==1)
			document.getElementById('reportForm').submit();
	}
		function printConf(type)
	{
		printLr=type;
		document.getElementById('printConfModal').style.display='block';
	}
	function printFile(printType)
	{
		var subdepartment = '<c:out value="${financeForm.subdepartment}"/>';
		if(printType=='single' && subdepartment != 'undefined' && subdepartment.includes("Bank Statement")){
			singlePrint('note');
		}
		else{
			if(printType=='single')
			{
				if(printLr=='L')
					singlePrint('note');
				else
					singlePrint('corr');
			}
			else
				printOut(fileId,currentSno,printLr,currentAlloteeName);	
	 	}
	}
	function printOut(fileId,currentSno,printLr,currentAlloteeName)
	{
			var url="viewFile?id="+fileId+"&sno="+currentSno+"&department=Finance&prFlage=print&name="+currentAlloteeName+"&lr="+printLr+"&subdepartment="+'<c:out value="${financeForm.subdepartment}"/>';
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
	function setPrint()
	{
		if(request.readyState==4)
		{
			var id=request.responseText;
			if(id.includes("_"))
			document.getElementById('printDiv').innerHTML='<iframe id="pdf" src="staticResources/pdfs/'+id.split("_")[0]+'.pdf"></iframe>';
			else
			document.getElementById('printDiv').innerHTML='<iframe id="pdf" src="staticResources/pdfs/'+id+'.pdf"></iframe>';
			var ifr=document.getElementById('pdf');
			document.getElementById('authModal').style.display='none';
			ifr.contentWindow.print();
		}
	}
	function singlePrint(type)
	{
		var subdepartment = '<c:out value="${financeForm.subdepartment}"/>';
		var contentDiv=document.getElementById('singlePrintDiv');
		if(subdepartment != 'undefined' && subdepartment.includes("Bank Statement")){
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'@'+currentNote+'@print.pdf"></iframe>';
		}else{
			if(type=='note')
				contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'L@'+currentNote+'L@print.pdf"></iframe>';
			else
				contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'R@'+currentCorr+'R@print.pdf"></iframe>';
		}
		document.getElementById('singlePdf').contentWindow.print();
	}
	function firLas(type,page)
	{
		if(type=='noteDiv')
		{
			if(page=='fir')
				getPage(type,1);
			else
				getPage(type,noteCount-1);
		}
		else
		{
			if(page=='fir')
				getPage(type,1);
			else
				getPage(type,corrCount-1);
		}
	}
	function setContent(content)
	{
		document.getElementById('authContentPara').innerHTML=content;
		document.getElementById('authModal').style.display='block';
	}
</script>
<c:choose>
	<c:when test="${financeForm.subdepartment eq 'Bank Statement'}">
		<div id="id01" class="base-modal" style="display: none; z-index:99999; margin: 0px; padding: 0px;">
		    <a href="" style="font-size: 26px;"><span onclick="deleteFile();" style="float: right; color: red;"><b>&times;</b></span></a>
			<table style="width: 100%;">
				<tr>
					<td>
						<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
						<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','fir');">First</button>
						<button class="btn btn-primary" onclick="nexPre('noteDiv','pre');">Previous</button>
						<select style="width: 70px; height: 25px;" id="notePage" onchange="getPage('notePage','self');"></select>
						<button class="btn btn-primary" onclick="nexPre('noteDiv','nex');">Next</button>
						<button class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','las');">Last</button>
						<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('L');">Print It</button></c:if>
					</td>
					<%-- <td>
						<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
						<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','fir');">First</button>
						<button class="btn btn-primary" onclick="nexPre('corrDiv','pre')">Previous</button>
						<select style="width: 70px; height: 25px;" id="corrPage" onchange="getPage('corrPage','self');"></select>
						<button class="btn btn-primary" onclick="nexPre('corrDiv','nex')">Next</button>
						<button class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','las');">Last</button>
						<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('R');">Print It</button></c:if>
					</td> --%>
				</tr>
			</table>
		   
		    <div id="noteDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:100%; height:90%;"></div>
		</div>
	</c:when>
	<c:otherwise>
		<div id="id01" class="base-modal" style="display: none; z-index:99999; margin: 0px; padding: 0px;">
	    <a href="" style="font-size: 26px;"><span onclick="deleteFile();" style="float: right; color: red;"><b>&times;</b></span></a>
		<table style="width: 100%;">
			<tr>
				<td>
					<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
					<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','fir');">First</button>
					<button class="btn btn-primary" onclick="nexPre('noteDiv','pre');">Previous</button>
					<select style="width: 70px; height: 25px;" id="notePage" onchange="getPage('notePage','self');"></select>
					<button class="btn btn-primary" onclick="nexPre('noteDiv','nex');">Next</button>
					<button class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','las');">Last</button>
					<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('L');">Print It</button></c:if>
				</td>
				<td>
					<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
					<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','fir');">First</button>
					<button class="btn btn-primary" onclick="nexPre('corrDiv','pre')">Previous</button>
					<select style="width: 70px; height: 25px;" id="corrPage" onchange="getPage('corrPage','self');"></select>
					<button class="btn btn-primary" onclick="nexPre('corrDiv','nex')">Next</button>
					<button class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','las');">Last</button>
					<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('R');">Print It</button></c:if>
				</td>
			</tr>
		</table>
	    <div id="noteDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height:90%;"></div>
	    <div id="corrDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height: 90%;"></div>
	</div>
	</c:otherwise>
</c:choose>



<div id="printModel" class="base-modal" style="display: none; z-index:99999;">
    <a href="#" style="text-decoration: none; color: red; font-family: cambria; font-size: 20px;"><span onclick="deleteFile();" class="base-closebtn base-hover-red base-display-topright">X</span></a>
    <div id="printDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height:99%;"></div>
</div>

<div id="authModal" class="modal" style="display: none; z-index: 100000;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;">
    		<span class="close" onclick="document.getElementById('authModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
    		<p style="text-align: center; color: #FFFFFF;" class="h3" id="authContentPara"></p>
    	</div>
  	</div>
</div>

<div id="printConfModal" class="modal" style="display: none; z-index: 1000000;">
  	<div class="modal-content" style="width: 20%;">
    	<div class="modal-header" style="background-color: #387403;">
    		<span class="close" onclick="document.getElementById('printConfModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
    		<table style="width: 100%;">
    			<tr><td colspan="2" align="center"><p style="text-align: center; color: #FFFFFF; margin-top: 0px; padding-top: 0px;" class="h3" id="printPara">Noting Print</p></td></tr>
    			<tr>
    				<td align="center"><button class="base-button base-round-large" style="background-color: #ffffff;" onclick="printFile('single')">Current Page</button></td>
    				<td align="center"><button class="base-button base-round-large" style="background-color: #ffffff;" onclick="printFile('cust')">Customize</button></td>
    			</tr>
    		</table>
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

<div id="singlePrintModal" class="modal" style="display: none; z-index: 100000;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;" id="singlePrintDiv"></div>
  	</div>
</div>
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
                	<label style="font-family: cambria;" for="Register Name"><h4><b>Account No:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" path="accountNo" id="accountNo" list="registerNameHelp" onkeyup="getHelp('registerName');"/>
                	<datalist id="registerNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Sector"><h4><b>Code No:</b></h4></label><br>
                	<financeForm:input style="width: 230px; height: 35px;" path="codeNo" id="codeNo" list="sectorHelp" onkeyup="getHelp('sector');"/>
                	<datalist id="sectorHelp"></datalist>
                </td>
             <td>
                	<label style="font-family: cambria;" for="subdepartment"><h4><b>Sub-Department:</b></h4></label><br>
                	<financeForm:select style="height: 35px; width: 200px;"  path="subdepartment" id="subdepartment">
                		<financeForm:option  value="Select" label="Select"/>
                		 <c:forTokens items = "${financeSubdepartment}" delims = "," var = "subdepartment">
					         <financeForm:option value="${subdepartment}" label="${subdepartment}"/>
					      </c:forTokens>
                   	</financeForm:select>
                </td>
            </tr>
            <tr><td><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td></tr>
        </table>
    </financeForm:form>
</div><br>
<c:if test="${not empty records}">
 <c:set var="subd" value="${param.subdepartment}"/>
<c:choose>
	<c:when test="${subd =='Bank Statement'  || subd=='Loan' || subd==''}" >
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
					<th>Subject</th>
					<th>Sub-Department</th>
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
						<td>${record.subject}</td>
						<td>${record.subdepartment}</td>
						<td>
							<a href="#" onclick="viewFile('${record.accountNo}','${record.sno}','${view}','${record.statement}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.accountNo}','${record.sno}','${download}','${record.statement}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.accountNo}','${record.sno}','${print}','${record.statement}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
	</c:when>
	<c:otherwise>
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="Finance">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
					<th></th>
					<th>File Number</th>
					<th>Code Number</th>
					<th>Subject</th>
					<th>Designation</th>
					<th>File Type</th>
					<th>Sub-Department</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.fileNo}</td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.codeNo}</a></td>
						<td>${record.subject}</td>
						<td>${record.designation}</td>
						<td>${record.fileType}</td>
						<td>${record.subdepartment}</td>
						<td>
							<a href="#" onclick="viewFile('${record.codeNo}','${record.sno}','${view}','${record.subdepartment}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.codeNo}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.codeNo}','${record.sno}','${print}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1',${record.subdepartment})" value="Generate Report">
	</form>
	</c:otherwise>
</c:choose>
	
	</c:if>
<br><br><br><br><br><br><br>
