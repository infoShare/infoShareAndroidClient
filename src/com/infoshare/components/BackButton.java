package com.infoshare.components;

import android.app.Activity;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

public class BackButton extends TextView {

	private static final OnClickListener closeListener = new OnClickListener() {
		@Override
		public void onClick(View view) {
			Activity host = (Activity) view.getContext();
			host.finish();
		}
	};
	
	public BackButton(Context context) {
		super(context);
	}

	public BackButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
	}

	public BackButton(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		setOnClickListener(closeListener);
	}

}
