package com.infoshare.pages;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.util.ToastService;

public class ReportProblemActivity extends Activity {

	private Context ctx = ReportProblemActivity.this;
	private EditText descriptionEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.report_problem);
		descriptionEditText = (EditText) findViewById(R.id.problemDescription);
	}

	public void send(View v) {
		ToastService.showToast(ctx, "Sent - " + descriptionEditText.getText());
	}

}