package com.arasu.vt.moneyhandler.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.activity.AddAccount;
import com.arasu.vt.moneyhandler.dbconnections.Income;

import java.util.List;

/**
 * Created by kyros on 24-10-2017.
 */

public class AccountGroupAdapter extends RecyclerView.Adapter<AccountGroupAdapter.AccountGroupViewHolder> {
    private Context mContext;
    private List<Income>incomeList;
    public AccountGroupAdapter(Context mContext,List<Income>incomeList){
        this.mContext=mContext;
        this.incomeList=incomeList;
    }
    public class AccountGroupViewHolder extends RecyclerView.ViewHolder{
        TextView text_group_account;
        LinearLayout linear_click_next;

        public AccountGroupViewHolder(View itemView) {
            super(itemView);
            linear_click_next=(LinearLayout)itemView.findViewById(R.id.linear_click_next);
            text_group_account=(TextView)itemView.findViewById(R.id.text_group_account);
        }
    }
    @Override
    public AccountGroupViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_account_group_list,parent,false);
        return new AccountGroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(AccountGroupViewHolder holder, int position) {
        Income income=incomeList.get(position);
        int  id=income.getId();
        final String title=income.getTitle();
        if(title!=null){
            holder.text_group_account.setText(title);
        }
        holder.linear_click_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext,AddAccount.class);
                intent.putExtra("title",title);
                mContext.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        if(incomeList!=null && incomeList.size()!=0){
            return incomeList.size();
        }else{
            return 0;
        }
    }
}
