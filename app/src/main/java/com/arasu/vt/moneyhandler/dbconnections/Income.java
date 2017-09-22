package com.arasu.vt.moneyhandler.dbconnections;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by kyros on 21-09-2017.
 */

public class Income extends RealmObject {
    @PrimaryKey
    private int id;
    private String title;
    public Income(){

    }
    public Income(String title){
        this.title=title;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
