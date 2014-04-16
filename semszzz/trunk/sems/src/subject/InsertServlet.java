package subject;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/subject/insert.bit")
@SuppressWarnings("serial")
public class InsertServlet  extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		String title =  request.getParameter("title");
		String description = 	request.getParameter("description");
		
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		SubjectDao 	dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		
		try {
	    dao.insert(new SubjectVo()
	    .setTitle(title)
	    .setDescription(description));
	    out.println("등록성공입니다.");
    } catch (Throwable e) {
    	out.println("등록실패입니다.");
    }
	}
}
