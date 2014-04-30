package team12.intouch;

import java.util.List;
import java.util.Locale;

import team12.intouch.adapter.NearbyAdapter;

import com.parse.*;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.ListFragment;
import android.support.v4.widget.SimpleCursorAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.AdapterContextMenuInfo;

public class Nearby_fragment extends ListFragment implements OnClickListener {



	//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	//	        Bundle savedInstanceState) {
	//		
	//	    return inflater.inflate(R.layout.fragment_nearby, container);
	//	}

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
			
			//if current user no location
			if(userLocation == null){
				return;
			}

			//geo query

			ParseQuery<ParseObject> geoQuery = ParseQuery.getQuery("_User");

			geoQuery.setCachePolicy(ParseQuery.CachePolicy.NETWORK_ELSE_CACHE);

			geoQuery.whereNear("Location", userLocation);
			geoQuery.setLimit(10);


			List<ParseObject> userRecords = geoQuery.find();
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
