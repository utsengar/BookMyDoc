<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Doctor's Online</title>

<link rel="stylesheet" type="text/css" href="style.css" />

</head>
<body>
<div id="maincontainer">

<div id="header">
<h1>Doctor's Online</h1>
<span>An Online Appointment Booking System</span>
</div>

<div id="contentwrapper">
<div id="contentcolumn">
<h2>Create Doctor</h2>


<ul>
Click Here To Create A New Doctor And Generate Doctor's PIN
<br /><br />
<br />


<form action="ControlServlet" method="post" name="create-doc"> 
<input name="Create Doctor" type="submit" value="Create Doctor">
<input type="hidden" name="page" value="create_doc.jsp"></form>

 </ul>

</div>
</div>


<div id="leftcolumn">
<h4>Navigation</h4>

<ul id="navlist">
<li><a href="admin_home.jsp">Home</a></li>
<li><a href="create_doc.jsp">Create Doctor</a></li>
<li><a href="delete_doc.jsp">Delete Doctor</a></li>
<li><a href="delete_pat.jsp">Delete Patient</a></li>
<li><a href="approve_leave.jsp">Approve Doctor's Leave</a></li>
</ul>
</div>

<div id="push"></div>
</div>


</body>
</html>
