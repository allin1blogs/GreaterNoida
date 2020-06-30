/*
 *	Swapril Tyagi 
*/

function checkRadio(radioType)
{
	if(radioType=='user')
	{
		document.getElementById('fileNoRadio').checked=false;
		document.getElementById('userTable').style.display='block';
		document.getElementById('fileNoTable').style.display='none';
	}
	else
	{
		document.getElementById('userRadio').checked=false;
		document.getElementById('fileNoTable').style.display='block';
		document.getElementById('userTable').style.display='none';
	}
}

function logs()
{
	if(document.getElementById('userRadio').checked)
	{
		var userName=document.getElementById('userName').value;
		if(userName=='Select')
		{
			document.getElementById('modal').style.display='block';
			document.getElementById('contentPara').innerHTML='Select a valid username.';
		}
		else
			document.getElementById('logForm').submit();
	}
	else if(document.getElementById('fileNoRadio').checked)
	{
		if(document.getElementById('fileNo').length<2)
		{
			document.getElementById('modal').style.display='block';
			document.getElementById('contentPara').innerHTML='Select a valid username.';
		}
		else
			document.getElementById('logForm').submit();
	}
}