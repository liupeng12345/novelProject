package novel.web.servers;

import java.util.List;

import novel.spider.entitys.Novel;

public interface Novelservers {
    public List<Novel> getsNovelBYKeyword(String keyword);
}
