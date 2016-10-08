package com.infoshare.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

public class ProgressTask extends AsyncTask<String, Void, Boolean> 
{
	private Runnable task;
    private ProgressDialog dialog;

    public ProgressTask(Context ctx, int titleId, int msgId, Runnable task) 
    {
        this.task = task;
        this.dialog = createProgressDialog(ctx, titleId, msgId);
    }
    
    private ProgressDialog createProgressDialog(Context ctx, int titleId, int msgId){
    	String title = ctx.getResources().getString(titleId);
		String msg = ctx.getResources().getString(msgId);
		ProgressDialog pd = new ProgressDialog(ctx);
	    pd.setProgressStyle(ProgressDialog.STYLE_SPINNER);
	    pd.setMessage(msg);
	    pd.setTitle(title);
	    pd.setIndeterminate(true);
	    pd.setCancelable(false);
	    return pd;
    }

    protected void onPreExecute() 
    {
        dialog.show();
    }

    @Override
    protected void onPostExecute(final Boolean success) 
    {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    protected Boolean doInBackground(final String... args) 
    {
        try {    
            task.run();
            Thread.sleep(200);
            return true;
        } catch (Exception e){
        	e.printStackTrace();
            return false;
        }
    }

}
