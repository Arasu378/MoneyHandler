package com.arasu.vt.moneyhandler.application;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * Created by Thirunavukkarasu on 28-07-2016.
 */
public class SessionManager {
    private final SharedPreferences pref;
    private final SharedPreferences.Editor editor;
    private final Context _context;
    private final int PRIVATE_MODE=0;
    private static final String PREF_NAME="SessionDetails";
    private static final String IS_FIRST_TIME_LAUNCH="Is First Time Launch";
    private static final String IS_FIRST_TIME_EXPENSES="Is First Time Expenses";

    public SessionManager(Context context){
        this._context=context;
        pref=_context.getSharedPreferences(PREF_NAME,PRIVATE_MODE);
        editor=pref.edit();

    }
      public void setIsFirstTimeLaunch(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_LAUNCH,isFirstTime);
        editor.commit();
    }
    public boolean  isFirstTimeLaunch(){
        return pref.getBoolean(IS_FIRST_TIME_LAUNCH,true);
    }
    public void setIsFirstTimeExpenses(boolean isFirstTime){
        editor.putBoolean(IS_FIRST_TIME_EXPENSES,isFirstTime);
        editor.commit();
    }
    public boolean  isFirstTimeExpenses(){
        return pref.getBoolean(IS_FIRST_TIME_EXPENSES,true);
    }

}


