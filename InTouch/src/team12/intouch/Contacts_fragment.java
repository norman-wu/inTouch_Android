package team12.intouch;

import java.util.ArrayList;
import java.util.List;

import team12.intouch.adapter.ContactsArrayAdapter;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;


public class Contacts_fragment extends ListFragment implements OnClickListener{

	// Create display object
	ArrayList<ParseObject> friends;
	ArrayList<String> objectIds;
			
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		friends = new ArrayList<ParseObject>();
		objectIds = new ArrayList<String>();
		ParseObject[] friends = findFriends();
		
		if(friends == null){return;}  //TODO no contacts
		
		setListAdapter(new ContactsArrayAdapter(getActivity(), friends));
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {

		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(getActivity().getApplicationContext(), selectedValue, Toast.LENGTH_SHORT).show();

	}

	//query the friend lists
	private ParseObject[] findFriends() {
		ParseObject[] friendsObjectArray;
		// Create query for objects of type Friend
		ParseQuery<ParseObject> friendQuery = ParseQuery.getQuery("Friend");
		ParseQuery<ParseObject> userQuery = ParseQuery.getQuery("_User");

		// Restrict to cases where the user_id is the current user.
		friendQuery.whereEqualTo("User_id", ParseUser.getCurrentUser());
		
		try {
		
			List<ParseObject> friendRecords = friendQuery.find();
			int numOfFriends = friendRecords.size();
			friendsObjectArray = new ParseObject[numOfFriends];
			
			if(numOfFriends==0){ //no friend
				return null;
			}
			
			ParseObject[] friendRecordsArray = new ParseObject[numOfFriends];
			friendRecords.toArray(friendRecordsArray);
			
			for(int i = 0; i < numOfFriends; i++){
				ParseObject friendRecord = friendRecordsArray[i];
				ParseUser friend = friendRecord.getParseUser("Friend_id");
				ParseObject friendObject = userQuery.get(friend.getObjectId());
				friendsObjectArray[i] = friendObject;
				
			}
	
			return friendsObjectArray;
			
			
		} catch (Exception e) {//no friend
			// TODO Auto-generated catch block
			
			return null;
		}
		
		
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
}
