package team12.intouch;

import com.parse.ParseGeoPoint;
import com.parse.ParseUser;

import team12.intouch.adapter.FragmentViewPagerItem;
import team12.intouch.adapter.TabsPagerAdapter;
import team12.intouch.entities.GPSTracker;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

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

		//get geo location
		GPSTracker gps = new GPSTracker(TabActivity.this);
		// Check if GPS enabled
		if(gps.canGetLocation()) {

			double latitude = gps.getLatitude();
			double longitude = gps.getLongitude();

			// \n is for new line
			//Toast.makeText(getApplicationContext(), "Your Location is - \nLat: " + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
			Log.e("GPS","Lat: "+latitude+" Long: "+longitude);
			
			//geo update code -----------------
			//get GeoLocation TODO get real geo point
			ParseGeoPoint point = new ParseGeoPoint(latitude, longitude);

			//update it on the server
			ParseUser currentUser = ParseUser.getCurrentUser(); 
			currentUser.put("Location", point);
			//Log.d("geolog", "log in: currentUser.getParseGeoPoint(Location): " + currentUser.getParseGeoPoint("Location"));

			//userObj.put("Location", point);
			//currentUser.put("additional","test from user put");
			currentUser.saveEventually(); //make sure save

			//Log.d("geolog", "log in: " + currentUser.getParseGeoPoint("Location").getLongitude());
			//Log.d("geolog", "log in: " + currentUser.getParseGeoPoint("Location").getLongitude());
			//geo update code ---------------------

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
	
	/**
     * On selecting action bar icons
     * */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Take appropriate action for each action item click
        switch (item.getItemId()) {
        case R.id.logout:
            // search action
        {
        	 ParseUser.logOut();
        	 Intent intent = new Intent(TabActivity.this, MainActivity.class);
        	 intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        	 startActivity(intent);
        	return true;
        }
        case R.id.refresh:
            // location found
          //  LocationFound();
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }
    }

	@Override
	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
	}

	@Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

}
