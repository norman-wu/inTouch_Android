package team12.intouch.adapter;
 
import team12.intouch.R;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseFile;
import com.parse.ParseObject;
 
public class ContactsArrayAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;
	private final ParseObject[] records;
 
	public ContactsArrayAdapter(Context context, ParseObject[] records) {
		super(context, R.layout.contact_row, records);
		this.context = context;
		this.records = records;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);


		View rowView = inflater.inflate(R.layout.contact_row, parent, false);
		
		//if no friends
		if (records == null){
			return rowView.findViewById(android.R.id.empty); //return empty view
		}

		TextView textView = (TextView) rowView.findViewById(R.id.contact_label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.contact_icon);

		ParseObject displayUserRecord = records[position];

		String name = displayUserRecord.getString("username");
		//String email = displayUserRecord.getString("email");
		textView.setText(name);

		Log.d("tes", name+" //name from get view");


		//get photo
		ParseFile profile = (ParseFile)displayUserRecord.get("Photo");

		byte[] image;
		try {
			image = profile.getData(); //throw exception
			Bitmap bmp = BitmapFactory.decodeByteArray(image, 0, image.length);
			imageView.setImageBitmap(bmp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			imageView.setImageResource(R.drawable.default_profile);
			return rowView;
		}  

		return rowView;

	}
//		LayoutInflater inflater = (LayoutInflater) context
//			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
// 
//		View rowView = inflater.inflate(R.layout.fragment_contacts, parent, false);
//		TextView textView = (TextView) rowView.findViewById(R.id.label);
//		ImageView imageView = (ImageView) rowView.findViewById(R.id.logo);
//		textView.setText(records[position]);
// 
//		// Change icon based on name
//		String s = records[position];
// 
//		System.out.println(s);
// 
//		imageView.setImageResource(R.drawable.default_profile);
		
		
		
		
		
//		if (s.equals("WindowsMobile")) {
//			imageView.setImageResource(R.drawable.windowsmobile_logo);
//		} else if (s.equals("iOS")) {
//			imageView.setImageResource(R.drawable.ios_logo);
//		} else if (s.equals("Blackberry")) {
//			imageView.setImageResource(R.drawable.blackberry_logo);
//		} else {
//			imageView.setImageResource(R.drawable.android_logo);
//		}
// 

}