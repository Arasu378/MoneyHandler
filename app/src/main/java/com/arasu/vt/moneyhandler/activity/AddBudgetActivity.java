package com.arasu.vt.moneyhandler.activity;

import android.content.pm.ActivityInfo;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.adapters.AddBudgetAdapter;
import com.arasu.vt.moneyhandler.adapters.CalculatorAdapter;
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
    private TextView text_grid_value,id_cat_value;
    private LinearLayout linear_calculator,linear_category_cli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContentView(R.layout.activity_add_budget);
        gridView = (GridView) findViewById(R.id.grid_data);
        text_grid_value=(TextView)findViewById(R.id.text_grid_value);
        id_cat_value=(TextView)findViewById(R.id.id_cat_value);
        calculator_grid=(GridView)findViewById(R.id.calculator_grid);
        linear_calculator=(LinearLayout)findViewById(R.id.linear_calculator);
        linear_category_cli=(LinearLayout)findViewById(R.id.linear_category_cli);
        getData();
        getCalculator();
        linear_calculator.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setVisibility(View.GONE);
                text_grid_value.setText("Amount");

                calculator_grid.setVisibility(View.VISIBLE);
            }
        });
        linear_category_cli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gridView.setVisibility(View.VISIBLE);
                text_grid_value.setText("Category");
                calculator_grid.setVisibility(View.GONE);
            }
        });


    }

    private void getCalculator() {
        CalculatorAdapter adapter=new CalculatorAdapter(AddBudgetActivity.this);
        calculator_grid.setAdapter(adapter);

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
        if(s.getMessage()!=null){
            id_cat_value.setText(s.getMessage());
        }




    }




}
