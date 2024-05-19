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
<title>Document Manager</title>
<style>
body {
	font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
	background-color: #f0f2f5;
	color: #333;
	margin: 0;
	padding: 0;
	display: flex;
	flex-direction: column;
	align-items: center;
}

h1, h3 {
	color: #2c3e50;
}

.container {
	max-width: 900px;
	width: 100%;
	padding: 20px;
	background: #fff;
	box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
	margin-top: 40px;
	border-radius: 12px;
}

table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 20px;
}

table, th, td {
	border: 1px solid #ddd;
}

th, td {
	padding: 12px;
	text-align: left;
}

th {
	background-color: #f7f9fc;
}

tr:hover {
	background-color: #f1f1f1;
}

.title {
	color: #2980b9;
	margin-bottom: 0;
}

.info {
	color: #7f8c8d;
}

.btn {
	padding: 8px 12px;
	margin: 2px;
	border: none;
	border-radius: 4px;
	cursor: pointer;
	font-size: 14px;
}

.update-btn {
	background-color: #3498db;
	color: white;
}

.delete-btn {
	background-color: #e74c3c;
	color: white;
}

.download-btn {
	background-color: #2ecc71;
	color: white;
}
.access-btn{
background-color: purple;
	color: white;
}
.btn:hover {
	opacity: 0.8;
	box-shadow: 0px 0px 2px 2px black;
}

#add-div {
	border: 1px solid #ddd;
	padding: 20px;
	margin-top: 20px;
	background: #f9f9f9;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

#add-div h4 {
	color: #2c3e50;
	margin-bottom: 15px;
}

#add-div form {
	display: flex;
	flex-direction: column;
}

#add-div form input[type="text"], #add-div form input[type="file"] {
	margin-bottom: 10px;
	padding: 10px;
	border: 1px solid #ddd;
	border-radius: 4px;
}

#add-div form button {
	align-self: flex-start;
	padding: 10px 15px;
	background-color: #2980b9;
	color: white;
	border: none;
	border-radius: 4px;
	cursor: pointer;
}

#add-div form button:hover {
	background-color: #2575a7;
}

#signout-btn {
	background-color: #2980b9;
	color: white;
}
.modal {
        display: none;
        position: fixed;
        z-index: 1;
        left: 0;
        top: 0;
        width: 100%;
        height: 100%;
        overflow: auto;
        background-color: rgb(0, 0, 0);
        background-color: rgba(0, 0, 0, 0.4);
    }
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto;
        padding: 20px;
        border: 1px solid #888;
        width: 80%;
        max-width: 500px;
        border-radius: 8px;
    }
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
</style>
</head>
<body>
<div class="container">
    <%
    User user = (User) request.getAttribute("user");
    List<Access> userDocsPermissions = (List<Access>) request.getAttribute("userDocsPermissions");
    List<User> userDocsOwners = (List<User>) request.getAttribute("userDocsOwners");
    List<Document> userDocs = (List<Document>) request.getAttribute("userDocs");
    String step = (String) request.getAttribute("step");
    if (step == null) {
        step = "initial";
    }
    %>
    <h1 class="info">My Documents Manager</h1>
    <h3 class="title">Hello, <%=user.getUsername()%></h3>

    <div id="add-div">
        <h4>add File process :</h4>
        <% if (step.equals("step1")) { %>
            <div id="step1">
                <form method="post" action="storeFile.dostep1">
                    <input type="text" name="doc_title" placeholder="Document Title">
                    <input type="text" name="doc_desc" placeholder="Document Description">
                    <button>Next</button>
                </form>
            </div>
        <% } else if (step.equals("step2")) { %>
            <div id="step2">
                <form method="post" action="storeFile.dostep2" enctype="multipart/form-data">
                    <input type="file" name="uploadedFile">
                    <button>Save</button>
                </form>
            </div>
        <% } else { %>
            <div id="initial">
                <form method="post" action="storeFile.dostart">
                    <input type="hidden" name="initiate" value="true">
                    <button>Start</button>
                </form>
            </div>
        <% } %>
    </div>
 <table>
        <tr>
            <th>Document Title</th>
            <th>Document Owner</th>
            <th>Permission</th>
            <th colspan="4">Actions</th>
        </tr>
        <% for (int i = 0; i < userDocs.size(); i++) { %>
        <tr>
            <td><%= userDocs.get(i).getTitre() %></td>
            <td><%= userDocsOwners.get(i).getUsername() %></td>
            <td><%= userDocsPermissions.get(i).getAccesslvl() %></td>
            <td><button class="update-btn btn"
                        data-id="<%= userDocs.get(i).getId() %>"
                        data-title="<%= userDocs.get(i).getTitre() %>"
                        data-desc="<%= userDocs.get(i).getDescription() %>"
                        data-permission="<%= userDocsPermissions.get(i).getAccesslvl() %>">update</button></td>
            <td><button class="delete-btn btn" id="<%= userDocs.get(i).getId() %>">delete</button></td>
            <td><button class="download-btn btn" id="<%= userDocs.get(i).getId() %>">download</button></td>
       		<% if(userDocsPermissions.get(i).getAccesslvl().equals("o")) {%>
       		<td><button class="access-btn btn"> manage access</button></td>
       	<% } %>
        </tr>
        <% } %>
    </table>
    <a href="signin.jsp"><button id="signout-btn" class="btn">sign out</button></a>
