package com.itrcr.custom.helpers;

import java.io.IOException;
import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.util.Log;


public class ThreadMapLoader implements Runnable{

	@Override
	public void run() {
		AssetManager assetManager = MapView.ct.getAssets();
		InputStream istr;
		Rect drawRegion;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inPreferredConfig = Config.RGB_565;
		drawRegion = new Rect(MapView.xPosition, MapView.yPosition,
				MapView.xPosition+MapView.imgSizeX, MapView.yPosition+MapView.imgSizeY);
		try {
			istr = assetManager.open("mapa.png");
			BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(istr, true);
			while(true){
				if(MapView.changed){
					drawRegion.left = MapView.xPosition;
					drawRegion.top = MapView.yPosition;
					drawRegion.right = MapView.xPosition+MapView.imgSizeX;
					drawRegion.bottom = MapView.yPosition+MapView.imgSizeY;
					
					MapView.map = decoder.decodeRegion(drawRegion, options);
					MapView.changed = false;
				}
			}
		} catch (IOException e) {
			Log.e("Thread MapLoader Error", e.getMessage());
		}
	}

}
