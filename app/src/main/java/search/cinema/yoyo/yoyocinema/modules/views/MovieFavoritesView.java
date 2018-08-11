package search.cinema.yoyo.yoyocinema.modules.views;

import io.realm.RealmResults;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.realm.FavoriteMovies;

/**
 * Eureka mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/10/18.
 */
public interface MovieFavoritesView {
    void showLoadingDialog(boolean show);
    void showFavoriteMovies(RealmResults<FavoriteMovies> favoriteMovies);
    void showMovieDetails(MovieDetails movieDetails, Result movieSummary);
}
