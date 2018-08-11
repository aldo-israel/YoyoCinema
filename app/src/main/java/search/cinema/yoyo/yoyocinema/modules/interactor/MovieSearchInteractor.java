package search.cinema.yoyo.yoyocinema.modules.interactor;

import retrofit2.Callback;
import search.cinema.yoyo.yoyocinema.BuildConfig;
import search.cinema.yoyo.yoyocinema.api.APIUtils;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.MovieSearchResults;

/**
 * Yoyo cinema mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public class MovieSearchInteractor {

    public void getSearchResults(String query,
                                Callback<MovieSearchResults> callback) {
        APIUtils.getEndPoints().getSearchResults(BuildConfig.MOVIE_DB_API_KEY, query).enqueue(callback);
    }

    public void getMovieDetails(int movie_id,
                                Callback<MovieDetails> callback) {
        APIUtils.getEndPoints().getMovieDetails(movie_id, BuildConfig.MOVIE_DB_API_KEY).enqueue(callback);
    }
}
