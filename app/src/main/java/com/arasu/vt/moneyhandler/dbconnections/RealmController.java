package com.arasu.vt.moneyhandler.dbconnections;

import android.app.Activity;
import android.app.Application;
import android.support.v4.app.Fragment;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by kyros on 21-09-2017.
 */

public class RealmController {
    private static RealmController realmController;
    private final Realm realm;
    public RealmController(Application application){
        realm=Realm.getDefaultInstance();
    }
    public static RealmController with(Fragment fragment){
        if(realmController==null){
            realmController=new RealmController(fragment.getActivity().getApplication());
        }
        return realmController;
    }
    public static RealmController with(Activity activity){
        if(realmController==null){
            realmController=new RealmController(activity.getApplication());
        }
        return realmController;
    }
    public static RealmController with(Application application){
        if(realmController==null){
            realmController=new RealmController(application);
        }
        return realmController;
    }
    public static RealmController getRealmController(){
        return realmController;
    }
    public Realm getRealm(){
        return realm;
    }
    public  void refresh(){
        realm.refresh();
    }
    public void clearAll(){
        realm.beginTransaction();
        realm.clear(Income.class);
        realm.commitTransaction();
    }
    public RealmResults<Income>getCategory(){
        return realm.where(Income.class).findAll();
    }
    public Income getCategory(String id){
        return realm.where(Income.class).equalTo("id",id).findFirst();
    }
    public boolean hasCategory(){
        return !realm.allObjects(Income.class).isEmpty();
    }
    public RealmResults<Income>queryedBooks(){
        return realm.where(Income.class)
                .contains("author", "Author 0")
                .or()
                .contains("title", "Realm")
                .findAll();
    }
}
