package servlets.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import vo.UserVo;
import dao.UserDao;

@WebServlet("/user/insert.bit")
@SuppressWarnings("serial")
public class UserInsertServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		doGet(request, response);
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		try {
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			
			UserVo user = new UserVo();
			user.setEmail(request.getParameter("email"));
			user.setPwd(request.getParameter("pwd"));
			user.setName(request.getParameter("name"));
			user.setTel(request.getParameter("tel"));
			user.setFax(request.getParameter("fax"));
			user.setPostno(request.getParameter("postno"));
			user.setAddr(request.getParameter("addr"));
			
			dao.insert(user);
			
			RequestDispatcher rd = request.getRequestDispatcher("/user/insert.jsp");
			rd.forward(request, response);
			
		}	catch (Throwable e) {
			RequestDispatcher rd = request.getRequestDispatcher("/user/fail.jsp");
			rd.forward(request, response);
			e.printStackTrace();
			
		}
	}
}
