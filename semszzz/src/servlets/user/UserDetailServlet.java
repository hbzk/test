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

/* 목록으로 가기, 삭제하기 - 링크 추가
 * 
 */
@WebServlet("/user/detail.bit")
@SuppressWarnings("serial")
public class UserDetailServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		try {
			
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			UserVo user = dao.detail(no);
			
			request.setAttribute("user", user);
			RequestDispatcher rd = request.getRequestDispatcher("/user/detail.jsp");
			rd.forward(request, response);

		
		}	catch (Throwable e) {
			RequestDispatcher rd = request.getRequestDispatcher("/user/fail.jsp");
			rd.forward(request, response);
			e.printStackTrace();
			
		}
	}
}
