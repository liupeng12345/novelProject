package novel.spider.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileFilter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;


public final class NovelSpiderUtil {
    private static final Map<NovelSiteEnum,Map<String,String>> CONTEXT_MAP=new HashMap<>();
    static {
    	init();
    }
	private NovelSpiderUtil() {}

	private static  void init(){
    	SAXReader reader =new SAXReader();
    	try
    	{
    		Document doc=reader.read(new File("conf/Spider-Rule.xml"));
    		Element root =doc.getRootElement();
    		List<Element> sites=root.elements("site");
    		for(Element site:sites)
    		{
    			List<Element> subs=site.elements();
    		
    			Map<String,String> subMap=new HashMap();
    			for(Element sub:subs)
    			{
    				String name =sub.getName();
    		
    				String text=sub.getTextTrim();
    				subMap.put(name, text);
    			}
    			
    		CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")),subMap);
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.toString());
		}
    }
    /**
     * 拿到对应网站的解析规则
     * @param novelSiteEnum
     * @return
     */
   public static Map<String,String> getContext(NovelSiteEnum novelSiteEnum)
   {
	   return CONTEXT_MAP.get(novelSiteEnum);
	   
   }
	public static void getFilesToFile(String url,boolean delete ) {
		// TODO Auto-generated constructor stub
		PrintWriter out=null;
		try {
			File file = new File(url);
			List<File> files = new ArrayList<File>();
			File[] files2=file.listFiles(new FileFilter() {
				
				@Override
				public boolean accept(File pathname) {
					// TODO Auto-generated method stub
					return pathname.toString().endsWith(".txt");
				
				}
			} );
			for(File file2:files2)
			{
				files.add(file2);
			}
			files.sort(new Comparator<File>() {

				@Override
				public int compare(File o1, File o2) {
					// TODO Auto-generated method stub
					System.out.println( o1.getName());
					if(o1.getName().length()==o2.getName().length())
					return o1.getName().compareTo(o2.getName());
					return Long.compare(o1.getName().length(), o2.getName().length());
				}
				
			});
			String path=url+"/"+"mian/";
			new File(path).mkdirs();
			 out=new PrintWriter(new FileWriter(path+""+new File(+new Date().getTime()+".txt")));
			 System.out.println(files.size());
			for(int i=0;i<files.size();i++)
			{
				
				BufferedReader reader=new BufferedReader(new FileReader(files.get(i)));
				String text=null;
				while((text=reader.readLine())!=null)
				{
				out.println(text);		
				}
				reader.close();
				if(delete)
				{
					files.get(i).delete();
				}
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			out.close();
		}
		
	}
	public static int getNovelstatus(String status)
	{if(status.contains("连载"))
		return 1;
	else if(status.contains("完结")||status.contains("完本"))
	{
		return 2;
	}
	else
		return 3;
	}
    public static Date getDate(String dateStr,String pattern) throws ParseException
    {SimpleDateFormat dateFormat=new SimpleDateFormat(pattern);
     Date date=dateFormat.parse(dateStr);
	return date;
    	
    }
	public static void setConfigPath(String path)
    {
    	SAXReader reader =new SAXReader();
    	try
    	{
    		Document doc=reader.read(new File(path));
    		Element root =doc.getRootElement();
    		List<Element> sites=root.elements("site");
    		for(Element site:sites)
    		{
    			List<Element> subs=site.elements();
    		
    			Map<String,String> subMap=new HashMap();
    			for(Element sub:subs)
    			{
    				String name =sub.getName();
    		
    				String text=sub.getTextTrim();
    				subMap.put(name, text);
    			}
    			
    		CONTEXT_MAP.put(NovelSiteEnum.getEnumByUrl(subMap.get("url")),subMap);
    		}
    	}
    	catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.toString());
		}
    	
    }
}
	
