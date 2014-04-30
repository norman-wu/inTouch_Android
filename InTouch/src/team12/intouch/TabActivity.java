package team12.intouch;

import team12.intouch.adapter.FragmentViewPagerItem;
import team12.intouch.adapter.TabsPagerAdapter;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

@SuppressLint("NewApi")
public class TabActivity extends FragmentActivity implements
     ActionBar.TabListener {

	private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    // Tab titles
    private String[] tabs = { "Nearby", "Contacts", "Profile" };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
    	setTitle("In Touch");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
 
        // Initilization
        viewPager = (ViewPager) findViewById(R.id.pager);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        
        FragmentViewPagerItem nearbyFragment = new FragmentViewPagerItem(
                "Nearby", Nearby_fragment.class);
        mAdapter.addFragment(nearbyFragment);
        
        FragmentViewPagerItem contactsFragment = new FragmentViewPagerItem(
                "Contacts", Contacts_fragment.class);
        mAdapter.addFragment(contactsFragment);
        
        FragmentViewPagerItem profileFragment = new FragmentViewPagerItem(
                "Profile", Profile_fragment.class);
        mAdapter.addFragment(profileFragment);
 
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);       
 
        // Adding Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }
         
        /**
         * on swiping the viewpager make respective tab selected
         * */
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
 
            @Override
            public void onPageSelected(int position) {
                // on changing the page
                // make respected tab selected
                actionBar.setSelectedNavigationItem(position);
            }
 
            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }
 
            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }
 
    @Override
    public void onTabReselected(Tab tab, FragmentTransaction ft) {
    }
 
    @Override
    public void onTabSelected(Tab tab, FragmentTransaction ft) {
        // on tab selected
        // show respected fragment view
        viewPager.setCurrentItem(tab.getPosition());
    }
 
    @Override
    public void onTabUnselected(Tab tab, FragmentTransaction ft) {
    }
 

}
