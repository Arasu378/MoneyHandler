package com.arasu.vt.moneyhandler.activity;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.arasu.vt.moneyhandler.R;

public class AddAccount extends AppCompatActivity {
    private String title=null;
    private TextView group_id_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_add_account);
        group_id_text=(TextView)findViewById(R.id.group_id_text);
        try{
            Bundle bundle=getIntent().getExtras();
            title=bundle.getString("title");
            if(title!=null){
                group_id_text.setText(title);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        group_id_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddAccount.this.finish();
            }
        });
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case android.R.id.home:
                AddAccount.this.finish();
                return true;

        }

        return super.onOptionsItemSelected(item);
    }
}
