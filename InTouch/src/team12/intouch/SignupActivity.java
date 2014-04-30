package team12.intouch;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

public class SignupActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setTitle("Sign Up");
		setContentView(R.layout.activity_signup);
	//	getWindow().setTitle("Sign up"); 
		
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new Signup_fragment()).commit();
		}
	}
	
}
