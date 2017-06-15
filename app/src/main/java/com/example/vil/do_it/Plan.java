package com.example.vil.do_it;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by vil on 2017-06-15.
 */

public class Plan implements Parcelable{
    private String name, time;
    private int numTime;

    public Plan(String name, String time, int numTime){
        this.name = name;
        this.time = time;
        this.numTime = numTime;
    }


    protected Plan(Parcel in) {
        name = in.readString();
        time = in.readString();
        numTime = in.readInt();
    }

    public static final Creator<Plan> CREATOR = new Creator<Plan>() {
        @Override
        public Plan createFromParcel(Parcel in) {
            return new Plan(in);
        }

        @Override
        public Plan[] newArray(int size) {
            return new Plan[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(time);
        dest.writeInt(numTime);
    }

    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }

    public int getNumTime() {
        return numTime;
    }
}
