<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
            
<!DOCTYPE html>
<html>
<head>
<title>Your Chats</title>

<style>
img{
 display: block;
  margin-left: auto;
  margin-right: auto;
  max-width:12%;
}
h1{
text-align:center;
}
body {
        background-color: #333333;
        color: #fff;
      }

      .chat-container {
        max-width: 80%;
        margin: 0 auto;
        padding: 20px;
        background-color: #444;
        border-radius: 10px;
      }

      .message {
        margin-bottom: 20px;
        padding: 10px;
        background-color: #555;
        border-radius: 5px;
      }

      .message p {
        margin: 0;
        padding: 0;
      }

      .message-user {
        font-weight: bold;
        margin-right: 10px;
      }



      a {
        padding: 10px 20px;
        background-color: #666;
        border-radius: 5px;
        color: #fff;
        border: none;
        font-weight: bold;
        cursor: pointer;
        width:10%;
        margin :10px;
        text-decoration:none;

      }
      
      form{
      width:100%
      }
</style>
</head>
<body>
<% 
    // Obtain strBook for rendering on current page
    String name = (String) request.getAttribute("name"); 
    String surname = (String) request.getAttribute("surname"); 
%>
<img src="logobw.png">
<h1><%="Hi, " + name + " " + surname%>
<br>these are your chats</h1>

    <div class="chat-container">
    <br>
<%
    List<String> ids = ( List<String>)request.getAttribute("idschats");
    for(String id : ids) {
%>
	<a href="chat?id=<%=id%>">Chat id : <%=id%> </a>
<% } %>
	<br><br>
	

      </div>




</body>
</html>
