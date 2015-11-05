package org.iblitzc0de.movielist.apis.daos;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;

import org.iblitzc0de.movielist.provider.movie.MovieCursor;

import java.util.ArrayList;
import java.util.List;

public class MovieDao implements Parcelable {
    public static final Creator<MovieDao> CREATOR = new Creator<MovieDao>() {
        public MovieDao createFromParcel(Parcel in) {
            return new MovieDao(in);
        }

        public MovieDao[] newArray(int size) {
            return new MovieDao[size];
        }
    };
    private boolean adult;
    private String backdrop_path;
    private List<Integer> genre_ids;
    private long id;
    private String original_language;
    private String original_title;
    private String overview;
    private double popularity;
    private String poster_path;
    private String release_date;
    private String title;
    private boolean video;
    private double vote_average;
    private int vote_count;

    public MovieDao(MovieCursor movieCursor) {
        this.backdrop_path = movieCursor.getBackdropPath();
        this.id = movieCursor.getMovieId().longValue();
        this.overview = movieCursor.getOverview();
        this.release_date = movieCursor.getReleaseDate();
        this.poster_path = movieCursor.getPosterPath();
        this.title = movieCursor.getTitle();
        this.vote_average = movieCursor.getVoteAverage().doubleValue();
    }

    public boolean isAdult() {
        return this.adult;
    }

    public String getBackdrop_path() {
        return this.backdrop_path;
    }

    public List<Integer> getGenre_ids() {
        return this.genre_ids;
    }

    public long getId() {
        return this.id;
    }

    public String getOriginal_language() {
        return this.original_language;
    }

    public String getOriginal_title() {
        return this.original_title;
    }

    public String getOverview() {
        return this.overview;
    }

    public String getRelease_date() {
        return this.release_date;
    }

    public String getPoster_path() {
        return this.poster_path;
    }

    public double getPopularity() {
        return this.popularity;
    }

    public String getTitle() {
        return this.title;
    }

    public boolean isVideo() {
        return this.video;
    }

    public double getVote_average() {
        return this.vote_average;
    }

    public int getVote_count() {
        return this.vote_count;
    }

    protected MovieDao(Parcel in) {
        boolean z = true;
        this.adult = in.readByte() != (byte) 0;
        this.backdrop_path = in.readString();
        if (in.readByte() == (byte) 1) {
            this.genre_ids = new ArrayList();
            in.readList(this.genre_ids, Integer.class.getClassLoader());
        } else {
            this.genre_ids = null;
        }
        this.id = in.readLong();
        this.original_language = in.readString();
        this.original_title = in.readString();
        this.overview = in.readString();
        this.release_date = in.readString();
        this.poster_path = in.readString();
        this.popularity = in.readDouble();
        this.title = in.readString();
        if (in.readByte() == (byte) 0) {
            z = false;
        }
        this.video = z;
        this.vote_average = in.readDouble();
        this.vote_count = in.readInt();
    }

    public int describeContents() {
        return 0;
    }

    public void writeToParcel(Parcel dest, int flags) {
        int i = 1;
        dest.writeByte((byte) (this.adult ? 1 : 0));
        dest.writeString(this.backdrop_path);
        if (this.genre_ids == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeList(this.genre_ids);
        }
        dest.writeLong(this.id);
        dest.writeString(this.original_language);
        dest.writeString(this.original_title);
        dest.writeString(this.overview);
        dest.writeString(this.release_date);
        dest.writeString(this.poster_path);
        dest.writeDouble(this.popularity);
        dest.writeString(this.title);
        if (!this.video) {
            i = 0;
        }
        dest.writeByte((byte) i);
        dest.writeDouble(this.vote_average);
        dest.writeInt(this.vote_count);
    }
}
