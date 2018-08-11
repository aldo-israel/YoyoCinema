package search.cinema.yoyo.yoyocinema.realm;

import io.realm.RealmObject;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/10/18.
 */

public class FavoriteMovies extends RealmObject {
    private int movieId;
    private String movieTitle;
    private Double voteAverage;
    private String posterPath;

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }
}
