package com.example.rahmabouraoui.foxsoundi;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Represents an adapter for contents
 */
public class GridAdapter extends BaseAdapter {

    Context context;
    private final String [] genres;
    private final int [] images;
    View view;
    LayoutInflater layoutInflater;

    public GridAdapter(Context context, String[] genres, int[] images) {
        this.context = context;
        this.genres = genres;
        this.images = images;
    }

    @Override
    public int getCount() {
        return genres.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if((convertView == null)) {
            view = new View(context);
            view = layoutInflater.inflate(R.layout.single_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
            TextView textView = (TextView) view.findViewById(R.id.textView);
            imageView.setImageResource(images[position]);
            textView.setText(genres[position]);
        }
        return  view;
    }

    @Nullable
    @Override
    public CharSequence[] getAutofillOptions() {
        return new CharSequence[0];
    }
}