</div>

<div id="updateModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Update Document</h2>
        <form id="updateForm" method="post" action="updateFile.do">
            <input type="hidden" name="doc_id" id="doc_id">
            <label for="doc_title">Title:</label>
            <input type="text" name="doc_title" id="doc_title">
            <label for="doc_desc">Description:</label>
            <input type="text" name="doc_desc" id="doc_desc">
            
            <button type="submit">Save Changes</button>
        </form>
    </div>
</div>
<div id="accessModal" class="modal">
    <div class="modal-content">
        <span class="close">&times;</span>
        <h2>Give Access</h2>
        <form id="accessForm" method="post" action="giveAccess.do">
            <input type="hidden" name="doc_id" id="access_doc_id">
            <label for="username">Username:</label>
            <input type="text" name="username" id="username">
            <label for="accesslvl">Access Level:</label>
            <select name="accesslvl" id="accesslvl">
                <option value="r">Read</option>
                <option value="r+w">Read and Write</option>
               </select>
               <button type="submit">Grant access</button>
               </form>
               </div>
               </div>
 <script>
$(document).ready(function() {
    $(".delete-btn").click(function() {
        var doc_id = $(this).attr('id');
        var url = 'deleteFile.do?doc_id=' + doc_id;
        window.location.href = url;
    });

    $(".download-btn").click(function() {
        var doc_id = $(this).attr('id');
        var url = 'downloadFile.do?doc_id=' + doc_id;
        window.location.href = url;
    });

    $(".update-btn").click(function() {
        var doc_id = $(this).data('id');
        var doc_title = $(this).data('title');
        var doc_desc = $(this).data('desc');

        $("#doc_id").val(doc_id);
        $("#doc_title").val(doc_title);
        $("#doc_desc").val(doc_desc);

        $("#updateModal").css("display", "block");
    });

    $(".access-btn").click(function() {
        var doc_id = $(this).data('id');
        $("#access_doc_id").val(doc_id);
        $("#accessModal").css("display", "block");
    });

    $(".close").click(function() {
        $("#updateModal").css("display", "none");
        $("#accessModal").css("display", "none");
    });

    $(window).click(function(event) {
        if (event.target.id == "updateModal") {
            $("#updateModal").css("display", "none");
        }
        if (event.target.id == "accessModal") {
            $("#accessModal").css("display", "none");
        }
    });
});
</script>
</body>
</html>