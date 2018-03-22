package novel.web.servers;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import novel.web.entitys.User;
import novel.web.mapper.UserMapper;


@Service
public class Userserversmpl implements Userservers  {
	  @Resource
	  private  UserMapper  mapper;
	
	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return mapper.getUser(username);
	}

	@Override
	public int insert(User user) {
		// TODO Auto-generated method stub
		return mapper.insert(user);
	}
}
