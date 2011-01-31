function isNumeric()
{
var value=document.login_form.id.value;
if (value == null||value == "")
{
alert("ID field can'nt be left blank");
return false;
}
else if(value.length!=5)
{
alert("Enter 5 digit numerls only");
return false;
}
else if (value =!value.toString().match(/^[-]?\d*\.?\d*$/))
{
alert("Enter only numerals");
return false;
}
return true;
}