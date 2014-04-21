package dao;

import java.util.List;

import vo.UserVo;

public interface UserDao {
	void insert(UserVo user) throws Throwable;
	List<UserVo> list(int pageNo, int pageSize) throws Throwable;
	UserVo detail(int no) throws Throwable;
	void update(UserVo user) throws Throwable;
	void delete(int no) throws Throwable;
	
	UserVo getUser(String email, String password)/* throws Exception*/; 	// > 에러 객체를 사용
}


