package com.infoshare.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

import com.infoshare.preferences.PreferencesService;


public class SecurityService {

	private static final String EMAIL_KEY = "__EMAIL__";
	private static final String PASSWORD_KEY = "__PASSWORD__";
	private static final String REMEMBER_KEY = "__REMEMBER__";
	
	public static void saveAuthenticationDetails(Context ctx, String email, String password){
		PreferencesService.saveToPrefs(ctx, EMAIL_KEY, email);
		PreferencesService.saveToPrefs(ctx, PASSWORD_KEY, password);
		PreferencesService.saveToPrefs(ctx, REMEMBER_KEY, true);
	}

	public static void clearAuthenticationDetails(Context ctx) {
		PreferencesService.deletePreference(ctx, EMAIL_KEY);
		PreferencesService.deletePreference(ctx, PASSWORD_KEY);
		PreferencesService.deletePreference(ctx, REMEMBER_KEY);
	}
	
	public static String getSavedEmail(Context ctx) {
		return PreferencesService.getStringFromPrefs(ctx, EMAIL_KEY, "");
	}
	
	public static String getSavedPassword(Context ctx) {
		return PreferencesService.getStringFromPrefs(ctx, PASSWORD_KEY, "");
	}

	public static Boolean getSavedRemember(Context ctx) {
		return PreferencesService.getBooleanFromPrefs(ctx, REMEMBER_KEY, false);
	}
	
	public static final String calculateHash(final String s) {
	    final String MD5 = "MD5";
	    try {
	        MessageDigest digest = java.security.MessageDigest
	                .getInstance(MD5);
	        digest.update(s.getBytes());
	        byte messageDigest[] = digest.digest();

	        StringBuilder hexString = new StringBuilder();
	        for (byte aMessageDigest : messageDigest) {
	            String h = Integer.toHexString(0xFF & aMessageDigest);
	            while (h.length() < 2)
	                h = "0" + h;
	            hexString.append(h);
	        }
	        return hexString.toString();

	    } catch (NoSuchAlgorithmException e) {
	        e.printStackTrace();
	    }
	    return "";
	}
	
}
