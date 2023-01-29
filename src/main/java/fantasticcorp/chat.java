package fantasticcorp;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oracle.sql.ARRAY;



/**
 * Servlet implementation class chat
 */
@WebServlet("/chat")
public class chat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public chat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String user = "fantasticcorp";
		String pass = "oracle";
		String sid = "orcl";
		String host = "192.168.1.55";
		int port = 1521;
		String url = "jdbc:oracle:thin:@" + host + ":" + port + "/" + sid;

		 int chatId = Integer.parseInt(request.getParameter("id"));
		 ArrayList<Message> messages = new ArrayList<>();
	        try {
	    		Class.forName("oracle.jdbc.driver.OracleDriver");
	    		Connection conn = DriverManager.getConnection(url, user, pass);
	        	PreparedStatement stmt = conn.prepareStatement("SELECT SCORE,NCHARS,NWORDS,CONTENT,TS FROM TABLE (SELECT MESSAGES FROM CHATS WHERE ID = ?) ORDER BY TS");
	            stmt.setInt(1, chatId);
	            
	            ResultSet rsMsg = stmt.executeQuery();
	            
	            
	
	               
	                while (rsMsg.next()) {
	                	UserMessage tmp = new UserMessage(rsMsg.getString("CONTENT"));
	                	messages.add(tmp);
	                	
	                }
	                
	           

	            
	        } catch (SQLException e) {
	        	throw new ServletException(e);
	        } catch(ClassNotFoundException e){
	               throw new ServletException(e);
		     }
	        
	    	
	        request.setAttribute("messages", messages);
	        request.setAttribute("id", chatId);
	        RequestDispatcher rd=request.getRequestDispatcher("chat.jsp");  
		     rd.include(request,response);  
		     
	    }
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
