package com.larryngo.fetchrewardsexercise;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class GridAdapter extends BaseAdapter {
    private Context context;
    private List<FetchObject> list_objects;

    public GridAdapter(Context context, List<FetchObject> list_objects) {
        this.context = context;
        this.list_objects = list_objects; //gets the list to do things with
    }

    @Override
    public int getCount() {
        return list_objects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public void addDataList(List<FetchObject> list_objects) {
        this.list_objects.clear(); //clearing data list so a new one can be made.
        this.list_objects = list_objects; //creating a new list of objects to show
        notifyDataSetChanged(); //will notify the gridview to update its view (everytime a new datalist is created)
    }


    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        if(view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE); //inflater info
            view = inflater.inflate(R.layout.grid_entry_layout, null); //grabs the necessary layout to show the information
        }

        //Getting the texts for the necessary columns
        TextView tv_listID = view.findViewById(R.id.fetch_listid);
        TextView tv_ID = view.findViewById(R.id.fetch_id);
        TextView tv_name = view.findViewById(R.id.fetch_name);

        //setting the texts for each column
        if(position >= 1) { //prevent accessing unknown in the array
            if(list_objects.get(position).getListId() == list_objects.get(position-1).getListId()) {
                tv_listID.setText(""); //no text
            } else {
                tv_listID.setText(String.valueOf(list_objects.get(position).getListId())); //sets the listid
            }
        } else {
            tv_listID.setText(String.valueOf(list_objects.get(position).getListId())); //sets the listid for the first entry ( 1 )
        }

        tv_ID.setText(String.valueOf(list_objects.get(position).getId()));
        tv_name.setText(list_objects.get(position).getName());

        return view;
    }
}
