package com.example.razva.searchshow;

import java.util.ArrayList;

/**
 * Created by razva on 13-Mar-18.
 */

public class SearchResponse {
    int page;
    ArrayList<TvShow> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public ArrayList<TvShow> getShows() {
        return results;
    }

    public void setShows(ArrayList<TvShow> results) {
        this.results = results;
    }
}
