<%@page import="com.jee.beans.User"%>
<%@page import="com.jee.beans.Access"%>
<%@page import="com.jee.beans.Document"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% User user =(User) request.getAttribute("user"); %>
<% List<Access> userDocsPermissions = (List<Access>)request.getAttribute("userDocsPermissions"); %>
<% List<User> userDocsOwners= (List<User>)request.getAttribute("userDocsOwners"); %>
<% List<Document> userDocs= (List<Document>)request.getAttribute("userDocs"); %>
<h1> hello <%= user.getUsername() %> </h1>
<h3> my Documents Manager</h3>

<table border="1">
  <tr>
    <th>Document Title</th>
    <th>Document Owner</th>
    <th>Permission</th>
  </tr>
  <% for(int i =0;i<userDocs.size();i++){ %>
  <tr>
    <td><%= userDocs.get(i).getTitre() %></td>
    <td><%= userDocsOwners.get(i).getUsername() %></td>
  </tr>
  <% } %>
</table>
</body>
</html>