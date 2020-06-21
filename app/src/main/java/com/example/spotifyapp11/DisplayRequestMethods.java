package com.example.spotifyapp11;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class DisplayRequestMethods {
    public static void displaySearchResults(JSONArray artists, View artistsLay,JSONArray albums, View albumsLay,
                                            JSONArray tracks, View tracksLay, SearchRequestObj searchRequestObj) {
        LayoutInflater layoutInflater = searchRequestObj.getLayoutInflater();
        LinearLayout artistsLinLayout = (LinearLayout) artistsLay;
        View parent = (View) artistsLinLayout.getParent();
        TextView artists_title = parent.findViewById(R.id.artists_results_title);
        if (artists.length() < 1 && albums.length() < 1 && tracks.length() < 1) {
            artists_title.setText("Nothing Found!");
            artists_title.setVisibility(View.VISIBLE);
        } else {
            artists_title.setText("Artists");
            displaySearchArtists(artists, artistsLay, searchRequestObj);
            displaySearchAlbums(albums, albumsLay, searchRequestObj);
            displaySearchTracks(tracks, tracksLay, searchRequestObj);
        }
    }
    public static void displaySearchArtists(JSONArray artists, View artistsLay, SearchRequestObj searchRequestObj) {
        try {
            Context context = searchRequestObj.getContext();
            LayoutInflater layoutInflater = searchRequestObj.getLayoutInflater();
            LinearLayout artistsLinLayout = (LinearLayout) artistsLay;
            // Display artist search results
            for (int i = 0; i < artists.length() && i < SearchDisplay.MAX_RESULTS; i++) {
                JSONObject artist = artists.getJSONObject(i);
                View search_card = layoutInflater.inflate(R.layout.chunk_search_card, artistsLinLayout, false);
                ImageView image = search_card.findViewById(R.id.image);
                try {
                    String image_url = artist.getJSONArray("images").getJSONObject(0).getString("url");
                    Picasso.with(context).load(image_url).into(image);
                } catch (JSONException e) {
                    String image_url = "https://www.iconsdb.com/icons/preview/white/dj-xxl.png";
                    Picasso.with(context).load(image_url).into(image);
                }

                TextView name_text = search_card.findViewById(R.id.search_name);
                String artist_name = artist.getString("name");
                if (artist_name.length() > SearchDisplay.MAX_NAME_LENGTH) {
                    artist_name = artist_name.substring(0, SearchDisplay.MAX_NAME_LENGTH - 3) + "...";
                }
                name_text.append(artist_name);

                TextView genres_text = search_card.findViewById(R.id.search_attributes);
                JSONArray artist_genres = artist.getJSONArray("genres");
                String genres = "Genres:";
                for (int j = 0; j < artist_genres.length() && j < 2; j++) {
                    if (j == 1 || j == artist_genres.length() - 1) {
                        genres += " " + artist_genres.getString(j);
                    } else {
                        genres += " " + artist_genres.getString(j) + ",";
                    }
                }
                if (artist_genres.length() < 1) {
                    genres += " unknown";
                } else if (genres.length() > SearchDisplay.MAX_ATTRIBUTE_LENGTH) {
                    genres = genres.substring(0, SearchDisplay.MAX_ATTRIBUTE_LENGTH - 4) + "...";
                } else if (artist_genres.length() > 2) {
                    genres += "...";
                }
                genres_text.append(genres);
                artistsLinLayout.addView(search_card);
            }
            if (artists.length() > 0) {
                View parent = (View) artistsLinLayout.getParent();
                View artists_title = parent.findViewById(R.id.artists_results_title);
                artists_title.setVisibility(View.VISIBLE);
                artistsLinLayout.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            Log.d("JSONerror", "display request error " + e);
        }
    }

    public static void displaySearchAlbums(JSONArray albums, View albumsLay, SearchRequestObj searchRequestObj) {
        try {
            Context context = searchRequestObj.getContext();
            LayoutInflater layoutInflater = searchRequestObj.getLayoutInflater();
            LinearLayout albumsLinLayout = (LinearLayout) albumsLay;
            // Display album search results
            for (int i = 0; i < albums.length() && i < SearchDisplay.MAX_RESULTS; i++) {
                JSONObject album = albums.getJSONObject(i);
                View search_card = layoutInflater.inflate(R.layout.chunk_search_card, albumsLinLayout, false);
                ImageView image = search_card.findViewById(R.id.image);
                try {
                    String image_url = album.getJSONArray("images").getJSONObject(0).getString("url");
                    Picasso.with(context).load(image_url).into(image);
                } catch (JSONException e) {
                    Log.d("JSONerror", "no images found");
                }

                TextView name_text = search_card.findViewById(R.id.search_name);
                String album_name = album.getString("name");
                if (album_name.length() > SearchDisplay.MAX_NAME_LENGTH) {
                    album_name = album_name.substring(0, SearchDisplay.MAX_NAME_LENGTH - 3) + "...";
                }
                name_text.append(album_name);

                TextView genres_text = search_card.findViewById(R.id.search_attributes);
                JSONArray artists = album.getJSONArray("artists");
                String genres = "Artists:";
                for (int j = 0; j < artists.length() && j < 2; j++) {
                    JSONObject artist = artists.getJSONObject(j);
                    if (j == 1 || j == artists.length() - 1) {
                        genres += " " + artist.getString("name");
                    } else {
                        genres += " " + artist.getString("name") + ",";
                    }
                }

                if (genres.length() > SearchDisplay.MAX_ATTRIBUTE_LENGTH) {
                    genres = genres.substring(0, SearchDisplay.MAX_ATTRIBUTE_LENGTH - 4) + " ...";
                } else if (artists.length() > 2) {
                    genres += " ...";
                }
                genres_text.append(genres);
                albumsLinLayout.addView(search_card);

            }
            if (albums.length() > 0) {
                View parent = (View) albumsLinLayout.getParent();
                View artists_title = parent.findViewById(R.id.albums_results_title);
                artists_title.setVisibility(View.VISIBLE);
                albumsLinLayout.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            Log.d("JSONerror", "display request error " + e);
        }
    }

    public static void displaySearchTracks(JSONArray tracks, View tracksLay, SearchRequestObj searchRequestObj) {
        try {
            Context context = searchRequestObj.getContext();
            LayoutInflater layoutInflater = searchRequestObj.getLayoutInflater();
            LinearLayout tracksLinLayout = (LinearLayout) tracksLay;
            // Display album search results
            for (int i = 0; i < tracks.length() && i < SearchDisplay.MAX_RESULTS; i++) {
                JSONObject track = tracks.getJSONObject(i);
                View search_card = layoutInflater.inflate(R.layout.chunk_search_card, tracksLinLayout, false);
                ImageView image = search_card.findViewById(R.id.image);
                try {
                    String image_url = track.getJSONObject("album").getJSONArray("images").getJSONObject(0).getString("url");
                    Picasso.with(context).load(image_url).into(image);
                } catch (JSONException e) {
                    Log.d("JSONerror", "no images found");
                }

                TextView name_text = search_card.findViewById(R.id.search_name);
                String name = track.getString("name");
                if (name.length() > SearchDisplay.MAX_NAME_LENGTH) {
                    name = name.substring(0, SearchDisplay.MAX_NAME_LENGTH - 3) + "...";
                }
                name_text.append(name);

                TextView genres_text = search_card.findViewById(R.id.search_attributes);
                JSONArray artists = track.getJSONArray("artists");
                String artists_str = "Artists:";
                for (int j = 0; j < artists.length() && j < 2; j++) {
                    JSONObject artist = artists.getJSONObject(j);
                    if (j == 1 || j == artists.length() - 1) {
                        artists_str += " " + artist.getString("name");
                    } else {
                        artists_str += " " + artist.getString("name") + ",";
                    }
                }

                if (artists_str.length() > SearchDisplay.MAX_ATTRIBUTE_LENGTH) {
                    artists_str = artists_str.substring(0, SearchDisplay.MAX_ATTRIBUTE_LENGTH - 4) + " ...";
                } else if (artists.length() > 2) {
                    artists_str += " ...";
                }
                genres_text.append(artists_str);
                tracksLinLayout.addView(search_card);
            }
            if (tracks.length() > 0) {
                View parent = (View) tracksLinLayout.getParent();
                View artists_title = parent.findViewById(R.id.tracks_results_title);
                artists_title.setVisibility(View.VISIBLE);
                tracksLinLayout.setVisibility(View.VISIBLE);
            }
        } catch (JSONException e) {
            Log.d("JSONerror", e.toString());
        }

    }
}
