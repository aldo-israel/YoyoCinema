package search.cinema.yoyo.yoyocinema.utils;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;

import search.cinema.yoyo.yoyocinema.R;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */
public class LoadingDialog extends Dialog {
    public LoadingDialog(@NonNull Context context) {
        super(context, R.style.NoBackgroundDialogTheme);
        setContentView(R.layout.loading_dialog);
    }
}
