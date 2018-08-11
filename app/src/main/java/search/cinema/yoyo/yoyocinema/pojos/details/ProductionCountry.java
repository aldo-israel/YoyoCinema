package search.cinema.yoyo.yoyocinema.pojos.details;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Yoyo mobile app.
 * Created by Aldo Israel Navarro Alcaraz on 8/8/18.
 */

public class ProductionCountry implements Parcelable {

    @SerializedName("iso_3166_1")
    @Expose
    private String iso31661;
    @SerializedName("name")
    @Expose
    private String name;

    public String getIso31661() {
        return iso31661;
    }

    public void setIso31661(String iso31661) {
        this.iso31661 = iso31661;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    protected ProductionCountry(Parcel in) {
        iso31661 = in.readString();
        name = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(iso31661);
        dest.writeString(name);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<ProductionCountry> CREATOR = new Parcelable.Creator<ProductionCountry>() {
        @Override
        public ProductionCountry createFromParcel(Parcel in) {
            return new ProductionCountry(in);
        }

        @Override
        public ProductionCountry[] newArray(int size) {
            return new ProductionCountry[size];
        }
    };
}