package team12.intouch;

import java.util.Locale;
import com.parse.*;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Profile_fragment extends Fragment implements OnClickListener {
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
     // TODO Auto-generated method stub
     super.onCreate(savedInstanceState); 
     
    }
	
	
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
    	
        // Inflate the layout for this fragment
    	View v = inflater.inflate(R.layout.fragment_profile, container, false);
    	
    	//get the text view for user name and email
    	//TODO need to make it final?
    	final TextView usernameBox= (TextView) v.findViewById(R.id.usernameView);
    	final TextView emailBox= (TextView) v.findViewById(R.id.emailView);
    	
    	
    	ParseUser currentUser = ParseUser.getCurrentUser(); 
    	String userRecordId = currentUser.getObjectId();
    	
    	
    	//get data from parse   	
    	ParseQuery<ParseObject> query = ParseQuery.getQuery("_User");
    	query.getInBackground(userRecordId, new GetCallback<ParseObject>() {
    	  public void done(ParseObject userRecord, ParseException e) {
    	    if (e == null) {
    	      // get the user record
    	    	String username = userRecord.getString("username");
    	    	String email = userRecord.getString("email");
    	    	
    	    	email = ( email == null || email.equals(""))? "No email set up yet" : email;
    	    	username = ( username == null || username.equals(""))? "No username set up yet" : username;
    	    	
    	    	usernameBox.setText(username);
    	    	emailBox.setText(email);
    	    } else {
    	      // something went wrong
    	     Log.e("test","error getting data from parse",e);
    	    }
    	  }
    	});
    	return v;
    }


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
