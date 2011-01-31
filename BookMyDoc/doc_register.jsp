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

<h2>Doctor's Registration</h2>

<form id="form1" name="form1" method="post" action="ControlServlet">
  <label>PIN:      <span class="style1">.........,..............</span>
  <input type="text" name="pin" id="pin" />
  </label><br /><br />
  


    <label>Password:<span class="style1">    ................</span>
    <input type="password" name="password" id="password" />
  </label><br /><br />

  
    <label>Name:<span class="style1">      .....................</span>
    <input type="text" name="name" id="name" />
  </label><br /><br />
  
        <label>Type:<span class="style1">      ....................</span>
<br>
<input type="radio" name="type" id="type" value="ENT"> ENT<br>
<input type="radio" name="type" id="type" value="Dentist" checked> Dentist<br>
<input type="radio" name="type" id="type" value="Pathology"> Pathology<br>
<input type="radio" name="type" id="type" value="Dermatologist"> Dermatologist<br>
<input type="radio" name="type" id="type" value="Physician"> 
Physician<br>
  </label>
  <input type="hidden" name="page" value="doc_register.jsp">
  <label>Age:      <span class="style1">..................... .</span>
  <input type="text" name="age" id="age" />
  </label><br /><br />
  

      <p>&nbsp;</p>
      <p>
        <label> <span class="style1">.....................
        </span>
        <input type="submit" action="ControlServlet" value="Register" />
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
<li><a href="admin_login.jsp">Admin Login</a></li>
</div>

</div>

<div id="push"></div>

</div>


</body>
</html>
