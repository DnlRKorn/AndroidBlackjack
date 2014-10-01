package com.AndroidBlackjack;

import com.example.myfirstapp_3.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
	/*	switch(item.getItemId()){
		case R.id.action_settings:
			openSettings();
			return true;
		case R.id.action_search:
			openSearch();
			return true;
		case default:*/
            return super.onOptionsItemSelected(item);
		}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	/*public void hitFunc(View view){
		Intent intent = new Intent(this, AndroidBlackjack.hit());
	}
	
	public void standFunc(View view){
		Intent intent = new Intent(this, AndroidBlackjack.stand());
	}*/
	public void playFunc(View view){
	//	LayoutInflater inflater = getLayoutInflater();
		//ViewGroup vg = (ViewGroup) findViewById(R.id.container);
		//vg.setView
//		ViewGroup con = getViewGroup();
//		inflater.inflate(R.layout.fragment_play,vg);
		//getSupportFragmentManager().beginTransaction()
	//	.add(R.id.container, R.layout.fragment_play).commit(); 
            // doStuff
           // CurrentActivity.this.startActivity(intentMain);
           // Log.i("Content "," Main layout ");
		Intent intent = new Intent(getBaseContext(), BlackJack.class);
		startActivity(intent);
    }
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}
}