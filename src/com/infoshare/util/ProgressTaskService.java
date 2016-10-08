package com.infoshare.util;

import android.app.Activity;
import android.app.ProgressDialog;

public class ProgressTaskService {

	public static void execute(final Activity ctx, int titleId, int msgId, final Runnable task) {
		String title = ctx.getResources().getString(titleId);
		String msg = ctx.getResources().getString(msgId);
		final ProgressDialog pd = new ProgressDialog(ctx);
	    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    pd.setMessage(msg);
	    pd.setTitle(title);
	    pd.setIndeterminate(true);
	    pd.setCancelable(false);
	    pd.show();
	    ctx.runOnUiThread(new Runnable() {
	      @Override
	      public void run()
	      {
	    	  try {
	    		  Thread.sleep(5000);
	    	  } catch (InterruptedException e) {
	    		  e.printStackTrace();
	    	  }
	    	  task.run();
	    	  pd.dismiss();
	      }
	    });
	}

}
