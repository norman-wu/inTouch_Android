package team12.intouch.adapter;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseQueryAdapter;
import com.parse.ParseUser;

public class FriendQueryAdapter extends ParseQueryAdapter<ParseUser> {
    
    public void FriendQeueryAdapter() {
	}

	public FriendQueryAdapter(Context context, final ArrayList<ParseObject> friends) {
		super(context, new ParseQueryAdapter.QueryFactory<ParseUser>() {
			public ParseQuery<ParseUser> create() {
				Log.d("adapter", "adapter constructed");
				
				// Here we can configure a ParseQuery to display friend objects
				ParseQuery<ParseUser> query = new ParseQuery<ParseUser>("User");
				ArrayList<String> objectID = new ArrayList<String>();
				Log.d("num", friends.size()+"");
				for (ParseObject frd: friends) {
					Log.d("adapter", frd.getObjectId());
					objectID.add(frd.getObjectId());
				}
				
				query.whereContainedIn("objectId", objectID);
				return query;
			}
		});
	}
}
