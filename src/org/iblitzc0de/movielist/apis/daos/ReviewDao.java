package org.iblitzc0de.movielist.apis.daos;

import org.iblitzc0de.movielist.provider.review.ReviewCursor;

public class ReviewDao {
    private String author;
    private String content;
    private String id;
    private String url;

    public ReviewDao(ReviewCursor c) {
        this.id = c.getReviewId();
        this.author = c.getAuthor();
        this.content = c.getContent();
        this.url = c.getUrl();
    }

    public String getId() {
        return this.id;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getContent() {
        return this.content;
    }

    public String getUrl() {
        return this.url;
    }
}
