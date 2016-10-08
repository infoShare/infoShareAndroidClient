package com.infoshare.soap;

import java.util.List;

import android.content.Context;

import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;

public interface ISoapService {
	void authenticate(Context ctx, String email, String password, SoapResultHandler<Integer> handler);
	void register(Context ctx, String email, String password, SoapResultHandler<Integer> handler);
	void getInformations(Context ctx, String category, SoapResultHandler<List<Information>> handler);
	void getCategories(Context ctx, SoapResultHandler<List<Category>> handler);
	void addCategory(Context ctx, String category, SoapResultHandler<Boolean> handler);
	void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler);
	void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler);
}
