package team12.intouch.entities;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;

public class GPSAPI implements GPS{
	private double lat;
	private double lng;
	
	public void createGPS(Context context) {
		LocationManager lm=(LocationManager)context.getSystemService(Context.LOCATION_SERVICE);
		Criteria c=new Criteria(); 
		//if we pass false than
		//it will check first satellite location than Internet and than Sim Network
		String provider=lm.getBestProvider(c, false);
		Location l=lm.getLastKnownLocation(provider);
		if(l!=null)
		{
			lng=l.getLongitude();
			lat=l.getLatitude();
		}
		else
		{
			lng=-1;
			lat=-1;
		}
	}
	
	public double getLatitude() {
		return lat;
	}
	
	public double getLongitude() {
		return lng;
	}

	
}
