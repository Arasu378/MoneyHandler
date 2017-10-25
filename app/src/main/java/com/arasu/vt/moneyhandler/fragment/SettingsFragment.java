package com.arasu.vt.moneyhandler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.activity.BackupActivity;
import com.arasu.vt.moneyhandler.adapters.SettingsAdapter;
import com.arasu.vt.moneyhandler.models.Events;
import com.arasu.vt.moneyhandler.models.GlobalBus;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by kyros on 20-09-2017.
 */

public class SettingsFragment extends Fragment {
    @Nullable
    public static Bus bus;

    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
      View view=inflater.inflate(R.layout.fragment_settings,container,false);

                 GridView gridView = (GridView) view.findViewById(R.id.grid_fragment);
        SettingsAdapter adapter=new SettingsAdapter(getContext());
        gridView.setAdapter(adapter);



        return view;
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


       switch (s.getMessage()){
           case "0":
        try{
            FragmentManager fragmentManager2 = getFragmentManager();
            FragmentTransaction fragmentTransaction2 = fragmentManager2.beginTransaction();
            ConfigurationFragment fragment=new ConfigurationFragment();
            fragmentTransaction2.addToBackStack("xyz");
            fragmentTransaction2.hide(SettingsFragment.this);
            fragmentTransaction2.add(android.R.id.content, fragment);
            fragmentTransaction2.commit();

        }catch (Exception e){
            e.printStackTrace();
        }
               break;
           case "1":
               break;
           case "2":
               break;
           case "3":
               break;
           case "4":
               Intent intent=new Intent(getContext(), BackupActivity.class);
               startActivity(intent);
               break;
           case "5":
               break;
           case "6":
               break;
           case "7":
               break;
           case "8":
               break;

       }

    }



}
