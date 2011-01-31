<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Doctor's Online</title>

<script language="javascript" src="cal2.js">
</script>
<script language="javascript" src="cal_conf2.js"></script>
<link rel="stylesheet" type="text/css" href="style.css" />

<style type="text/css">
<!--
.style1 {color: #FFFFFF}
-->
</style>
</head>
<body>
<div id="maincontainer">

<div id="header">
<h1>Doctor's Online</h1>
<span>An Online Appointment Booking System</span>
</div>

<div id="contentwrapper">
<div id="contentcolumn">

<h2>Doctor List</h2>
<%@ page import="java.util.*"%>
<%@ page import="beans.Doctor" %>

<ul>
<%
	ArrayList al = (ArrayList) session.getAttribute("Result");
	Iterator itr = al.iterator();
%>
<TR>
       <TH>ID</TH>
       <TH>Name</TH>
   
   </TR>
   
  <%
     	while (itr.hasNext()) {
     		Doctor d1 = (Doctor) itr.next();
     %>
       	     
         <TR>
         <br>
             <TD> <%=d1.getId()%></TD>
             <TD> <%=d1.getname()%></td>
             
         
         </TR>
         <%
         	}
         %>
     
<br>
<br>
  <form name="sampleform" method="post" action="ControlServlet">
<input type="text" name="firstinput" size=20> 
<small><a href="javascript:showCal('Calendar1')">[Select Date]</a></small><br />

   <br>
Enter the Doctor ID for your appointment
  <input type="text" name="doc_id" size="20">

  <br />
 Enter the slot for your appointment
<br>
 <input type="radio" name="slot" value="A"> 0900 hours<br>
<input type="radio" name="slot" value="B" checked> 1100 hours<br>
<input type="radio" name="slot" value="C"> 1300 hours<br>
<input type="radio" name="slot" value="D"> 1500 hours<br>
<br />
   <input type="hidden" name="page" value="docs_list.jsp">
<input name="Submit" type="submit" value="Check availability" action="ControlServlet" onClick="temp_data.js" />
</form>



<p></div>
</div>

<div id="leftcolumn">
<h4>Navigation</h4>

<ul id="navlist">
<li><a href="pat_home.jsp">Home</a></li>

<li><a href="search_docs.jsp">Book Appointment</a></li>
<li><a href="cancel_apps.jsp">Cancel Appointment</a></li>
<li><a href="compose_feedback.jsp">Give Feedback</a></li>
<li><a href="view_prescription.jsp">View Prescription</a></li>
<li><a href="pat_compose_mail.jsp">Compose Mail</a></li>
<li><a href="show_pat_inbox.jsp">Check Mail</a></li>
<li><a href="show_pat_sent.jsp">Check Sent Mail</a></li>
<li><a href="logout.jsp">Logout</a></li>
</ul>



</div>

<div id="push"></div>

</div>


</body>
</html>
