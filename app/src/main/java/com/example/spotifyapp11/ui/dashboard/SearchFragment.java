package com.example.spotifyapp11.ui.dashboard;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.spotifyapp11.ApiRequestMethods;
import com.example.spotifyapp11.R;
import com.example.spotifyapp11.SearchRequestObj;

import java.util.ArrayList;

import static android.media.CamcorderProfile.get;

public class SearchFragment extends Fragment {

    private RequestQueue mQueue;
    private SearchView searchView;
    private LinearLayout search_results;
    private View root;
    private View fragment_save;
    private ArrayList<View> fragments = new ArrayList<View>();
    private int fragment_pointer;
    private SearchView.LayoutParams searchview_layout_params;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mQueue = Volley.newRequestQueue(getActivity().getApplicationContext());
        root = inflater.inflate(R.layout.fragment_search, container, false);
        searchView = root.findViewById(R.id.search_view);
        searchView.setQueryHint("Search Artist, Album, or Track");
        search_results = root.findViewById(R.id.search_results);

        View fragment_initial = root.findViewById(R.id.fragment_view);
        fragments.add(fragment_initial);
        fragment_pointer = 0;

        return root;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("debugging", "fragment onActivityCreated called");
    }

    public void onStart() {
        super.onStart();
        if (fragment_save != null) {
            if (fragment_save.getParent() != null) {
                ((ViewGroup)fragment_save.getParent()).removeView(fragment_save);
            }
            LinearLayout root_layout = (LinearLayout) root;
            root_layout.removeAllViews();
            root_layout.addView(fragment_save);
            search_results = fragment_save.findViewById(R.id.search_results);
            Log.d("debugging", "restored fragment");
        }
        searchView.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    setSearching();
                } else {
                    setNotSearching();
                }
            }
        });
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                hideKeyboardFrom(getActivity().getApplicationContext(), getView().getRootView());
                searchView.clearFocus();
                search_results.removeAllViews();
                View results = getLayoutInflater().inflate(R.layout.chunk_search_results, search_results, false);
                View search_card = getLayoutInflater().inflate(R.layout.chunk_search_card, search_results, false);
                SearchRequestObj sRO = new SearchRequestObj(getActivity().getApplicationContext(), mQueue, getLayoutInflater(), query, search_card);
                ApiRequestMethods.getInfo(sRO, (LinearLayout) results, search_card);
                search_results.addView(results);
                Log.d("debugging", "searched");
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return true;
            }
        });
        searchView.setOnCloseListener(new SearchView.OnCloseListener() {
            @Override
            public boolean onClose() {
                return false;
            }
        });
    }

    @Override
    public void onDestroyView() {
        fragment_save = root.findViewById(R.id.fragment_view);
        super.onDestroyView();
        Log.d("debugging", "fragment view destroyed");
    }

    public static void hideKeyboardFrom(Context context, View view) {
        InputMethodManager imm = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public void setSearching() {
        Button back = root.findViewById(R.id.back_button);
        Button forward = root.findViewById(R.id.forward_button);
        back.setVisibility(View.GONE);
        forward.setVisibility(View.GONE);
        searchview_layout_params = (SearchView.LayoutParams) searchView.getLayoutParams();
        searchView.setLayoutParams(new SearchView.LayoutParams(SearchView.LayoutParams.MATCH_PARENT, SearchView.LayoutParams.WRAP_CONTENT));
    }

    public void setNotSearching() {
        Button back = root.findViewById(R.id.back_button);
        Button forward = root.findViewById(R.id.forward_button);
        back.setVisibility(View.VISIBLE);
        forward.setVisibility(View.VISIBLE);
        searchView.setLayoutParams(searchview_layout_params);
    }

    public void addFragment(View fragment) {
        fragments.add(fragment);
        forwardFragment();
    }

    public void forwardFragment() {
        if (fragment_pointer + 1 < fragments.size()) {
            fragment_pointer++;
            replaceFragment();
        }
    }

    public void backFragment() {
        if (fragment_pointer - 1 >= 0) {
            fragment_pointer--;
            replaceFragment();
        }
    }

    private void replaceFragment() {
        View forward_frag = fragments.get(fragment_pointer);
        LinearLayout root_layout = (LinearLayout) root;
        root_layout.removeAllViews();
        root_layout.addView(forward_frag);
    }
}