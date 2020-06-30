<%-- 
    Document   : Agenda
    Created on : 03 Dec, 2017, 05:00:32 PM
    Author     : Swapril Tyagi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<link rel="stylesheet" href="<c:url value='staticResources/styleSheets/tableManager.css'/>"/>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/retrieval.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.js'/>"></script>
<script type="text/javascript" src="<c:url value='/staticResources/scripts/table.min.js'/>"></script>

<script type="text/javascript">
	var request;
	$(document).ready(function() 
	{
    	$('#agendaTable').DataTable({
        	"pagingType":"full_numbers"
    	});
	});
	function viewFile(agendaNo,fileNo)
	{
		var url="viewAgendaFile?agendaNo="+agendaNo+"&fileNo="+fileNo;
		if(window.XMLHttpRequest)  
			request=new XMLHttpRequest();  
		else if(window.ActiveXObject)  
			request=new ActiveXObject("Microsoft.XMLHTTP");
		document.getElementById('id01').style.display='block';
		document.getElementById('fileDiv').innerHTML='<p style="margin-top: 5%; font-size: 22px; font-family: cambria; text-align: center;">Opening File...</p>';
		try
		{
			request.onreadystatechange=setFile;
			request.open("GET",url,true);  
			request.send();
		}
		catch(e)
		{}
	}
	function setFile()
	{
		if(request.readyState==4)
		{
			var id=request.responseText;
			document.getElementById('fileDiv').innerHTML='<object oncontextmenu="return false" style="height: 100%; width: 100%;" data="staticResources/pdfs/'+id+'.pdf#toolbar=0"></object>';
		}
	}
	function downloadFile(agendaNo,fileNo)
	{
		window.location="downloadAgendaFile?agendaNo="+agendaNo+"&fileNo="+fileNo;
	}
	function delAgView()
	{
		window.location="delAgView";
	}
	function getDescription()
	{
		var url="getHelp?help=getDescription&key=description&value="+document.getElementById('description').value;
		if(window.XMLHttpRequest)  
			request=new XMLHttpRequest();  
		else if(window.ActiveXObject)  
			request=new ActiveXObject("Microsoft.XMLHTTP");
		try
		{
			request.onreadystatechange=setDescription;
			request.open("GET",url,true);  
			request.send();
		}
		catch(e)
		{}
	}
	function setDescription()
	{
		if(request.readyState==4)
		{
			var dataList=document.getElementById('descriptionHelp');
			var data=request.responseText.split('<@>');
			var options=null;
			for(var i=0;i<data.length;i++)
			{
				if(options==null)
					options='<option value="'+data[i]+'">';
				else
					options=options+'<option value="'+data[i]+'">';
			}
			dataList.innerHTML=options;
		}
	}
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #387403;">
      			<span class="close" onclick="document.getElementById('myModal').style.display='none'" style="color: black;">&times;</span>
      			<p style="text-align: center; color: #FFFFFF;" class="h3">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<div id="id01" class="base-modal" style="display: none; z-index:99999;">
    <a href="#" style="text-decoration: none;"><span onclick="delAgView();" class="base-closebtn base-hover-red base-display-topright">X</span></a>
    <div id="fileDiv" class="base-modal-content base-card-8 base-animate-zoom" style="float: left; width:99%; height: 99%;"></div>
</div>

<p class="h1" style="font-family: cambria math; text-align: center; color: #387403;">Agenda Files</p><br>
<div style="width: 510px; height: 140px;" class="container">
    <form action="${pageContext.request.contextPath}/retrieveAgenda" method="get">
    	<table class="table">
    		<tr>
    			<td><label style="color: black; font-family: cambria;" for="Description"><h4><b>Enter Keywords:</b></h4></label></td>
    			<td>
    				<input style="width: 245px; height: 35px;" type="text" list="descriptionHelp" id="description" name="description" onkeyup="getDescription();">
                    <dataList id="descriptionHelp"></dataList>
    			</td>
    		</tr>
    		<tr><td colspan="2" align="center"><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" type="submit" value="Search Agenda"></td></tr>
    	</table>
    </form>
</div>

<c:if test="${not empty agendas}">
  	<table id="agendaTable" class="table display" style="width: 99%; background-color: #ffffff;"> 
		<thead> 
			<tr>
				<th>Board Meeting No.</th>  
				<th>Description</th>   
				<th>Item No.</th>
				<th>Action</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${agendas}" var="agenda">
				<tr>
					<td>${agenda.agendaNo}</td>
					<td>${agenda.description}</td>
					<td>${agenda.fileNo}</td>
					<td>
						<a href="#" onclick="viewFile('${agenda.agendaNo}','${agenda.fileNo}')">View</a>&nbsp;&nbsp;
						<a href="#" onclick="downloadFile('${agenda.agendaNo}','${agenda.fileNo}');">Download</a>
					</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</c:if>
<br><br><br><br><br><br><br><br>