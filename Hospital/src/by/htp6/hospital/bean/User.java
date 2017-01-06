package by.htp6.hospital.bean;

public class User {
	private int id;

	private String username;
	
	private String password;
	
	private String type;
	
	private String createTime;
	
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public User(){}
	
	public User(int id, String username, String password, String type,
			String createTime){
		this.id = id;
		this.username = username;
		this.type = type;
		this.password = password;
		this.createTime = createTime;
	}
}
