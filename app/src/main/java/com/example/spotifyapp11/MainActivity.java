package com.example.spotifyapp11;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;
import android.widget.FrameLayout;

import com.example.spotifyapp11.ui.dashboard.SearchFragment;
import com.example.spotifyapp11.ui.home.HomeFragment;
import com.example.spotifyapp11.ui.notifications.NotificationsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity {

    private HomeFragment homeFrag;
    private SearchFragment searchFrag;
    private NotificationsFragment notifFrag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(navListener);
        // Passing each menu ID as a set of IDs because each menu should be considered as top level destinations.

        homeFrag = new HomeFragment();
        searchFrag = new SearchFragment();
        notifFrag = new NotificationsFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                homeFrag).commit();
    }


    public void onAttachedToWindow(Bundle savedInstanceState)
    {
        super.onAttachedToWindow();
        // Set a new pixel format for the window to use for rendering
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;

                    Log.d("sdfsdf", "selected bottomnav view");

                    switch(item.getItemId()) {
                        case R.id.navigation_home:
                            selectedFragment = homeFrag;
                            break;
                        case R.id.navigation_search:
                            selectedFragment = searchFrag;
                            break;
                        case R.id.navigation_notifications:
                            selectedFragment = notifFrag;
                            Log.d("sdfsdf", "selected result frag");
                            break;
                    }
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);
//
//        //Save the fragment's instance
//        getSupportFragmentManager().putFragment(outState, "searchFrag", searchFrag);
//        Log.d("debugging", "fragment saved in activity");
//    }

}
