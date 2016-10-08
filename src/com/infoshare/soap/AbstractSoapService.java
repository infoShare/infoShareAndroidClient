package com.infoshare.soap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import android.app.Activity;
import android.content.Context;

import com.infoshare.R;
import com.infoshare.builders.PropertyInfoBuilder;
import com.infoshare.util.ProgressTask;
import com.infoshare.util.ToastService;

public abstract class AbstractSoapService {
	
	protected PropertyInfo createProperty(String name, String value, Object type) {
		return new PropertyInfoBuilder().withName(name).withValue(value).withType(type).build();
	}
	
	protected void executeTask(final Context ctx, final String methodName, final SoapResultHandler<?> handler, final PropertyInfo... propertyInfos) {
		Runnable task = new Runnable() {
			@Override
			public void run() {
				try {
					final SoapObject response = execute(methodName, propertyInfos);
					handleOnUI(ctx, handler, response);
				} catch (Exception e) {
					e.printStackTrace();
					ToastService.showToast(ctx, e.getMessage());
				}
			}

			private void handleOnUI(final Context ctx, final SoapResultHandler<?> handler,
					final SoapObject response) {
				Activity activity = (Activity) ctx;
				activity.runOnUiThread(new Runnable() {
					
					@Override
					public void run() {
						handler.handle(response);
					}
				});
			}
		};
		new ProgressTask(ctx, R.string.connectingToServer, R.string.pleaseWait, task).execute();
	}

	private SoapObject execute(String methodName, PropertyInfo ... propertyInfos) throws Exception {
		SoapObject request = new SoapObject(SoapDetails.NAMESPACE, methodName);
		for(PropertyInfo prop : propertyInfos){
			request.addProperty(prop);
		}
		SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(
				SoapEnvelope.VER11);
		envelope.dotNet = true;
		envelope.setOutputSoapObject(request);
		HttpTransportSE androidHttpTransport = new HttpTransportSE(SoapDetails.URL);
		androidHttpTransport.debug = true;
		androidHttpTransport.call(SoapDetails.NAMESPACE + "#" + methodName, envelope);
		return (SoapObject) envelope.bodyIn;
	}
	
}
