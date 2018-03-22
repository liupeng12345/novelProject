import org.apache.ibatis.session.SqlSessionFactory;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

import impl.BxwxNovelStorage;
import impl.Processor;
import mapper.NovelMapper;
import novel.spider.entitys.Novel;


public class te{
	private SqlSessionFactory sessionFactory;
	@Test
	public void t2() {
		Processor processor=null;
		try {
			processor = new BxwxNovelStorage();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		processor.process();
	}
	@Test
	public void t1()
	{
		NovelMapper mapp=null;
		List<Novel> novels=mapp.getsNovelBYKeyword("%");
	for (Novel novel : novels) {
		System.out.println(novel.toString());
	}
		
	}
}