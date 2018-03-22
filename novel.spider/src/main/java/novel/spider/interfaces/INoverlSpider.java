package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entitys.Novel;
/**
 * 得到小说列表
 * @author 刘鹏
 *
 */
public interface INoverlSpider {
	/**
	 * 尝试次数
	 */
	public static final Integer TRY_TIME=3;
	public boolean hasNext();
	public String next();
	public Iterator<List<Novel>> iterator(String firstPage,Integer maxTryTimes);
    public List<Novel> getsNovel(String url);
}
