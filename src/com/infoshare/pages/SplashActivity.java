package com.infoshare.pages;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.infoshare.R;
import com.infoshare.util.LoadingTask;
import com.infoshare.util.LoadingTask.LoadingTaskFinishedListener;

public class SplashActivity extends Activity implements LoadingTaskFinishedListener {

	private ProgressBar progressBar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash); 
		progressBar = (ProgressBar) findViewById(R.id.splash_progress_bar);
        new LoadingTask(progressBar, this).execute("www.infoshare.com");
	}

    @Override
    public void onTaskFinished() {
        completeSplash();
    }
 
    private void completeSplash(){
        startApp();
        finish();
    }
 
    private void startApp() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }
	
}
