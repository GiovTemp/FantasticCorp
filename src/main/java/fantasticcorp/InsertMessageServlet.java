package fantasticcorp;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/InsertMessageServlet")
public class InsertMessageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public InsertMessageServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String user = "fantasticcorp";
		String pass = "oracle";
		String sid = "orcl";
		String host = "192.168.1.55";
		int port = 1521;
		
		String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + sid;
		try 
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pass);
			
	        String content = request.getParameter("content");
	        String[] words = content.split("\\s+");
	        int score = 120;
	        int nchars = content.length();
	        int nwords = words.length;
	        int chatId = Integer.parseInt(request.getParameter("chatId"));

			PreparedStatement pstmt=conn.prepareStatement("INSERT INTO TABLE(SELECT MESSAGES FROM CHATS WHERE ID = ?) VALUES (MESSAGETY(?, ?, ?, ?, CURRENT_TIMESTAMP))");
			pstmt.setInt(1, chatId);
			pstmt.setFloat(2, score);
            pstmt.setInt(3, nchars);
            pstmt.setInt(4, nwords);
            pstmt.setString(5, content);

            pstmt.executeUpdate();

			pstmt.close();
			conn.commit();
			conn.close();
			
			response.sendRedirect("/FantasticCorp/chat?id="+chatId);
	    }
	    catch(ClassNotFoundException e){
               throw new ServletException(e);
	     }
	    catch(SQLException e){
               throw new ServletException(e);
	     }
    }
    

}
