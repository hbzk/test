package course;

import java.util.List;

public interface CourseDao {
	void insert(CourseVo subject) throws Throwable;
	List<CourseVo> list(int pageNo, int pageSize) throws Throwable;
	CourseVo detail(int no) throws Throwable;
	void update(CourseVo subject) throws Throwable;
	void delete(int no) throws Throwable;
}


