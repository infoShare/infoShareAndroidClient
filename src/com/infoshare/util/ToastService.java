package com.infoshare.util;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.infoshare.R;

public class ToastService {

	public static void showToast(final Context ctx, final String text){
		Activity activity = (Activity) ctx;
		activity.runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				View customToastRoot = createToastView(ctx, text);
				Toast customToast = new Toast(ctx);
				customToast.setView(customToastRoot);
				customToast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL, 0, 0);
				customToast.setDuration(Toast.LENGTH_SHORT);
				customToast.show();
			}
		});
	}

	private static View createToastView(Context ctx, String text) {
		LayoutInflater inflater = LayoutInflater.from(ctx);
		View customToastRoot = inflater.inflate(R.layout.custom_toast, null);
		TextView toastText = (TextView) customToastRoot.findViewById(R.id.toastText);
		toastText.setText(text);
		return customToastRoot;
	}

}
