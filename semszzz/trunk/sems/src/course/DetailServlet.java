package course;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet("/course/detail.bit")
public class DetailServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
	int no =Integer.parseInt(request.getParameter("no")); 
	
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<html><head><title>교육과정 상세정보</title></head>");
	out.println("<body><h1>교육과정 상세정보</h1>");
	
	
	DBConnectionPool dbConnectionPool = new DBConnectionPool();
	CourseDao dao = new MysqlCourseDao();
	((MysqlCourseDao)dao).setDBConnectionPool(dbConnectionPool);
	 int nno = 0;
	  String title = null;
	  String dest=null;
	  int hours = 0;
  try {
  	CourseVo  course = dao.detail(no);
	   nno = course.getNo();
	   title = course.getTitle();
	   dest = course.getDescription();
	   hours = course.getHours();
  } catch (Throwable e) {}
	
	
	
	out.println("<table border=\"1\"><tr><td>번호</td><td>" + nno + "</td>");
	out.println("<tr><td>교육과정명</td><td>" + title + "</td>");
	out.println("<tr><td>설명</td><td><textarea>"  +dest + "</textarea></td>");
	out.println("<tr><td>시간</td><td>"  +hours + "</td>");
	out.println("</table></body></html>");
	
	
	
}
}
