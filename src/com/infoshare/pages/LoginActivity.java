package com.infoshare.pages;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.authentication.AuthenticationStatus;
import com.infoshare.authentication.IAuthenticationService;
import com.infoshare.parsers.IntegerParser;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.security.SecurityService;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.AlertService;

public class LoginActivity extends Activity {

	private Context ctx = LoginActivity.this;
	private EditText emailEditText;
	private EditText passwordEditText;
	private CheckBox rememberCheckBox;
	private IAuthenticationService authenticationService = ServiceProvider.provideAuthenticationService();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		rememberCheckBox = (CheckBox) findViewById(R.id.rememberMe);
		rememberCheckBox.setChecked(SecurityService.getSavedRemember(ctx));
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		emailEditText.setText(SecurityService.getSavedEmail(ctx));
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		passwordEditText.setText(SecurityService.getSavedPassword(ctx));
	}

	public void login(View v) {
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		authenticationService.authenticate(ctx, email, password, createSoapLoginHandler(email, password));
	}
	
	private SoapResultHandler<Integer> createSoapLoginHandler(final String email, final String password){
		return new SoapResultHandler<Integer>() {

			@Override
			public void handle(SoapObject response) {
				Integer result = parseResponse(response); 
				if (result < 1) {
					handleFailedAuthorization(result);
				} else {
					updateAuthenticationDetails(email, password, result);
					startMainActivity();
				}
			}

			@Override
			public Integer parseResponse(SoapObject response) {
				return new IntegerParser().parse(response);
			}
		};
	}

	private void handleFailedAuthorization(int authenticationStatus) {
		int realStatusNumber = -1 * authenticationStatus;
		AuthenticationStatus loginStatus = AuthenticationStatus.values()[realStatusNumber];
		switch (loginStatus) {
			case NOT_REGISTERED:
				AlertService.displayOkDialog(ctx, R.string.authenticationStatus, R.string.notRegistered);
				break;
			case NOT_CONFIRMED:
				AlertService.displayOkDialog(ctx, R.string.authenticationStatus, R.string.notConfirmed);
				break;
			case WRONG_CREDENTIALS:
				AlertService.displayOkDialog(ctx, R.string.authenticationStatus, R.string.wrongCredentials);
				break;
		}
	}

	private void updateAuthenticationDetails(String email, String password, long userId) {
		if (rememberCheckBox.isChecked()) {
			SecurityService.saveAuthenticationDetails(ctx, email, password);
		} else {
			SecurityService.clearAuthenticationDetails(ctx);
		}
		PreferencesService.saveToPrefs(ctx, PreferencesService.USER_KEY, email);
		PreferencesService.saveToPrefs(ctx, PreferencesService.USER_ID_KEY, userId);
	}

	private void startMainActivity() {
		Intent i = new Intent(getApplicationContext(), MainActivity.class);
		startActivity(i);
		finish();
	}

	public void register(View v) {
		Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
		startActivity(i);
	}

	public void forgot(View v) {
		Intent i = new Intent(getApplicationContext(),
				ResetPasswordActivity.class);
		startActivity(i);
	}
}
