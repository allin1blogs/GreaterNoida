/*
 *	Swapril Tyagi 
*/

function validateUser(formId)
{
	var userId=document.getElementById('userId').value;
	var password=document.getElementById('password').value;
	var emailId=document.getElementById('emailId').value;
	var mobileNo=document.getElementById('mobileNo').value;
	var firstName=document.getElementById('firstName').value;
	var lastName=document.getElementById('lastName').value;
	var usertype=document.getElementById('userType').value;
	var fileType=document.getElementById('fileType').value;
	if(password.length<8 || password.length>15)
		alert('Password length should be between 8 and 15.');
	else if(emailId!='N.A.' && (emailId.indexOf('@')==-1 || emailId.indexOf('.')==-1))
		alert('Invalid Email Id. Fill valid email Id or N.A.')
	else if(mobileNo!='N.A.' && mobileNo.length!=10)
		alert('Invalid Mobile No.. Fill valid Mobile No. or N.A.');
	else if(userType=='Select')
		alert('Select a valid User Module');
	else if(userType!='Select')
	{
		if(fileType=='Select')
			alert('Select a valid File Category.');
		else
		{
			var inputs=document.getElementsByTagName('input')
			var permissionFlage=false;
			loop:for(var i=0;i<inputs.length;i++)
			{
				if(inputs[i].checked==true)
				{
					permissionFlage=true;
					break loop;
				}
			}
			if(!permissionFlage)
				alert('Select permissions for operating application.')
			else
			{
				for(var i=0;i<inputs.length;i++)
					inputs[i].disabled=false;
				document.getElementById(formId).submit();
			}
		}
	}
}

function validateCha(formId)
{
	var name=document.getElementById('name').value;
	var chaNo=document.getElementById('chaNo').value;
	var iec=document.getElementById('iec').value;
	var branchSerialNo=document.getElementById('branchSerialNo').value;
	var emailId=document.getElementById('emailId').value;
	var gstNo=document.getElementById('gstNo').value;
	var regAdd=document.getElementById('regAdd').value;
	var country=document.getElementById('country').value;
	var state=document.getElementById('state').value;
	var city=document.getElementById('city').value;
	var pincode=document.getElementById('pincode').value;
	var mobileNo=document.getElementById('mobileNo').value;
	var phoneNo=document.getElementById('phoneNo').value;
	var faxNo=document.getElementById('faxNo').value;
	var website=document.getElementById('website').value;
	if(emailId!='N.A.' && (emailId.indexOf('@')==-1 || emailId.indexOf('.')==-1))
		alert('Invalid Email Id. Fill valid email Id or N.A.')
	else if(mobileNo!='N.A.' && mobileNo.length!=10)
		alert('Invalid Mobile No.. Fill valid Mobile No. or N.A.');
	else if(website!='N.A.' && (website.indexOf('@')==-1 || website.indexOf('.')==-1))
		alert('Invalid website. Fill valid website or N.A.')
	else
		document.getElementById(formId).submit();
}