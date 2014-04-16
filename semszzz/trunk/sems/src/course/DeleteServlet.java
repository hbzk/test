package course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/course/delete.bit")
@SuppressWarnings("serial")
public class DeleteServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		int no = 	Integer.parseInt(request.getParameter("no"));
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		CourseDao 	dao = new MysqlCourseDao();
		((MysqlCourseDao)dao).setDBConnectionPool(dbConnectionPool);
		
		
		try {
	    dao.delete(no);
	    out.println("삭제성공입니다.");
    } catch (Throwable e) {
    	out.println("삭제실패입니다.");
    }
	}
}
