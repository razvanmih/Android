package com.example.razva.searchshow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by razva on 13-Mar-18.
 */

public interface ApiClient {
    @GET("search/tv")
    Call<SearchResponse> searchTV(
            @Query("api_key") String key,
            @Query("language") String language,
            @Query("query") String query,
            @Query("page") int page);

}
