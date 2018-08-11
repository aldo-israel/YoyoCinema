package search.cinema.yoyo.yoyocinema.api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.MovieSearchResults;

/**
 * Yoyo cinema mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public interface MovieDBApi {
    @GET("search/movie")
    Call<MovieSearchResults> getSearchResults(@Query("api_key") String api_key,
                                              @Query("query") String query);

    @GET("movie/{movie_id}")
    Call<MovieDetails> getMovieDetails(@Path("movie_id") int movie_id,
                                       @Query("api_key") String api_key);
}
