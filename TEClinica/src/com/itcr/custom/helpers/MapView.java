package com.itcr.custom.helpers;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.itcr.custom.utils.Utils;

public class MapView extends View {

	private static final String MAP = "mapa.png";
	private static final int INVALID_POINTER_ID = -1;

	private BitmapDrawable mMap;
	private float mPosX;
	private float mPosY;
	private float mMin;
	private DisplayMetrics mMetrics;

	private int mSizeX;
	private int mSizeY;

	private int mActivePointerId = INVALID_POINTER_ID;
	private float mLastTouchX;
	private float mLastTouchY;

	private ScaleGestureDetector mScaleDetector;
	private float mScaleFactor = 1.f;



	public MapView(Context context) {
		this(context, null, 0);
		sharedConstructor(context);
	}

	public MapView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		sharedConstructor(context);
	}

	public MapView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		mScaleDetector = new ScaleGestureDetector(context, new ScaleListener());
		sharedConstructor(context);
	}

	public void sharedConstructor(Context context){
		float Xpx;
		float Ypy;
		mSizeX = Utils.getWidth(this.getContext(), MAP);
		mSizeY = Utils.getHeight(this.getContext(), MAP);
		mMap = Utils.getBitmapFromAsset(this.getContext(), MAP, 0, 0, mSizeX, mSizeY);
		mMetrics = new DisplayMetrics();
		((Activity) getContext()).getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
		Xpx = mMetrics.widthPixels;
		Ypy = mMetrics.heightPixels;
		mMin = Math.max((Xpx/mSizeX), (Ypy/mSizeY));

	}

	@Override
	public boolean onTouchEvent(MotionEvent ev){
		// Let the ScaleGestureDetector inspect all events.
		mScaleDetector.onTouchEvent(ev);

		final int action = ev.getAction();
		switch (action & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN: {
			final float x = ev.getX();
			final float y = ev.getY();

			mLastTouchX = x;
			mLastTouchY = y;
			mActivePointerId = ev.getPointerId(0);
			break;
		}

		case MotionEvent.ACTION_MOVE: {
			final int pointerIndex = ev.findPointerIndex(mActivePointerId);
			final float x = ev.getX(pointerIndex);
			final float y = ev.getY(pointerIndex);

			// Only move if the ScaleGestureDetector isn't processing a gesture.
			if (!mScaleDetector.isInProgress()) {
				final float dx = x - mLastTouchX;
				final float dy = y - mLastTouchY;

				mPosX += dx;
				mPosY += dy;


				invalidate();
			}

			mLastTouchX = x;
			mLastTouchY = y;

			break;
		}

		case MotionEvent.ACTION_UP: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_CANCEL: {
			mActivePointerId = INVALID_POINTER_ID;
			break;
		}

		case MotionEvent.ACTION_POINTER_UP: {
			final int pointerIndex = (ev.getAction() & MotionEvent.ACTION_POINTER_INDEX_MASK) 
					>> MotionEvent.ACTION_POINTER_INDEX_SHIFT;
					final int pointerId = ev.getPointerId(pointerIndex);
					if (pointerId == mActivePointerId) {
						// This was our active pointer going up. Choose a new
						// active pointer and adjust accordingly.
						final int newPointerIndex = pointerIndex == 0 ? 1 : 0;
						mLastTouchX = ev.getX(newPointerIndex);
						mLastTouchY = ev.getY(newPointerIndex);
						mActivePointerId = ev.getPointerId(newPointerIndex);
					}
					break;
		}
		}


		return true;
	}

	@Override
	public void onDraw(Canvas g){
		super.onDraw(g);
		if(mPosX > 0) mPosX = 0;
		if(mPosY > 0) mPosY = 0;

        if(mPosX < (-1*mSizeX*mScaleFactor)+mMetrics.widthPixels) mPosX = (-1*mSizeX*mScaleFactor)+mMetrics.widthPixels;
		if(mPosY < (-1*mSizeY*mScaleFactor)+mMetrics.heightPixels) mPosY = (-1*mSizeY*mScaleFactor)+mMetrics.heightPixels;

        g.translate(mPosX, mPosY);
		g.scale(mScaleFactor, mScaleFactor);

		mMap.draw(g);
		g.restore();
	}

	private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			mScaleFactor *= detector.getScaleFactor();

			// Don't let the object get too small or too large.
			mScaleFactor = Math.max(mMin, Math.min(mScaleFactor, 1.5f));

			invalidate();
			return true;
		}
	}

}
