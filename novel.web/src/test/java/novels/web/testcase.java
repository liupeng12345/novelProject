package novels.web;




import javax.annotation.Resource;

import org.junit.Test;

import novel.spider.impl.chapter.AbstractChapterSpider;
import novel.web.servers.Novelserversmpl;
import novel.web.servers.Userserversmpl;



public class testcase extends AbstractChapterSpider {
	@Resource	
		private Userserversmpl  userservers;
	@Resource
	private Novelserversmpl novelserversmpl;
	@Test
	public void t1() {
   System.out.println(novelserversmpl.getsNovelBYKeyword("ÉñÄ¹"));	
	}


}
