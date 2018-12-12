package com.mobile.thais.pilojinha.Model;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class Session {

    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setToken(String token) {
        prefs.edit().putString("token", token).commit();
    }

    public String token() {
        String token = prefs.getString("token", "");
        return token;
    }
}
