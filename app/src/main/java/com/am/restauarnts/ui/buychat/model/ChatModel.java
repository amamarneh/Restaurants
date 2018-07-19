package com.am.restauarnts.ui.buychat.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

public class ChatModel implements Parcelable{
    private String username;
    private String id;
    private Date lastTime;


    protected ChatModel(Parcel in) {
        username = in.readString();
        id = in.readString();
        lastTime = new Date(in.readLong());
    }

    public static final Creator<ChatModel> CREATOR = new Creator<ChatModel>() {
        @Override
        public ChatModel createFromParcel(Parcel in) {
            return new ChatModel(in);
        }

        @Override
        public ChatModel[] newArray(int size) {
            return new ChatModel[size];
        }
    };

    public ChatModel() {
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(username);
        parcel.writeString(id);
        parcel.writeLong(lastTime==null?0:lastTime.getTime());
    }
}
