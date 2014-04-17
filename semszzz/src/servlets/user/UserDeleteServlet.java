package servlets.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;

@WebServlet("/user/delete.bit")
@SuppressWarnings("serial")
public class UserDeleteServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		try {
			
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			dao.delete(no);
			
			RequestDispatcher rd = request.getRequestDispatcher("/user/success.jsp");
			rd.forward(request, response);
			
		}	catch (Throwable e) {
			RequestDispatcher rd = request.getRequestDispatcher("/user/fail.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}
}

