package com.techg.restaurant;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class GridViewAdapter extends BaseAdapter {
    private Context mContext;
    private SQLiteDatabase db;
    public GridViewAdapter(Context context, SQLiteDatabase db) {
        mContext = context;
        this.db = db;
    }

    public int getCount() {
//        return Item.getAllUntaggedItemsFromDb(db).size();
        return 20;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(320, 320));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        }
        else {
            imageView = (ImageView) convertView;
        }

        int resid = mContext.getResources().getIdentifier("image","drawable",mContext.getPackageName());
        imageView.setImageResource(resid);
        return imageView;
    }

}