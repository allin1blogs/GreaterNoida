<%-- 
    Document   : Retrieve Records
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="planningForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<script type="text/javascript">
	var request,fileId,noteCount,corrCount,currentNote,currentCorr;
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
		var subDepartment=document.getElementById('subDepartment').value;
		var applicantName=document.getElementById('applicantName').value;
		var allotmentNo=document.getElementById('allotmentNo').value;
		var bpNo=document.getElementById('bpNo').value;
		var category=document.getElementById('category').value;
		var sector=document.getElementById('sector').value;
		var plotNo=document.getElementById('plotNo').value;
		var blockNo=document.getElementById('blockNo').value;
		var plotSize=document.getElementById('plotSize').value;
		if(subDepartment=='Select' && applicantName=='' && allotmentNo=='' && bpNo=='' && category=='' && sector=='' && plotNo=='' && blockNo=='' && plotSize=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('planningForm').submit();
	}
	function deleteFile()
	{
		window.location="delView?department=Planning";
	}
	function viewFile(bpNo,sno,right,applicantName)
	{
		if(right==1)
		{
			var url="viewFile?id="+bpNo+"&sno="+sno+"&department=Planning&prFlage=null&name="+applicantName;
			if(window.XMLHttpRequest)  
				request=new XMLHttpRequest();  
			else if(window.ActiveXObject)  
				request=new ActiveXObject("Microsoft.XMLHTTP");
			document.getElementById('id01').style.display='block';
			document.getElementById('noteDiv').innerHTML='<p style="margin-top: 5%; font-size: 22px; font-family: cambria; text-align: center;">Opening Notesheet...</p>';
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
			fileId=data[0];noteCount=data[1];corrCount=data[2];
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
			var pages='<p style="text-align: center; font-family: cambria; font-size: 14px; color: #ffffff;">Go To Page:</p><select style="width: 50px; height: 15px;" name="notePage"><option value="Select">Select</option></select>';
			currentNote=1;currentCorr=1;
			getPage('noteDiv',1);getPage('corrDiv',1);
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
	}
	function getPage(type,page)
	{
		var div=document.getElementById(type);
		if(page=='self')
		{
			page=parseInt(document.getElementById(type).value);
			if(type=='notePage')
				div=document.getElementById('noteDiv');
			else
				div=document.getElementById('corrDiv');
		}
		if(type=='noteDiv' || type=='notePage')
		{
			currentNote=page;
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'L@'+page+'L.pdf#toolbar=0"></object>';
		}
		else
		{
			currentCorr=page;
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+fileId+'R@'+page+'R.pdf#toolbar=0"></object>';
		}
	}
	function downloadFile(bpNo,sno,right)
	{
		if(right==1)
			window.location="downloadFile?id="+bpNo+"&sno="+sno+"&department=Planning";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=Planning";
	}
	function report(sno,right,flage)
	{
		if(right==1)
			document.getElementById('reportForm').submit();
	}
	function printOut(bpNo,sno,right)
	{
		if(right==1)
		{
			var url="viewFile?id="+bpNo+"&sno="+sno+"&department=Planning&prFlage=print&name="+applicantName;
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
	function setContent(content)
	{
		document.getElementById('authContentPara').innerHTML=content;
		document.getElementById('authModal').style.display='block';
	}
</script>

<div id="id01" class="base-modal" style="display: none; z-index:99999; margin: 0px; padding: 0px;">
    <a href="#" style="text-decoration: none; color: red; font-family: cambria; font-size: 20px;"><span onclick="deleteFile();" class="base-closebtn base-hover-red base-display-topright">X</span></a>
	<table style="width: 100%;">
		<tr>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 32%;" class="btn btn-primary" onclick="nexPre('noteDiv','pre');">Previous</button>
				<select style="width: 70px; height: 25px;" id="notePage" onchange="getPage('notePage','self');"></select>
				<button class="btn btn-primary" onclick="nexPre('noteDiv','nex');">Next</button>
			</td>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 32%;" class="btn btn-primary" onclick="nexPre('corrDiv','pre')">Previous</button>
				<select style="width: 70px; height: 25px;" id="corrPage" onchange="getPage('corrPage','self');"></select>
				<button class="btn btn-primary" onclick="nexPre('corrDiv','nex')">Next</button>
			</td>
		</tr>
	</table>
    <div id="noteDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height:90%;"></div>
    <div id="corrDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height: 90%;"></div>
</div>

<div id="printModel" class="base-modal" style="display: none; z-index:99999;">
    <a href="#" style="text-decoration: none; color: red; font-family: cambria; font-size: 20px;"><span onclick="deleteFile();" class="base-closebtn base-hover-red base-display-topright">X</span></a>
    <div id="printDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:50%; height:99%;"></div>
</div>

<div id="authModal" class="modal" style="display: none;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;">
    		<span class="close" onclick="document.getElementById('authModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
    		<p style="text-align: center; color: #FFFFFF;" class="h3" id="authContentPara"></p>
    	</div>
  	</div>
</div>

<c:if test="${not empty msg}">
	<div id="modal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #387403;">
      			<span class="close" onclick="document.getElementById('modal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      			<p style="text-align: center; color: #FFFFFF;" class="h3" id="contentPara">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<c:if test="${empty pol}"><p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Planning</p></c:if>
<c:if test="${not empty pol}"><p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Planning(Policies)</p></c:if>
<div style="margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
	<c:if test="${empty pol}">
    <planningForm:form action="retrievePlan" id="planningForm" method="get" modelAttribute="planningForm">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Scheme"><h4><b>Sub-Department:</b></h4></label><br>
                	<planningForm:select style="height: 35px; width: 200px;" path="subDepartment" id="subDepartment">
                		<planningForm:option value="Select" label="Select"/>
    					<planningForm:option value="Industry" label="Industry"/>
    					<planningForm:option value="Residential" label="Residential"/>
    					<planningForm:option value="Bn" label="Bn"/>
                   	</planningForm:select>
                </td>
            	<td>
            		<label style="font-family: cambria;" for="BP No."><h4><b>BP No.:</b></h4></label><br>
            		<planningForm:input style="width: 230px; height: 35px;" id="bpNo" path="bpNo" list="bpNoHelp" onkeyup="getHelp('bpNo');"/>
            		<datalist id="bpNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Applicant Name"><h4><b>Applicant Name:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" id="applicantName" path="applicantName" list="applicantNameHelp" onkeyup="getHelp('applicantName');"/>
                	<datalist id="applicantNameHelp"></datalist>
                </td>
                <td>
            		<label style="font-family: cambria;" for="Allotment No."><h4><b>Allotment No.:</b></h4></label><br>
            		<planningForm:input style="width: 230px; height: 35px;" id="allotmentNo" path="allotmentNo" list="allotmentNoHelp" onkeyup="getHelp('allotmentNo');"/>
            		<datalist id="allotmentNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Category"><h4><b>Category:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="category" id="category" list="categoryHelp" onkeyup="getHelp('category');"/>
                	<datalist id="schemeHelp"></datalist>
                </td>
            </tr>
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="sector" id="sector" list="sectorHelp" onkeyup="getHelp('sector');"/>
					<datalist id="sectorHelp"></datalist>                
                </td>
				<td>
                	<label style="font-family: cambria;" for="Plot No."><h4><b>Plot No.:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="plotNo" id="plotNo" list="plotNoHelp" onkeyup="getHelp('plotNo');"/>
                	<datalist id="plotNoHelp"></datalist>
                </td>
            	<td>
                	<label style="font-family: cambria;" for="Block No."><h4><b>Block No.:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="blockNo" id="blockNo" list="blockNoHelp" onkeyup="getHelp('blockNo');"/>
                	<datalist id="blockNoHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Plot Size"><h4><b>Plot Size:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="plotSize" id="plotSize" list="plotSizeHelp" onkeyup="getHelp('plotSize');"/>
                	<datalist id="plotSizeHelp"></datalist>
                </td>
	     	</tr>
	     	<tr>
                <td><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td>
            </tr>
        </table>
    </planningForm:form>
    </c:if>
    <c:if test="${not empty pol}">
    <planningForm:form action="retrievePlan" id="planningForm" method="get" modelAttribute="planningForm">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
            		<label style="font-family: cambria;" for="BP No."><h4><b>OPA/FTS No.:</b></h4></label><br>
            		<planningForm:input style="width: 230px; height: 35px;" id="fts" path="fts" list="ftsHelp" onkeyup="getHelp('fts');"/>
            		<datalist id="ftsHelp"></datalist>
            	</td>
            	<td>
            		<label style="font-family: cambria;" for="BP No."><h4><b>Policy No.:</b></h4></label><br>
            		<planningForm:input style="width: 230px; height: 35px;" id="policyNo" path="policyNo" list="policyNoHelp" onkeyup="getHelp('policyNo');"/>
            		<datalist id="policyNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Applicant Name"><h4><b>Subject Name:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" id="subject" path="subject" list="subjectHelp" onkeyup="getHelp('subjectName');"/>
                	<datalist id="subjectHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="sector" id="sector" list="sectorHelp" onkeyup="getHelp('sector');"/>
					<datalist id="sectorHelp"></datalist>                
                </td>
            	<td>
                	<label style="font-family: cambria;" for="Block No."><h4><b>Block No.:</b></h4></label><br>
                	<planningForm:input style="width: 230px; height: 35px;" path="blockNo" id="blockNo" list="blockNoHelp" onkeyup="getHelp('blockNo');"/>
                	<datalist id="blockNoHelp"></datalist>
                </td>
                <tr>
                <td><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td>
            </tr>
            </tr>
        </table>
    </planningForm:form>
    </c:if>
</div><br>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="Planning">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
					<th></th>
					<th>BP No.</th>
					<th>Applicant Name</th>
					<th>Sector</th>
					<th>Allotment No.</th>
					<th>Category</th>
					<th>Plot No.</th>
					<th>Block No.</th>
					<th>Plot Size</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.bpNo}</a></td>
						<td>${record.applicantName}</td>
						<td>${record.sector}</td>
						<td>${record.allotmentNo}</td>
						<td>${record.category}</td>
						<td>${record.plotNo}</td>
						<td>${record.blockNo}</td>
						<td>${record.plotSize}</td>
						<td>
							<c:if test="${record.subDepartment=='Industry'}"><a href="#" onclick="viewFile('${record.bpNo}','${record.sno}','${view}');" style="text-decoration: none;">View</a>&nbsp;&nbsp;</c:if>
							<c:if test="${record.subDepartment=='Residential'}"><a href="#" onclick="viewFile('${record.allotmentNo}','${record.sno}','${view}');" style="text-decoration: none;">View</a>&nbsp;&nbsp;</c:if>
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.bpNo}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('form','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br><br>