package impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import novel.spider.entitys.Novel;
import novel.spider.impl.novel.BxwxNovelSpider;
import sun.util.locale.provider.SPILocaleProviderAdapter;

public class BxwxNovelStorage implements Processor {
private static final Map<String, String> TASKS=new TreeMap<>();
private SqlSessionFactory sessionFactory;

public BxwxNovelStorage() throws FileNotFoundException {
	
	sessionFactory =  new SqlSessionFactoryBuilder().build(new FileInputStream("conf/SqlMapConfig.xml")) ;
}
static {
	TASKS.put("0", "http://www.bxwx9.org/binitial1/0/1.htm");
	TASKS.put("A", "http://www.bxwx9.org/binitialA/0/1.htm");
	TASKS.put("B", "http://www.bxwx9.org/binitialB/0/1.htm");
	TASKS.put("C", "http://www.bxwx9.org/binitialC/0/1.htm");
	TASKS.put("D", "http://www.bxwx9.org/binitialD/0/1.htm");
	TASKS.put("E", "http://www.bxwx9.org/binitialE/0/1.htm");
	TASKS.put("F", "http://www.bxwx9.org/binitialF/0/1.htm");
	TASKS.put("G", "http://www.bxwx9.org/binitialG/0/1.htm");
	TASKS.put("H", "http://www.bxwx9.org/binitialH/0/1.htm");
	TASKS.put("I", "http://www.bxwx9.org/binitialI/0/1.htm");
	TASKS.put("J", "http://www.bxwx9.org/binitialJ/0/1.htm");
	TASKS.put("K", "http://www.bxwx9.org/binitialK/0/1.htm");
	TASKS.put("L", "http://www.bxwx9.org/binitialL/0/1.htm");
	TASKS.put("M", "http://www.bxwx9.org/binitialM/0/1.htm");
	TASKS.put("N", "http://www.bxwx9.org/binitialN/0/1.htm");
	TASKS.put("O", "http://www.bxwx9.org/binitialO/0/1.htm");
	TASKS.put("P", "http://www.bxwx9.org/binitialP/0/1.htm");
	TASKS.put("Q", "http://www.bxwx9.org/binitialQ/0/1.htm");
	TASKS.put("R", "http://www.bxwx9.org/binitialR/0/1.htm");
	TASKS.put("S", "http://www.bxwx9.org/binitialS/0/1.htm");
	TASKS.put("T", "http://www.bxwx9.org/binitialT/0/1.htm");
	TASKS.put("U", "http://www.bxwx9.org/binitialU/0/1.htm");
	TASKS.put("V", "http://www.bxwx9.org/binitialV/0/1.htm");
	TASKS.put("W", "http://www.bxwx9.org/binitialW/0/1.htm");
	TASKS.put("X", "http://www.bxwx9.org/binitialX/0/1.htm");
	TASKS.put("Y", "http://www.bxwx9.org/binitialY/0/1.htm");
	TASKS.put("Z", "http://www.bxwx9.org/binitialZ/0/1.htm");
}
	@Override
	public void process() {
		// TODO Auto-generated method stub
		ExecutorService service=Executors.newFixedThreadPool(TASKS.size());
		List<Future<String>> futures=new ArrayList<>();
		for(Entry<String, String> entry: TASKS.entrySet())
		{
			String key =entry.getKey();
			String value=entry.getValue();
			futures.add(service.submit(new Callable<String>() {

				@Override
				public String call() throws Exception {
					// TODO Auto-generated method stub
					BxwxNovelSpider spider=new BxwxNovelSpider();
					Iterator<List<Novel>> iterator= spider.iterator(value,100);
					while(iterator.hasNext())
					{
						System.err.println("开始抓取"+key+ spider.next());
						List<Novel> novels =iterator.next();
						SqlSession session=sessionFactory.openSession();
						for(Novel novel:novels)
						{
							novel.setFristLetter(entry.getKey().toString());
							try {
							session.insert("insert",novel);
							}
							catch(Exception e){
								session.update("updateByPrimaryKey",novel);
								System.err.println(novel.getName()+"已更新");
							}
						}
						session.commit();
						Thread.sleep(1);
					}
					return key;
					
				}
			}));
			
		}
	  service.shutdown();
	  for(Future<String> future:futures)
	  {
		  try {
			  System.out.println("抓取"+future.get()+"完");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	  }
	}

}
