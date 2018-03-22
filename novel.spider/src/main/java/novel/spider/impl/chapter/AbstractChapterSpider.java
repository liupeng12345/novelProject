package novel.spider.impl.chapter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;



import novel.spider.entitys.Chapter;
import novel.spider.impl.AbstractSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public abstract class  AbstractChapterSpider extends AbstractSpider implements IChapterSpider {
   
	@Override
	public List<Chapter> getChapter(String url) {
		// TODO Auto-generated method stub
		try {
			String result= crawl(url);
			Document doc =Jsoup.parse(result);
			doc.setBaseUri(url);
			Elements as= doc.select(NovelSpiderUtil.getContext(NovelSiteEnum.getEnumByUrl(url)).get("chapter-list-selector"));
			List<Chapter> chapters=new ArrayList<>();
			for(Element a:as)
			{
				Chapter chapter=new Chapter();
				chapter.setTitle(a.text());
				chapter.setUrl(a.absUrl("href"));
				chapters.add(chapter);
			}
			chapters.sort(new Comparator<Chapter>() {

				@Override
				public int compare(Chapter o1, Chapter o2) {
					// TODO Auto-generated method stub
					return o1.getUrl().compareTo(o2.getUrl());
				}
			});
			return chapters;
		}catch(Exception e ){
			throw new RuntimeException(e);
		}
	}


}
