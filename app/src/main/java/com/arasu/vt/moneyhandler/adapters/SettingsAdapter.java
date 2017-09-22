package com.arasu.vt.moneyhandler.adapters;

import android.content.Context;
import android.media.Image;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.fragment.SettingsFragment;
import com.arasu.vt.moneyhandler.models.Events;
import com.arasu.vt.moneyhandler.models.GlobalBus;
import com.squareup.otto.Bus;
import com.squareup.otto.Produce;
import com.squareup.otto.Subscribe;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by kyros on 20-09-2017.
 */

public class SettingsAdapter extends BaseAdapter {
    private Context mContext;
    public static Bus bus;

    private String[] mNames={"Configuration","Accounts","Passcode",
    "PC Manager","Backup","Style","Feedback","Help","Recommend"};
    private int[] mIcons={R.drawable.settings_filled_color,R.drawable.accounts_icon,R.drawable.passcode_icon,
    R.drawable.pc_manager_icon,R.drawable.backup_icon,R.drawable.style_icon,R.drawable.feedback_icon,
    R.drawable.help_icon,R.drawable.share_icon};
    public SettingsAdapter(Context mContext){
        this.mContext=mContext;
    }
    @Override
    public int getCount() {
        return mNames.length;
    }

    @Override
    public Object getItem(int position) {
        return mNames[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view= LayoutInflater.from(mContext).inflate(R.layout.grid_item_list,parent,false);
        bus = new Bus(ThreadEnforcer.MAIN);
        bus.register(this);
        TextView text_settings=(TextView)view.findViewById(R.id.text_settings);
        ImageView image_settings_icon=(ImageView)view.findViewById(R.id.image_settings_icon);
        LinearLayout layout_settings=(LinearLayout)view.findViewById(R.id.layout_settings);
        layout_settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    bus.post(String.valueOf(position));
                    Log.d("Adapter Position : ",""+position);
                    Events.AdapterFragmentMessage adapterFragmentMessageEvent =
                            new Events.AdapterFragmentMessage(String.valueOf(position));
                    GlobalBus.getBus().post(adapterFragmentMessageEvent);

                    //   Toast.makeText(mContext.getApplicationContext(),"Adapter Position : "+position,Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });


        text_settings.setText(mNames[position]);
        image_settings_icon.setImageResource(mIcons[position]);

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
