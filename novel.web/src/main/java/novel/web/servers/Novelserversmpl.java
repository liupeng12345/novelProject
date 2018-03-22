package novel.web.servers;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import novel.spider.entitys.Novel;
import novel.web.mapper.NovelMapper;
@Service
public class Novelserversmpl implements Novelservers {
    @Resource
	private  NovelMapper  mapper;
	@Override
	public List<Novel> getsNovelBYKeyword(String keyword) {
		// TODO Auto-generated method stub
		keyword="%"+keyword+"%";
		System.out.println(keyword);
        return mapper.getsNovelBYKeyword(keyword);
	}		

}
