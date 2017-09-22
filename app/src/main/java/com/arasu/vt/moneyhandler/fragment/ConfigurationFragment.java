package com.arasu.vt.moneyhandler.fragment;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.activity.Budget;
import com.arasu.vt.moneyhandler.activity.ChangeIncomeActivity;
import com.arasu.vt.moneyhandler.activity.ExpensesCategoryActivity;
import com.arasu.vt.moneyhandler.activity.SubCategorySettings;

/**
 * Created by kyros on 21-09-2017.
 */

public class ConfigurationFragment extends Fragment {
    private ImageView back_fragment;
    private LinearLayout income_category_linearlayout,expense_linear;
    private RelativeLayout subcategory_relative,budgetsetting_relative;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_configuration,container,false);
        income_category_linearlayout=(LinearLayout)view.findViewById(R.id.income_category_linearlayout);
        expense_linear=(LinearLayout)view.findViewById(R.id.expense_linear);
        subcategory_relative=(RelativeLayout)view.findViewById(R.id.subcategory_relative);
        budgetsetting_relative=(RelativeLayout)view.findViewById(R.id.budgetsetting_relative);
        budgetsetting_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), Budget.class);
                startActivity(i);
            }
        });
        subcategory_relative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), SubCategorySettings.class);
                startActivity(i);
            }
        });

        back_fragment=(ImageView)view.findViewById(R.id.back_fragment);
        income_category_linearlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ChangeIncomeActivity.class);
                startActivity(i);
            }
        });
        expense_linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getActivity(), ExpensesCategoryActivity.class);
                startActivity(i);
            }
        });
        back_fragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    FragmentManager fragmentManager2 = getFragmentManager();
                    FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
                    SettingsFragment fragment=new SettingsFragment();
                    fragmentTransaction2.addToBackStack(null);
                    fragmentTransaction2.detach(ConfigurationFragment.this);
                    fragmentTransaction2.add(android.R.id.content, fragment);
                    fragmentTransaction2.commit();

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

        return view;
    }
}
