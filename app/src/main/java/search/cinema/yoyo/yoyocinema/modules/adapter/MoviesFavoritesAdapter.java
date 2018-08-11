package search.cinema.yoyo.yoyocinema.modules.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.realm.RealmResults;
import search.cinema.yoyo.yoyocinema.BuildConfig;
import search.cinema.yoyo.yoyocinema.R;
import search.cinema.yoyo.yoyocinema.pojos.search.Result;
import search.cinema.yoyo.yoyocinema.realm.FavoriteMovies;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/9/18.
 */
public class MoviesFavoritesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private RealmResults<FavoriteMovies> favoriteMovies;
    private MovieCardClickListener listener;

    public MoviesFavoritesAdapter(RealmResults<FavoriteMovies> favoriteMovies,
                                  Context context, MovieCardClickListener listener) {
        this.favoriteMovies = favoriteMovies;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent,
                false);
        return new MovieCardViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FavoriteMovies favoriteMovie = favoriteMovies.get(position);

        Glide.with(context).load(BuildConfig.MOVIE_DB_IMAGES_BASE_URL + favoriteMovie.getPosterPath())
                .into(((MovieCardViewHolder) holder).imageView_movie_poster);
        ((MovieCardViewHolder) holder).textView_movie_title.setText(favoriteMovie.getMovieTitle());
        ((MovieCardViewHolder) holder).textView_movie_vote_average.setText(String.valueOf(favoriteMovie.getVoteAverage()));
        ((MovieCardViewHolder) holder).favoriteMovie = favoriteMovie;
    }

    @Override
    public int getItemCount() {
        if (favoriteMovies != null) {
            return favoriteMovies.size();
        }
        return 0;
    }

    public interface MovieCardClickListener {
        void onCardClick(FavoriteMovies favoriteMovie);
    }

    class MovieCardViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imageView_movie_poster)
        ImageView imageView_movie_poster;
        @BindView(R.id.textView_movie_title)
        TextView textView_movie_title;
        @BindView(R.id.textView_movie_vote_average)
        TextView textView_movie_vote_average;

        public View cardView;
        public FavoriteMovies favoriteMovie;

        public MovieCardViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            cardView = view;
            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onCardClick(favoriteMovie);
                }
            });
        }
    }
}
