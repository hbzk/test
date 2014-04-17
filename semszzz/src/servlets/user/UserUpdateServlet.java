package servlets.user;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import user.UserVo;
import dao.UserDao;

@WebServlet("/user/update.bit")
@SuppressWarnings("serial")
public class UserUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			UserVo user = dao.detail(no);
			
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/user/update.jsp");
			rd.forward(request, response);
			
		}	catch (Throwable e) {
			e.printStackTrace();
		}
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		try {
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			
			UserVo vo = new UserVo();
			vo.setNo(Integer.parseInt(request.getParameter("no")));
			vo.setEmail(request.getParameter("email"));
			vo.setPwd(request.getParameter("pwd"));
			vo.setName(request.getParameter("name"));
			vo.setTel(request.getParameter("tel"));
			vo.setFax(request.getParameter("fax"));
			vo.setPostno(request.getParameter("postno"));
			vo.setAddr(request.getParameter("addr"));
			
			dao.update(vo);
			RequestDispatcher rd = request.getRequestDispatcher("/user/success.jsp");
			rd.forward(request, response);
			
		}	catch (Throwable e) {
			RequestDispatcher rd = request.getRequestDispatcher("/user/fail.jsp");
			rd.forward(request, response);
			e.printStackTrace();
		}
	}
}
