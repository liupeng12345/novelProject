package novel.web.servers;

import novel.web.entitys.User;

public interface Userservers {
	public  User getUser(String username);
	public  int insert(User user); 
}
