package com.itrcr.custom.helpers;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class MapView extends ImageView {

	public static BitmapDrawable map;
	public static ThreadMapLoader loader;
	public static Context ct;
	public static int xPosition;
	public static int yPosition;



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
		
		xPosition = 0;
		yPosition = 0;

		loader = new ThreadMapLoader();
		loader.execute();


	}

	@Override
	public void onDraw(Canvas g){
		super.onDraw(g);
		g.translate(-xPosition, -yPosition);
		map.draw(g);
	}

}
