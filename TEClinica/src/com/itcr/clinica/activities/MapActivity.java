package com.itcr.clinica.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

import com.itcr.clinica.R;
import com.itrcr.custom.helpers.MapView;
import com.itrcr.custom.helpers.ThreadMapLoader;

public class MapActivity extends Activity implements OnGestureListener{

	private GestureDetector gestureScanner;
	private MapView mapView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		mapView = new MapView(this);
		setContentView(mapView);
		gestureScanner = new GestureDetector(this, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	@Override
	protected void onPause(){
		super.onPause();
		ThreadMapLoader.running = false;
	}

	@Override 
	protected void onResume(){
		super.onResume();
		MapView.loader = new ThreadMapLoader();
		MapView.loader.execute();
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
		ThreadMapLoader.running = false;
	}

	@Override
	public boolean onTouchEvent(MotionEvent me){
		return gestureScanner.onTouchEvent(me);
	}

	@Override
	public boolean onDown(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float x1,
			float y1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onScroll(MotionEvent arg0, MotionEvent arg1, float x1,
			float y1) {

		MapView.xPosition += (int)(x1);
		MapView.yPosition += (int)(y1);
		mapView.invalidate();

		return true;
	}

	@Override
	public void onShowPress(MotionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean onSingleTapUp(MotionEvent arg0) {
		// TODO Auto-generated method stub
		return false;
	}
}
