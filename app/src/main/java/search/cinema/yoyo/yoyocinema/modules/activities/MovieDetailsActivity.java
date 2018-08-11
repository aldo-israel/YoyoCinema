package search.cinema.yoyo.yoyocinema.modules.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import search.cinema.yoyo.yoyocinema.BuildConfig;
import search.cinema.yoyo.yoyocinema.R;
import search.cinema.yoyo.yoyocinema.modules.presenter.MovieDetailsPresenter;
import search.cinema.yoyo.yoyocinema.modules.views.MovieDetailsView;
import search.cinema.yoyo.yoyocinema.pojos.details.Genre;
import search.cinema.yoyo.yoyocinema.pojos.details.MovieDetails;
import search.cinema.yoyo.yoyocinema.pojos.details.ProductionCompany;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.utils.Constants;

public class MovieDetailsActivity extends AppCompatActivity implements MovieDetailsView {
    @BindView(R.id.imageView_movie_poster)
    ImageView imageView_movie_poster;
    @BindView(R.id.textView_movie_title)
    TextView textView_movie_title;
    @BindView(R.id.textView_movie_release_date)
    TextView textView_movie_release_date;
    @BindView(R.id.textView_movie_vote_average)
    TextView textView_movie_vote_average;
    @BindView(R.id.textView_movie_tagline)
    TextView textView_movie_tagline;
    @BindView(R.id.textView_movie_genres)
    TextView textView_movie_genres;
    @BindView(R.id.textView_movie_companies)
    TextView textView_movie_companies;
    @BindView(R.id.textView_movie_overview)
    TextView textView_movie_overview;

    @BindString(R.string.movie_favorite_saved)
    String onSuccessMessage;
    @BindString(R.string.movie_favorite_removed)
    String onRemoveMessage;

    private MovieDetailsPresenter mPresenter;
    private Result movieSummary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        ButterKnife.bind(this);
        mPresenter = new MovieDetailsPresenter(this);
        setupUI(getIntent());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_favorite) {
            item.setIcon(R.drawable.ic_star_activated);
            mPresenter.saveFavoriteMovie(movieSummary, item);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupUI(Intent intent) {
        MovieDetails movieDetails = intent.getParcelableExtra(Constants.MOVIE_DETAILS_OBJ);
        movieSummary = intent.getParcelableExtra(Constants.MOVIE_SUMMARY_OBJ);


        Glide.with(this).load(BuildConfig.MOVIE_DB_IMAGES_BASE_URL
                + movieDetails.getPosterPath()).into(imageView_movie_poster);

        textView_movie_title.setText(movieDetails.getTitle());
        textView_movie_release_date.setText(movieDetails.getReleaseDate());
        textView_movie_vote_average.setText(String.valueOf(movieDetails.getVoteAverage()));
        textView_movie_tagline.setText(movieDetails.getTagline());
        textView_movie_overview.setText(movieDetails.getOverview());

        StringBuilder genres = new StringBuilder();
        int iGenres = 0;
        for (Genre genre : movieDetails.getGenres()) {
            genres.append(genre.getName());
            if (iGenres++ < movieDetails.getGenres().size() - 1) {
                genres.append(" - ");
            }
        }
        textView_movie_genres.setText(genres.toString());

        StringBuilder companies = new StringBuilder();
        int iCompanies = 0;
        for (ProductionCompany company : movieDetails.getProductionCompanies()) {
            companies.append(company.getName());
            if (iCompanies++ < movieDetails.getGenres().size() - 1) {
                companies.append(", ");
            }
        }
        textView_movie_companies.setText(companies.toString());
    }

    @Override
    public void updateFavoriteButton(MenuItem item, boolean isSaved) {
        if (isSaved) {
            item.setIcon(getDrawable(R.drawable.ic_star_activated));
            Toast.makeText(getApplicationContext(), onSuccessMessage, Toast.LENGTH_SHORT).show();
        } else {
            item.setIcon(getDrawable(R.drawable.ic_star_deactivated));
            Toast.makeText(getApplicationContext(), onRemoveMessage, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void updateFavorites() {
        //Reload favorites fragment.
        Intent reloadFavorites = new Intent(Constants.RELOAD_FAVORITES);
        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(reloadFavorites);
    }
}
