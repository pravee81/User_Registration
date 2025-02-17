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

@WebServlet("/showdata")
public class ShowUserServlet extends HttpServlet {

	private final static String query="select name,email,mobile,dob,city,gender from user";

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
		PrintWriter pw=res.getWriter();
		res.setContentType("text/html");
		
		pw.println("<link rel='stylesheet' href='css/bootstrap.css'></link>");
		
		
	try {
		Class.forName("com.mysql.cj.jdbc.Driver");
	}catch(Exception e) {
		e.printStackTrace();
	}
	try(Connection con=DriverManager.getConnection("jdbc:mysql:///test","root","Ps@123");
			PreparedStatement ps=con.prepareStatement(query);){
		ResultSet rs=ps.executeQuery();
		pw.println("<table class='table table-hover table-striped'>");
		pw.println("<tr>");
		
		pw.println("<th>Name</th>");
		pw.println("<th>Email</th>");
		pw.println("<th>Mobile No</th>");
		pw.println("<th>DOB</th>");
		pw.println("<th>City</th>");
		pw.println("<th>Gender</th>");
		pw.println("</tr>");
		while(rs.next()) {
			pw.println("<tr>");
			pw.println("<td>"+rs.getString(1)+"</td>");
			pw.println("<td>"+rs.getString(2)+"</td>");
			pw.println("<td>"+rs.getString(3)+"</td>");
			pw.println("<td>"+rs.getString(4)+"</td>");
			pw.println("<td>"+rs.getString(5)+"</td>");
			pw.println("<td>"+rs.getString(6)+"</td>");	
			pw.println("</tr>");
		}
		pw.println("</table>");
	}catch(SQLException se) {
		pw.println("<h2 class='bg-danger text-light text-center'>"+se.getMessage()+"</h2>");
		se.printStackTrace();
	}catch(Exception e) {
		e.printStackTrace();
	}
	pw.println("<a href='home.html'><button class='btn btn-outline-success'>Home</button></a>");
	
	pw.close();
	}
	
protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
		
	}
}


