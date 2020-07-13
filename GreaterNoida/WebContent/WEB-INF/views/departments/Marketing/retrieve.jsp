<%-- 
    Document   : Create Records
    Created on : 22 June, 2020, 01:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="MarketingForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<div id="ftsNo" style="display:none">${param.fts_No_Opa_No}</div>
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
		var lot_No=document.getElementById('lot_No').value;
		var clerk_Name=document.getElementById('clerk_Name').value;
		var file_No=document.getElementById('file_No').value;
		var Department=document.getElementById('department').value;
		var fts_No_Opa_No=document.getElementById('fts_No_Opa_No').value;
		var applicant_Name=document.getElementById('applicant_Name').value;
		//var No_Of_NoteSheet=document.getElementById('no_Of_NoteSheet').value;
		if(lot_No=='' && clerk_Name=='' &&  file_No=='' && Department=='' && fts_No_Opa_No=='' && applicant_Name=='' 
		//	 && No_Of_NoteSheet==''
				 )
			setContent('Sorry Empty Parameters!');
		else
			document.getElementById('MarketingForm').submit();
	}
	function deleteFile()
	{
		window.location="delView?department=Marketing";
	}
	function viewFile(pos,sno,right,name)
	{
		if(right==1)
		{
			var url="viewFile?id="+pos+"&sno="+sno+"&department=Marketing&prFlage=null&name="+name;
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
			var pages='<p style="text-align: center; font-family: cambria; font-size: 14px; color: #ffffff;">Go To Page:</p>
				<select style="width: 50px; height: 15px;" name="notePage"><option value="Select">Select</option></select>';
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
		var ftsNumber =document.getElementById('ftsNo').innerHTML;
		if(type=='noteDiv' || type=='notePage')
		{
			currentNote=page;
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+ftsNumber+'L@'+page+'L.pdf#toolbar=0"></object>';
		}
		else
		{
			currentCorr=page;
			div.innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+ftsNumber+'R@'+page+'R.pdf#toolbar=0"></object>';
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
	function downloadFile(pos,sno,right)
	{
		if(right==1)
			window.location="downloadFile?id="+pos+"&sno="+sno+"&department=Marketing";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=Marketing";
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
			var url="viewFile?id="+pos+"&sno="+sno+"&department=Marketing&prFlage=print&name="+bankName;
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
		if(type=='note')
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fts_No_Opa_No+'L@'+currentNote+'L@print.pdf"></iframe>';
		else
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fts_No_Opa_No+'R@'+currentCorr+'R@print.pdf"></iframe>';
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
    <a href="#" style="text-decoration: none; color: red; font-family: cambria; font-size: 20px;"><span onclick="deleteFile();" class="base-closebtn base-hover-red base-display-topright">Close
    </span></a>
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Marketing</p>
<div style="margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
    <MarketingForm:form action="retrieveMarketing" id="MarketingForm" method="get" modelAttribute="MarketingForm">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Court Name"><h4><b>Lot No:</b></h4></label><br>
                	<MarketingForm:input style="width: 230px; height: 35px;" path="lot_No" id="lot_No" list="courtNameHelp" onkeyup="getHelp('courtName');"/>
                	<datalist id="courtNameHelp"></datalist>
                </td>
            	<td>
            		<label style="font-family: cambria;" for="PetitionNo"><h4><b>Clerk Name:</b></h4></label><br>
            		<MarketingForm:input style="width: 230px; height: 35px;" id="clerk_Name" path="clerk_Name" list="petitionNoHelp" onkeyup="getHelp('petitionNo');"/>
            		<datalist id="petitionNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Party Name"><h4><b>File No:</b></h4></label><br>
                	<MarketingForm:input style="width: 230px; height: 35px;" id="file_No" path="file_No" list="partyNameHelp" onkeyup="getHelp('partyName');"/>
                	<datalist id="partyNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Petitioner's Advocate"><h4><b>Department:</b></h4></label><br>
                	<MarketingForm:input style="width: 230px; height: 35px;" path="department" id="department" list="petitionerAdvocateHelp" onkeyup="getHelp('petitionerAdvocate');"/>
                	<datalist id="petitionerAdvocateHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Respondent's Advocate"><h4><b>FTS_NO/OPA_NO:</b></h4></label><br>
                	<MarketingForm:input style="width: 230px; height: 35px;" path="fts_No_Opa_No" id="fts_No_Opa_No" list="respondentAdvocateHelp" onkeyup="getHelp('respondentAdvocate');"/>
                	<datalist id="respondentAdvocateHelp"></datalist>
                </td>
            </tr>
            <tr>
            	<td>
            		<label style="font-family: cambria;" for="Year"><h4><b>Subject Name:</b></h4></label><br>
            		<MarketingForm:input style="width: 230px; height: 35px;" id="applicant_Name" path="applicant_Name" list="yearHelp" onkeyup="getHelp('year');"/>
            		<datalist id="yearHelp"></datalist>
            	</td>
            	<%-- <td>
            		<label style="font-family: cambria;" for="Department"><h4><b>No_Of_Note_Sheet:</b></h4></label><br>
            		<MarketingForm:input style="width: 230px; height: 35px;" id="no_Of_NoteSheet" path="no_Of_NoteSheet" list="relatedDepartmentHelp" onkeyup="getHelp('relatedDepartment');"/>
            		<datalist id="relatedDepartmentHelp"></datalist>
            	</td> --%>
            	 </tr>
            	<tr>
            	<td><br><br><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td>
            </tr>
        </table>
    </MarketingForm:form>
</div><br>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="Marketing">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
				    <th></th>
				    <th>SNo.</th>
				     
					<th>File_No</th>
					
				     
					<th>lot_No</th>
					<th>Department</th>	
					<th>FTS_NO/OPA_NO</th>
					<th>Applicant_Name</th>					
					<th>Clerk_Name</th>
					<th>Date</th>		
							
					<th>Action</th>		
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.sno}</td> 					
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.file_No}</a></td>
						<%-- <td>${record.file_No}</td>	 --%>
											
						<td>${record.lot_No}</td>
						<td>${record.department}</td>						
						<td>${record.fts_No_Opa_No}</td>
						<td>${record.applicant_Name}</td>						
			            <td>${record.clerk_Name}</td>
			            <td>${record.date}</td>
						<%-- <td>${record.no_Of_NoteSheet}</td> --%>
					   <%-- 	<td>${record.No_Of_Cros}</td> --%>
						<%-- <td>${record.Total_No_Of_Pages}</td> --%>
       <td>            
      
							<a href="#" onclick="viewFile('${record.fts_No_Opa_No}','${record.sno}','${view}','${record.fts_No_Opa_No}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.fts_No_Opa_No}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.fts_No_Opa_No}','${record.sno}','${print}','${record.clerk_Name}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br><br><br>