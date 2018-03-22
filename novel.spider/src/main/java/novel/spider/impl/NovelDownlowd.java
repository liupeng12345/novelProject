package novel.spider.impl;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import novel.spider.configuration.Configuration;
import novel.spider.entitys.Chapter;
import novel.spider.entitys.ChapterDetail;
import novel.spider.impl.chapter.DefaultChapterDetailSpader;
import novel.spider.impl.chapter.DefaultChapterSpider;
import novel.spider.interfaces.IChapterDetailSpider;
import novel.spider.interfaces.IChapterSpider;
import novel.spider.interfaces.INovelDownlowd;
import novel.spider.util.NovelSiteEnum;

public class NovelDownlowd implements INovelDownlowd {
	@Override
	public String download(String url,Configuration config) {
		IChapterSpider chapterSpider=null;
		// TODO Auto-generated method stub
		chapterSpider=new DefaultChapterSpider();
		List<Chapter> chapter=chapterSpider.getChapter(url);
		int size =config.getSize();
		int maxThreadSize=(int)Math.ceil(chapter.size()*1.0/size);
		Map<String,List<Chapter>> downloadTaskAlloc=new HashMap<>();
		for(int i=0;i<maxThreadSize;i++)
		{
		  //i=0;0-100;
			//i=1;101-200;
			int fromIndex=i*config.getSize();
			if(i==maxThreadSize-1)
			{
				int toIndex=chapter.size()-1;
			}
			int toIndex=i==maxThreadSize-1?chapter.size():i*config.getSize()+config.getSize();
			downloadTaskAlloc.put(fromIndex+"-"+toIndex,chapter.subList(fromIndex, toIndex) );
		}
		ExecutorService service=Executors.newFixedThreadPool(maxThreadSize);
		Set<String> keySet=downloadTaskAlloc.keySet();
		List<Future<String>> tasks=new ArrayList<>();
		for(String key:keySet)
		{
			tasks.add(service.submit(new DownloadCallable(config.getPath()+"/"+key+".txt", downloadTaskAlloc.get(key),config.getTryTime())));
		}
		service.shutdown();
		for(Future<String> future:tasks )
		{
			try {
				System.out.println(future.get()+"下载完成！");
			} catch (InterruptedException | ExecutionException e) {
				// TODO Auto-generated catch block
				System.out.println(e.toString());
				e.printStackTrace();
			}
		}
		return null;
	}

}
class DownloadCallable implements Callable<String>
{
  private List<Chapter> chapters;
  private String path;
  private int  tryTime;
   public DownloadCallable(String path,List<Chapter> chapters,int tryTime) {
	// TODO Auto-generated constructor stub
	   this.chapters=chapters;
	   this.path=path;
	   this.tryTime=tryTime;
	   
}
	@Override
	public String call() throws Exception {
		// TODO Auto-generated method stub
		try (PrintWriter out=new PrintWriter(new File(path));){
			
			for(Chapter chapter:chapters)
			{
				IChapterDetailSpider sDetailSpider=new DefaultChapterDetailSpader();
	            ChapterDetail  chapterDetail=null;
	            boolean flag=false;
	            for(int i=0;i<3;i++)
	            { try
	              { 
		             if( (chapterDetail=sDetailSpider.getChapterDetail(chapter.getUrl()))!=null)
		             { out.println(chapterDetail.getTitle());
		              out.println(chapterDetail.getContent().replace("\n", "\r\n"));
		              flag=true;
		              break;
		              }
		             
		            }
		            catch(RuntimeException e){
		            	System.out.println("尝试第"+(i+1)+"次");
		            }
		        }
	            if(!flag)
	            {
	            	System.out.println(chapter.getTitle()+"下载失败");
	            }
	           
			}
		} catch (IOException e) {
			// TODO: handle exception
			throw new RuntimeException(e);
		}
		
		return path;
	}
	}