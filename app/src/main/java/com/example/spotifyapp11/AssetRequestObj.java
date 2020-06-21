package com.example.spotifyapp11;

import android.content.Context;
import android.view.LayoutInflater;

import com.android.volley.RequestQueue;

public class AssetRequestObj extends RequestObj {
    private String id;
    public AssetRequestObj(Context context, RequestQueue rQ, LayoutInflater layoutInflater, String type, String ID) {
        super.setContext(context);
        super.setRequestQueue(rQ);
        super.setLayoutInflater(layoutInflater);
        super.setType(type);
        this.setID(ID);
    }
    public String getID() {
        return id;
    }
    public void setID(String id) {
        this.id = id;
    }
}
