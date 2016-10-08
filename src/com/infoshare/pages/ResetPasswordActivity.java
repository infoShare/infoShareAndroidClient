package com.infoshare.pages;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.util.ToastService;

public class ResetPasswordActivity extends Activity {

	private EditText emailEditText;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reset_password);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
	}

	public void reset(View v) {
		ToastService.showToast(getApplicationContext(), "Reset - " + emailEditText.getText());
	}

}