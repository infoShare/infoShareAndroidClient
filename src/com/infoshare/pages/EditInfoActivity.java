package com.infoshare.pages;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.model.Information;
import com.infoshare.parsers.BooleanParser;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.AlertService;
import com.infoshare.util.ToastService;

public class EditInfoActivity extends Activity {

	private Context ctx = EditInfoActivity.this;
	private Information information;
	private EditText infoEditText;
	private IDataRetriever dataRetriever = ServiceProvider.provideDataRetriever();
			
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.edit_info);
		
		information = (Information) getIntent().getSerializableExtra(Information.INFO_KEY);
		
		infoEditText = (EditText) findViewById(R.id.infoContent);
		infoEditText.setText(information.getContent());
	}

	public void update(View v) {
		AlertService.displayDialog(ctx, R.string.updateTitle, R.string.updateMessage, new Runnable() {
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
					information.updateContent(infoEditText.getText().toString());
					dataRetriever.addInformation(ctx, information.getContent(), 
							information.getCategory().getName(), createAddHandler());	
				}else{
					ToastService.showToast(ctx, "Error occured");
				}
			}
		};
	}
	
	private SoapResultHandler<Boolean> createAddHandler() {
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