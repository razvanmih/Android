package com.example.razva.searchshow;

import java.util.Date;

/**
 * Created by razva on 13-Mar-18.
 */

class TvShow {
    int id;
    String name,poster_path,first_air_date,overview;


    public int getId() {
        return id;
    }

    public String getOverview() {
        return overview;
    }

    public String getName() {
        return name;
    }


    public String getPoster_path() {
        return poster_path;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }
}
