package com.example.spotifyapp11;

import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.Window;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_search, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);
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
                            Log.d("debug","clicked bottom nav");
                            break;
                        case R.id.navigation_search:
                            Log.d("debug","clicked bottom nav");
                            break;
                        case R.id.navigation_notifications:
                            Log.d("debug","clicked bottom nav");
                            Log.d("sdfsdf", "selected result frag");
                            break;
                    }
//                    getSupportFragmentManager().beginTransaction().replace(R.id.nav_host_fragment,
//                            selectedFragment).commit();

                    return true;
                }
            };

}
