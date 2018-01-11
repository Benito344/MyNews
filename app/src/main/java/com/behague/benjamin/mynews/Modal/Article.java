package com.behague.benjamin.mynews.Modal;

import java.io.Serializable;

/**
 * Created by Benjamin BEHAGUE on 08/01/2018.
 */

public class Article implements Serializable {

    private String section;
    private String title;
    private String abstact;
    private String articleUrl;
    private String publishedDate;
    private String imageUrl;

    public void setSection (String section){
        this.section = section;
    }

    public String getSection() {
        return section;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setAbstact(String abstact) {
        this.abstact = abstact;
    }

    public String getAbstact() {
        return abstact;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
