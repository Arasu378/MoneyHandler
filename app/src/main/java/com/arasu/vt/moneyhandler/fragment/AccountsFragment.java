package com.arasu.vt.moneyhandler.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.activity.AccountGroupActivity;
import com.arasu.vt.moneyhandler.activity.AddAccount;
import com.arasu.vt.moneyhandler.activity.SelectTheAccountGroup;

/**
 * Created by kyros on 29-09-2017.
 */

public class AccountsFragment extends Fragment {
    private ImageView edit_accounts;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_accounts,container,false);
        edit_accounts=(ImageView)view.findViewById(R.id.edit_accounts);
        edit_accounts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 PopupMenu popupMenu=new PopupMenu(getContext(),edit_accounts);

                popupMenu.getMenuInflater().inflate(R.menu.account_menu,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        CharSequence value=item.getTitle();
                        Toast.makeText(getContext(),"value : "+value.toString(),Toast.LENGTH_SHORT).show();
                        switch (value.toString()){
                            case "Add":
                                Intent intent=new Intent(getContext(),AccountGroupActivity.class);
                                startActivity(intent);

                                break;
                            case "Edit":
                                break;
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });
        return view;
    }
}
