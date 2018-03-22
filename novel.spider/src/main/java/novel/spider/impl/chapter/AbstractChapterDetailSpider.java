package novel.spider.impl.chapter;

import java.util.Map;

import javax.xml.soap.Detail;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public abstract class AbstractChapterDetailSpider extends AbstractSpider implements IChapterDetailSpider  {
   
	@Override
	public ChapterDetail getChapterDetail(String url) {
		try {
			String result=super.crawl(url);
			result=result.replace("&nbsp;","  ").replace("<br />", "${line}").replace("<br/>", "${line}");
			Document doc =Jsoup.parse(result);
			doc.setBaseUri(url);
			
			 Map<String, String> contexts=NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			 //ÄÇ±êÌâ
			 String titleSelector=contexts.get("chapter-detail-title-selector");
			 String[] splits=titleSelector.split("\\,");
			 splits=parseSelector(splits);
			 ChapterDetail detail=new ChapterDetail();
			 detail.setTitle(doc.select(splits[0]).get(Integer.parseInt(splits[1])).text());
			 
			 //
			 String contentSelector =contexts.get("chapter-detail-content-selector");
			 detail.setContent(doc.select(contentSelector).first().text().replace("${line}", "\n"));
			 
			 //
			 String prevSelector=contexts.get("chapter-detail-prev-selector");
			 splits=prevSelector.split("\\,");
			 splits=parseSelector(splits);
			 detail.setPrev(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			 //
			 String nextSelector=contexts.get("chapter-datail-next-selector");
			 splits=nextSelector.split("\\,");
			 splits=parseSelector(splits);
			 detail.setNext(doc.select(splits[0]).get(Integer.parseInt(splits[1])).absUrl("href"));
			 return detail;
		} catch (Exception e) {
			// TODO: handle exception
		
			throw new RuntimeException(e);
		}
		// TODO Auto-generated method stub
		
	}
    private String[] parseSelector(String[] splits )
    {
    	String[] newSplist=new String[2];
    	if(splits.length==1)
    	{
    		newSplist[0]=splits[0];
    		newSplist[1]="0";
    		return newSplist;
    	}else
    	{
    		return splits;
    	}
    }
}
