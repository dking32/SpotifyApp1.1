//package com.example.spotifyapp11;
//
//import android.util.Log;
//
//import com.android.volley.AuthFailureError;
//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonObjectRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;
//
//import org.json.JSONObject;
//
//import java.util.HashMap;
//import java.util.Map;
//
//public class ApiRequestMethods {
//    public static void getInfo(RequestObj rO) {
//        final String refreshToken = "AQCh2moWxnrNIvw-Bj0Bqaujq-wvnDko_cgiWr4RT7ekTPuzgp0xQO7r9PJ2YBpu4ZedK5EqcCg41nGSR79I0W-Jy9LG6DEHd2dqI7tuS26m6i54uHzxgmL5rCDcZng6wQk";
//        final String clientIDb64 = "NGJhNWY0MGE0Y2RmNDE5ZGI0MzgyNWNmNDAwNTNlMDc6MTFiYWIwZmYwNjBjNDY5M2I4NTdiNDYyYTJjY2Q4NjM=";
//        final RequestObj requestObj = rO;
//        StringRequest request = new StringRequest(Request.Method.POST, "https://accounts.spotify.com/api/token",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        try {
//                            JSONObject r = new JSONObject(response);
//                            String oAuthToken = r.getString("access_token");
//                            if (requestObj.getType().equals("search")) {
//                                searchRequest(oAuthToken, requestObj);
//                            } else if (requestObj.getType().equals("artist")) {
//                                artistRequest(oAuthToken, requestObj);
//                            } else if (requestObj.getType().equals("album")) {
//                                albumRequest(oAuthToken, requestObj);
//                            } else if (requestObj.getType().equals("track")) {
//                                trackRequest(oAuthToken, requestObj);
//                            }
//
//                        } catch (Exception e) {
//                            Log.d("sdfsdf", "ERROR CASTING RESPONSE TO JSON" + e.toString());
//                        }
//                    }
//                }, new Response.ErrorListener() {
//            public void onErrorResponse(VolleyError error) {
//                Log.d("werewr", error.toString());
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("grant_type", "refresh_token");
//                params.put("refresh_token", refreshToken);
//                return params;
//            }
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> params = new HashMap<String, String>();
//                params.put("Authorization", "Basic " + clientIDb64);
//                return params;
//            }
//        };
//        rO.getRequestQueue().add(request);
//    }
//
//    public static void searchRequest(String oAuthToken, RequestObj requestObj) {
//
//    }
//}
