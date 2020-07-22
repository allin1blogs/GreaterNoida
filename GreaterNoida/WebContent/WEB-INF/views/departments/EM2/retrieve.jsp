

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="emForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<script type="text/javascript">
	var request,fileId,corrCount,noteCount,currentNote,currentCorr;
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
		var department=document.getElementById('department').value;
		var File_number=document.getElementById('file_number').value;
		var Contractor_Name=document.getElementById('contractor_Name').value;
		
		var OPA_FTS=document.getElementById('opaFts').value;
		var CATEGORY=document.getElementById('category').value;
		var Name_Of_Work=document.getElementById('name_Of_Work').value;
		
		if(department=='Select' && File_number=='' && Contractor_Name=='' && OPA_FTS=='' && CATEGORY=='' && Name_Of_Work=='')
			setContent('Empty Parameters!');
		else
			document.getElementById('EM2Form').submit();
	}
	function getValue(val){
		   window.location= "retrieve?department="+val;
		}
	function deleteFile()
	{
		window.location="delView?department=EM2";
	}
	function viewFile(opaFts,sno,right,contractorName)
	{
		if(right==1)
		{
			var url="viewFile?id="+opaFts+"&sno="+sno+"&department=EM2&prFlage=null&name="+contractorName;
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
	function downloadFile(opaFts,sno,right)
	{
		if(right==1)
			window.location="downloadFile?id="+opaFts+"&sno="+sno+"&department=EM2";
	}
	function updateFile(sno,right)
	{
		if(right==1)
			window.location="updateFile?sno="+sno+"&department=EM2";
	}
	function report(sno,right,flage)
	{
		if(right==1)
			document.getElementById('reportForm').submit();
	}
	function printOut(opaFts,sno,right,contractorName)
	{
		if(right==1)
		{
			var url="viewFile?id="+opaFts+"&sno="+sno+"&department=EM2&prFlage=print&name="+contractorName;
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
	function singlePrint(type)
	{
		var contentDiv=document.getElementById('singlePrintDiv');
		if(type=='note')
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'L@print.pdf"></iframe>';
		else
			contentDiv.innerHTML='<iframe id="singlePdf" src="staticResources/pdfs/'+fileId+'R@print.pdf"></iframe>';
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
				<button class="btn btn-primary" onclick="nexPre('noteDiv','pre');">Previous</button>
				<select style="width: 70px; height: 25px;" id="notePage" onchange="getPage('notePage','self');"></select>
				<button class="btn btn-primary" onclick="nexPre('noteDiv','nex');">Next</button>
				<button class="btn btn-primary" id="preButNote" onclick="firLas('noteDiv','las');">Last</button>
				<c:if test="${print=='1'}"><button class="btn btn-primary" style="margin-left: 25%;" onclick="singlePrint('note');">Print It</button></c:if>
			</td>
			<td>
				<p style="margin-left: 42%; font-family: cambria; font-size: 18px; color: #ffffff;">Go To Page:</p>
				<button style="margin-left: 20%;" class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','fir');">First</button>
				<button class="btn btn-primary" onclick="nexPre('corrDiv','pre')">Previous</button>
				<select style="width: 70px; height: 25px;" id="corrPage" onchange="getPage('corrPage','self');"></select>
				<button class="btn btn-primary" onclick="nexPre('corrDiv','nex')">Next</button>
				<button class="btn btn-primary" id="preButNote" onclick="firLas('corrDiv','las');">Last</button>
				<button class="btn btn-primary" style="margin-left: 25%;" onclick="singlePrint('corr');">Print It</button>
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
      			<p style="text-align: center; color: #FFFFFF;" class="h3" id="contentPara">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">EM2</p>
<div style="margin-bottom: 0px; padding-bottom: 0px; margin-left: 1%;">
    <emForm:form action="retrieveEM2" id="EM2Form" method="get" modelAttribute="EM2Form">
        <table style="border-spacing: 20px; border-top:0px; border-collapse: separate;">
            <tr>
            	<td>
                	<label style="font-family: cambria;" for="Scheme"><h4><b>Sub-Department:</b></h4></label><br>
                	<emForm:select style="height: 35px; width: 200px;" path="department" id="department" onchange="getValue(this.value)">
                		<emForm:option value="Select" label="Select"/>
    					<emForm:option value="EM" label="E&M-1"/>
    					<emForm:option value="EM2" label="E&M-2" selected="selected"/>
    					<emForm:option value="EM3" label="E&M-3"/>
                   	</emForm:select>
                </td>
                <td>
                	<label style="font-family: cambria;" for="Year"><h4><b>File_number:</b></h4></label><br>
                	<emForm:input style="width: 230px; height: 35px;" path="file_number" id="file_number" list="yearHelp" onkeyup="getHelp('year');"/>
                	<datalist id="yearHelp"></datalist>
                </td>
                 <td>
                	<label style="font-family: cambria;" for="OPA/FTS"><h4><b>Contractor_Name.:</b></h4></label><br>
                	<emForm:input style="width: 230px; height: 35px;" path="contractor_Name" id="contractor_Name" list="opaFtsHelp" onkeyup="getHelp('opaFts');"/>
                	<datalist id="opaFtsHelp"></datalist>
                </td>
            	<td>
            		<label style="font-family: cambria;" for="Contractor Name"><h4><b>OPA/FTS:</b></h4></label><br>
            		<emForm:input style="width: 230px; height: 35px;" id="opaFts" path="opaFts" list="contractorNameHelp" onkeyup="getHelp('contractorName');"/>
            		<datalist id="contractorNameHelp"></datalist>
            	</td>
                <td>
                	<label style="font-family: cambria;" for="Work Name"><h4><b>Name_Of_Work:</b></h4></label><br>
                	<emForm:input style="width: 230px; height: 35px;" id="name_Of_Work" path="name_Of_Work" list="workHelp" onkeyup="getHelp('workName');"/>
                	<datalist id="workNameHelp"></datalist>
                </td>
               
               
                
            </tr>
            <tr>
				 <td>
                	<label style="font-family: cambria;" for="Category"><h4><b>Category:</b></h4></label><br>
                	<emForm:input style="width: 230px; height: 35px;" path="category" id="category" list="categoryHelp" onkeyup="getHelp('category');"/>
                	<datalist id="categoryHelp"></datalist>
                </td>
               </tr>
               <tr> 
				<td><br><br><input class="btn btn-primary" style="background-color: #1B3AD1; color: #ffffff; font-size: 14px;" type="button" value="Retrieve Files" onclick="retrieveFiles();"></td>
			</tr>
        </table>
    </emForm:form>
</div><br>
<c:if test="${not empty records}">
	<form action="generateReport" id="reportForm" method="post">
		<input type="hidden" name="department" value="EM2">
		<table id="fileTable" class="table display" style="margin-left: 1%; width: 99%;">
			<thead>
				<tr>
				    <th>Check_Box</th>
					<th>Sno</th>
					<th>File_number</th>
					<th>Contractor Name</th>
					<th>Department</th>
					<th>OPA/FTS No.</th>
										
					<th>Name_Of_Work</th>
					<th>Category</th>
					
					<th>Action</th>
					
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${records}" var="record">
					<tr>
					
						<td><input type="checkbox" name="snos" value="${record.sno}"></td>
						<td>${record.sno}</td>
						<td><a href="#" onclick="updateFile('${record.sno}','${update}')" style="text-decoration: none;">${record.file_number}</a></td>
						
						<td>${record.contractor_Name}</td>
						<td>${record.department}</td>
						<td>${record.opaFts}</td>
						<td>${record.name_Of_Work}</td>
						<td>${record.category}</td>				
						
						
						<td>
							<a href="#" onclick="viewFile('${record.opaFts}','${record.sno}','${view}','${record.contractor_Name}')" style="text-decoration: none;">View</a>&nbsp;&nbsp;
							<c:if test="${download=='1'}"><a href="#" onclick="downloadFile('${record.opaFts}','${record.sno}','${download}');" style="text-decoration: none;">Download</a>&nbsp;&nbsp;</c:if>
							<c:if test="${print=='1'}"><a href="#" onclick="printOut('${record.opaFts}','${record.sno}','${print}','${record.contractor_Name}');" style="text-decoration: none;">Print</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input class="btn btn-primary" style="margin-left: 45%; margin-top: 5px; background-color: #1B3AD1; color: #ffffff;" type="button" onclick="report('','${report}','1')" value="Generate Report">
	</form>
</c:if>
<br><br><br><br><br><br><br>