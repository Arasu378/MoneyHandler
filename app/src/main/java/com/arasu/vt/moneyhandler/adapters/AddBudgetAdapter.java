package com.arasu.vt.moneyhandler.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.dbconnections.Income;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

import java.util.List;

/**
 * Created by kyros on 27-09-2017.
 */

public class AddBudgetAdapter extends BaseAdapter {
    private Context mContext;
    private static Bus bus;
    private List<Income>incomeList;
    public AddBudgetAdapter(Context mContext,List<Income>incomeList){
        this.incomeList=incomeList;
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return incomeList.size();
    }

    @Override
    public Object getItem(int position) {
        return incomeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
       View view= LayoutInflater.from(mContext).inflate(R.layout.grid_add_budget_list,parent,false);
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
        RelativeLayout relative_click=(RelativeLayout)view.findViewById(R.id.relative_click);
        TextView text_cast_name=(TextView)view.findViewById(R.id.text_cast_name);
        Income value=incomeList.get(position);
        String text_Name=value.getTitle();
        if(text_Name!=null){
            text_cast_name.setText(text_Name);
        }
        relative_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"Clicked : "+position,Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
    @Subscribe
    public void getMessage(String message) {
        Log.d("Adapter message : ",""+message);

        //  Toast.makeText(mContext.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }


    @Produce
    public String produceEvent() {
        return "Starting up";
    }
}
