package novel.spider.interfaces;

import java.util.Iterator;
import java.util.List;

import novel.spider.entitys.Novel;
/**
 * �õ�С˵�б�
 * @author ����
 *
 */
public interface INoverlSpider {
	/**
	 * ���Դ���
	 */
	public static final Integer TRY_TIME=3;
	public boolean hasNext();
	public String next();
	public Iterator<List<Novel>> iterator(String firstPage,Integer maxTryTimes);
    public List<Novel> getsNovel(String url);
}
