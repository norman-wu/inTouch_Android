package team12.intouch.adapter;

import java.util.Iterator;
import java.util.List;

import com.parse.ParseException;
import com.parse.ParseFile;
import com.parse.ParseObject;

import team12.intouch.*;
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

public class NearbyAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;

	private final ParseObject[] records;

	public NearbyAdapter(Context context,ParseObject[] records) {
		super(context, R.layout.nearby_row, records);
		this.context = context;
		this.records = records;
	}

	@SuppressWarnings("finally")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Log.d("test2","msg from get view");

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View rowView = inflater.inflate(R.layout.nearby_row, parent, false);

		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

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
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			imageView.setImageResource(R.drawable.default_profile);
		}  finally
		{
			return rowView;
		}
	}
} 


//
//import com.parse.ParseObject;
//
//import team12.intouch.*;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//public class NearbyAdapter extends ArrayAdapter<String> {
//  private final Context context;
//  private final String[] values;
//
//  public NearbyAdapter(Context context, ParseObject[] values) {
//    super(context, R.layout.nearby_row, values);
//    this.context = context;
//    this.values = values;
//  }
//
//  @Override
//  public View getView(int position, View convertView, ViewGroup parent) {
//    LayoutInflater inflater = (LayoutInflater) context
//        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//    View rowView = inflater.inflate(R.layout.nearby_row, parent, false);
//    TextView textView = (TextView) rowView.findViewById(R.id.label);
//    ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
//    textView.setText(values[position]);
//    // Change the icon for Windows and iPhone
//    String s = values[position];
//    if (s.startsWith("Windows7") || s.startsWith("iPhone")
//        || s.startsWith("Solaris")) {
//      imageView.setImageResource(R.drawable.default_profile);
//    } else {
//      imageView.setImageResource(R.drawable.default_profile);
//    }
//
//    return rowView;
//  }
//} 
