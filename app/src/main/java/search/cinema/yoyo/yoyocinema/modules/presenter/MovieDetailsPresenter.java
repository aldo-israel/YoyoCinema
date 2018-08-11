package search.cinema.yoyo.yoyocinema.modules.presenter;

import android.view.MenuItem;

import io.realm.Realm;
import io.realm.RealmResults;
import search.cinema.yoyo.yoyocinema.modules.views.MovieDetailsView;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.realm.FavoriteMovies;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/10/18.
 */
public class MovieDetailsPresenter {
    private MovieDetailsView mView;
    private boolean isSaved = false;
    private RealmResults<FavoriteMovies> savedMovies;

    public MovieDetailsPresenter(MovieDetailsView view) {
        mView = view;
    }

    public void saveFavoriteMovie(final Result movieSummary, final MenuItem menuItem) {
        Realm realm = Realm.getDefaultInstance();
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm bgRealm) {
                if (isMovieAlreadySaved(movieSummary.getId())) { // If is already saved remove the movie.
                    savedMovies.deleteFirstFromRealm();
                } else { // Save the movie.
                    FavoriteMovies favoriteMovie = bgRealm.createObject(FavoriteMovies.class);
                    favoriteMovie.setMovieId(movieSummary.getId());
                    favoriteMovie.setMovieTitle(movieSummary.getTitle());
                    favoriteMovie.setPosterPath(movieSummary.getPosterPath());
                    favoriteMovie.setVoteAverage(movieSummary.getVoteAverage());
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.updateFavorites();
                mView.updateFavoriteButton(menuItem, isSaved); // Update the button
            }
        });
    }

    private boolean isMovieAlreadySaved(int movieId) {
        Realm realm = Realm.getDefaultInstance();
        savedMovies = realm.where(FavoriteMovies.class)
                .equalTo("movieId", movieId)
                .findAll();

        isSaved = !(savedMovies.size() > 0);

        return (savedMovies.size() > 0);
    }
}
