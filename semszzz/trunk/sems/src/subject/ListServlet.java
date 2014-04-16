package subject;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/subject/list.bit")
@SuppressWarnings("serial")
public class ListServlet extends HttpServlet {
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		int pageNo = Integer.parseInt(request.getParameter("pageNo"));
		int pageSize = Integer.parseInt(request.getParameter("pageSize"));
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>test01</title></head>");
		out.println("<body><h1>과목정보</h1>");
		out.println("<table border=\"1\">");
		out.println("<tr><th>번호</th><th>과목명</th></tr>");
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		SubjectDao dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		
    try {
    	List<SubjectVo> list = dao.list(pageNo, pageSize);
	    
	    for (SubjectVo subject : list) {
	    	out.println("<tr>");
	    	out.println("<td>" + subject.getNo() + "</td>");
	    	out.println("<td>" + subject.getTitle() + "</td>");
	    	out.println("</tr>");
	    }
    } catch (Throwable e) {}
		
		
		
		out.println("</table></body></html>");
		
		
		
		
	}
}
