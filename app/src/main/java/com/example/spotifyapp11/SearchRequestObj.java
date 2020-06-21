package com.example.spotifyapp11;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.android.volley.RequestQueue;
import com.example.spotifyapp11.RequestObj;

public class SearchRequestObj extends RequestObj {
    private String query;
    private View search_card;
    public SearchRequestObj(Context context, RequestQueue rQ, LayoutInflater layoutInflater, String query, View search_card) {
        super.setContext(context);
        super.setRequestQueue(rQ);
        super.setLayoutInflater(layoutInflater);
        super.setType("search");
        this.setQuery(query);
        this.setSearchCard(search_card);
    }
    public String getQuery() {
        return query;
    }
    public View getSearchCard() {
        return search_card;
    }
    public void setQuery(String query) {
        this.query = query;
    }
    public void setSearchCard(View search_card) {
        this.search_card = search_card;
    }
}
