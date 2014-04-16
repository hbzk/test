package course;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;

/* Pooling 기법
 * - 적은 자원을 효율적으로 사용
 * - 미리 자원을 준비한 후 필요한 곳에 빌려줌
 * - 자원을 사용하고 난 다음에는 반납함.
 * 
 * DB Connection Pooling
 * - Connection이 필요할 때 생성 후 대여해 줌
 * - 사용 후 '닫지 않고' 반납 함.
 * - 기존에 커넥션이 있으면 기존 커넥션을 대여해 줌
 *  	=> DBMS와 연결하는 시간을 줄일 수 있다.
 */
public class DBConnectionPool {
	ArrayList<Connection> list = new ArrayList<Connection>();
	
	// > 여러 종류의 컨넥션을 관리 할 목적이라면 static 메서드로 만들면 안된다.
	public Connection getConnection() throws Exception {
		if (list.size() > 0) { // > 있으면 풀에서 빼고 대여
			return list.remove(0);
			// Connection con = list.get(0);
			// list.remove(0);
			// return con;
		}	else { // > 없으면 생성
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://192.168.200.45:3306/studydb", "study", "study");
		}
	}
	
	public void returnConnection(Connection con) {
		try {
			if (!con.isClosed()) { 	// > 닫히지 않았다면
				list.add(con); 	// > 풀에 넣는다.
			}
		}	catch (Exception e) {}
	}
	
	public void closeAll() {
		for (Connection con : list) {
			try {con.close();} catch (Exception e) {}
		}
	}
}
