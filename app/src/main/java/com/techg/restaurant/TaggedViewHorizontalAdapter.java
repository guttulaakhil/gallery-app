package com.techg.restaurant;


import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class TaggedViewHorizontalAdapter extends RecyclerView.Adapter<TaggedViewHorizontalAdapter.ViewHolder> {
    private SQLiteDatabase db;
    private ArrayList<Item> items;
    private Category category;
    private Context context;
    private FragmentManager fm;

    // Provide a suitable constructor (depends on the kind of dataset)
    public TaggedViewHorizontalAdapter(SQLiteDatabase db, Category category, Context context, FragmentManager fm) {
        this.db = db;
        this.category = category;
        if(db != null)
            this.items = Item.getItemsOfCategoryFromDb(db, category);

        this.category = category;
        this.context = context;
        this.fm = fm;
    }

    @Override
    public TaggedViewHorizontalAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.tagged_horizontal_view, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        int resid = context.getResources().getIdentifier(items.get(position).filename,"drawable",context.getPackageName());
        holder.imageView.setImageResource(resid);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ContentView.class);
                intent.putExtra("position", position);
                intent.putExtra("type", "category");
                intent.putExtra("category_id", category.id);
                context.startActivity(intent);
            }
        });
        holder.imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {

                FragmentTransaction ft = fm.beginTransaction();
                Fragment prev = fm.findFragmentByTag("dialog");
                if (prev != null) {
                    ft.remove(prev);
                }
                ft.addToBackStack(null);

                Bundle args = new Bundle();
                args.putLong("item_id", items.get(position).id);
                args.putBoolean("editMode", true);
                DialogFragment dialogFragment = new AddTagsFragment();
                dialogFragment.setArguments(args);
                dialogFragment.show(ft, "dialog_edit_tags");

                return true;
            }
        });


    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public View view;

        public ViewHolder(View v) {
            super(v);
            this.view = v;
            imageView = (ImageView) v.findViewById(R.id.horizontal_view_image);
        }
    }

}
