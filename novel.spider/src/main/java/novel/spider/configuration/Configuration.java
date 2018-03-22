package novel.spider.configuration;

import java.io.Serializable;

public class Configuration implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1229699157478643704L;
	public static final int DEFAULT_SIZE = 100;
	public static final int DEFAULT_TRYTIME = 3;
	public Configuration() {

		this.size = DEFAULT_SIZE;
		this.tryTime=DEFAULT_TRYTIME;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * 下载路径
	 */
	private String path;
	/**
	 * 线程的下载章节数
	 */
	private int size;
	private int tryTime;

	public int getTryTime() {
		return tryTime;
	}

	public void setTryTime(int tryTime) {
		this.tryTime = tryTime;
	}
}
