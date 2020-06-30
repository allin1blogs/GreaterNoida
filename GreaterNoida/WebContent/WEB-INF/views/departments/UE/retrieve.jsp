

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="lawForm"%>
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
		var Name_Of_Work=document.getElementById('name_Of_Work').value;
		var Contractor_Name=document.getElementById('contractor_Name').value;
		var Department=document.getElementById('department').value;
		var file_Number=document.getElementById('file_Number').value;
		var no_Of_Cros=document.getElementById('no_Of_Cros').value;
		
		if(Name_Of_Work=='' && Contractor_Name=='' && Department=='' && file_Number=='' && no_Of_Cros=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('UEForm').submit();
	}
	function deleteFile()
	{
		window.location="delView?department=UE";
	}
	function viewFile(pos,sno,right,bankName)
	{
		if(right==1)
		{
			var url="viewFile?id="+pos+"&sno="+sno+"&department=UE&prFlage=null&name="+bankName;
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
			window.location="downloadFile?id="+pos+"&sno="+sno+"&department=UE";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=UE";
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
			var url="viewFile?id="+pos+"&sno="+sno+"&department=UE&prFlage=print&name="+bankName;
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
		contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'@print.pdf"></iframe>';
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

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">UE</p>
<div style="margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
    <lawForm:form action="retrieveUE" id="UEForm" method="get" modelAttribute="UEForm">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Court Name"><h4><b>Name Of Work:</b></h4></label><br>
                	<lawForm:input style="width: 230px; height: 35px;" path="name_Of_Work" id="name_Of_Work" list="courtNameHelp" onkeyup="getHelp('courtName');"/>
                	<datalist id="courtNameHelp"></datalist>
                </td>
            	<td>
            		<label style="font-family: cambria;" for="PetitionNo"><h4><b>Contractor Name:</b></h4></label><br>
            		<lawForm:input style="width: 230px; height: 35px;" id="contractor_Name" path="contractor_Name" list="petitionNoHelp" onkeyup="getHelp('petitionNo');"/>
            		<datalist id="petitionNoHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Party Name"><h4><b>Department:</b></h4></label><br>
                	<lawForm:input style="width: 230px; height: 35px;" id="department" path="department" list="partyNameHelp" onkeyup="getHelp('partyName');"/>
                	<datalist id="partyNameHelp"></datalist>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Petitioner's Advocate"><h4><b>File_Number</b></h4></label><br>
                	<lawForm:input style="width: 230px; height: 35px;" path="file_Number" id="file_Number" list="petitionerAdvocateHelp" onkeyup="getHelp('petitionerAdvocate');"/>
                	<datalist id="petitionerAdvocateHelp"></datalist>
                </td>
                
            </tr>
            <tr>
            	<td>
            		<label style="font-family: cambria;" for="Year"><h4><b>No_Of_Cros:</b></h4></label><br>
            		<lawForm:input style="width: 230px; height: 35px;" id="no_Of_Cros" path="no_Of_Cros" list="yearHelp" onkeyup="getHelp('year');"/>
            		<datalist id="yearHelp"></datalist>
            	</td>
            	
            	<td><br><br><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td>
            </tr>
        </table>
    </lawForm:form>
</div><br>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="UE">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
				    <th>CheckBox</th>				     
					<th>SNo.</th>		
							
					<th>Name_Of_Work</th>
					<th>File_Number</th>
					<th>Contractor_Name.</th>
					
					<th>Department</th>					
					<th>No_Of_Cros</th>
					<th>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.sno}</td>
						
						<td>${record.name_Of_Work}</td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.file_Number}</a></td>
						<%-- <td>${record.file_Number}</td> --%>
						<td>${record.contractor_Name}</td>
						<td>${record.department}</td>
						
						<td>${record.no_Of_Cros}</td>
						
						<td>
							<a href="#" onclick="viewFile('${record.file_Number}','${record.sno}','${view}','${record.name_Of_Work}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.file_Number}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.file_Number}','${record.sno}','${print}','${record.contractor_Name}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br><br><br>