package com.bankmandiri.helloandroid2.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Todo implements Parcelable {
    private String name;
    private boolean isDone;

    public Todo(String name, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
    }

    protected Todo(Parcel in) {
        name = in.readString();
        isDone = in.readByte() != 0;
    }

    public static final Creator<Todo> CREATOR = new Creator<Todo>() {
        @Override
        public Todo createFromParcel(Parcel in) {
            return new Todo(in);
        }

        @Override
        public Todo[] newArray(int size) {
            return new Todo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean isDone) {
        this.isDone = isDone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeByte((byte) (isDone ? 1 : 0));
    }
}
