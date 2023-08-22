package com.kouleen.message.service.domain.book.entity;


import com.kouleen.message.service.infrastructure.core.BaseDomain;

/**
 * @author zhangqing
 * @since 2023/7/16 17:20
 */
public class BookFictionHeader extends BaseDomain {

    public static final String COL_FICTION_NAME = "fiction_name";
    public static final String COL_FICTION_AUTHOR = "fiction_author";
    public static final String COL_FICTION_TYPE = "fiction_type";
    public static final String COL_NEWEST = "newest";
    public static final String COL_FICTION_STATE = "fiction_state";
    public static final String COL_FICTION_INTRODUCE = "fiction_introduce";
    public static final String COL_IMAGE_URL = "image_url";
    public static final String COL_FICTION_URL = "fiction_url";
    public static final String COL_VIEWS = "views";
    public static final String COL_SCORE = "score";
    public static final String COL_WORD_COUNT = "word_count";

    private String fictionName;

    private String fictionAuthor;

    private String fictionType;

    private String newest;

    private String fictionState;

    private String fictionIntroduce;

    private String imageUrl;

    private String fictionUrl;

    private Integer views;

    private Float score;

    private Integer wordCount;

    public String getFictionName() {
        return fictionName;
    }

    public void setFictionName(String fictionName) {
        this.fictionName = fictionName;
    }

    public String getFictionAuthor() {
        return fictionAuthor;
    }

    public void setFictionAuthor(String fictionAuthor) {
        this.fictionAuthor = fictionAuthor;
    }

    public String getFictionType() {
        return fictionType;
    }

    public void setFictionType(String fictionType) {
        this.fictionType = fictionType;
    }

    public String getNewest() {
        return newest;
    }

    public void setNewest(String newest) {
        this.newest = newest;
    }

    public String getFictionState() {
        return fictionState;
    }

    public void setFictionState(String fictionState) {
        this.fictionState = fictionState;
    }

    public String getFictionIntroduce() {
        return fictionIntroduce;
    }

    public void setFictionIntroduce(String fictionIntroduce) {
        this.fictionIntroduce = fictionIntroduce;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getFictionUrl() {
        return fictionUrl;
    }

    public void setFictionUrl(String fictionUrl) {
        this.fictionUrl = fictionUrl;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public Integer getWordCount() {
        return wordCount;
    }

    public void setWordCount(Integer wordCount) {
        this.wordCount = wordCount;
    }
}
