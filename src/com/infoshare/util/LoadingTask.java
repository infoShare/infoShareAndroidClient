package com.infoshare.util;

import android.os.AsyncTask;
import android.widget.ProgressBar;

public class LoadingTask extends AsyncTask<String, Integer, Integer> {
 
    public interface LoadingTaskFinishedListener {
        void onTaskFinished();
    }
    
    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener finishedListener;
 
    public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener) {
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
    }
 
    @Override
    protected Integer doInBackground(String... params) {
        if(resourcesDontAlreadyExist()){
            downloadResources();
        }
        return 1234;
    }
 
    private boolean resourcesDontAlreadyExist() {
        return true; 
    }
 
 
    private void downloadResources() {
        int count = 20;
        for (int i = 0; i < count; i++) {
            int progress = (int) ((i / (float) count) * 100);
            publishProgress(progress);
            try { 
            	Thread.sleep(75); 
            } catch (InterruptedException ignore) {
            	
            }
        }
    }
 
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
    }
 
    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
        finishedListener.onTaskFinished();
    }
}