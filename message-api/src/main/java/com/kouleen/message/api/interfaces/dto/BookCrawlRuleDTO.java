package com.kouleen.message.api.interfaces.dto;

import java.util.Map;

/**
 * @author zhangqing
 * @since 2023/8/4 9:33
 */
public class BookCrawlRuleDTO {

    /**
     * 小说更新列表url
     * */
    private String updateBookListUrl;

    /**
     * 分类列表页URL规则
     * */
    private String bookListUrl;

    private Map<String,String> catIdRule;

    private Map<String,Byte> bookStatusRule;

    private String bookIdPatten;
    private String pagePatten;
    private String totalPagePatten;
    private String bookDetailUrl;
    private String bookNamePatten;
    private String authorNamePatten;
    private String picUrlPatten;
    private String statusPatten;
    private String scorePatten;
    private String visitCountPatten;
    private String descStart;
    private String descEnd;
    private String upadateTimePatten;
    private String upadateTimeFormatPatten;
    private String bookIndexUrl;
    private String indexIdPatten;
    private String indexNamePatten;
    private String bookContentUrl;
    private String contentStart;
    private String contentEnd;


    private String picUrlPrefix;

    private String bookIndexStart;

    public String getUpdateBookListUrl() {
        return updateBookListUrl;
    }

    public void setUpdateBookListUrl(String updateBookListUrl) {
        this.updateBookListUrl = updateBookListUrl;
    }

    public String getBookListUrl() {
        return bookListUrl;
    }

    public void setBookListUrl(String bookListUrl) {
        this.bookListUrl = bookListUrl;
    }

    public Map<String, String> getCatIdRule() {
        return catIdRule;
    }

    public void setCatIdRule(Map<String, String> catIdRule) {
        this.catIdRule = catIdRule;
    }

    public Map<String, Byte> getBookStatusRule() {
        return bookStatusRule;
    }

    public void setBookStatusRule(Map<String, Byte> bookStatusRule) {
        this.bookStatusRule = bookStatusRule;
    }

    public String getBookIdPatten() {
        return bookIdPatten;
    }

    public void setBookIdPatten(String bookIdPatten) {
        this.bookIdPatten = bookIdPatten;
    }

    public String getPagePatten() {
        return pagePatten;
    }

    public void setPagePatten(String pagePatten) {
        this.pagePatten = pagePatten;
    }

    public String getTotalPagePatten() {
        return totalPagePatten;
    }

    public void setTotalPagePatten(String totalPagePatten) {
        this.totalPagePatten = totalPagePatten;
    }

    public String getBookDetailUrl() {
        return bookDetailUrl;
    }

    public void setBookDetailUrl(String bookDetailUrl) {
        this.bookDetailUrl = bookDetailUrl;
    }

    public String getBookNamePatten() {
        return bookNamePatten;
    }

    public void setBookNamePatten(String bookNamePatten) {
        this.bookNamePatten = bookNamePatten;
    }

    public String getAuthorNamePatten() {
        return authorNamePatten;
    }

    public void setAuthorNamePatten(String authorNamePatten) {
        this.authorNamePatten = authorNamePatten;
    }

    public String getPicUrlPatten() {
        return picUrlPatten;
    }

    public void setPicUrlPatten(String picUrlPatten) {
        this.picUrlPatten = picUrlPatten;
    }

    public String getStatusPatten() {
        return statusPatten;
    }

    public void setStatusPatten(String statusPatten) {
        this.statusPatten = statusPatten;
    }

    public String getScorePatten() {
        return scorePatten;
    }

    public void setScorePatten(String scorePatten) {
        this.scorePatten = scorePatten;
    }

    public String getVisitCountPatten() {
        return visitCountPatten;
    }

    public void setVisitCountPatten(String visitCountPatten) {
        this.visitCountPatten = visitCountPatten;
    }

    public String getDescStart() {
        return descStart;
    }

    public void setDescStart(String descStart) {
        this.descStart = descStart;
    }

    public String getDescEnd() {
        return descEnd;
    }

    public void setDescEnd(String descEnd) {
        this.descEnd = descEnd;
    }

    public String getUpadateTimePatten() {
        return upadateTimePatten;
    }

    public void setUpadateTimePatten(String upadateTimePatten) {
        this.upadateTimePatten = upadateTimePatten;
    }

    public String getUpadateTimeFormatPatten() {
        return upadateTimeFormatPatten;
    }

    public void setUpadateTimeFormatPatten(String upadateTimeFormatPatten) {
        this.upadateTimeFormatPatten = upadateTimeFormatPatten;
    }

    public String getBookIndexUrl() {
        return bookIndexUrl;
    }

    public void setBookIndexUrl(String bookIndexUrl) {
        this.bookIndexUrl = bookIndexUrl;
    }

    public String getIndexIdPatten() {
        return indexIdPatten;
    }

    public void setIndexIdPatten(String indexIdPatten) {
        this.indexIdPatten = indexIdPatten;
    }

    public String getIndexNamePatten() {
        return indexNamePatten;
    }

    public void setIndexNamePatten(String indexNamePatten) {
        this.indexNamePatten = indexNamePatten;
    }

    public String getBookContentUrl() {
        return bookContentUrl;
    }

    public void setBookContentUrl(String bookContentUrl) {
        this.bookContentUrl = bookContentUrl;
    }

    public String getContentStart() {
        return contentStart;
    }

    public void setContentStart(String contentStart) {
        this.contentStart = contentStart;
    }

    public String getContentEnd() {
        return contentEnd;
    }

    public void setContentEnd(String contentEnd) {
        this.contentEnd = contentEnd;
    }

    public String getPicUrlPrefix() {
        return picUrlPrefix;
    }

    public void setPicUrlPrefix(String picUrlPrefix) {
        this.picUrlPrefix = picUrlPrefix;
    }

    public String getBookIndexStart() {
        return bookIndexStart;
    }

    public void setBookIndexStart(String bookIndexStart) {
        this.bookIndexStart = bookIndexStart;
    }
}
