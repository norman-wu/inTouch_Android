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

public class NearbyAdapter extends ArrayAdapter<ParseObject> {
	private final Context context;

	private final ParseObject[] records;

	public NearbyAdapter(Context context,ParseObject[] records) {
		super(context, R.layout.nearby_row, records);
		this.context = context;
		this.records = records;
	}


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
} 