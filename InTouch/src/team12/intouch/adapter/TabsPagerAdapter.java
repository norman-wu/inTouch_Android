package team12.intouch.adapter;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
 
public class TabsPagerAdapter extends FragmentPagerAdapter {
	
	private List<FragmentViewPagerItem> mFragments = new ArrayList<FragmentViewPagerItem>();
 
    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    
    public void addFragment(FragmentViewPagerItem fragment) {
        if (mFragments == null) {
            mFragments = new ArrayList<FragmentViewPagerItem>();
        }
        mFragments.add(fragment);
        notifyDataSetChanged();
    }
 
    @Override
    public Fragment getItem(int index) {
 
//        switch (index) {
//        case 0:
//            // nearby fragment activity
//            return new Nearby_fragment();
//        case 1:
//            // contacts fragment activity
//            return new Contacts_fragment();
//        case 2:
//            // profile fragment activity
//            return new Profile_fragment();
//        }
// 
    
    	Fragment fragment = null;

        try {
            fragment = mFragments.get(index % mFragments.size())
                    .newInstanceOfFragmentClass();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return fragment;
       // return null;
    }
    
    public int getCount() {
        return mFragments.size();
    }

    public CharSequence getPageTitle(int position) {
        return mFragments.get(position % mFragments.size()).getTitle()
                .toUpperCase();
    }
  
}