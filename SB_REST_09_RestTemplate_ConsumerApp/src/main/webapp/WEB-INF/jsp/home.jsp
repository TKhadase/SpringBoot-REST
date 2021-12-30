<%@ page language="java"   isELIgnored="false"%>
 <%@ taglib  uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
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
<h3 style="color:red;text-align:right;">${resultMsg}</h3>
<c:choose>
<c:when test="${!empty accList }">
<h3 style="color:black;text-align:center;">Available Accounts</h3>
</b>
<table  id="accounts" align="center">
<tr><th>Acc.No</th><th>Owner Name</th><th>Balance</th><th>Acc.Opening Dt</th><th>Acc.Status</th><th></th></tr>
<c:forEach var="acc" items="${accList}">
<tr>
<td><a style="color:red" href='edit?accid=${acc.accno}'>${acc.accno}</a></td><td>${acc.name}</td><td>${acc.balance}</td><td>${acc.openingDt}</td><td>${acc.isActive}</td><td><a href='delete?accid=${acc.accno}'>DeActivate</a></td>
</tr>
</c:forEach>
</table>
</c:when>
<c:otherwise><b>
<h4 style="color:red;text-align:center;">No Accounts Available yet</h4></b>
</c:otherwise>
</c:choose>


</p>
</body>
</html>