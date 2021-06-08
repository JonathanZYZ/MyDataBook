package com.myapplicationdev.android.mydatabook;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    private ActionBarDrawerToggle drawerToggle;
    private String[] drawerItems;
    private DrawerLayout drawerLayout;
    private NavigationView drawerList;
    ArrayAdapter<String> aa;
    String currentTitle;
    ActionBar ab;
    Fragment fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = findViewById(R.id.left_drawer);

        drawerItems = new String[]{"Bio", "Vaccination", "Anniversary", "About Us"};
        ab = getSupportActionBar();

        aa = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_activated_1, drawerItems);


        drawerList.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                Fragment fragment = null;
                String msg = "";
                switch (item.getItemId()) {
                    case R.id.nav_bio:
                        fragment = new BioFragment();
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        FragmentTransaction transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.content_frame, fragment);
                        transaction.commit();
                        break;
                    case R.id.nav_vacci:
                        fragment = new VaccinationFragment();
                        fragmentManager = getSupportFragmentManager();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.content_frame, fragment);
                        transaction.commit();

                        break;
                    case R.id.nav_anni:
                        fragment = new AnniversaryFragment();
                        fragmentManager = getSupportFragmentManager();
                        transaction = fragmentManager.beginTransaction();
                        transaction.replace(R.id.content_frame, fragment);
                        transaction.commit();
                        break;
                    case R.id.nav_about:
                        Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                        startActivity(intent);
                        break;
                }
                //Having conflit with fragment and activity.Thus, we have to put all these all
//                four lines under each case
//                fragmentManager = getSupportFragmentManager();
//                transaction = fragmentManager.beginTransaction();
//                transaction.replace(R.id.content_frame, fragment);
//                transaction.commit();
                ab.setTitle(msg);
                drawerLayout.closeDrawers();
                return true;
            }
        });
        currentTitle = this.getTitle().toString();

        drawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,      /* DrawerLayout object */
                R.string.drawer_open, /* "open drawer" description */
                R.string.drawer_close /* "close drawer" description */
        ) {

            /** Would be called when a drawer has completely closed */
            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                ab.setTitle(currentTitle);
            }

            /** Would be called when a drawer has completely open */
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                ab.setTitle("Make a selection");
            }
        };

        // Set the drawer toggle as the DrawerListener
        drawerLayout.addDrawerListener(drawerToggle);
        ab.setDisplayHomeAsUpEnabled(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                drawerLayout.openDrawer(drawerList);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync toggle state so the indicator is shown properly.
        //  Have to call in onPostCreate()
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // The home/up action should open or close the drawer.
        // ActionBarDrawerToggle will take care of this.
        if (drawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }


}