package search.cinema.yoyo.yoyocinema.modules.presenter;

import io.realm.Realm;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import search.cinema.yoyo.yoyocinema.modules.interactor.MovieSearchInteractor;
import search.cinema.yoyo.yoyocinema.modules.views.MovieFavoritesView;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.realm.FavoriteMovies;

/**
 * Eureka mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/10/18.
 */
public class MovieFavoritesPresenter {
    private MovieFavoritesView mView;
    private MovieSearchInteractor mInteractor;

    public MovieFavoritesPresenter(MovieFavoritesView mView, MovieSearchInteractor interactor) {
        this.mView = mView;
        mInteractor = interactor;
    }

    public void getFavoriteMovies() {
        Realm realm = Realm.getDefaultInstance();
        RealmResults<FavoriteMovies> favoriteMovies = realm.where(FavoriteMovies.class).findAll();
        mView.showFavoriteMovies(favoriteMovies);
    }

    public void getMovieDetails(final FavoriteMovies   favoriteMovie) {
        mInteractor.getMovieDetails(favoriteMovie.getMovieId(), new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    Result selectedMovie = new Result();
                    selectedMovie.setPosterPath(favoriteMovie.getPosterPath());
                    selectedMovie.setId(favoriteMovie.getMovieId());
                    selectedMovie.setTitle(favoriteMovie.getMovieTitle());
                    selectedMovie.setVoteAverage(favoriteMovie.getVoteAverage());
                    mView.showMovieDetails(response.body(), selectedMovie);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }
}
