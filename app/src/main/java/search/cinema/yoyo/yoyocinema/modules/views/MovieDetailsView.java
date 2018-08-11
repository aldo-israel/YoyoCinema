package search.cinema.yoyo.yoyocinema.modules.views;

import android.view.MenuItem;

/**
 * Eureka mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/10/18.
 */
public interface MovieDetailsView {
    void updateFavoriteButton(MenuItem item, boolean isSaved);
    void updateFavorites();
}
