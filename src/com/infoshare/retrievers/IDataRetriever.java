package com.infoshare.retrievers;

import java.util.List;

import android.content.Context;

import com.infoshare.model.AbstractIdentity.Id;
import com.infoshare.model.Category;
import com.infoshare.model.Information;
import com.infoshare.soap.SoapResultHandler;

public interface IDataRetriever {

	void updateCategories(Context ctx, SoapResultHandler<List<Category>> handler);
	void updateInfoList(Context ctx, String category, SoapResultHandler<List<Information>> handler);
	void deleteInformation(Context ctx, Id id, SoapResultHandler<Boolean> handler);
	void addCategory(Context ctx, String category, SoapResultHandler<Boolean> handler);
	void addInformation(Context ctx, String content, String categoryName, SoapResultHandler<Boolean> handler);

}
