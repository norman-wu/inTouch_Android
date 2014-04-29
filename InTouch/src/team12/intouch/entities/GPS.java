package team12.intouch.entities;

import android.content.Context;
import android.location.Location;

public interface GPS {
	public double getLatitude();
	public double getLongitude();
	public void createGPS(Context context);
}
