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
import androidx.navigation.NavOptions;
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

            case android.R.id.home: {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                    return true; // Consume the click
                } else {
                    return false; // Don't consume the click
                }
            }

                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        // Switch statement to handle when menu items are clicked
        switch (item.getItemId()) {

            case R.id.nav_profile: {

                // Enabled clearing of backstack when user navigates to navigation screen.
                NavOptions navOptions = new NavOptions.Builder()
                        .setPopUpTo(R.id.main, true)
                        .build();

                Navigation.findNavController(this, R.id.nav_host_fragment)
                        .navigate(
                                R.id.profileScreen,
                                null,
                                navOptions);
                break;
            }

            case R.id.nav_posts: {

                if (isValidDestination(R.id.postsScreen)) {
                    Navigation.findNavController(this, R.id.nav_host_fragment)
                            .navigate(R.id.postsScreen);
                }
                break;
            }
        }

        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    /*
     * Method that prevents PostFragment from being added to backstack if it is already in view.
     *
     * NOTE: int destination is the id of the fragment we are trying to navigate to.
     *
     * Compare the id's and if it is the one that is currently on, then don't do the transaction.
     */
    private boolean isValidDestination(int destination) {
        return destination != Navigation.findNavController(this, R.id.nav_host_fragment)
                .getCurrentDestination().getId();
    }

    /**
     * When you want to navigate up, reference the NavigationDrawer and do whatever works with
     * them.
     */
    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(
                Navigation.findNavController(this, R.id.nav_host_fragment), drawerLayout);
    }
}
