package com.blog.vo;

import com.blog.po.Comment;

import java.util.List;

public class TitleComment {
    public String title;
    public List<Comment> comments;

    public TitleComment() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
