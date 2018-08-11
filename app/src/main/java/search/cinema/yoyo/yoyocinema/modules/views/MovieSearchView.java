package search.cinema.yoyo.yoyocinema.modules.views;

import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.MovieSearchResults;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;

/**
 * Eureka mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public interface MovieSearchView {
    void showLoadingDialog(boolean show);
    void showSearchResults(MovieSearchResults results);
    void showMovieDetails(MovieDetails movieDetails, Result movieSummary);
}
