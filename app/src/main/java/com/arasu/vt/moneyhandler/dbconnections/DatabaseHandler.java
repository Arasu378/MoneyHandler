package com.arasu.vt.moneyhandler.dbconnections;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kyros on 21-09-2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="moneyhandler";
    private static final String TABLE_CATEGORY="category";
    private static final String TABLE_EXPENSES="expenses";
    private static final String TABLE_ACCOUNT_GROUP="accountgroup";

    private static final String KEY_ID="id";
    private static final String KEY_TITLE="title";
    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
String  CREATE_CATEGORY_TABLE="CREATE TABLE "+TABLE_CATEGORY+"("+
        KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT"+")";
        db.execSQL(CREATE_CATEGORY_TABLE);
        String CREATE_EXPENSES_TABLE="CREATE TABLE "+TABLE_EXPENSES+"("+
                KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT"+")";
        db.execSQL(CREATE_EXPENSES_TABLE);
        String CREATE_TABLE_ACCOUNT_GROUP_TABLE="CREATE TABLE "+TABLE_ACCOUNT_GROUP+"("+
                KEY_ID+" INTEGER PRIMARY KEY,"+KEY_TITLE+" TEXT"+")";
        db.execSQL(CREATE_TABLE_ACCOUNT_GROUP_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
db.execSQL("DROP TABLE IF EXISTS "+TABLE_CATEGORY);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_EXPENSES);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_ACCOUNT_GROUP);
        onCreate(db);
    }
    public void addCategory(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        db.insert(TABLE_CATEGORY,null,values);
        db.close();

    }
    public void addExpenses(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        db.insert(TABLE_EXPENSES,null,values);
        db.close();

    }
    public void addAccountGroup(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        db.insert(TABLE_ACCOUNT_GROUP,null,values);
        db.close();
    }
    public Income getCategory(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_CATEGORY,new String[]{KEY_ID, KEY_TITLE },KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
            if(cursor!=null)
                cursor.moveToFirst();
                Income income=new Income();
        income.setTitle(cursor.getString(1));
        income.setId(cursor.getInt(0));
        return income;
    }

    public Income getExpenses(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_EXPENSES,new String[]{KEY_ID, KEY_TITLE },KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Income income=new Income();
        income.setTitle(cursor.getString(1));
        income.setId(cursor.getInt(0));
        return income;
    }
    public Income getAccountGroup(int id){
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.query(TABLE_ACCOUNT_GROUP,new String[]{KEY_ID,KEY_TITLE},KEY_ID+"=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor!=null)
            cursor.moveToFirst();
        Income income=new Income();
        income.setTitle(cursor.getString(1));
        income.setId(cursor.getInt(0));
        return income;

    }

    public List<Income>getAllCategories(){
        List<Income>categoryList=new ArrayList<Income>();
        String selectedQuery="SELECT * FROM "+TABLE_CATEGORY;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectedQuery,null);
        if(cursor.moveToFirst()){
            do{
                Income contact = new Income();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                categoryList.add(contact);
            }while (cursor.moveToNext());
        }
        return categoryList;
    }
    public List<Income>getAllExpenses(){
        List<Income>categoryList=new ArrayList<Income>();
        String selectedQuery="SELECT * FROM "+TABLE_EXPENSES;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(selectedQuery,null);
        if(cursor.moveToFirst()){
            do{
                Income contact = new Income();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                contact.setTitle(cursor.getString(1));
                categoryList.add(contact);
            }while (cursor.moveToNext());
        }
        return categoryList;
    }
    public List<Income>getAllAccountGroup(){
        List<Income>accountGroupList=new ArrayList<Income>();
        String query="SELECT * FROM "+TABLE_ACCOUNT_GROUP;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
              Income income=new Income();
                income.setId(Integer.parseInt(cursor.getString(0)));
                income.setTitle(cursor.getString(1));
                accountGroupList.add(income);
            }while (cursor.moveToNext());
        }
        return accountGroupList;
    }
    public int updateCategory(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
            values.put(KEY_TITLE,income.getTitle());
        return db.update(TABLE_CATEGORY,values,KEY_ID+" = ?",new String[]
                {String.valueOf(income.getId())});
    }
    public int updateAccountGroup(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        return db.update(TABLE_ACCOUNT_GROUP,values,KEY_ID+" = ?",new String[]
                {String.valueOf(income.getId())});
    }
    public int updateExpenses(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        return db.update(TABLE_EXPENSES,values,KEY_ID+" = ?",new String[]
                {String.valueOf(income.getId())});
    }
    public int updateCategorynewList(Income income,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        return db.update(TABLE_CATEGORY,values,KEY_ID+" = ?",new String[]
                {String.valueOf(id)});

    }
    public int updateExpensesnewList(Income income,int id){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(KEY_TITLE,income.getTitle());
        return db.update(TABLE_EXPENSES,values,KEY_ID+" = ?",new String[]
                {String.valueOf(id)});
    }
    public void deleteCategory(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_CATEGORY,KEY_ID+" = ?",new String[]
                {String.valueOf(income.getId())});
        db.close();

    }
    public void deleteAccountGroupActivity(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_ACCOUNT_GROUP,KEY_ID+" = ?",new String[]
                {String .valueOf(income.getId())});
        db.close();
    }
    public void deleteExpenses(Income income){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE_EXPENSES,KEY_ID+" = ?",new String[]
                {String.valueOf(income.getId())});
        db.close();

    }
    public int getCategoryCount(){
        String countQuery="SELECT * FROM "+TABLE_CATEGORY;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int getExpensesCount(){
        String countQuery="SELECT * FROM "+TABLE_EXPENSES;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(countQuery,null);
        cursor.close();
        return cursor.getCount();
    }
    public int getAccountGroupCount(){
        String query="SELECT * FROM "+TABLE_ACCOUNT_GROUP;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        cursor.close();
        return cursor.getCount();
    }
}
