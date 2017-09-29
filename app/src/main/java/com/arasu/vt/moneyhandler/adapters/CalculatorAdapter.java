package com.arasu.vt.moneyhandler.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;

/**
 * Created by kyros on 27-09-2017.
 */

public class CalculatorAdapter extends BaseAdapter {
    private int [] totalSize={1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16};
    private Context mContext;
    private String [] inputText={"1","2","3","back","4","5","6","C","7","8","9","calc",".","0","","Done"};
    public CalculatorAdapter(Context mContext){
        this.mContext=mContext;
    }


    @Override
    public int getCount() {
        return totalSize.length;
    }

    @Override
    public Object getItem(int position) {
        return totalSize[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view=LayoutInflater.from(mContext).inflate(R.layout.calculator_list_item,parent,false);
        LinearLayout linear_cal_input=(LinearLayout)view.findViewById(R.id.linear_cal_input);
        TextView calculator_text_input=(TextView)view.findViewById(R.id.calculator_text_input);
        ImageView image_calculator=(ImageView)view.findViewById(R.id.image_calculator);
        String text_value=inputText[position];
        if(text_value.equals("calc")){
            calculator_text_input.setVisibility(View.GONE);
            image_calculator.setVisibility(View.VISIBLE);
            image_calculator.setImageResource(R.drawable.calculator_icon);
        }else if(text_value.equals("back")){
            calculator_text_input.setVisibility(View.GONE);
            image_calculator.setVisibility(View.VISIBLE);
            image_calculator.setImageResource(R.drawable.back_space_icon);
        }
        calculator_text_input.setText(inputText[position]);

        linear_cal_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"position : "+position,Toast.LENGTH_SHORT).show();

            }
        });
        return view;
    }
}
