package com.arasu.vt.moneyhandler.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.adapters.AccountGroupAdapter;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;

import java.util.ArrayList;
import java.util.List;

public class AccountGroupActivity extends AppCompatActivity {
    private RecyclerView account_group_recycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_group);
        account_group_recycler=(RecyclerView)findViewById(R.id.account_group_recycler);
        DatabaseHandler db=new DatabaseHandler(this);
        List<Income> accountGroupList=db.getAllAccountGroup();
        if(accountGroupList!=null && accountGroupList.size()!=0){
            account_group_recycler.setHasFixedSize(true);
            final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
            layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            account_group_recycler.setLayoutManager(layoutManager);
            AccountGroupAdapter adapter=new AccountGroupAdapter(AccountGroupActivity.this,accountGroupList);
            account_group_recycler.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }else{
           String[] values=new String[]{"Cash","Accounts","Card","Debit Card","Savings","Top-Up/Prepaid","Investments",
           "Overdrafts","Loan","Insurance","Others"};
            for(int i=0;i<values.length;i++){
                db.addAccountGroup(new Income(values[i]));

            }
            List<Income> accountGroupLists=db.getAllAccountGroup();


            if(accountGroupLists!=null && accountGroupLists.size()!=0){
                account_group_recycler.setHasFixedSize(true);
                final LinearLayoutManager layoutManager=new LinearLayoutManager(this);
                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                account_group_recycler.setLayoutManager(layoutManager);
                AccountGroupAdapter adapter=new AccountGroupAdapter(AccountGroupActivity.this,accountGroupLists);
                account_group_recycler.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        }

    }
}
