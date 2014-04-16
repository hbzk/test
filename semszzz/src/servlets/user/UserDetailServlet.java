package servlets.user;

import java.io.IOException;
import java.io.PrintWriter;

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
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자상세정보</title>");
		
		
		
		out.println("<style>");
		out.println("body{ background: #E1FF72; font-family: dotum; }");
		out.println("#all {width: 500px;margin: 200px auto;background: #D6FF3F;border: 1px solid #000;}");
		out.println("h1 { margin-left: 30px;}");
		out.println(".link {font-size: 130%; display: inline-block; margin: 10px 30px 20px; }");
		out.println("table {font-size: 130%; text-align: center;}");
		out.println("h1:hover {background:#74C22E;}");
		out.println("td:hover {background: #74C22E;}");

		out.println("#left {width: 200px;}");
		out.println("#right {width: 500px;}");


		out.println("</style></head><body><div id = 'all'>");
		
		
		try {
			out.println("<h1>사용자 상세정보</h1>");
			
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			
			int no = Integer.parseInt(request.getParameter("no"));
			
			UserVo user = dao.detail(no);
			
			// === null 찍힘 방지 
			if (user.getFax() == null) {
				user.setFax("");
			}
			if (user.getPostno() == null) {
				user.setPostno("");
			}
			if (user.getAddr() == null) {
				user.setAddr("");
			}
			// ===================

			
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th id='left'>번호</th>");
			out.println("<td id='right'>" + user.getNo() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<th>이메일</th>");
			out.println("<td>" + user.getEmail() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<th>암호</th>");
			out.println("<td>" + user.getPwd() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>이름</th>");
			out.println("<td>" + user.getName() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>전화번호</th>");
			out.println("<td>" + user.getTel() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>팩스</th>");
			out.println("<td>" + user.getFax() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>우편번호</th>");
			out.println("<td>" + user.getPostno() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>주소</th>");
			out.println("<td>" + user.getAddr() + "</td>");
			out.println("</tr>");

			
			out.println("</table>");
			out.println("<a class='link' href='list.bit?pageNo=1&pageSize=10'>목록</a> ");
			out.println("<a class='link' href='delete.bit?no="
					+ user.getNo()
					+ "'>삭제</a> ");
			out.println("<a class='link' href='update.bit?no="
					+ user.getNo()
					+ "'>변경</a><br>");
			
			
			
			
			
			
			
			
			/*out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>번호</th>");
			out.println("<td>" + user.getNo() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<th>이메일</th>");
			out.println("<td>" + user.getEmail() + "</td>");
			out.println("</tr>");
			
			out.println("<tr>");
			out.println("<th>암호</th>");
			out.println("<td>" + user.getPwd() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>이름</th>");
			out.println("<td>" + user.getName() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>전화번호</th>");
			out.println("<td>" + user.getTel() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>팩스</th>");
			out.println("<td>" + user.getFax() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>우편번호</th>");
			out.println("<td>" + user.getPostno() + "</td>");
			out.println("</tr>");

			out.println("<tr>");
			out.println("<th>주소</th>");
			out.println("<td>" + user.getAddr() + "</td>");
			out.println("</tr>");

			
			out.println("</table>");
			out.println("<a href='list.bit?pageNo=1&pageSize=10'>목록</a> ");
			out.println("<a href='delete.bit?no="
					+ user.getNo()
					+ "'>삭제</a> ");
			out.println("<a href='update.bit?no="
					+ user.getNo()
					+ "'>변경</a><br>");*/
		}	catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</div></body></html>");
	}
}
