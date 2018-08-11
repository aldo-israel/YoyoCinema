package search.cinema.yoyo.yoyocinema.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import search.cinema.yoyo.yoyocinema.BuildConfig;

/**
 * Yoyo cinema mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public class APIUtils {
    public static MovieDBApi getEndPoints() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIE_DB_API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(MovieDBApi.class);
    }
}
