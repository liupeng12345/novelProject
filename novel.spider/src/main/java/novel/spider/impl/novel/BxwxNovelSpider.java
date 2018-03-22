package novel.spider.impl.novel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import novel.spider.entitys.Novel;
import novel.spider.util.NovelSiteEnum;
import novel.spider.util.NovelSpiderUtil;

public class BxwxNovelSpider extends AbstractNovelSpider {
	public String nexpage = null;

	public BxwxNovelSpider() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Novel> getsNovel(String url) {

		// TODO Auto-generated method stub
		List<Novel> novels = new ArrayList<>();
		try {
			Elements trs = super.getsTr(url);
			for (int index = 1, size = trs.size(); index < size; index++) {

				Element tr = trs.get(index);
				Elements tds = tr.getElementsByTag("td");
				Novel novel = new Novel();

				novel.setName(tds.first().text());

				String novelUrl = tds.first().getElementsByTag("a").first().absUrl("href").replace(".htm", "/")
						.replace("binfo", "b");

				novel.setLastUpdateChapter(tds.get(1).text());

				novel.setLastUpdateUrl(tds.get(1).getElementsByTag("a").first().absUrl("href"));
				novel.setAuthor(tds.get(2).text());
				try {
					novel.setLastUpdateTime(NovelSpiderUtil.getDate(tds.get(4).text(), "yy-MM-dd"));
				} catch (Exception e) {
					continue;
				}
				novel.setStatus(NovelSpiderUtil.getNovelstatus(tds.get(5).text()));
				novel.setPlatformId(NovelSiteEnum.getEnumByUrl(url).getId());
				novel.setUrl(novelUrl);
				if (!novel.isNotNULL())
					continue;
				novels.add(novel);
			}
			if (nextpageElement != null) {
				nexpage = nextpageElement.absUrl("href");
			} else
				nexpage = "";
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			;
		}
		return novels;
	}

	@Override
	public boolean hasNext() {
		// TODO Auto-generated method stub
		return !nexpage.isEmpty();
	}

	@Override
	public String next() {
		// TODO Auto-generated method stub
		return nexpage;
	}

	@Override
	public Iterator<List<Novel>> iterator(String firstPage, Integer maxTryTimes) {
		// TODO Auto-generated method stub
		nexpage = firstPage;
		return new NovelIterrator(maxTryTimes);
	}

	private class NovelIterrator implements Iterator<List<Novel>> {
		public Integer maxTryTimes;

		public NovelIterrator(Integer maxTryTimes) {

			this.maxTryTimes = maxTryTimes;
		}

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return BxwxNovelSpider.this.hasNext();
		}

		@Override
		public List<Novel> next() {
			// TODO Auto-generated method stub
			List<Novel> novels = getsNovel(nexpage);
			return novels;
		}

	}

}
