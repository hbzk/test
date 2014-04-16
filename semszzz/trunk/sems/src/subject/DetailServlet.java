package subject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@SuppressWarnings("serial")
@WebServlet("/subject/detail.bit")
public class DetailServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
	
	int no =Integer.parseInt(request.getParameter("no")); 
	
	
	response.setContentType("text/html;charset=UTF-8");
	PrintWriter out = response.getWriter();
	out.println("<html><head><title>과목상세정보</title></head>");
	out.println("<body><h1>과목상세정보</h1>");
	
	
	DBConnectionPool dbConnectionPool = new DBConnectionPool();
	SubjectDao dao = new MysqlSubjectDao();
	((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
	 int nno = 0;
	  String title = null;
	  String dest=null;
  try {
  	SubjectVo  subject = dao.detail(no);
	   nno = subject.getNo();
	   title = subject.getTitle();
	   dest = subject.getDescription();
  } catch (Throwable e) {}
	
	
	
	out.println("<table border=\"1\"><tr><td>번호</td><td>" + nno + "</td>");
	out.println("<tr><td>과목명</td><td>" + title + "</td>");
	out.println("<tr><td>설명</td><td><textarea>"  +dest + "</textarea></td>");
	out.println("</table></body></html>");
	
	
	
}
}
