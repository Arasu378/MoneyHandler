package com.arasu.vt.moneyhandler.adapters;

import android.support.v7.widget.RecyclerView;

import io.realm.RealmBaseAdapter;
import io.realm.RealmObject;

/**
 * Created by kyros on 21-09-2017.
 */

public abstract class RealmRecylerViewAdapter<T extends RealmObject> extends RecyclerView.Adapter{
private RealmBaseAdapter<T>realmBaseAdapter;
    public T getIem(int position){
        return realmBaseAdapter.getItem(position);
    }
    public RealmBaseAdapter<T>getRealmAdapter(){
        return realmBaseAdapter;
    }
    public void setRealmAdapter(RealmBaseAdapter<T>realmAdapter){realmBaseAdapter=realmAdapter;}
}
