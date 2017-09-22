package com.arasu.vt.moneyhandler.activity;

import android.app.ActionBar;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.adapters.CategoryAdapter;
import com.arasu.vt.moneyhandler.adapters.RealmCategoryAdapter;
import com.arasu.vt.moneyhandler.application.Prefs;
import com.arasu.vt.moneyhandler.application.SessionManager;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;
import com.arasu.vt.moneyhandler.dbconnections.RealmController;
import com.arasu.vt.moneyhandler.swipegestures.OnBarListChangedListner;
import com.arasu.vt.moneyhandler.swipegestures.OnStartDragListener;
import com.arasu.vt.moneyhandler.swipegestures.SimpleItemTouchHelperCallback;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

public class ChangeIncomeActivity extends AppCompatActivity  implements OnBarListChangedListner, OnStartDragListener{
        private CategoryAdapter adapter;
    private Realm realm;
    private RecyclerView recycler_category;
    private SessionManager prefManager;
    private ItemTouchHelper mItemTouchHelper;
    public ChangeIncomeActivity(){

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_change_income);
        prefManager = new SessionManager(this);

        recycler_category=(RecyclerView)findViewById(R.id.recycler_category);
        this.realm= RealmController.with(this).getRealm();
        if (prefManager.isFirstTimeLaunch()) {
            prefManager.setIsFirstTimeLaunch(false);
            setRealmData();
        }
       // setupRecycler();



    }

    private void setRealmData() {
        DatabaseHandler db=new DatabaseHandler(this);
        String[] titleName={"Salary","Allowance","petty cash","Bonus","Other"};
        for(int i=0;i<titleName.length;i++){
            db.addCategory(new Income(titleName[i]));

        }

        Prefs.with(this).setPreLoad(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        setupRecycler();
    }

    private void setupRecycler() {
        DatabaseHandler db=new DatabaseHandler(this);
        recycler_category.setHasFixedSize(true);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_category.setLayoutManager(layoutManager);
        List<Income>categoryList=db.getAllCategories();
        Log.d("CategoryList size : ",""+categoryList.size());
        for (Income cn : categoryList) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getTitle() ;
            Log.d("Name: ", log);
        }

        adapter=new CategoryAdapter(this,categoryList,this,this);
        recycler_category.setAdapter(adapter);
        ItemTouchHelper.Callback callback=new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper=new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recycler_category);
        adapter.notifyDataSetChanged();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.change_income_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                ChangeIncomeActivity.this.finish();
                return true;
            case R.id.change_plus:
                Intent i=new Intent(ChangeIncomeActivity.this,InsertIncomeCategoryActivity.class);
                startActivity(i);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
            mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onBarListChanged(List<Income> categoryList) {

        DatabaseHandler db=new DatabaseHandler(this);
        if(categoryList!=null && categoryList.size()!=0){
            for(int i=0;i<categoryList.size();i++){
                Income income=categoryList.get(i);
                int y=i+1;
                db.updateCategorynewList(income,y);
            }
        }else{
            Log.e("Category List Error: ", ""+categoryList.size());
        }

    }
}
