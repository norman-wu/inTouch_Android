package team12.intouch;

import java.util.Locale;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class Login_fragment extends Fragment implements OnClickListener{
	String tag = "Login_fragment";

	Button loginIn = null;
	Button signUp = null;
	//Button forgetPass = null;
	private EditText mUserNameEditText;
	private EditText mPasswordEditText;

	// flag for Internet connection status
	Boolean isInternetPresent = false;
	// Connection detector class
	ConnectionDetector cd;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View v = inflater.inflate(R.layout.fragment_main, container, false);
		// creating connection detector class instance
		cd = new ConnectionDetector(getActivity().getApplicationContext());

		loginIn = (Button) v.findViewById(R.id.signin);
		signUp = (Button) v.findViewById(R.id.signup);
		mUserNameEditText = (EditText) v.findViewById(R.id.username);
		mPasswordEditText = (EditText) v.findViewById(R.id.password);

		loginIn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();
				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					attemptLogin();
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(getActivity(), "No Internet Connection",
							"You don't have internet connection.", false);
				}

			}
		});

		signUp.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				//Toast.makeText(getActivity().getApplicationContext(), "the button is clicked!!!", Toast.LENGTH_SHORT).show();

				// get Internet status
				isInternetPresent = cd.isConnectingToInternet();
				// check for Internet status
				if (isInternetPresent) {
					// Internet Connection is Present
					// make HTTP requests
					Intent in =  new Intent(getActivity(), SignupActivity.class);
					startActivity(in);
				} else {
					// Internet connection is not present
					// Ask user to connect to Internet
					showAlertDialog(getActivity(), "No Internet Connection",
							"You don't have internet connection.", false);
				}

			}
		});

		return v;
	}

	public void attemptLogin() {

		clearErrors();

		// Store values at the time of the login attempt.
		String username = mUserNameEditText.getText().toString();
		String password = mPasswordEditText.getText().toString();

		boolean cancel = false;
		View focusView = null;

		// Check for a valid password.
		if (TextUtils.isEmpty(password)) {
			mPasswordEditText.setError(getString(R.string.error_field_required));
			focusView = mPasswordEditText;
			cancel = true;
		} 

		// Check for a valid email address.
		if (TextUtils.isEmpty(username)) {
			mUserNameEditText.setError(getString(R.string.error_field_required));
			focusView = mUserNameEditText;
			cancel = true;
		}

		if (cancel) {
			// There was an error; don't attempt login and focus the first
			// form field with an error.
			focusView.requestFocus();
		} else {
			// perform the user login attempt.
			login(username.toLowerCase(Locale.getDefault()), password);
		}
	}

	private void clearErrors(){
		mUserNameEditText.setError(null);
		mPasswordEditText.setError(null);
	}


	private void login(String lowerCase, String password) {
		// TODO Auto-generated method stub
		ParseUser.logInInBackground(lowerCase, password, new LogInCallback() {
			@Override
			public void done(ParseUser user, ParseException e) {
				// TODO Auto-generated method stub
				if(e == null)
					loginSuccessful();
				else
					loginUnSuccessful();
			}
		});

	}

	protected void loginSuccessful() {
		Intent in =  new Intent(getActivity(), TabActivity.class);
		startActivity(in);
	}

	protected void loginUnSuccessful() {
		// TODO Auto-generated method stub

		Toast.makeText(getActivity().getApplicationContext(), "", Toast.LENGTH_SHORT).show();
		showAlertDialog(getActivity(),"Login", "Username or Password is invalid.", false);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}

	@SuppressWarnings("deprecation")
	public void showAlertDialog(Context context, String title, String message, Boolean status) {
		AlertDialog alertDialog = new AlertDialog.Builder(context).create();

		// Setting Dialog Title
		alertDialog.setTitle(title);

		// Setting Dialog Message
		alertDialog.setMessage(message);

		// Setting alert dialog icon
		alertDialog.setIcon(R.drawable.fail);

		// Setting OK Button
		alertDialog.setButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
			}
		});

		// Showing Alert Message
		alertDialog.show();
	}




}