package novel.spider.impl.novel;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entitys.Novel;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.INoverlSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;
/**
 * 一个抽象的小说解析，已经支持tr
 * @author 刘鹏
 *
 */
public abstract class AbstractNovelSpider extends AbstractSpider implements INoverlSpider {
protected Element nextpageElement;
protected Elements getsTr(String url)throws Exception{
	return getsTr(url,INoverlSpider.TRY_TIME);
}
protected Elements getsTr(String url,Integer tryTime)throws Exception{
	tryTime = tryTime==null ?INoverlSpider.TRY_TIME:tryTime;
	Elements trs=null;
	for(int i=0;i<tryTime;i++)
	{
		try {
			String result =super.crawl(url);
			Map<String , String > context=NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url));
			String novelSelector=context.get("novel-selector");
			if(novelSelector==null)
				throw new  RuntimeException(NovelSiteEnum.getEnumByUrl(url).getUrl()+",url="+url+"目前还不被支持");
			Document doc=Jsoup.parse(result);
			doc.setBaseUri(url);
			 trs=doc.select(novelSelector);
			 String nextpage= context.get("novel-next-page-selector");
		        if(nextpage!=null)
		        {
		          Elements neElements= 	doc.select(nextpage);
		          nextpageElement=neElements==null?null:neElements.first();
		        }
			return trs;
			
		}catch (Exception e) {
			// TODO: handle exception
			
		}
		
	}
	throw new RuntimeException(url+"尝试了"+tryTime+"依然失败");
}
	@Override

	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		// TODO Auto-generated method stub
		return null;
	}

}
