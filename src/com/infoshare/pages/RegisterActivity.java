package com.infoshare.pages;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.authentication.IAuthenticationService;
import com.infoshare.authentication.RegistrationStatus;
import com.infoshare.parsers.IntegerParser;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.ToastService;

public class RegisterActivity extends Activity {

	private Context ctx = RegisterActivity.this;
	private EditText emailEditText;
	private EditText passwordEditText;
	private EditText confirmPasswordEditText;
	private IAuthenticationService authenticationService = ServiceProvider.provideAuthenticationService();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
		emailEditText = (EditText) findViewById(R.id.emailEditText);
		passwordEditText = (EditText) findViewById(R.id.passwordEditText);
		confirmPasswordEditText = (EditText) findViewById(R.id.confirmPasswordEditText);
	}

	public void register(View v) {
		String email = emailEditText.getText().toString();
		String password = passwordEditText.getText().toString();
		String confirmPassword = confirmPasswordEditText.getText().toString();
		
		if(!areDataProvided(email, confirmPassword)){
			ToastService.showToast(ctx, getResources().getString(R.string.detailsNotProvided));
			return;
		}
		if(!arePasswordsSame(password, confirmPassword)){
			ToastService.showToast(ctx, getResources().getString(R.string.passwordDifferent));
			return;
		}
		authenticationService.register(ctx, email, password, createResultHandler());
	}
	
	private SoapResultHandler<Integer> createResultHandler(){
		return new SoapResultHandler<Integer>() {

			@Override
			public void handle(SoapObject response) {
				Integer result = parseResponse(response); 
				handleResult(result);
			}

			private void handleResult(Integer result) {
				RegistrationStatus status = RegistrationStatus.values()[result];
				switch(status){
					case SUCCESS:
						ToastService.showToast(ctx, getResources().getString(R.string.registered));
						break;
					case ALREADY_REGISTERED:
						ToastService.showToast(ctx, getResources().getString(R.string.alreadyRegistered));
						break;
				}
			}

			@Override
			public Integer parseResponse(SoapObject response) {
				return new IntegerParser().parse(response);
			}
		};
	}

	private boolean areDataProvided(String email, String password){
		return !email.isEmpty() && !password.isEmpty(); 
	}
	
	private boolean arePasswordsSame(String first, String second){
		return !first.isEmpty() && first.equals(second);
	}
	
}
