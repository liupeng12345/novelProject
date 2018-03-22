package novel.spider.impl;

import java.io.BufferedReader;
import java.io.InputStreamReader;




public abstract class AbstractSpider {
	/**
	 * 抓取信息
	 * @param url
	 * @return
	 * @throws Exception
	 */
	 protected String crawl(String url) throws Exception{
//		 CloseableHttpClient httpClient=null;
//		 CloseableHttpResponse httpResponse=null;
//		
//		 try
//	    	{
//	    		httpClient =HttpClientBuilder.create().build();
//    			httpResponse =httpClient.execute(new NovelHttGet(url));
//    			httpResponse.getHeaders(url);
//    			
//	          String result=new String( EntityUtils.toByteArray(httpResponse.getEntity()),"utf-8");
//	    		return result;
//	    		
//	    	}catch (Exception e) {
//				// TODO: handle exception
//	    		System.out.println(e.toString());
//	    		throw new RuntimeException(e);
//			}finally {
//				httpClient.close();
//				httpResponse.close();
//			}
//
		 Process process= Runtime.getRuntime().exec("python  G:\\java_workspace\\novel.spider\\conf\\spider.py "+url);
	     BufferedReader reader =new BufferedReader(new InputStreamReader(process.getInputStream()));
	     String a=null;
	     String s=null;
	     while((a=reader.readLine())!=null)
	     {
	    	 s+=a;
	     }
		 return s;
		 
	 }
}
