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
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
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
    <td><%= userDocsPermissions.get(i).getAccesslvl() %></td>
  </tr>
  <% } %>
</table>
<script>
$(document).ready(function(){
    // Example ID
    var id = localStorage.getItem('myId');
    
    // Send the ID to a Java servlet using AJAX
    $.ajax({
        url: 'userId', // URL of your Java servlet
        type: 'POST',
        data: { id: id },
        success: function(response) {
            console.log('ID sent to Java servlet successfully');
        },
        error: function(xhr, status, error) {
            console.error('Error sending ID to Java servlet: ' + error);
        }
    });
});
</script>
</body>
</html>