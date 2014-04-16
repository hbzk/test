/*package course;

import java.util.List;
import java.util.Scanner;

 1) DBConnectionPool을 준비
 * 2) MysqlSubjectDao에 커넥션 풀을 주입한다.
 
public class CourseMgt {
	static Scanner sc = new Scanner(System.in);
	static CourseDao dao;
	
	public static void TestInsert() throws Throwable {
		dao.insert(new CourseVo()
				.setTitle( "Java의 신")
				.setDescription("자바의 신을 존경할 수 있을 정도의 실력으로 향상시킴."));
		
		System.out.println("등록 성공!!!");
	}
	
	public static void TestList() throws Throwable {
		int pageNo = 1, pageSize = 10;
		
		do {
			List<CourseVo> list = dao.list(pageNo, pageSize);
			System.out.println("[" + pageNo + "]-------------------------------------");
			
			for (CourseVo subject : list) {
				System.out.print(subject.getNo() + ", ");
				System.out.println(subject.getTitle());
			}
			
			pageNo = Integer.parseInt(sc.nextLine());
			
		}	while(pageNo > 0);
		
	}
	
	public static void TestUpdate() throws Throwable {
		
		dao.update(new CourseVo()
				.setNo(114)
				.setTitle( "Java의 신")
				.setDescription("자바의 신을 존경할 수 있을 정도의 실력으로 향상시킴."));
		System.out.println("변경 성공!");
		
	}
	
	public static void TestDelete() throws Throwable {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("삭제할 번호 입력: ");
		dao.delete(Integer.parseInt(sc.nextLine()));
		
		System.out.println("삭제 성공!");
		
		sc.close();
	}
	
	public static void TestDetail() throws Throwable {
		
		System.out.println("불러올 번호 입력 :");
		CourseVo subject = dao.detail(Integer.parseInt(sc.nextLine()));
		
		System.out.println(subject.getNo());
		System.out.println(subject.getTitle());
		System.out.println(subject.getDescription());
		
		sc.close();
	}
	
	public static void main(String[] args) throws Throwable {
		DBConnectionPool dbConnectionPool = new DBConnectionPool();
		dao = new MysqlSubjectDao();
		((MysqlSubjectDao)dao).setDBConnectionPool(dbConnectionPool);
		
		
		
		String command = null;
		
		do {
			try {
				System.out.print("명령>");
				command = sc.nextLine();
				switch (command) {
				case "insert":
					TestInsert(); break;
				case "list":
					TestList(); break;
				case "update":
					TestUpdate(); break;
				case "delete":
					TestDelete(); break;
				case "detail":
					TestDetail(); break;
				}
			}	catch (Throwable e) {} 	// 블록 안에서 예외가 발생하더라도 동작을 멈추지 않는다
		}	while (!"q".equals(command));
		sc.close();
		dbConnectionPool.closeAll();
	}

}
*/