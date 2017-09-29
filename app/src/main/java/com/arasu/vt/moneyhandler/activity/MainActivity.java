package com.arasu.vt.moneyhandler.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.fragment.AccountsFragment;
import com.arasu.vt.moneyhandler.fragment.SettingsFragment;

public class MainActivity extends AppCompatActivity {


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.navigation_accounts:
                    AccountsFragment fragment_Accounts=new AccountsFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction_acc=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction_acc.replace(R.id.content,fragment_Accounts);
                    fragmentTransaction_acc.commit();
                    return true;
                case R.id.navigation_settings:
                    SettingsFragment fragment=new SettingsFragment();
                    android.support.v4.app.FragmentTransaction fragmentTransaction=
                            getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.content,fragment);
                    fragmentTransaction.commit();

                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout frameLayout=(FrameLayout)findViewById(R.id.content);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}
