package Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet("/processHandler")
public class processHandler {
	
	public void doGet(HttpServletRequest httpServletRequest, HttpServletResponse response) throws ServletException, IOException {
		 String strAction = httpServletRequest.getParameter("action");
	    	System.out.println("Processor Servlet:"+ strAction );
	    	
	    	
	    	  if (strAction != null && !strAction.equals("")) {
	    		  if (strAction.equals("Query")) {
	    		       
		              	String Query = httpServletRequest.getParameter("Query");
		                HttpSession session = httpServletRequest.getSession();
		                session.setAttribute("Query",Query);
		                response.sendRedirect("Index.jsp");
	    	  }
	    	  }else{
	    		  
	    		  System.out.print("The Action is either null or blank");
	    		  
	    	  }
	    	
	}

}
