package com.example.spotifyapp11;

import android.util.Log;
import android.view.View;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

public class ApiRequestMethods {
    private static String refreshToken = "AQCh2moWxnrNIvw-Bj0Bqaujq-wvnDko_cgiWr4RT7ekTPuzgp0xQO7r9PJ2YBpu4ZedK5EqcCg41nGSR79I0W-Jy9LG6DEHd2dqI7tuS26m6i54uHzxgmL5rCDcZng6wQk";
    private static String clientIDb64 = "NGJhNWY0MGE0Y2RmNDE5ZGI0MzgyNWNmNDAwNTNlMDc6MTFiYWIwZmYwNjBjNDY5M2I4NTdiNDYyYTJjY2Q4NjM=";

    public static void getInfo(RequestObj rO, View l, View sc) {
        final RequestObj requestObj = rO;
        final View linearLayout = l;
        final View search_card = sc;
        StringRequest request = new StringRequest(Request.Method.POST, "https://accounts.spotify.com/api/token",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject r = new JSONObject(response);
                            String oAuthToken = r.getString("access_token");
                            if (requestObj instanceof SearchRequestObj) {
                                SearchRequestObj sRO = (SearchRequestObj) requestObj;
                                searchRequest(oAuthToken, sRO, linearLayout);
                            } else if (requestObj instanceof AssetRequestObj){
                                AssetRequestObj aRO = (AssetRequestObj) requestObj;
                                if (aRO.getType().equals("artist")) {
                                    artistRequest(oAuthToken, aRO, linearLayout);
                                } else if (aRO.getType().equals("album")) {
                                    albumRequest(oAuthToken, aRO, linearLayout);
                                } else if (aRO.getType().equals("track")) {
                                    trackRequest(oAuthToken, aRO, linearLayout);
                                }
                            }
                        } catch (Exception e) {
                            Log.d("sdfsdf", "ERROR CASTING RESPONSE TO JSON" + e.toString());
                        }
                    }
                }, new Response.ErrorListener() {
            public void onErrorResponse(VolleyError error) {
                Log.d("werewr", error.toString());
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("grant_type", "refresh_token");
                params.put("refresh_token", refreshToken);
                return params;
            }
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Authorization", "Basic " + clientIDb64);
                return params;
            }
        };
        rO.getRequestQueue().add(request);
    }

    public static void searchRequest(String oAuthToken, SearchRequestObj sRO, View l) {
        final SearchRequestObj searchRequestObj = sRO;
        final View linearLayout = l;

        String baseURL = "https://api.spotify.com/v1/search";
        String encQuery = "";
        final String oAuthTok = oAuthToken;
        try {
            encQuery = URLEncoder.encode(searchRequestObj.getQuery(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            Log.d("sdfdsf", e.toString());
        }
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, baseURL + "?q=" + encQuery + "&type=track%2Calbum%2Cartist", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray trackResults = response.getJSONObject("tracks").getJSONArray("items");
                            JSONArray artistResults = response.getJSONObject("artists").getJSONArray("items");
                            JSONArray albumResults = response.getJSONObject("albums").getJSONArray("items");

                            DisplayRequestMethods.displaySearchResults(artistResults, linearLayout.findViewById(R.id.artists_results), albumResults,
                                    linearLayout.findViewById(R.id.albums_results), trackResults, linearLayout.findViewById(R.id.tracks_results),
                                    searchRequestObj);
                        } catch (Exception e) {
                            Log.d("sdfsdf",e.toString());
                            Log.d("sdfdsf", "set no results visible");
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("sdfsdf","VOLLEY ERROR did not get track search result array");
            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();
                params.put("Accept", "application/json");
                params.put("Content-Type", "application/json");
                params.put("Authorization", "Bearer " + oAuthTok);

                return params;
            }
        };

        searchRequestObj.getRequestQueue().add(request);
    }

    public static void artistRequest(String oAuthToken, RequestObj requestObj, View l) {

    }

    public static void albumRequest(String oAuthToken, RequestObj requestObj, View l) {

    }

    public static void trackRequest(String oAuthToken, RequestObj requestObj, View l) {

    }
}
