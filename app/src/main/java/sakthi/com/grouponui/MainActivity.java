package sakthi.com.grouponui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import sakthi.com.grouponui.view.SlidingTabLayout;
import sakthi.com.grouponui.view.SlidingTabStrip;

public class MainActivity extends FragmentActivity {

    private SlidingTabLayout mSlidingTabLayout;

    private ViewPager mViewPager;

    private SlidingTabStrip mTabStrip;

    private EditText mActionBarEditText;

    private String[] sections;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sections = getResources().getStringArray(R.array.sections);
        mActionBarEditText = (EditText) findViewById(R.id.actionbar_edittext);
        mActionBarEditText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mActionBarEditText.setCursorVisible(true);
            }
        });
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateEditTextHint(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        // Give the SlidingTabLayout the ViewPager, this must be done AFTER the ViewPager has had
        // it's PagerAdapter set.
        mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
        mSlidingTabLayout.setViewPager(mViewPager);

        mTabStrip = mSlidingTabLayout.getTabStrip();
        mTabStrip.setBackgroundColor(getResources().getColor(android.R.color.holo_green_dark));
        mTabStrip.setDividerColors(Color.TRANSPARENT);
        mTabStrip.setSelectedIndicatorColors(Color.WHITE);
    }

    private void updateEditTextHint(int position) {
        if (position == (sections.length - 1)) {
            mActionBarEditText.setHint("Search " + sections[position]);
        } else if (position == (sections.length - 3)) {
            mActionBarEditText.setHint("Search " + sections[position]);
        } else {
            mActionBarEditText.setHint("Search Groupon");
        }
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            return sections.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return sections[position].toUpperCase();
        }
    }

}
