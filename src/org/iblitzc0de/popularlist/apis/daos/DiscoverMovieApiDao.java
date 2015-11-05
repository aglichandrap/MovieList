package org.iblitzc0de.popularlist.apis.daos;

import java.util.List;

public class DiscoverMovieApiDao {
    private int page;
    private List<MovieDao> results;
    private String status_code;
    private String status_message;
    private int total_pages;
    private long total_results;

    public String getStatus_code() {
        return this.status_code;
    }

    public String getStatus_message() {
        return this.status_message;
    }

    public List<MovieDao> getResults() {
        return this.results;
    }

    public long getTotal_results() {
        return this.total_results;
    }

    public int getTotal_pages() {
        return this.total_pages;
    }

    public int getPage() {
        return this.page;
    }
}
