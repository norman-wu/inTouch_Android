package team12.intouch;

import java.text.DecimalFormat;
import java.util.Locale;

import team12.intouch.entities.GPSAPI;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;
public class Signup_fragment extends Fragment implements OnClickListener{
	
	private Button signUp;
	private EditText username;
	private EditText email;
	private EditText pwd;
	private EditText pwdConfirm;

  //private Location lastLocation = null;
  private Location currentLocation = null;
  private static Context mContext;
  
	@Override
    public void onCreate(Bundle savedInstanceState) {
     // TODO Auto-generated method stub
	  super.onCreate(savedInstanceState);  		
    }
    

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
        // Inflate the layout for this fragment
    	View v = inflater.inflate(R.layout.fragment_signup, container, false);

    	signUp = (Button) v.findViewById(R.id.signupButton);
    	email = (EditText) v.findViewById(R.id.email);
       	username = (EditText) v.findViewById(R.id.usernameSignup);
    	pwd = (EditText) v.findViewById(R.id.passwordSignup);
    	pwdConfirm = (EditText) v.findViewById(R.id.passwordConfirm);
 

    signUp.setOnClickListener(new OnClickListener() {
 
    @Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    	Log.d("debug", "the sign up button !!!");
    	
		clearErrors();

		// Store values at the time of the sign up attempt.
		String name = username.getText().toString();
		String emailAdd = email.getText().toString();
		String password = pwd.getText().toString();
		String passwordCon = pwdConfirm.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid username.
		if (TextUtils.isEmpty(name)) {
			username.setError(getString(R.string.error_field_required));
			focusView = username;
			cancel = true;
		} 
		
		// Check for a valid email
		if (TextUtils.isEmpty(emailAdd)) {
			email.setError(getString(R.string.error_field_required));
			focusView = email;
			cancel = true;
		} 
		
		// Check for a valid password.
		if (TextUtils.isEmpty(password)) {
			pwd.setError(getString(R.string.error_field_required));
			focusView = pwd;
			cancel = true;
		}
		
		// Check for a valid confirm password.
		if (TextUtils.isEmpty(passwordCon)) {
			pwdConfirm.setError(getString(R.string.error_field_required));
			focusView = pwd;
			cancel = true;
		} else if (!password.equals(passwordCon)) {
			focusView = pwdConfirm;
			Toast.makeText(getActivity(), "Password does not match the confirm password", Toast.LENGTH_SHORT).show();;
			cancel = true;
		}
		
		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// perform the user sigh up attempt.
			Toast.makeText(getActivity().getApplicationContext(), "signing up...", Toast.LENGTH_SHORT).show();
			signUp(name.toLowerCase(Locale.getDefault()), emailAdd, password);
		}
	   } 
    });
    
    
	return v;
}
  
    
	private void signUp(final String mUsername, String mEmail, String mPassword) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity().getApplicationContext(), mUsername, Toast.LENGTH_SHORT).show();
//		//----------------GPS------------------
//		//no longer used
//	    GPSAPI loc = new GPSAPI();
//	    Context mcontext = null;
//		loc.createGPS(this.getActivity().getApplicationContext());
//	    //ParseGeoPoint myPoint = new ParseGeoPoint(loc.getLatitude(),loc.getLongitude());
//	    
//	    String str = new DecimalFormat("#").format(loc.getLatitude());
//	    
//	    Log.e("GPS", str);
//	    str = new DecimalFormat("#").format(loc.getLongitude());
//	    Log.e("GPS", str);
//	  //----------------GPS------------------
	    
		ParseUser user = new ParseUser();
		user.setUsername(mUsername);
		user.setPassword(mPassword);
		user.setEmail(mEmail);
		
		
		user.signUpInBackground(new SignUpCallback() {
		  public void done(ParseException e) {
		    if (e == null) {
		      signUpMsg("Account Created Successfully");
		      Intent in = new Intent(getActivity().getApplicationContext(),TabActivity.class);
		      startActivity(in);
		    } else {
		      // Sign up didn't succeed. Look at the ParseException
		      // to figure out what went wrong
		      signUpMsg(e.getMessage());
		    }
		  }
		});
	}

	protected void signUpMsg(String msg) {
		// TODO Auto-generated method stub
		Toast.makeText(getActivity().getApplicationContext(), msg, Toast.LENGTH_SHORT).show();	
	}

    
    @Override
   	public void onClick(View v) {
   		// TODO Auto-generated method stub
   		
   	}
    
    private void clearErrors(){
    	username.setError(null);
		email.setError(null);
		pwd.setError(null);
		pwdConfirm.setError(null);
	}

}


	