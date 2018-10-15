package com.packtpub.slideshow;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private final int PAGE_COUNT=4;
    private ViewPager mViewPager;
    private PagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mViewPager = findViewById(R.id.viewPager);
        mPagerAdapter = new SlideAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mPagerAdapter);
    }

    private class SlideAdapter extends FragmentStatePagerAdapter {
        public SlideAdapter(FragmentManager fm) {
            super(fm);
        }
        @Override
        public Fragment getItem(int position) {
            SlideFragment slideFragment = new SlideFragment();
            switch (position) {
                case 0:
                    slideFragment.setImage(R.drawable.slide_0);
                    break;
                case 1:
                    slideFragment.setImage(R.drawable.slide_1);
                    break;
                case 2:
                    slideFragment.setImage(R.drawable.slide_2);
                    break;
                case 3:
                    slideFragment.setImage(R.drawable.slide_3);
                    break;
            }
            return slideFragment;
        }
        @Override
        public int getCount() {
            return PAGE_COUNT;
        }
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }
}
