package com.thedancercodes.daggersandbox.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;
import com.thedancercodes.daggersandbox.BaseActivity;
import com.thedancercodes.daggersandbox.R;

public class MainActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);

        init();
    }

    private void init() {

        // Tying the NavController to our fragment in our view.
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout);
        NavigationUI.setupWithNavController(navigationView, navController);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        // Switch statement for catching the different clicks to the menu items.
        switch (item.getItemId()) {

            case R.id.logout: {
                sessionManager.logOut();
                return true;
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Switch statement to handle when menu items are clicked
        switch (item.getItemId()) {

            case R.id.nav_profile: {

                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.profileScreen);
                break;
            }

            case R.id.nav_posts: {

                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(R.id.postsScreen);
                break;
            }
        }

        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
