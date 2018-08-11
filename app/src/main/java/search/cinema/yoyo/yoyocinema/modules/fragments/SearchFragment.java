package search.cinema.yoyo.yoyocinema.modules.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import butterknife.BindView;
import butterknife.ButterKnife;
import search.cinema.yoyo.yoyocinema.R;
import search.cinema.yoyo.yoyocinema.modules.activities.MovieDetailsActivity;
import search.cinema.yoyo.yoyocinema.modules.adapter.MoviesSearchAdapter;
import search.cinema.yoyo.yoyocinema.modules.interactor.MovieSearchInteractor;
import search.cinema.yoyo.yoyocinema.modules.presenter.MovieSearchPresenter;
import search.cinema.yoyo.yoyocinema.modules.views.MovieSearchView;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.search.MovieSearchResults;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.utils.Constants;
import search.cinema.yoyo.yoyocinema.utils.LoadingDialog;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */

public class SearchFragment extends Fragment implements MovieSearchView {
    @BindView(R.id.searchView)
    SearchView searchView;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private MovieSearchPresenter mPresenter;
    private LoadingDialog loadingDialog;

    public SearchFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this, view);

        loadingDialog = new LoadingDialog(getContext());
        mPresenter = new MovieSearchPresenter(this, new MovieSearchInteractor());

        setupView();

        return view;
    }

    private void setupView() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                if (query.isEmpty()) {
                    return false;
                } else {
                    mPresenter.getSearchResults(query);
                    return true;
                }
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
    }

    @Override
    public void showLoadingDialog(boolean show) {
        if (show) {
            loadingDialog.show();
        } else {
            loadingDialog.hide();
        }
    }

    @Override
    public void showSearchResults(MovieSearchResults results) {
        MoviesSearchAdapter moviesAdapter = new MoviesSearchAdapter(results, getContext(),
                new MoviesSearchAdapter.MovieCardClickListener() {
                    @Override
                    public void onCardClick(Result selectedMovie) {
                        mPresenter.getMovieDetails(selectedMovie);
                    }
                });

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(moviesAdapter);
        moviesAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMovieDetails(MovieDetails movieDetails, Result movieSummary) {
        Intent movieDetailsIntent = new Intent(getContext(), MovieDetailsActivity.class);
        movieDetailsIntent.putExtra(Constants.MOVIE_DETAILS_OBJ, movieDetails);
        movieDetailsIntent.putExtra(Constants.MOVIE_SUMMARY_OBJ, movieSummary);
        startActivity(movieDetailsIntent);
    }
}