<%@ page import="java.util.*,com.employee.controller.*,com.employee.bean.*,com.employee.constants.*" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
 th { color: green; font-size:10pt; }
 td { color: white; font-size:10pt; }
  a { color: white; }
</style>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Employee Portal - Inventory</title>
</head>
<body background="<c:url value='/resources/images/index.jpg'/>">
<div style="text-align: center">
<!-- TODO - Logic has to be moved to controller and the servlet reponse has to be processed -->
<%  
String startindexstring=request.getParameter("startindex");
int currentstartindex = 0;
if(startindexstring != null) {
 currentstartindex = Integer.parseInt(startindexstring);
}

int pagelimit=10;  

Map<String,Object> serverResponse = EmployeeController.retrieveEmployeeList(currentstartindex,pagelimit);
List<Employee> employeeList = (List<Employee>) serverResponse.get("employeeList");
int totalrecords = (int) serverResponse.get("totalRecords");
  
out.print("<h1> <font color=\"white\">Total Employees: "+totalrecords+"</font></h1>");  
out.print("<table border='1' cellpadding='4' width='60%' align='center'>");
StringBuilder headerbuilder = new StringBuilder();
headerbuilder.append("<tr>");
for(String columnHeader:Constants.getEmployeeColumnHeaders()) {
	headerbuilder.append("<th>");
	headerbuilder.append(columnHeader.toUpperCase());
	headerbuilder.append("</th>");
}
headerbuilder.append("</tr>");
out.print(headerbuilder.toString());  
for(Employee employee:employeeList){
	StringBuilder rowBuilder = new StringBuilder();
	rowBuilder.append("<tr>");
	rowBuilder.append("<td>" + employee.getId() + "</td>");
	rowBuilder.append("<td>" + employee.getFirstName() + "</td>");
	rowBuilder.append("<td>" + employee.getLastName() + "</td>");
	rowBuilder.append("<td>" + employee.getDob() + "</td>");
	rowBuilder.append("<td>" + employee.getDoj() + "</td>");
	rowBuilder.append("<td>" + employee.getDesignation() + "</td>");
	rowBuilder.append("<td>" + employee.getContact() + "</td>");
	rowBuilder.append("</tr>");
    out.print(rowBuilder.toString());  
}  
out.print("</table>");

int nextstartindex = currentstartindex + pagelimit;
int endindex = nextstartindex -1;
if(currentstartindex == 0 && totalrecords > pagelimit) {
	out.print("<a href=\"view-employee-inventory.do?startindex="+nextstartindex+"\">Next</a>");
} else if(currentstartindex > 0) {
	int previousstartindex = currentstartindex - pagelimit;
	out.print("<a href=\"view-employee-inventory.do?startindex="+previousstartindex+"\">Previous</a> &nbsp; ");
	if(nextstartindex < totalrecords) {
		out.print("      <a href=\"view-employee-inventory.do?startindex="+nextstartindex+"\">Next</a>");
	}
}
%>  
</div>
</body>
</html>