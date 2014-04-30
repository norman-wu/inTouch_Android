package team12.intouch;

import java.util.List;

import team12.intouch.adapter.NearbyAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import com.parse.ParseException;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Nearby_fragment extends ListFragment implements OnClickListener {

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		try {

			Log.d("test", "msg from onActivit");

			//get current user
			ParseUser currentUser = ParseUser.getCurrentUser(); 
			//String userRecordId = currentUser.getObjectId();

			//get user's location
			ParseGeoPoint userLocation = (ParseGeoPoint) currentUser.get("Location");
			
			//TODO if current user no location
			if(userLocation == null){
				Log.d("test3","no current user location for username: "+currentUser.getUsername());
				return;
			}

			//geo query

			ParseQuery<ParseObject> geoQuery = ParseQuery.getQuery("_User");

			geoQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

			geoQuery.whereNear("Location", userLocation);
			geoQuery.setLimit(10);


			List<ParseObject> userRecords = geoQuery.find();
			
			//TODO if no nearby user
			if(userRecords == null){return;}
			
			
			Log.d("test", "geoQuery.find();"+geoQuery.find().size());
			ParseObject[] userRecordArray = new ParseObject[userRecords.size()];

			userRecords.toArray(userRecordArray);

			Log.d("test", "message from nearby frag normal: userRecordArray.size(): "+userRecordArray);
			NearbyAdapter adapter = new NearbyAdapter(getActivity(),userRecordArray);
			//NearbyAdapter adapter = new NearbyAdapter(getActivity(),values);

			setListAdapter(adapter);

			//setListAdampter(adapter);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// do something with the data
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

}
