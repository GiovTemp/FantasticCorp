


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

            
<!DOCTYPE html>
<html>
<head>
<title>Login</title>

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
		width:95%
        
        
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
<h1>Login</h1>
<script type="text/javascript">
  function showErrorMessage() {
    var errorMessage = "<%= request.getAttribute("errorMessage") %>";
    if (errorMessage!='null') {
      alert(errorMessage);
    }
  }
</script>
<body onload="showErrorMessage()">

    <div class="chat-container">

      <div class="chat-input">
 <form method="POST" action="login">

	<input type="email" id="email" name="email" placeholder="Your Email"/><br><br>

	<input type="password" id="password" name="password"placeholder="Your Password"/><br><br>
	<button type="submit" >Login</button>
</form>
      </div>
      </div>




</body>
</html>
