package com.itcr.custom.utils;

import java.io.IOException;
import java.io.InputStream;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

public class Utils {

	public static BitmapDrawable getBitmapFromAsset(Context context, String strName, int x0, int y0,
			int x1, int y1) {
		AssetManager assetManager = context.getAssets();

		InputStream istr;
		BitmapDrawable bitmapDrawable = null;
		try {
			istr = assetManager.open(strName);
			bitmapDrawable = new BitmapDrawable(context.getResources(), istr);
			bitmapDrawable.setBounds(new Rect(x0, y0, x1, y1));
			bitmapDrawable.setTileModeX(Shader.TileMode.CLAMP);
		} catch (IOException e) {
			return null;
		}
		return bitmapDrawable;
	}

	public static int getHeight(Context context, String strName) {
		AssetManager assetManager = context.getAssets();
		InputStream istr;
		try {
			istr = assetManager.open(strName);
			BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(istr, false);
			return decoder.getHeight();
		} catch (IOException e) {
			return -1;
		}
	}

	public static int getWidth(Context context, String strName) {
		AssetManager assetManager = context.getAssets();
		InputStream istr;
		try {
			istr = assetManager.open(strName);
			BitmapRegionDecoder decoder = BitmapRegionDecoder.newInstance(istr, false);
			return decoder.getWidth();
		} catch (IOException e) {
			return -1;
		}
	}

}
