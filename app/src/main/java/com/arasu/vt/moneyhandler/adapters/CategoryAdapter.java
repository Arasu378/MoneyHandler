package com.arasu.vt.moneyhandler.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.arasu.vt.moneyhandler.R;
import com.arasu.vt.moneyhandler.activity.InsertIncomeCategoryActivity;
import com.arasu.vt.moneyhandler.dbconnections.DatabaseHandler;
import com.arasu.vt.moneyhandler.dbconnections.Income;
import com.arasu.vt.moneyhandler.dbconnections.RealmController;
import com.arasu.vt.moneyhandler.swipegestures.ItemTouchHelperAdapter;
import com.arasu.vt.moneyhandler.swipegestures.OnBarListChangedListner;
import com.arasu.vt.moneyhandler.swipegestures.OnStartDragListener;

import java.util.Collections;
import java.util.List;

import io.realm.Realm;

/**
 * Created by kyros on 21-09-2017.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> implements ItemTouchHelperAdapter {
    private Context mContext;
    private List<Income>categoryList;
    private final OnStartDragListener mDragStartListener;
    private OnBarListChangedListner mListChangedListener;

    public CategoryAdapter(Context mContext,List<Income>categoryList,OnStartDragListener onStartDragListener,OnBarListChangedListner mListChangedListener){
        this.mContext=mContext;
        this.categoryList=categoryList;
        mDragStartListener=onStartDragListener;
        this.mListChangedListener=mListChangedListener;

    }


    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item_list,parent,false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final CategoryViewHolder holder, int position) {
       final Income income=categoryList.get(position);
        holder.text_category.setText(income.getTitle());
        int id=income.getId();
        holder.category_move.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
               if(MotionEventCompat.getActionMasked(event)==MotionEvent.ACTION_DOWN){
                   mDragStartListener.onStartDrag(holder);
               }
                return false;
            }
        });
        holder.relative_category.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(mContext, InsertIncomeCategoryActivity.class);
                i.putExtra("title",income.getTitle());
                i.putExtra("id",income.getId());
                mContext.startActivity(i);
            }
        });
    }




    @Override
    public int getItemCount() {
        if(categoryList!=null){
            return categoryList.size();
        }
        return 0;
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition) {
        Collections.swap(categoryList,fromPosition,toPosition);
        mListChangedListener.onBarListChanged(categoryList);
        notifyItemMoved(fromPosition,toPosition);
        return true;
    }

    @Override
    public void onItemDismiss(final int position) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(mContext);
        alertDialogBuilder.setMessage("Are you sure wanted to delete?");
        alertDialogBuilder.setPositiveButton("yes",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        categoryList.remove(position);
                        notifyItemRemoved(position);
                        Log.d("Position: ",""+position);
                        notifyDataSetChanged();
                        Log.d("Category Size : ",""+categoryList.size());
                        try{
                            int y;
                            if(position==0){
                                y=1;
                            }else{
                                y=position-1;
                            }
                            Income income=categoryList.get(y);
                            Log.d("Income title : ",""+ income.getTitle()+" / "+" / Id : "+income.getId());
                          //  DatabaseHandler db=new DatabaseHandler(mContext);

                           // db.deleteCategory(income);
                        }catch (Exception e){
                            e.printStackTrace();
                        }




                        Toast.makeText(mContext.getApplicationContext(),"Category Deleted Successfully!",Toast.LENGTH_SHORT).show();


                    }
                });
        alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notifyDataSetChanged();

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder{
        public ImageView delete_icon,edit_category,category_move;
        public TextView text_category;
        public RelativeLayout relative_category;
        public CategoryViewHolder(View itemView) {
            super(itemView);
            text_category=(TextView)itemView.findViewById(R.id.text_category);
            delete_icon=(ImageView)itemView.findViewById(R.id.delete_icon);
            edit_category=(ImageView)itemView.findViewById(R.id.edit_category);
            category_move=(ImageView)itemView.findViewById(R.id.category_move);
            relative_category=(RelativeLayout)itemView.findViewById(R.id.relative_category);
        }
    }
}
