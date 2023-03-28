<%@page import="dto.Customer"%>
<%@page import="dto.BankTransaction"%>
<%@page import="java.util.List"%>
<%@page import="dto.BankAccount"%>
<%@page import="dao.BankDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%Customer customer =(Customer)session.getAttribute("customer"); 
if(customer==null)
{
	response.getWriter().print("<h1>Session Expired Login Again</h1>");
	request.getRequestDispatcher("Login.html").include(request, response);
}
else{
%>
<%
long acno=(Long) request.getSession().getAttribute("acno");
BankDao bankDao=new BankDao();
BankAccount account=bankDao.find(acno);
List<BankTransaction> list=account.getTransaction();
%>
<h1>Account Number: <%=acno %></h1><br>
<h1>Account Type: <%=account.getType() %></h1><br>

<table border="1">
<tr>
<th>Transaction_Id</th>
<th>Deposit</th>
<th>Withdraw</th>
<th>Balance</th>
<th>Time</th>
</tr>
<%for(BankTransaction transaction:list){ %>
<tr>
<th><%=transaction.getId() %></th>
<th>Rs.<%=transaction.getDeposit() %></th>
<th>Rs.<%=transaction.getWithdraw() %></th>
<th>Rs. <%=transaction.getBalance() %></th>
<th><%=transaction.getDateTime() %></th>
</tr>
<%} %> 
</table>
<br>
<br>
<a href="AccountHome.jsp"><button>Back</button></a>
<%} %>
</body>
</html>