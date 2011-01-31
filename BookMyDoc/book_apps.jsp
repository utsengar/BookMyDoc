<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />

<title>Doctor's Online</title>

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

<h2>Patients Interface - Book Appointment</h2>

The Doctor You Requested Is Avaliable!!!
Ab Aap Swasth Ho Jayenge......HopeFully...!

<form name="book_app" method="post" action="ControlServlet">

    <input type="hidden" name="page" value="book_apps.jsp">
  </label>
        <input type="submit" name="Book" id="Book" value="Book Appointment" onclick="temp_data.js" />
        </label>

</form>

<p></div>
</div>

<div id="leftcolumn">
<h4>Navigation</h4>

<ul id="navlist">
<li><a href="pat_home.jsp">Home</a></li>

<li><a href="book_apps.jsp">Book Appointment</a></li>
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
