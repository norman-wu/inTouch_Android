package team12.intouch;

import java.util.Locale;

import team12.intouch.adapter.ContactsArrayAdapter;

import com.parse.*;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


public class Contacts_fragment extends ListFragment {

	private ParseQueryAdapter<ParseObject> mainAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		mainAdapter = new ParseQueryAdapter<ParseObject>(getActivity(), "User");
		mainAdapter.setTextKey("username");
		mainAdapter.setImageKey("photo");
		
	    setListAdapter(mainAdapter);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		
		//get selected items
		String selectedValue = (String) getListAdapter().getItem(position);
		Toast.makeText(getActivity().getApplicationContext(), selectedValue, Toast.LENGTH_SHORT).show();

	}
}
