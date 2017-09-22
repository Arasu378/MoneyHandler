package com.arasu.vt.moneyhandler.adapters;

import android.content.Context;

import com.arasu.vt.moneyhandler.dbconnections.Income;

import io.realm.RealmResults;

/**
 * Created by kyros on 21-09-2017.
 */

public class RealmCategoryAdapter extends RealmModelAdapter<Income> {
    public RealmCategoryAdapter(Context context, RealmResults<Income> realmResults, boolean automaticUpdate) {
        super(context, realmResults, automaticUpdate);
    }
}
