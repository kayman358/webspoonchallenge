/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.snippet.CodeChallenge.model;

import java.util.Date;

/**
 *
 * @author Arowolo Ibrahim K
 */
public class RecipeObject {
    private String url;
    private long like = 0;
    private String name;
    private Date expires_in;
    private String snippet;

    public String getUrl() {
        return url;
    }
    
    public void setUrl(String url) {
        this.url = url;
    }

    public long getLike() {
        return like;
    }

    public void setLike(long like) {
        this.like = like;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Date expires_in) {
        this.expires_in = expires_in;
    }

    public String getSnippet() {
        return snippet;
    }

    public void setSnippet(String snippet) {
        this.snippet = snippet;
    }
    
    
}
