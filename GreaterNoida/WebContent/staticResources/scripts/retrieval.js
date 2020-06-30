/*
 * created by Swapril Tyagi
*/

var request,listId;

function getHelp(param)
{
	var value=document.getElementById(param).value;
	listId=param+"Help";
	var url="getHelp?param="+param+"&value="+value;
	if(window.XMLHttpRequest)  
		request=new XMLHttpRequest();  
	else if(window.ActiveXObject)  
		request=new ActiveXObject("Microsoft.XMLHTTP");
	try
	{
		request.onreadystatechange=getInfo;
		request.open("GET",url,true);  
		request.send();
	}
	catch(e)
	{}
}

function setInfo()
{
	var dataList=document.getElementById(listId);
	var options;
	if(request.readyState==4)
	{
		var val=request.responseText.split('<!@!>');
		for(var i=0;i<val.length;i++)
			options=options+'<option value="'+val[i]+'">';
		dataList.innerHTML=options;
	}
}

function retrieve()
{
	var fileCategory=document.getElementById('type').value;
	if(fileCategory=='Select')
		alert('Select a valid File Category');
	else
		document.getElementById('fileForm').submit();
}