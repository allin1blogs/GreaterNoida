<%-- 
    Document   : Create Records
    Created on : 22 June, 2020, 01:00:32 PM
    Author     : Preeti Rani
--%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="LandForm"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	function update()
	{
		mscConfirm("Have checked all details before updating record?",function(){
		  	document.getElementById('updateLandForm').submit();
		});
	}
</script>

<c:if test="${not empty msg}">
	<div id="myModal" class="modal" style="display: block;">
  		<div class="modal-content">
    		<div class="modal-header" style="background-color: #387403;">
      			<span class="close" onclick="document.getElementById('myModal').style.display='none'" style="color: #FFFFFF;">&times;</span>
      			<p style="text-align: center; color: #FFFFFF;" class="h3">${msg}</p>
    		</div>
  		</div>
	</div>
</c:if>

<p class="h1" style="font-family: cambria; text-align: center; color: #387403;">Land</p>
<div class="container">
	<LandForm:form action="updateLand" id="updateLandForm" enctype="multipart/form-data" method="POST" modelAttribute="LandForm">
        <table class="table">
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="Lot No"><h4><b>Account No:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="accountNo" required="true"/>
                </td>
                <td>
                    <label style="color: black; font-family: cambria;" for="fileNo"><h4><b>File No:</b></h4></label><br>
                    <LandForm:input style="width: 235px; height: 35px;" path="fileNo" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for=filesub><h4><b>File Subject:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="filesub" required="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="fileType"><h4><b>File Type:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="fileType" required="true"/>
                </td>
               <td>
                  	<label style="color: black; font-family: cambria;" for="line"><h4><b>Line:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="line" required="true"/>
                </td> 
                <td>
                   	<label style="color: black; font-family: cambria;" for="opaFts"><h4><b>Opa/Fts No.:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="opaFts" readonly="true"/>
                </td>
            </tr>
             
            <tr>
                
                 <td>
                  	<label style="color: black; font-family: cambria;" for="no_of_cos"><h4><b>No Of Cros:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="no_of_cos" required="true"/>
                </td> 
                <td>
                   	<label style="color: black; font-family: cambria;" for="village"><h4><b>Village:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="village" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="department"><h4><b>Department:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="department" readonly="true"/>
                </td>
            </tr>
            <tr>
                <td>
                   	<label style="color: black; font-family: cambria;" for="year"><h4><b>Year:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="year" required="true"/>
                </td>
                <td>
                  	<label style="color: black; font-family: cambria;" for="no_of_notsheet"><h4><b>No Of Notsheet:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="no_of_notsheet" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="servey_letter"><h4><b>Servey Letter:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="servey_letter" required="true"/>
                </td>
            </tr>
            <tr>
            	<td>
                   	<label style="color: black; font-family: cambria;" for="notifection"><h4><b>Notification:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="notifection" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="morgeg_letter"><h4><b>Morgeg letter:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="morgeg_letter" required="true"/>
                </td>
                <td>
                   	<label style="color: black; font-family: cambria;" for="date"><h4><b>Date:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="date" required="true"/>
                </td>
            </tr>
            <tr>
            <td>
                   	<label style="color: black; font-family: cambria;" for="total_pages"><h4><b>Total Pages:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="total_pages" required="true"/>
                </td>
                 <td>
                   	<label style="color: black; font-family: cambria;" for="map_11"><h4><b>Map 11:</b></h4></label><br>
                   	<LandForm:input style="width: 235px; height: 35px;" path="map_11" required="true"/>
                </td>
             <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose NoteSheet Pages:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="noteSheet"/>
                </td>
                
            </tr>
            <tr>
            <td>
                   	<label style="color: black; font-family: cambria;" for="File"><h4><b>Choose Correspondence Page:</b></h4></label><br>
                   	<input style="width: 235px; height: 35px; margin-top: 7px;" type="file" name="correspondence"/>
                </td>
            </tr>
            <tr><td><LandForm:hidden path="sno"/></td><td colspan="2"><LandForm:hidden path="location"/></td></tr>
            <tr><td colspan="3" align="center"><br><input class="btn btn-primary" style="background-color: #2D6419; color: #ffffff;" value="Update It" onclick="update();"></td></tr>
        </table>
    </LandForm:form>
</div>