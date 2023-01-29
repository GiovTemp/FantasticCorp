<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="fantasticcorp.Message" %>
<%@ page import="fantasticcorp.AIMessage" %>
<%@ page import="fantasticcorp.UserMessage" %>

            
<!DOCTYPE html>
<html>
<head>
<title>Chat Messages</title>

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

      .chat-input {
        margin-top: 20px;
        display: flex;
        justify-content: space-between;
        width:100%
      }

      .chat-input input {
        
        padding: 10px;
        background-color: #555;
        border-radius: 5px;
        color: #fff;
        border: none;
		width:80%
        
        
      }

      .chat-input button {
        padding: 10px 20px;
        background-color: #666;
        border-radius: 5px;
        color: #fff;
        border: none;
        font-weight: bold;
        cursor: pointer;
        width:10%

      }
      
      form{
      width:100%
      }
</style>
</head>
<body>
<img src="logobw.png">
<h1>Chat</h1>

    <div class="chat-container">
		<%
		List<Message> messages = (List<Message>) request.getAttribute("messages");
		for (int i = 0; i < messages.size(); i++) {
		    Message message = messages.get(i);
		
		    %>
		    <div class="message">
		        <p><%= message.getContent() %></p>
		    </div>
		<% } %>
	<hr>
      <div class="chat-input">

		 <form action="InsertMessageServlet" method="POST">
		    <input type="text" id="content" placeholder="Type your message here..." name="content">
		    <input type="hidden" id="chatId" name="chatId" value="<%= request.getAttribute("id") %>" >
		    <button type="submit">Send</button>
		</form>
      </div>
      </div>




</body>
</html>
