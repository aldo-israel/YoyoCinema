package search.cinema.yoyo.yoyocinema.modules.presenter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import search.cinema.yoyo.yoyocinema.modules.interactor.MovieSearchInteractor;
import search.cinema.yoyo.yoyocinema.modules.views.MovieSearchView;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.MovieSearchResults;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;

/**
 * Eureka mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public class MovieSearchPresenter {

    private MovieSearchView mView;
    private MovieSearchInteractor mInteractor;

    public MovieSearchPresenter(MovieSearchView view, MovieSearchInteractor interactor) {
        mView = view;
        mInteractor = interactor;
    }

    public void getSearchResults(String query) {
        mView.showLoadingDialog(true);
        mInteractor.getSearchResults(query, new Callback<MovieSearchResults>() {
            @Override
            public void onResponse(Call<MovieSearchResults> call, Response<MovieSearchResults> response) {
                if (response.isSuccessful()) {
                    mView.showSearchResults(response.body());
                    mView.showLoadingDialog(false);
                }
            }

            @Override
            public void onFailure(Call<MovieSearchResults> call, Throwable t) {
                mView.showLoadingDialog(false);
            }
        });
    }

    public void getMovieDetails(final Result selectedMovie) {
        mInteractor.getMovieDetails(selectedMovie.getId(), new Callback<MovieDetails>() {
            @Override
            public void onResponse(Call<MovieDetails> call, Response<MovieDetails> response) {
                if (response.isSuccessful()) {
                    mView.showMovieDetails(response.body(), selectedMovie);
                }
            }

            @Override
            public void onFailure(Call<MovieDetails> call, Throwable t) {

            }
        });
    }
}