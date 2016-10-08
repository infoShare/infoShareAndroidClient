package com.infoshare.mock;

import android.util.Log;

public abstract class AbstractMock {

	private static final String TAG = "Mocked Serivce";
	
	protected void log(String message) {
		Log.d(TAG, message);
	}

}
