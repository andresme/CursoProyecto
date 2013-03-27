package com.itrcr.custom.helpers;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ImageView;

import com.itrcr.custom.utils.Utils;

public class MapView extends ImageView {
	
	public static Bitmap map;
	private Thread loader;
	public static Context ct;
	private DisplayMetrics metrics;
	public static int xPosition;
	public static int yPosition;
	private int minXScroll;
	private int minYScroll;
	private int maxXScroll;
	private int maxYScroll;
	public static int imgSizeX;
	public static int imgSizeY;
	public static boolean changed;
	
	
	public MapView(Context context, AttributeSet attrs) {
	    super(context, attrs);
	    
	    sharedConstructor(context);
	}
	
	public MapView(Context context){
		super(context);
		sharedConstructor(context);
	}
	
	public void sharedConstructor(Context context){
		ct = context;
		metrics = this.getContext().getResources().getDisplayMetrics();
		int sizeX = Utils.getWidth(this.getContext(), "mapa.png");
		int sizeY = Utils.getHeight(this.getContext(), "mapa.png");
		xPosition = 0;
	    yPosition = 0;
	    
	    imgSizeX = Math.min(metrics.widthPixels, sizeX);
	    imgSizeY = Math.min(metrics.heightPixels, sizeY);
	    
		map = Utils.getBitmapFromAsset(this.getContext(), "mapa.png", 0, 0, imgSizeX, imgSizeY);
		minXScroll = 0;
	    minYScroll = 0;
	    maxXScroll = -sizeX + metrics.widthPixels;
	    maxYScroll = -sizeY + metrics.heightPixels;
	    loader = new Thread(new ThreadMapLoader());
	    loader.start();
	    changed = false;
	}
	
	@Override
	public void onDraw(Canvas canvas){
		super.onDraw(canvas);
		Canvas g = canvas;
		
		//if(xPosition < minXScroll) xPosition = minXScroll;
		//if(yPosition < minYScroll) yPosition = minYScroll;
		//if(xPosition > maxXScroll) xPosition = maxXScroll;
		//if(yPosition > maxYScroll) yPosition = maxYScroll;
		g.drawBitmap(map, 0, 0, null);	}

	public int getxPosition() {
		return xPosition;
	}

	public void setxPosition(int xPosition) {
		this.xPosition = xPosition;
	}

	public int getyPosition() {
		return yPosition;
	}

	public void setyPosition(int yPosition) {
		this.yPosition = yPosition;
	}

	public boolean isChanged() {
		return changed;
	}
	
	public void setMap(Bitmap map){
		this.map = map;
	}

	public void setChanged(boolean changed) {
		this.changed = changed;
	}

	

}
