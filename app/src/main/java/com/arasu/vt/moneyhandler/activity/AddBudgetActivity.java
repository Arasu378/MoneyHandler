package com.arasu.vt.moneyhandler.activity;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.adapters.AddBudgetAdapter;
import com.arasu.vt.moneyhandler.adapters.SettingsAdapter;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;
import com.arasu.vt.moneyhandler.fragment.ConfigurationFragment;
import com.arasu.vt.moneyhandler.fragment.SettingsFragment;
import com.arasu.vt.moneyhandler.models.Events;
import com.arasu.vt.moneyhandler.models.GlobalBus;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

public class AddBudgetActivity extends AppCompatActivity {
    private List<Income>incomeList=new ArrayList<Income>();
    private GridView gridView,calculator_grid;
    private LinearLayout linear_calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_add_budget);
        gridView = (GridView) findViewById(R.id.grid_data);
        calculator_grid=(GridView)findViewById(R.id.calculator_grid);
        linear_calculator=(LinearLayout)findViewById(R.id.linear_calculator);
        getData();
        getCalculator();

    }

    private void getCalculator() {

    }

    private void getData(){
        DatabaseHandler db=new DatabaseHandler(this);
        incomeList=db.getAllExpenses();
        if(incomeList.size()!=0 && incomeList!=null){
            AddBudgetAdapter adapter=new AddBudgetAdapter(AddBudgetActivity.this,incomeList);
            gridView.setAdapter(adapter);
        }else {
            Toast.makeText(getApplicationContext(),"List is empty or null",Toast.LENGTH_SHORT).show();
            Log.d("GetData",""+incomeList.size());
        }

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                AddBudgetActivity.this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onStart() {
        super.onStart();
        try{
            GlobalBus.getBus().register(this);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Subscribe
    public void getMessage(Events.AdapterFragmentMessage s) {
        Log.d("Fragment Position: ",""+s.getMessage());




    }




}
