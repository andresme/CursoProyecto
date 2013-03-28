package com.itrcr.custom.helpers;

import com.itrcr.custom.utils.Utils;

import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.DisplayMetrics;
import android.util.Log;


public class ThreadMapLoader extends AsyncTask<Void, Void, Void>{

	private int minXScroll;
	private int minYScroll;
	private int maxXScroll;
	private int maxYScroll;
	private int displayX;
	private int displayY;
	private Rect drawRegion;

	public static boolean running;


	@Override
	protected void onPreExecute() {
		super.onPreExecute();

		DisplayMetrics metrics = MapView.ct.getResources().getDisplayMetrics();

		displayX = metrics.widthPixels;
		displayY = metrics.heightPixels;

		MapView.map = Utils.getBitmapFromAsset(MapView.ct, "mapa.png", 0, 0, displayX, displayY);

		minXScroll = 0;
		minYScroll = 0;
		maxXScroll = Utils.getWidth(MapView.ct.getApplicationContext(), "mapa.png")-displayX;
		maxYScroll = Utils.getHeight(MapView.ct.getApplicationContext(), "mapa.png")-displayY;

		drawRegion = new Rect(MapView.xPosition, MapView.yPosition,
				MapView.xPosition+displayX, MapView.yPosition+displayY);
		running = true;
	}

	@Override
	protected Void doInBackground(Void... arg0) {
		while(running){
			if(MapView.xPosition <= minXScroll) 
				MapView.xPosition = minXScroll;
			if(MapView.yPosition <= minYScroll) 
				MapView.yPosition = minYScroll;
			if(MapView.xPosition >= maxXScroll) 
				MapView.xPosition = maxXScroll;
			if(MapView.yPosition >= maxYScroll) 
				MapView.yPosition = maxYScroll;

			drawRegion.left = MapView.xPosition;
			drawRegion.top = MapView.yPosition;
			drawRegion.right = (MapView.xPosition + displayX);
			drawRegion.bottom = (MapView.yPosition + displayY);

			MapView.map.setBounds(drawRegion);
			MapView.map.invalidateSelf();

		}
		Log.d("Thread", "finished");
		return null;
	}

}
