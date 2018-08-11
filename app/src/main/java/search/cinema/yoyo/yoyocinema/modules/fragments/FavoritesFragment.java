package search.cinema.yoyo.yoyocinema.modules.fragments;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import search.cinema.yoyo.yoyocinema.R;
import search.cinema.yoyo.yoyocinema.modules.activities.MovieDetailsActivity;
import search.cinema.yoyo.yoyocinema.modules.adapter.MoviesFavoritesAdapter;
import search.cinema.yoyo.yoyocinema.modules.interactor.MovieSearchInteractor;
import search.cinema.yoyo.yoyocinema.modules.presenter.MovieFavoritesPresenter;
import search.cinema.yoyo.yoyocinema.modules.views.MovieFavoritesView;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.realm.FavoriteMovies;
import search.cinema.yoyo.yoyocinema.utils.Constants;
import search.cinema.yoyo.yoyocinema.utils.LoadingDialog;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */

public class FavoritesFragment extends Fragment implements MovieFavoritesView {
    @BindView(R.id.favorites_recyclerView)
    RecyclerView recyclerView;

    private MovieFavoritesPresenter mPresenter;
    private LoadingDialog loadingDialog;

    public FavoritesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorites, container, false);
        ButterKnife.bind(this, view);
        setupView();

        return view;
    }

    private void setupView() {
        // Observer (RELOAD_FAVORITES) to receive Intents with actions
        LocalBroadcastManager.getInstance(getContext()).registerReceiver(reloadView,
                new IntentFilter(Constants.RELOAD_FAVORITES));

        loadingDialog = new LoadingDialog(getContext());
        mPresenter = new MovieFavoritesPresenter(this, new MovieSearchInteractor());
        mPresenter.getFavoriteMovies();
    }

    // Handler for received Intents. This will be called whenever an Intent
    // with an action named "RELOAD_FAVORITES" is broadcasted.
    BroadcastReceiver reloadView = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            mPresenter.getFavoriteMovies();
        }
    };

    @Override
    public void showLoadingDialog(boolean show) {
        if (show) {
            loadingDialog.show();
        } else {
            loadingDialog.hide();
        }
    }

    @Override
    public void showFavoriteMovies(RealmResults<FavoriteMovies> favoriteMovies) {
        MoviesFavoritesAdapter adapter = new MoviesFavoritesAdapter(favoriteMovies, getContext(),
                new MoviesFavoritesAdapter.MovieCardClickListener() {
                    @Override
                    public void onCardClick(FavoriteMovies favoriteMovie) {
                        mPresenter.getMovieDetails(favoriteMovie);
                    }
                });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void showMovieDetails(MovieDetails movieDetails, Result movieSummary) {
        Intent movieDetailsIntent = new Intent(getContext(), MovieDetailsActivity.class);
        movieDetailsIntent.putExtra(Constants.MOVIE_DETAILS_OBJ, movieDetails);
        movieDetailsIntent.putExtra(Constants.MOVIE_SUMMARY_OBJ, movieSummary);
        startActivity(movieDetailsIntent);
    }
}
