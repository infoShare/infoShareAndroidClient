package com.infoshare.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

import com.infoshare.R;

public class AlertService {
	
	public static void displayDialog(final Context ctx, final int titleId,final int messageId, final Runnable action) {
		Activity act = (Activity) ctx;
		act.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(ctx)
		        .setTitle(titleId)
		        .setMessage(messageId)
		        .setNegativeButton(android.R.string.no, null)
		        .setPositiveButton(android.R.string.yes, new OnClickListener() {
		            public void onClick(DialogInterface arg0, int arg1) {
		            	action.run();
		            }
		        }).create().show();
				
			}
		});
	}
	
	public static void displayAlert(Context ctx, int messageId, final Runnable action) {
		AlertService.displayDialog(ctx, R.string.alertTitle, messageId, action);
	}
	
	public static void displayWarning(Context ctx, int messageId, final Runnable action) {
		AlertService.displayDialog(ctx, R.string.warningTitle, messageId, action);
	}
	
	public static void displayOkDialog(final Context ctx, final int titleId, final int messageId) {
		Activity act = (Activity) ctx;
		act.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				new AlertDialog.Builder(ctx)
		        .setTitle(titleId)
		        .setMessage(messageId)
		        .setNegativeButton(android.R.string.ok, null)
		        .create().show();		
			}
		});		
	}
	
}
