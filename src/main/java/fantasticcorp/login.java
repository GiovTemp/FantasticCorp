package fantasticcorp;


import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.RequestDispatcher;
import oracle.sql.ArrayDescriptor;
import oracle.sql.ARRAY;

import java.util.ArrayList;
import java.util.List;
/**
 * Servlet implementation class login
 */
@WebServlet("/login")
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
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

		boolean status=false;  
		try{  
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(url, user, pass);
		    String e=request.getParameter("email");  
		    String p=request.getParameter("password");  
		    
		    //Get chats
		    
		    

			PreparedStatement ps=conn.prepareStatement("select * FROM fantasticcorp.REGISTEREDUSERS where email=? and password=?");  
			ps.setString(1,e);  
			ps.setString(2,p);  
			      
			ResultSet rs=ps.executeQuery();  
			status=rs.next();

	
			if(status==true) {
				 request.setAttribute("email", rs.getString("EMAIL"));
				 request.setAttribute("name", rs.getString("NAME"));
				 request.setAttribute("surname", rs.getString("SURNAME"));
				 ArrayDescriptor desc = ArrayDescriptor.createDescriptor("CHATNT", conn);
				 PreparedStatement stmt = conn.prepareStatement(
			                "SELECT u.chats FROM fantasticcorp.REGISTEREDUSERS u WHERE u.email = ?");
			            stmt.setString(1, e);
			            ResultSet rsChats = stmt.executeQuery();
			            List<String> ids = new ArrayList<>();
			            if(rsChats.next()) {
			                ARRAY chats =(ARRAY) rsChats.getObject("chats");
			                ResultSet chatRs = chats.getResultSet();

			                while (chatRs.next()) {
			                	ids.add(chatRs.getObject(1).toString());
			                }
			                
			            }
			     request.setAttribute("idschats", ids);
			     stmt.close();
				 ps.close();
				 conn.close();
					
				 RequestDispatcher rd=request.getRequestDispatcher("Dashboard.jsp");  
			     rd.include(request,response);  
			}else {
				  request.setAttribute("errorMessage", "Invalid username or password.");
				 ps.close();
				 conn.close();
				
				 RequestDispatcher rd=request.getRequestDispatcher("login.jsp");  
			     rd.include(request,response);  
			}
			        
		}catch(ClassNotFoundException e){
            throw new ServletException(e);
	     }
	    catch(SQLException e){
            throw new ServletException(e);
	     }
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
