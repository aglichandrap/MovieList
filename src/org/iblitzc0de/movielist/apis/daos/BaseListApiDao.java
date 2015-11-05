package org.iblitzc0de.movielist.apis.daos;

import java.util.List;

public class BaseListApiDao<T> {
    private int page;
    private List<T> results;
    private int total_pages;
    private long total_results;

    public List<T> getResults() {
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
