package novel.spider.util;

public enum NovelSiteEnum {

	DingDianXiaoShuo(1,"x23us.com"), 
	BiQuge(2,"biquge.tw"),
	BiXiaWenXue(3,"bxwx9.org"),
	XAS(4,"xaishang.com");
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	private int id;
	private String url;
	private NovelSiteEnum(int id, String url) {
		this.id = id;
		this.url = url;
	}
	public static  NovelSiteEnum getEnumById(int id)
	{
		switch (id) {
		case 1:return DingDianXiaoShuo;
		case 2:return BiQuge;
		default:throw new RuntimeException("id="+id+"是不被支持的小说网站");
		}
	}
    public static NovelSiteEnum getEnumByUrl(String url )
    {
    	for(NovelSiteEnum novelSiteEnum :values() )
    	{
    		if(url.contains(novelSiteEnum.url))
    		{
    	
    			
    			return novelSiteEnum;
    		}
    	}
    	throw new RuntimeException("url="+url+"是不被支持的小说网站");
    }

}
