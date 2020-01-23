package com.example.spotifyapp11;

import com.android.volley.RequestQueue;

public class RequestObj {
    private RequestQueue queue;
    private String id;
    private String type;
    private String query;

    public RequestObj(RequestQueue queue, String id, String type, String query) {
        this.queue = queue;
        this.id = id;
        this.type = type;
        this.query = query;
    }

    public RequestObj(RequestQueue queue, String id, String type) {
        this(queue, id, type, "no query found");
    }

    public RequestQueue getRequestQueue() {
        return queue;
    }

    public String getID() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getQuery() {
        return query;
    }

    public void setRequestQueue(RequestQueue queue) {
        this.queue = queue;
    }

    public void setID(String id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setQuery(String query) {
        this.query = query;
    }
}
