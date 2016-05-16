package com.example.huy.androiddesignsupportlibrarydemo.ui.activity;

import android.graphics.PorterDuff;
import android.os.Build;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.huy.androiddesignsupportlibrarydemo.R;
import com.example.huy.androiddesignsupportlibrarydemo.ui.adapter.TabSectionsPagerAdapter;
import com.example.huy.androiddesignsupportlibrarydemo.ui.fragment.OneFragment;
import com.example.huy.androiddesignsupportlibrarydemo.ui.fragment.ThreeFragment;
import com.example.huy.androiddesignsupportlibrarydemo.ui.fragment.TwoFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TabActivity extends AppCompatActivity{

    private TabSectionsPagerAdapter mTabSectionsPagerAdapter;
    private int[] tabIcons = {
            R.drawable.ic_menu_camera,
            R.drawable.ic_menu_gallery,
            R.drawable.ic_menu_share,
    };

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.view_paper)
    ViewPager mViewPager;
    @BindView(R.id.fab)
    FloatingActionButton mFloatingActionButton;
    @BindView(R.id.tabs)
    TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
        ButterKnife.bind(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mViewPager = (ViewPager) findViewById(R.id.view_paper);
        setupViewPager(mViewPager);
        mTabLayout.setupWithViewPager(mViewPager);
        setUpTabIcons();
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(1);
        mTabLayout.setOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager){
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                super.onTabSelected(tab);
                int colorTabSelectedIcon = ContextCompat.getColor(getApplicationContext(), R.color.colorTabSelectedIcon);
                tab.getIcon().setColorFilter(colorTabSelectedIcon, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                super.onTabReselected(tab);

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                super.onTabUnselected(tab);
                int colorTabUnSelectedIcon = ContextCompat.getColor(getApplicationContext(), R.color.colorTabUnSelectedIcon);
                tab.getIcon().setColorFilter(colorTabUnSelectedIcon, PorterDuff.Mode.SRC_IN);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tab, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        } else if (id == android.R.id.home) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                finishAfterTransition();
            } else {
                finish();
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.fab)
    void showMessage(View view) {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    private void setupViewPager(ViewPager mViewPager) {
        mTabSectionsPagerAdapter = new TabSectionsPagerAdapter(getSupportFragmentManager());
        mTabSectionsPagerAdapter.addFrag(new OneFragment(), "One");
        mTabSectionsPagerAdapter.addFrag(new TwoFragment(), "Two");
        mTabSectionsPagerAdapter.addFrag(new ThreeFragment(), "Three");
        mViewPager.setAdapter(mTabSectionsPagerAdapter);
    }

    private void setUpTabIcons() {
        int colorTabSelectedIcon = ContextCompat.getColor(getApplicationContext(), R.color.colorTabSelectedIcon);
        int colorTabUnSelectedIcon = ContextCompat.getColor(getApplicationContext(), R.color.colorTabUnSelectedIcon);
        for (int i = 0; i < mTabSectionsPagerAdapter.getCount(); i++) {
            mTabLayout.getTabAt(i).setIcon(tabIcons[i]);
            if (i == 0) {
                mTabLayout.getTabAt(i).getIcon().setColorFilter(colorTabSelectedIcon, PorterDuff.Mode.SRC_IN);
                continue;
            }
            mTabLayout.getTabAt(i).getIcon().setColorFilter(colorTabUnSelectedIcon, PorterDuff.Mode.SRC_IN);
        }
    }
}
