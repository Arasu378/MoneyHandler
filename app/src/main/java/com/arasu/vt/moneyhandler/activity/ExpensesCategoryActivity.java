package com.arasu.vt.moneyhandler.activity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.adapters.CategoryAdapter;
import com.arasu.vt.moneyhandler.adapters.ExpensesAdapter;
import com.arasu.vt.moneyhandler.application.SessionManager;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;
import com.arasu.vt.moneyhandler.dbconnections.RealmController;
import com.arasu.vt.moneyhandler.swipegestures.OnBarListChangedListner;
import com.arasu.vt.moneyhandler.swipegestures.OnStartDragListener;
import com.arasu.vt.moneyhandler.swipegestures.SimpleItemTouchHelperCallback;

import java.util.List;

public class ExpensesCategoryActivity extends AppCompatActivity implements OnBarListChangedListner, OnStartDragListener {
    private RecyclerView recycler_expenses;
    private SessionManager prefManager;
    private ItemTouchHelper mItemTouchHelper;
    private ExpensesAdapter adapter;
    public ExpensesCategoryActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_expenses_category);
        prefManager = new SessionManager(this);

        recycler_expenses=(RecyclerView)findViewById(R.id.recycler_expenses);
        if (prefManager.isFirstTimeExpenses()) {
            prefManager.setIsFirstTimeExpenses(false);
            setRealmData();

        }
    }

    private void setRealmData() {
        DatabaseHandler db=new DatabaseHandler(this);
        String[] titleName={"Food","Social Life","Self-development","Transportation","Culture",
        "Household","Apparel","Beauty","Health","Education","Gift","Other"};
        for(int i=0;i<titleName.length;i++){
            db.addExpenses(new Income(titleName[i]));

        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        setupRecycler();
    }

    private void setupRecycler() {
        DatabaseHandler db=new DatabaseHandler(this);
        recycler_expenses.setHasFixedSize(true);
        final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_expenses.setLayoutManager(layoutManager);
        List<Income>categoryList=db.getAllExpenses();
        Log.d("CategoryList size : ",""+categoryList.size());
        for (Income cn : categoryList) {
            String log = "Id: "+cn.getId()+" ,Name: " + cn.getTitle() ;
            Log.d("Name: ", log);
        }

        adapter=new ExpensesAdapter(this,categoryList,this,this);
        recycler_expenses.setAdapter(adapter);
        ItemTouchHelper.Callback callback=new SimpleItemTouchHelperCallback(adapter);
        mItemTouchHelper=new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(recycler_expenses);
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
                ExpensesCategoryActivity.this.finish();
                return true;
            case R.id.change_plus:
                    Intent intent=new Intent(ExpensesCategoryActivity.this,InsertExpensesActivity.class);
                startActivity(intent);
                return true;

        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }

    @Override
    public void onBarListChanged(List<Income> expenseList) {
        DatabaseHandler db=new DatabaseHandler(this);
        if(expenseList!=null && expenseList.size()!=0){
            for(int i=0;i<expenseList.size();i++){
                Income income=expenseList.get(i);
                int y=i+1;
                db.updateExpensesnewList(income,y);
            }
        }else{
            Log.e("Category List Error: ", ""+expenseList.size());
        }
    }
}
