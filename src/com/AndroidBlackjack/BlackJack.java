package com.AndroidBlackjack;

import java.util.ArrayList;

import com.example.myfirstapp_3.R;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Build;

public class BlackJack extends ActionBarActivity {
	private AndroidBlackjack B1;
	private ImageView[] IV;
	private int Bank;
	private int bet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black_jack);

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	//    t1 = new TextView(this); 
	//	t1 = (TextView)findViewById(R.id.textView1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.black_jack, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_black_jack,
					container, false);
			return rootView;
		}
	}
	public void hitFunc(View view){
		//ImageView iv = (ImageView) findViewById(R.id.imageView1);
		//Drawable d = getResources().getDrawable(R.drawable.card0);
		//iv.setImageDrawable(d);
		B1.hit();
		draw();
		updateSums();
		if(B1.playerSum()>20 ||B1.playerSum()==0){
			dealerTurn();
		}
	}
	
	public void standFunc(View view){
		if(B1.getGameState()){
		dealerTurn();
		}
	}
	
	private void dealerTurn(){
		int d = B1.dealerSum();
		while(d<17 && d!=0){
			dealerdraw();
			B1.dealerHit();
			updateSums();
			d = B1.dealerSum();
		}
			dealerdraw();
			updateSums(1);
	}	
	
	private void updateSums(int i) {
		TextView t2 = (TextView) findViewById(R.id.textView2);
		t2.setText(Integer.toString(B1.dealerSum()));	
		results();
		B1.finish();
	}
	
	private void updateSumsZero(){
		TextView t1 = (TextView) findViewById(R.id.textView1);
		TextView t2 = (TextView) findViewById(R.id.textView2);
		t1.setText("0");
		t2.setText("0");
	}
	
	private void results(){
		int w = B1.win();
		switch(w){
		case 0:
			break;
		case 1:
			Bank += bet;
			break;
		case 2:
			Bank += (bet*2);
			break;
		case 3:
			Bank += (bet*2.5);
			break;
		}
		bankUpdate();
	}

	public void resetFunc(View view){
		if(IV==null){
		IV = new ImageView[15];
		int a = R.id.imageView1;
		a--;
		for(int i=0;i<IV.length;i++){
			a++;
			IV[i] = (ImageView) findViewById(a);
			}
		}
		B1 = new AndroidBlackjack();
		Bank = 1000;
		updateSumsZero();
		clearDraw();
		bankUpdate();
	}
	
	private void clearDraw() {
		for(ImageView m : IV){
			m.setImageDrawable(null);
			}
		}

	public void dealFunc(View view){
		EditText t = (EditText) findViewById(R.id.editText1);
		if(B1==null){
			resetFunc(null);
		}
		bet = Integer.parseInt(t.getText().toString());
		Bank -= bet;
		bankUpdate();
		B1.deal();
		updateSums();
		resetdraw();
		draw();
		if(B1.playerSum()>20){
			dealerTurn();
			}
	}
	
	private void bankUpdate(){
		TextView t = (TextView) findViewById(R.id.textView5);
		String b = Integer.toString(Bank);
		t.setText(b);
	}

	private void updateSums(){
		TextView t1 = new TextView(this);
		t1 = (TextView) findViewById(R.id.textView1);
		TextView t2 = (TextView) findViewById(R.id.textView2);
		int[] a = B1.getSums();
		t1.setText(Integer.toString(a[0]));
		t2.setText(Integer.toString(a[1]));
	}
	
	private void draw(){
		Card[] p = B1.getPlayer();
		for(int j=0;j<p.length;j++){
			if(p[j]!=null){
			IV[j].setImageDrawable(getDrawableCard(p[j]));
			}
			else{
				break;
			}
		}
	}
	
	private void dealerdraw(){
		//ViewGroup vg = findViewById (R.id.;
		//vg.invalidate();
		Card[] d = B1.getDealer();
		for(int j=0;j<d.length;j++){
			if(d[j]!=null){
			IV[j+8].setImageDrawable(getDrawableCard(d[j]));
			//IV[j+8].invalidate();
			//setContentView(R.layout.activity_black_jack);
			}
			else{
				break;
			}
		}
	}
	
	private void resetdraw() {
		for(ImageView m : IV){
			m.setImageDrawable(null);
		}
		IV[8].setImageDrawable(getDrawableCard(B1.getDealerFirst()));
		IV[9].setImageDrawable(getResources().getDrawable(R.drawable.card0));
	}
	
	private Drawable getDrawableCard(Card c){
		switch(c.getFace()){
		case 0:
			return getResources().getDrawable(R.drawable.card0);
		case 1:
			return getResources().getDrawable(R.drawable.card1);
		case 2:
			return getResources().getDrawable(R.drawable.card2);
		case 3:
			return getResources().getDrawable(R.drawable.card3);
		case 4:
			return getResources().getDrawable(R.drawable.card4);
		case 5:
			return getResources().getDrawable(R.drawable.card5);
		case 6:
			return getResources().getDrawable(R.drawable.card6);
		case 7:
			return getResources().getDrawable(R.drawable.card7);
		case 8:
			return getResources().getDrawable(R.drawable.card8);
		case 9:
			return getResources().getDrawable(R.drawable.card9);
		case 10:
			return getResources().getDrawable(R.drawable.card10);
		case 11:
			return getResources().getDrawable(R.drawable.card11);
		case 12:
			return getResources().getDrawable(R.drawable.card12);
		case 13:
			return getResources().getDrawable(R.drawable.card13);
		}
		return null;

		
		
	}
}