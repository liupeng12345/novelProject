package novel.web.entitys;

import java.util.ArrayList;
import java.util.List;

import novel.spider.entitys.Novel;

public class NovelList {
	private ArrayList<Novel> novels;
	@Override
	public String toString() {
		return "NovelList [novels=" + novels + ", page=" + page + "]";
	}
	private int page;
	public List<Novel> getNovels() {
		return novels;
	}
	public void setNovels(ArrayList<Novel> novels) {
		this.novels = novels;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}

}
