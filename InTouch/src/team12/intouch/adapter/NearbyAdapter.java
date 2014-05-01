package team12.intouch.adapter;

import team12.intouch.R;
import team12.intouch.SignupActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class NearbyAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;

	private final ParseObject[] records;
	private final ParseObject[] friends;

	public NearbyAdapter(Context context,ParseObject[] records, ParseObject[] friends) {
		super(context, R.layout.nearby_row, records);
		this.context = context;
		this.records = records;
		this.friends = friends;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("test2","msg from get view");

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.nearby_row, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		final ImageButton addIcon = (ImageButton) rowView.findViewById(R.id.add);
		addIcon.setTag(position);
		

		final ParseObject displayUserRecord = records[position];

		String name = displayUserRecord.getString("username");
		
		textView.setText(name);

		Log.d("tes", name+" //name from get view");
		
		addIcon.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int position=(Integer)v.getTag();
				ParseObject displayUserRecord = records[position];
				if (checkFriend(displayUserRecord.getObjectId())) {
					return;
				} else {
					addIcon.setImageResource(R.drawable.ic_action_accept);
					addFriend(displayUserRecord);
				}
			}

		});


		//get photo
		ParseFile profile = (ParseFile)displayUserRecord.get("Photo");
		byte[] image;
		try {
			image = profile.getData(); //throw exception
			Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
			imageView.setImageBitmap(bmp);
			if (checkFriend(displayUserRecord.getObjectId())) {
				addIcon.setImageResource(R.drawable.ic_action_accept);
			
			} else {
				addIcon.setImageResource(R.drawable.ic_action_add_person);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			imageView.setImageResource(R.drawable.default_profile);
			return rowView;
		}  
		
		return rowView;
	}
	
	//check if the userID is the friend of current user
	public boolean checkFriend(String id) {

		if (friends.length == 0) {
			return false;
		}
		
		for (ParseObject frd: friends) {
			ParseUser curUser = frd.getParseUser("Friend_id");
			Log.d("checkFriends1",curUser.getObjectId());
			Log.d("checkFriends2",id);
			if (curUser.getObjectId().equals(id)) {
				return true;
			}
		}
		return false;
		
	}
	
	
	
	//create a new friendship in the backend database
	public void addFriend(ParseObject id) {
		// TODO Auto-generated method stub
		ParseObject newFriend = new ParseObject("Friend");
		newFriend.put("User_id", ParseUser.getCurrentUser());
		newFriend.put("Friend_id", id);
		newFriend.saveInBackground();
	}
} 