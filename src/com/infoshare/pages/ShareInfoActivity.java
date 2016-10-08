package com.infoshare.pages;

import org.ksoap2.serialization.SoapObject;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.infoshare.R;
import com.infoshare.parsers.BooleanParser;
import com.infoshare.preferences.PreferencesService;
import com.infoshare.retrievers.IDataRetriever;
import com.infoshare.service.providers.ServiceProvider;
import com.infoshare.soap.SoapResultHandler;
import com.infoshare.util.AlertService;
import com.infoshare.util.ToastService;

public class ShareInfoActivity extends Activity {

	private Context ctx = ShareInfoActivity.this;
	private EditText infoEditText;
	private IDataRetriever dataRetriever = ServiceProvider.provideDataRetriever();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.share_info);
		infoEditText = (EditText) findViewById(R.id.infoContent);
	}

	public void share(View v) {
		AlertService.displayDialog(ctx, R.string.shareTitle, R.string.shareMessage, new Runnable() {
			@Override
			public void run() {
				String categoryName = PreferencesService.getCurrentCategory(ctx);
				dataRetriever.addInformation(ctx, infoEditText.getText().toString(), categoryName, new SoapResultHandler<Boolean>() {
					
					@Override
					public Boolean parseResponse(SoapObject response) {
						return new BooleanParser().parse(response);
					}
					
					@Override
					public void handle(SoapObject response) {
						if(parseResponse(response)){
							PreferencesService.saveToPrefs(ctx, PreferencesService.UPDATE_KEY, true);
							ToastService.showToast(ctx, getResources().getString(R.string.verificationPending));
							((Activity)ctx).finish();
						}else{
							ToastService.showToast(ctx, "Error occured");
						}
						
					}
				});

				
			}
		});
	}

}