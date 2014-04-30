package team12.intouch;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.locks.*;

import team12.intouch.adapter.ContactsArrayAdapter;
import team12.intouch.adapter.FriendQueryAdapter;

import com.parse.*;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Contacts_fragment extends ListFragment {

	// Create the Post object
	ArrayList<ParseObject> friends;
	ArrayList<String> objectIds;
			
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		friends = new ArrayList<ParseObject>();
		objectIds = new ArrayList<String>();
		findFriends();
		final String[] users = {"norman","qiulu","mark"};
		setListAdapter(new ContactsArrayAdapter(getActivity(), users));
	}

//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//
//		// Inflate the layout for this fragment
//		View v = inflater.inflate(R.layout.fragment_profile, container, false);
//
//		friends = new ArrayList<ParseObject>();
//		updateFriendList();		
//		return v;
//	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(getActivity().getApplicationContext(), selectedValue, Toast.LENGTH_SHORT).show();

	}

	//query the friend lists
	private void findFriends() {

		// Create query for objects of type Friend
		ParseQuery<ParseObject> query = ParseQuery.getQuery("Friend");

		// Restrict to cases where the user_id is the current user.
		query.whereEqualTo("User_id", ParseUser.getCurrentUser());
		
		// Run the query  
		query.findInBackground(new FindCallback<ParseObject>() {	

			@Override
			public void done(List<ParseObject> friendList,
					ParseException e) {
				if (e == null) {
					// If there are results, update the list of posts
					// and notify the adapter
					friends.clear();
					Log.d("friendList", "print out the object lists");
					Log.d("friend #", friendList.size()+"");
					
					for (ParseObject frd : friendList) {
						friends.add(frd.getParseObject("Friend_id"));
					}
					
					updateList();
					
				//  ((ArrayAdapter<ParseUser>)getListAdapter()).notifyDataSetChanged();
				} else {
					Log.d("No friends: ", "Error: " + e.getMessage());
				}
			}

			private void updateList() {
				// TODO Auto-generated method stub
				// Create query for objects of type Friend
				ParseQuery<ParseObject> queryUser = ParseQuery.getQuery("User");

				for (ParseObject frd: friends) {
					queryUser.whereEqualTo("User_id", frd.getObjectId());
				}
				queryUser.whereEqualTo("User_id", ParseUser.getCurrentUser());
				
				
			}

		});
		
    }
}
