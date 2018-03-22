package novel.spider.junit;

import java.util.Iterator;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.Novel;
import novel.spider.impl.NovelDownlowd;
import novel.spider.impl.chapter.AbstractChapterSpider;
import novel.spider.impl.chapter.DefaultChapterDetailSpader;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.impl.novel.BxwxNovelSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownlowd;
import novel.spider.interfaces.INoverlSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public class testcase extends AbstractChapterSpider {
	@Test
	public void testGetChapters() throws Exception {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getChapter("http://www.bxwx9.org/b/70/70093/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}
    @Test 
    public void ts() {
       try {
    	   String result= crawl("http://www.bxwx9.org/b/70/70093/");
	
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	
    }
	@Test
	public void testGetContext() {
		System.out.println(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl("http://biquge.tw/0_5/")));
	}

	@Test
	public void testGetChapterDetail() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpader();
		System.out.println(spider.getChapterDetail("http://biquge.tw/0_5/1374.html"));
	}

	@Test
	public void testgetchapter2() {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getChapter("http://www.bxwx9.org/b/57/57181/");
		for (Chapter chapter : chapters) {
			System.out.println(chapter);
		}
	}

	@Test
	public void testgetchapterDetail() {
		IChapterDetailSpider spider = new DefaultChapterDetailSpader();
		System.out.println(spider.getChapterDetail("http://www.bxwx9.org/b/70/70093/39125128.html"));
	}
    @Test
    public void testDownload()
    {INovelDownlowd downlowd=new NovelDownlowd();
    Configuration configuration=new Configuration(); 
   
    configuration.setSize(40);
    configuration.setPath("f:/3");
    downlowd.download("http://www.bxwx9.org/b/7/7115/index.html", configuration);
    NovelSpiderUtil.getFilesToFile(configuration.getPath(), true);
    }
    @Test
    public void testBxwxGetsNovel()
    { INoverlSpider iNoverlSpider=new BxwxNovelSpider();
       List<Novel> novels=iNoverlSpider.getsNovel("http://www.bxwx9.org/binitialR/0/36.htm");
       for(Novel novel:novels )
       {
    	   System.out.println(novel.toString());
       }
    	
    }
    @Test
    public void testlist() {
    	INoverlSpider spider =new BxwxNovelSpider();
        Iterator<List<Novel>> iterator=spider.iterator("http://www.bxwx9.org/binitialR/0/36.htm",60);
        while(iterator.hasNext())
        {
        	List<Novel> novels=iterator.next();
        	System.err.println("URL:"+spider.next());
        	
        }
    }

}
