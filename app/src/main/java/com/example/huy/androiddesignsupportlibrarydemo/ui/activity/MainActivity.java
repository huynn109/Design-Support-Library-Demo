package com.example.huy.androiddesignsupportlibrarydemo.ui.activity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.Toolbar;
import android.transition.Fade;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.huy.androiddesignsupportlibrarydemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final String TAG = "MainActivity";
    private Intent mIntent;

    @BindView(R.id.image_button_appbar)
    Button mButtonAppbar;
    @BindView(R.id.button_tap_strip)
    Button mButtonTapStrip;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setupWindowAnimations();
        setSupportActionBar(mToolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.menu_night_mode_system:
                setDayNightTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                break;
            case R.id.menu_night_mode_day:
                setDayNightTheme(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case R.id.menu_night_mode_night:
                setDayNightTheme(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case R.id.menu_night_mode_auto:
                setDayNightTheme(AppCompatDelegate.MODE_NIGHT_AUTO);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        switch (AppCompatDelegate.getDefaultNightMode()) {
            case AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM:
                menu.findItem(R.id.menu_night_mode_system).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_AUTO:
                menu.findItem(R.id.menu_night_mode_auto).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_YES:
                menu.findItem(R.id.menu_night_mode_night).setChecked(true);
                break;
            case AppCompatDelegate.MODE_NIGHT_NO:
                menu.findItem(R.id.menu_night_mode_day).setChecked(true);
                break;
        }
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_camera) {

        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @OnClick(R.id.fab)
    void onClickFab() {
        mIntent = new Intent(MainActivity.this, CoordinatorLayoutActivity.class);
        ActivityOptions transitionActivityOptions = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View sharedView = mFloatingActionButton;
            String transitionName = getString(R.string.title_activity_scrolling);
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                    , sharedView
                    , transitionName);
            startActivity(mIntent, transitionActivityOptions.toBundle());
        } else {
            startActivity(mIntent);
        }
    }

    @OnClick(R.id.image_button_appbar)
    void onClickButtonAppbar() {
        mIntent = new Intent(MainActivity.this, TabActivity.class);
        ActivityOptions transitionActivityOptions = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View shareView = mButtonAppbar;
            String transitionName = getString(R.string.title_activity_tab);
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                    , shareView
                    , transitionName);
            startActivity(mIntent, transitionActivityOptions.toBundle());
        } else {
            startActivity(mIntent);
        }
    }

    @OnClick(R.id.button_tap_strip)
    void onClickButtonTapStrip() {
        mIntent = new Intent(MainActivity.this, TabStripActivity.class);
        ActivityOptions transitionActivityOptions = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            View shareView = mButtonTapStrip;
            String transitionName = getString(R.string.title_activity_tab);
            transitionActivityOptions = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this
                    , shareView
                    , transitionName);
            startActivity(mIntent, transitionActivityOptions.toBundle());
        } else {
            startActivity(mIntent);
        }
    }

    private void setupWindowAnimations() {
        Fade slideTransition = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            slideTransition = new Fade();
            slideTransition.setDuration(getResources().getInteger(R.integer.anim_duration_long));
            getWindow().setReenterTransition(slideTransition);
            getWindow().setExitTransition(slideTransition);
        }
    }

    private void setDayNightTheme(int dayNightMode) {
        AppCompatDelegate.setDefaultNightMode(dayNightMode);
        recreate();
    }
}
