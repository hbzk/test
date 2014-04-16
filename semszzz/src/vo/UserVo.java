package vo;

/* setter/getter 적용 <= 캡슐화
 */
public class UserVo {
	private int 			no;
	private String 	email;
	private String 	pwd;
	private String 	name;
	private String 	tel;
	private String 	fax;
	private String 	postno;
	private String 	addr;
	
	
	public int getNo() {
		return no;
	}
	public UserVo setNo(int no) {
		this.no = no;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public UserVo setEmail(String email) {
		this.email = email;
		return this;
	}
	public String getPwd() {
		return pwd;
	}
	public UserVo setPwd(String pwd) {
		this.pwd = pwd;
		return this;
	}
	public String getName() {
		return name;
	}
	public UserVo setName(String name) {
		this.name = name;
		return this;
	}
	public String getTel() {
		return tel;
	}
	public UserVo setTel(String tel) {
		this.tel = tel;
		return this;
	}
	public String getFax() {
		return fax;
	}
	public UserVo setFax(String fax) {
		this.fax = fax;
		return this;
	}
	public String getPostno() {
		return postno;
	}
	public UserVo setPostno(String postno) {
		this.postno = postno;
		return this;
	}
	public String getAddr() {
		return addr;
	}
	public UserVo setAddr(String addr) {
		this.addr = addr;
		return this;
	}
	
}
