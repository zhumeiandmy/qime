package therr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import one.onee;



/**
 * Servlet implementation class Api
 */
@WebServlet(description = "jiekou", urlPatterns = { "/Api" })
public class Api extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Api() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/json");
		 response.setCharacterEncoding("UTF-8");
		 request.setCharacterEncoding("UTF-8");
		try {
			String booksjson = onee.Query();
		      PrintWriter out = response.getWriter();
		      out.write(booksjson);
		      out.flush();
		      out.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 response.setContentType("application/plain");
		    response.setCharacterEncoding("UTF-8");
		    request.setCharacterEncoding("UTF-8");
		    if (request.getRequestURI().equals("/Java/Api/DELETE")) {
		        System.out.println("delete");
		        String empno = request.getParameter("empno");
		        String result;
		     try {
		    	 result = onee.DELETE(empno);
		         PrintWriter out = response.getWriter();
		         out.write(result);
		         out.flush();
		         out.close();
		    	 
			} catch (ClassNotFoundException | SQLException e) {
				// TODO: handle exception
			}   
		        
		        
		    }
		    if (request.getRequestURI().equals("/Java/Api/UPDATE")) {
		        System.out.println("update");

		        BufferedReader reader = request.getReader();
		        String json = reader.readLine();
		        System.out.println(json);
		        reader.close();
		        String result;
		        try {
		          result =onee.UPDATE(json);
		          PrintWriter out = response.getWriter();
		          out.write(result);
		          out.flush();
		          out.close();
		        } catch (ClassNotFoundException | SQLException e) {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		        }
		      }

		      if (request.getRequestURI().equals("/Java/Api/INSERT")) {
		        System.out.println("insert");

		        BufferedReader reader = request.getReader();
		        String json = reader.readLine();
		        System.out.println(json);
		        reader.close();
		        String result;
		        try {
		          result = onee.INSERT(json);
		          PrintWriter out = response.getWriter();
		          out.write(result);
		          out.flush();
		          out.close();
		        } catch (ClassNotFoundException | SQLException e) {
		          // TODO Auto-generated catch block
		          e.printStackTrace();
		        }
		      }
	}
}
