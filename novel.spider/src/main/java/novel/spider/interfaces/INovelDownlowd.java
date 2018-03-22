package novel.spider.interfaces;

import novel.spider.configuration.Configuration;

public interface INovelDownlowd {
	/**
	 * 小说下载
	 * @param url 章节列表的网页
	 * @return
	 */
	public String download(String url,Configuration configuration);
	
}


