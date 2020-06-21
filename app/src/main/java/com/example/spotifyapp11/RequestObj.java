package com.example.spotifyapp11;

import android.content.Context;
import android.view.LayoutInflater;

import com.android.volley.RequestQueue;

public abstract class RequestObj {
    private Context context;
    private LayoutInflater layoutInflater;
    private RequestQueue queue;
    private String type;

    public RequestQueue getRequestQueue() {
        return queue;
    }

    public String getType() {
        return type;
    }

    public Context getContext() { return context; }

    public LayoutInflater getLayoutInflater() {
        return layoutInflater;
    }

    public void setRequestQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setContext(Context context) { this.context = context; }

    public void setLayoutInflater(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }
}
