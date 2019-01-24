package com.example.ethan.androidprojectfinal;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;


@Entity(tableName = "festival_table")
public class Festival implements Parcelable {

    @PrimaryKey (autoGenerate = true)
    private long id;
    @NonNull
    @ColumnInfo(name = "festival")
    private String FestivalName;


    @ColumnInfo(name = "long")
    private double longitude;
    @ColumnInfo(name = "lat")
    private double latitude;


    public Festival(String FestivalName, double latitude, double longitude) {
        this.FestivalName = FestivalName;
        this.latitude = latitude;
        this.longitude = longitude;

    }


    protected Festival(Parcel in) {
        id = in.readLong();
        FestivalName = in.readString();
        longitude = in.readDouble();
        latitude = in.readDouble();
    }

    public static final Creator<Festival> CREATOR = new Creator<Festival>() {
        @Override
        public Festival createFromParcel(Parcel in) {
            return new Festival(in);
        }

        @Override
        public Festival[] newArray(int size) {
            return new Festival[size];
        }
    };

    public void setFestivalName(@NonNull String festivalName) {
        FestivalName = festivalName;
    }

    public String getFestivalName(){return this.FestivalName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(FestivalName);
        dest.writeDouble(longitude);
        dest.writeDouble(latitude);
    }
}

