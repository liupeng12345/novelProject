package novel.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.entitys.Novel;
import novel.spider.impl.chapter.DefaultChapterDetailSpader;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;

import novel.spider.util.NovelSpiderUtil;
import novel.web.entitys.JSONResponse;
import novel.web.entitys.NovelList;
import novel.web.entitys.User;
import novel.web.servers.Novelserversmpl;
import novel.web.servers.Userserversmpl;

@Controller

public class NovelController extends BaseController {
	@Resource
	private Novelserversmpl novelserversmpl;
	@Resource
	private Userserversmpl userserversmpl;
	static {
		NovelSpiderUtil.setConfigPath("G:/conf/Spider-Rule.xml");
	}

	@RequestMapping(value = "/chapter.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getChapter(String url) {
		IChapterSpider spider = new DefaultChapterSpider();
		List<Chapter> chapters = spider.getChapter(url);

		return JSONResponse.succcess(chapters);
	}

	@RequestMapping(value = "/text.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse gettext(String url) {
		IChapterDetailSpider spider = new DefaultChapterDetailSpader();
		ChapterDetail chapterDetail = spider.getChapterDetail(url);

		return JSONResponse.succcess(chapterDetail);
	}

	@RequestMapping(value = "/novel.do", method = RequestMethod.POST)
	@ResponseBody
	public JSONResponse getNovelByKeyword(String keyword, int page) throws UnsupportedEncodingException {
		List<Novel> list = novelserversmpl.getsNovelBYKeyword(keyword);
		System.out.println(list.size());
		NovelList lists = new NovelList();
		ArrayList<Novel> ls = new ArrayList<>();
		for (int i = page * 18; i < page * 18 + 18 && i != list.size() - 1; i++) {
			ls.add(list.get(i));
		}
		lists.setPage((page + 1) % ((list.size() % 18 == 0) ? list.size() / 18 - 1 : list.size() / 18));
		lists.setNovels(ls);
		return JSONResponse.succcess(lists);
	}

	
}
