package novel.spider.entitys;

import java.util.Date;

public class Novel extends NovelKey {
    private String url;

    private String type;

    private String lastUpdateChapter;

    private String lastUpdateUrl;

    private Date lastUpdateTime;

    private Integer status;

    private String fristLetter;

    private Integer platformId;

    private Date addTime;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getLastUpdateChapter() {
        return lastUpdateChapter;
    }

    public void setLastUpdateChapter(String lastUpdateChapter) {
        this.lastUpdateChapter = lastUpdateChapter == null ? null : lastUpdateChapter.trim();
    }

    public String getLastUpdateUrl() {
        return lastUpdateUrl;
    }

    public void setLastUpdateUrl(String lastUpdateUrl) {
        this.lastUpdateUrl = lastUpdateUrl == null ? null : lastUpdateUrl.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFristLetter() {
        return fristLetter;
    }

    public void setFristLetter(String fristLetter) {
        this.fristLetter = fristLetter == null ? null : fristLetter.trim();
    }

    public Integer getPlatformId() {
        return platformId;
    }

    public void setPlatformId(Integer platformId) {
        this.platformId = platformId;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }
    public boolean isNotNULL() {
    	return this.getName()!=null&&this.getAuthor()!=null&&this.url!=null&&this.platformId!=null;
    }
}