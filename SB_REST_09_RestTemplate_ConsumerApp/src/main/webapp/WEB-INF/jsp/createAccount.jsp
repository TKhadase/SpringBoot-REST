<%@ page language="java"   isELIgnored="false"%>
 <%@ taglib  uri="http://www.springframework.org/tags/form"  prefix="sform"%>
<html>
<head>
<style>
#accounts {
  font-family: Arial, Helvetica, sans-serif;
  border-collapse: collapse;
  width: 80%;
}

#accounts td, #accounts th {
  border: 1px solid #ddd;
  padding: 8px;
}

#accounts tr:nth-child(even){background-color: #f2f2f2;}

#accounts tr:hover {background-color: #ddd;}

#accounts th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #04AA6D;
  color: white;
}
</style>
<meta charset="ISO-8859-1">
<title>Standard Bank Of INDIA</title>
</head>
<body>
<p style="text-align:center;"><b>
<a href="./"><h2 style="color:Blue; text-align:center;">Standard Bank Of INDIA</h2></a> <br><br>
<a style="color:Blue" href="./">Home</a> &nbsp; &nbsp; &nbsp;
<a style="color:Blue" href="newAccount">Add new Account</a> &nbsp; &nbsp; &nbsp;
<a style="color:Blue" href="transferBalance">Transfer Balance</a> &nbsp; &nbsp; &nbsp;
<br><br>

<h3 style="color:black;text-align:center;">New Account Form</h3>
</b>
<table  id="accounts" align="center">
<tr><th>Owner Name</th><th>Balance</th><th></th></tr>
<sform:form  modelAttribute="account">
<tr>
<td><sform:input path="name"/></td><td><sform:input path="balance"/></td><td><input type="submit"  value="SUBMIT"/>  </td>
</tr>
</sform:form>
</table>


</p>
</body>
</html>