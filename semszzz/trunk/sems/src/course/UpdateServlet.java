package course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/course/update.bit")
@SuppressWarnings("serial")
public class UpdateServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		int  no =  Integer.parseInt(request.getParameter("no"));
		String title =  request.getParameter("title");
		String description = 	request.getParameter("description");
		int  hours =  Integer.parseInt(request.getParameter("hours"));
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		CourseDao 	dao = new MysqlCourseDao();
		((MysqlCourseDao)dao).setDBConnectionPool(dbConnectionPool);
		
		
		try {
	    dao.update(new CourseVo()
	     .setNo(no)
	    .setTitle(title)
	    .setDescription(description)
	    .setHours(hours));
	    out.println("변경성공입니다.");
    } catch (Throwable e) {
    	out.println("변경실패입니다.");
    }
	}
}
