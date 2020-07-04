<%-- 
    Document   : Preeti Rani
    Created on : 30/6/2020, 05:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="generalForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>
<script type="text/javascript">
	var request,fileId,noteCount,corrCount,currentNote,currentCorr,currentSno,currentAlloteeName,printLr;;
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
		var allotmentNo=document.getElementById('allotmentNo').value;
		var alloteeName=document.getElementById('alloteeName').value;
		var scheme=document.getElementById('scheme').value;
		var sector=document.getElementById('sector').value;
		var plotNo=document.getElementById('plotNo').value;
		var plotSize=document.getElementById('plotSize').value;
		var fts=document.getElementById('fts').value;
		if(allotmentNo=='' && alloteeName=='' && scheme=='Select' && sector=='' && plotNo=='' && plotSize=='' && fts=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('generalForm').submit();
	}
	function deleteFile()
	{
		window.location="delView?department=General";
	}
	function viewFile(allotmentNo,sno,view,alloteeName)
	{
		currentSno=sno;currentAlloteeName=alloteeName;
		if(view==1)
		{
			var url="viewFile?id="+allotmentNo+"&sno="+sno+"&department=General&prFlage=null&name="+alloteeName;
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
			setPageList();
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
	function downloadFile(allotmentNo,sno,right)
	{
		if(right==1)
			window.location="downloadFile?id="+allotmentNo+"&sno="+sno+"&department=General";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=General";
	}
	function report(sno,right,flage)
	{
		document.getElementById('reportForm').submit();
	}
	function printConf(type)
	{
		printLr=type;
		document.getElementById('printConfModal').style.display='block';
	}
	function printFile(printType)
	{
		if(printType=='single')
		{
			if(printLr=='L')
				singlePrint('note');
			else
				singlePrint('corr');
		}
		else
			printOut();
	}
	function printOut()
	{
		document.getElementById('printConfModal').style.display='none';
		var url="viewFile?id="+fileId+"&sno="+currentSno+"&department=General&prFlage=print&name="+currentAlloteeName+"&lr="+printLr;
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
			document.getElementById('printDiv').innerHTML='<iframe id="pdf" src="staticResources/pdfs/'+id+printLr+'.pdf"></iframe>';
			var ifr=document.getElementById('pdf');
			document.getElementById('authModal').style.display='none';
			ifr.contentWindow.print();
		}
	}
	function singlePrint(type)
	{
		var contentDiv=document.getElementById('singlePrintDiv');
		if(type=='note')
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'L@'+currentNote+'L@print.pdf"></iframe>';
		else
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'R@'+currentCorr+'R@print.pdf"></iframe>';
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

<div id="id01" class="base-modal" style="display: none; z-index:99999; margin: 0px; padding: 0px;">
    <a href="" style="font-size: 26px;"><span onclick="deleteFile();" style="float: right; color: red;"><b>&times;</b></span></a>
	<table style="width: 100%;">
		<tr>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','fir');">First</button>
				<button class="btn btn-primary" id="preButNote" onclick="nexPre('noteDiv','pre');">Previous</button>
				<select style="width: 70px; height: 25px;" id="notePage" onchange="getPage('notePage','self');"></select>
				<button class="btn btn-primary" id="nexButNote" onclick="nexPre('noteDiv','nex');">Next</button>
				<button class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','las');">Last</button>
				<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('L');">Print It</button></c:if>
			</td>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','fir');">First</button>
				<button class="btn btn-primary" id="preButCorr" onclick="nexPre('corrDiv','pre')">Previous</button>
				<select style="width: 70px; height: 25px;" id="corrPage" onchange="getPage('corrPage','self');"></select>
				<button class="btn btn-primary" onclick="nexPre('corrDiv','nex')" id="nexButCorr">Next</button>
				<button class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','las');">Last</button>
				<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 20%;" onclick="printConf('R');">Print It</button></c:if>
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
      			<p style="text-align: center; color: #FFFFFF;" class="h3" id="contentPara">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<div id="singlePrintModal" class="modal" style="display: none; z-index: 100000;">
  	<div class="modal-content">
    	<div class="modal-header" style="background-color: #387403;" id="singlePrintDiv"></div>
  	</div>
</div>

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">${department}</p>
<div style="align: center; margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
    <generalForm:form action="retrieveGen" id="generalForm" method="get" modelAttribute="generalForm">
        <table style="align: center; border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
            	
            		<label style="font-family: cambria;" for="AllotmentNo"><h4><b>Allotment No.:</b></h4></label><br>
            		<generalForm:input style="width: 220px; height: 35px;" id="allotmentNo" path="allotmentNo" list="allotmentNoHelp" onkeyup="getHelp('allotmentNo');"/>
            		<datalist id="allotmentNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Allotee Name"><h4><b>Allotee Name:</b></h4></label><br>
                	<generalForm:input style="width: 220px; height: 35px;" id="alloteeName" path="alloteeName" list="alloteeNameHelp" onkeyup="getHelp('alloteeName');"/>
                	<datalist id="alloteeNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Scheme"><h4><b>Scheme Code:</b></h4></label><br>
                	<generalForm:select style="height: 35px; width: 200px;" path="scheme" id="scheme">
                		<generalForm:option value="Select" label="Select"/>
                  		<generalForm:options items="${sectors}"/>
                   	</generalForm:select>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Sector"><h4><b>Sector:</b></h4></label><br>
                	<generalForm:input style="width: 220px; height: 35px;" path="sector" id="sector" list="sectorHelp" onkeyup="getHelp('sector');"/>
                	<datalist id="sectorHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Plot No."><h4><b>Plot No.:</b></h4></label><br>
                	<generalForm:input style="width: 220px; height: 35px;" path="plotNo" id="plotNo" list="plotNoHelp" onkeyup="getHelp('plotNo');"/>
                	<datalist id="plotNoHelp"></datalist>
                </td>
            </tr>
            <tr>
				<td>
                	<label style="font-family: cambria;" for="Plot Size"><h4><b>Plot Size:</b></h4></label><br>
                	<generalForm:input style="width: 220px; height: 35px;" path="plotSize" id="plotSize" list="plotSizeHelp" onkeyup="getHelp('plotSize');"/>
                	<datalist id="plotSizeHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="FTS No."><h4><b>FTS No.:</b></h4></label><br>
                	<generalForm:input style="width: 230px; height: 35px;" path="fts" id="fts" list="ftsHelp" onkeyup="getHelp('fts');"/>
                	<datalist id="ftsHelp"></datalist>
                </td>
            </tr>
	    	<tr><td><input class="btn btn-primary" style="background-color: #387403; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td></tr>
            <tr><td><generalForm:hidden path="department"/></td></tr>
        </table>
    </generalForm:form>
</div>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="General">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%; ">
			<thead>
				<tr>
					<th></th>
					<th>Allotment No.</th>
					<th>Allotee Name</th>
					<th>Sector</th>
					<th>Scheme Code</th>
					<th>Plot No.</th>
					<th>Block No.</th>
					<th>Plot Size</th>
					<th>FTS No.</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.allotmentNo}</a></td>
						<td>${record.alloteeName}</td>
						<td>${record.sector}</td>
						<td>${record.scheme}</td>
						<td>${record.plotNo}</td>
						<td>${record.block}</td>
						<td>${record.plotSize}</td>
						<td>${record.fts}</td>
						<td>
							<a href="#" onclick="viewFile('${record.allotmentNo}','${record.sno}','${view}','${record.alloteeName}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.allotmentNo}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br>