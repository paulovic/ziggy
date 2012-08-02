package com.paulovic.labs.movee.adapter;

import java.util.List;

import com.paulovic.labs.movee.R;
import com.paulovic.labs.movee.model.MovieData;
import com.paulovic.labs.movee.view.MovieItemView;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class MoviesAdapter extends BaseAdapter {

    private List mData;
    private LayoutInflater mInflater;
    // Helper for communication with owner activity
    private Handler mHandler;

    public MoviesAdapter(List data, Context context, Handler handler) {
        mData = data;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mHandler = handler;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MovieItemView view;
        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.list_item_movie, parent, false);
        }
        view = (MovieItemView) convertView;

        MovieData movieData = (MovieData) getItem(position);
        movieData.setId(position);

        view.bindView(movieData, mHandler); 
        return view;
    }
    
    public void setCommunicationHandler(Handler handler) {
        mHandler = handler;
    }

    public void updateItems(List entries) {
        mData = entries;
        notifyDataSetChanged();
    }
    
    public void removeItem(int id) {
        mData.remove(id);
        notifyDataSetChanged();
    }
}
