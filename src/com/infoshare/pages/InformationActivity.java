package com.infoshare.pages;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.infoshare.R;
import com.infoshare.model.Information;
import com.infoshare.parsers.BooleanParser;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.AlertService;
import com.infoshare.util.DateUtils;
import com.infoshare.util.ToastService;

public class InformationActivity extends Activity {

	private Context ctx = InformationActivity.this;
	private Information information;
	private TextView authorTextView;
	private TextView dateTextView;
	private TextView contentTextView;
	private TextView deleteTextView;
	private TextView editTextView;
	private IDataRetriever dataRetriever = ServiceProvider.provideDataRetriever();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.information);
		initializeComponents();
	}
	
	private void initializeComponents() {
		authorTextView = (TextView) findViewById(R.id.author);
		dateTextView = (TextView) findViewById(R.id.date);
		contentTextView = (TextView) findViewById(R.id.content);
		editTextView = (TextView) findViewById(R.id.editTV);
		deleteTextView = (TextView) findViewById(R.id.deleteTV);
		
	    information = (Information) getIntent().getSerializableExtra(Information.INFO_KEY);

	    authorTextView.setText(information.getUserName());
	    dateTextView.setText(DateUtils.convertDate(information.getCreationDate()));
	    contentTextView.setText(information.getContent());
	    updateVisibility();
	}
	
	private void updateVisibility() {
		Long userId = information.getUserId();
		Long loggedUser = PreferencesService.getLoggedUser(ctx);
		if(!userId.equals(loggedUser)){
			editTextView.setVisibility(View.INVISIBLE);
			deleteTextView.setVisibility(View.INVISIBLE);
		}
	}

	public void report(View v){
		AlertService.displayDialog(ctx, R.string.reportTitle, R.string.reportMessage, new Runnable() {
			@Override
			public void run() {
				AlertService.displayOkDialog(ctx, R.string.reportTitle, R.string.reportConfirmation);
			}
		});
	}

	public void edit(View v){
		Intent intent = new Intent(ctx, EditInfoActivity.class);
		intent.putExtra(Information.INFO_KEY, information);
		startActivity(intent);
	}
	
	public void delete(View v){
		AlertService.displayDialog(ctx, R.string.deleteTitle, R.string.deleteMessage, new Runnable() {
			@Override
			public void run() {
				dataRetriever.deleteInformation(ctx, information.getId(), createDeleteHandler());
			}
		});
	}
	
	private SoapResultHandler<Boolean> createDeleteHandler() {
		return new SoapResultHandler<Boolean>() {
			
			@Override
			public Boolean parseResponse(SoapObject response) {
				return new BooleanParser().parse(response);
			}
			
			@Override
			public void handle(SoapObject response) {
				if(parseResponse(response)){
					PreferencesService.saveToPrefs(ctx, PreferencesService.UPDATE_KEY, true);
					((Activity)ctx).finish();
				}else{
					ToastService.showToast(ctx, "Error occured");
				}
			}
		};
	}
}
