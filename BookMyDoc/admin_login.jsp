<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<script src="ad_log1.js" >
</script>
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

<h2>Admin Login</h2>

<form name="login_form" method="post" action="ControlServlet"onsubmit="return isNumeric();">
  <label>User ID:      <span class="style1">...................</span>
  <input type="text" name="id"/>
  </label><br /><br />

    <label>Password:<span class="style1">    ................</span>
    <input type="password" name="password"/>
    <input type="hidden" name="page" value="admin_login.jsp">
  </label><br /><br />



      <p>&nbsp;</p>
      <p>
        <label> <span class="style1">.....................
        </span>
        <input type="submit" name="login" id="login" value="Login" />
        </label>
        <br />
          <br />
            </p>
</form>
<p></div>
</div>

<div id="leftcolumn">
<h4>Navigation</h4>

<ul id="navlist">
<li><a href="index.jsp">Home</a></li>
<li><a href="pat_register.jsp">Patient Registration</a></li>
<li><a href="doc_register.jsp">Doctor's Registration</a></li>


</ul>

<div class="greybox">
<li><a href="pat_login.jsp">Patient's Login</a></li>
<li><a href="doc_login.jsp">Doctor's Login</a></li>

</div>

</div>

<div id="push"></div>

</div>



</body>
</html>
