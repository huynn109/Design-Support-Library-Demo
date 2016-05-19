package com.example.huy.androiddesignsupportlibrarydemo.ui.activity;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;

import com.example.huy.androiddesignsupportlibrarydemo.R;
import com.gigamole.library.navigationtabstrip.NavigationTabStrip;

public class TabStripActivity extends AppCompatActivity {

    private ViewPager mViewPager;

    private NavigationTabStrip mTopNavigationTabStrip;
    private NavigationTabStrip mCenterNavigationTabStrip;
    private NavigationTabStrip mBottomNavigationTabStrip;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_strip);

        initUI();
        setUI();
    }

    private void initUI() {
        mViewPager = (ViewPager) findViewById(R.id.vp);
//        mTopNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_top);
        mCenterNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_center);
//        mBottomNavigationTabStrip = (NavigationTabStrip) findViewById(R.id.nts_bottom);
    }

    private void setUI() {
        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 3;
            }

            @Override
            public boolean isViewFromObject(final View view, final Object object) {
                return view.equals(object);
            }

            @Override
            public void destroyItem(final View container, final int position, final Object object) {
                ((ViewPager) container).removeView((View) object);
            }

            @Override
            public Object instantiateItem(final ViewGroup container, final int position) {
                final View view = new View(getBaseContext());
                container.addView(view);
                return view;
            }
        });

//        mTopNavigationTabStrip.setTabIndex(1, true);
        mCenterNavigationTabStrip.setViewPager(mViewPager, 1);
//        mBottomNavigationTabStrip.setTabIndex(1, true);
    }
}
