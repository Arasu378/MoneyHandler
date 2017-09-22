package com.arasu.vt.moneyhandler.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;

public class InsertExpensesActivity extends AppCompatActivity {
    private Button save_category_name;
    private EditText title_id;
    private String title=null;
    private int id=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_insert_expenses);
        try{
            Bundle bundle=getIntent().getExtras();
            title=bundle.getString("title");
            id=bundle.getInt("id");
        }catch (Exception e){
            e.printStackTrace();
        }
        save_category_name=(Button)findViewById(R.id.save_category_name_expense);
        title_id=(EditText)findViewById(R.id.title_id_expense);
        if(title!=null){
            title_id.setText(title);
        }
        save_category_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(title==null){
                    DatabaseHandler db=new DatabaseHandler(InsertExpensesActivity.this);
                    String value=title_id.getText().toString();
                    db.addExpenses(new Income(value));
                    Toast.makeText(getApplicationContext(),"Title inserted Successfully!",Toast.LENGTH_SHORT).show();
                    InsertExpensesActivity.this.finish();
                }else{
                    DatabaseHandler db=new DatabaseHandler(InsertExpensesActivity.this);
                    String value=title_id.getText().toString();
                    Income income=new Income();
                    income.setId(id);
                    income.setTitle(value);
                    db.updateExpenses(income);
                    Toast.makeText(getApplicationContext(),"Title updated Successfully!",Toast.LENGTH_SHORT).show();
                    InsertExpensesActivity.this.finish();
                }




            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                InsertExpensesActivity.this.finish();
                return true;


        }

        return super.onOptionsItemSelected(item);
    }
}
