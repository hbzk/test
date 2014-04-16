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

@WebServlet("/user/update.bit")
@SuppressWarnings("serial")
public class UserUpdateServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자변경</title></head><body>");
		
		try {
			int no = Integer.parseInt(request.getParameter("no"));
			UserDao dao = (UserDao) this.getServletContext().getAttribute("userDao");
			UserVo vo = dao.detail(no);
			
			
			// === null 찍힘 방지 
			if (vo.getFax() == null) {
				vo.setFax("");
			}
			if (vo.getPostno() == null) {
				vo.setPostno("");
			}
			if (vo.getAddr() == null) {
				vo.setAddr("");
			}
			// ===================
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>사용자 입력폼</title>");
			out.println("<style type='text/css'>");
			out.println("body{");
			out.println("background: #FFC3FA;");
			out.println("font-family: dotum;");
			out.println("}");

			out.println("#all{");
			out.println("background:#FFA6C5;");
			out.println("height: 380px;");
			out.println("width: 400px;");
			out.println("	padding : 10px;");
			out.println("margin-left : 400px;");
			out.println("margin-top : 200px;");
			out.println("text-align : center;");

			out.println("}");

			out.println("#input{");
			out.println("text-color: #AD19EC;");
			out.println("font-weight: bold;");
			out.println("padding-top: 20px;");
			out.println("padding-bottom : 20px;");
			out.println("font-size: 30px;");
			out.println("}");

			out.println("#insert{");
			out.println("text-align : left;");
			out.println("padding-left: 85px;");
			out.println("}");


			out.println("	#no{");
			out.println("margin-right:40px;");
			out.println("}");
			
			out.println("	#email{");
			out.println("margin-right:25px;");
			out.println("}");

			out.println("#pwd{");
			out.println("margin-right:40px;");
			out.println("}");

			out.println("#name{");
			out.println("margin-right:40px;");
			out.println("}");

			out.println("#tel{");
			out.println("margin-right:10px;");
			out.println("}");

			out.println("#fax{");
			out.println("margin-right:40px;");
			out.println("}");

			out.println("#postno{");
			out.println("margin-right:10px;");
			out.println("}");


			
			
			out.println("#addr{");
			out.println("margin-right:40px;");
			out.println("}");
			out.println(".a{");
			out.println("margin-bottom: 7px;");
			out.println("}");

			out.println("#botton{");
			out.println("text-align: right;");
			out.println("padding-right: 75px;");
			out.println("}");
			out.println("</style>");

			out.println("</head>");
			out.println("<body>");

			out.println("<div id=all>");
			out.println("<div id='input'>사용자 입력</div>");
			out.println("<form action='update.bit' method='post' id= insert>");

			out.println("<div class=a><span id= no>번호: </span>  <input type='text' name='no' value='"
					+ vo.getNo()
					+ "' readonly><br>");
			out.println("<div class=a><span id= email>이메일:</span> <input   type='text' name='email'  value='"
					+ vo.getEmail()
					+ "'><br></div>");
			out.println("<div class=a><span id= pwd>암호: </span>   <input  type='text' name='pwd' value='"
					+ vo.getPwd()
					+ "'><br></div>");
			out.println("<div class=a><span id= name>이름: </span>    <input  type='text' name='name' value='"
					+ vo.getName()
					+ "'><br></div>");
			out.println("<div class=a><span id= tel>전화번호: </span><input  type='text' name='tel' value='"
					+ vo.getTel()
					+ "'><br></div>");
			out.println("<div class=a><span id= fax>팩스: </span>   <input  type='text' name='fax' value='"
					+ vo.getFax()
					+ "'><br></div>");
			out.println("<div class=a><span id= postno>우편번호:</span> <input  type='text' name='postno' value='"
					+ vo.getPostno()
					+ "'><br></div>");
			out.println("<div class=a><span id= addr>주소: </span>   <input  type='text' name='addr' value='"
					+ vo.getAddr()
					+ "'><br></div>");
			out.println("<div id = 'botton'>");
			out.println("<input type='submit' value='등록'>");
			out.println("<input type='reset' value='취소'>");
			out.println("</div>");
			out.println("</form>");

			out.println("</div>");
			out.println("</body>");
			out.println("</html>");
			
			
			
			
		/*	
			
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<meta charset='UTF-8'>");
			out.println("<title>사용자 변경폼</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>사용자 변경</h1>");
			out.println("<form action='update.bit' method='post'>");
			out.println("번호 : <input type='text' name='no' value='"
					+ vo.getNo()
					+ "' readonly><br>");
			out.println("이메일: <input type='text' name='email' value='"
					+ vo.getEmail()
					+ "'><br>");
			out.println("암호: <input type='text' name='pwd' value='"
					+ vo.getPwd()
					+ "'><br>");
			out.println("이름: <input type='text' name='name' value='"
					+ vo.getName()
					+ "'><br>");
			out.println("전화번호: <input type='text' name='tel' value='"
					+ vo.getTel()
					+ "'><br>");
			out.println("팩스: <input type='text' name='fax' value='"
					+ vo.getFax()
					+ "'><br>");
			out.println("우편번호: <input type='text' name='postno' value='"
					+ vo.getPostno()
					+ "'><br>");
			out.println("주소: <input type='text' name='addr' value='"
					+ vo.getAddr()
					+ "'><br>");
			out.println("<input type='submit' value='변경'>");
			out.println("<input type='button' value='취소'");
			out.println("onclick=\"location.href='detail.bit?no="
					+ vo.getNo()
					+ "'\">");
			out.println("</form>");
			out.println("</body>");
			out.println("</html>");
			*/
			
		}	catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");

		
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<html><head><title>사용자변경</title></head><body>");
		
		try {
			out.println("<h1>사용자 변경 결과</h1>");
			
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
			
			out.println("변경 성공!");
			
			response.sendRedirect("detail.bit?no=" + vo.getNo());
			out.println("</table>");
		}	catch (Throwable e) {
			out.println("오류 발생 했음!");
			e.printStackTrace();
		}
		out.println("</body></html>");
	}
}
