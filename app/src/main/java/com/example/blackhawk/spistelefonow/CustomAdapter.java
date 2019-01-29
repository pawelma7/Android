package com.example.blackhawk.spistelefonow;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends BaseAdapter {

    Context context;
    List<RowContact> rowContacts;

    public CustomAdapter(Context context, List<RowContact> rowContacts) {
        this.context = context;
        this.rowContacts = rowContacts;
    }

    @Override
    public int getCount() {
        return rowContacts.size();
    }

    @Override
    public Object getItem(int i) {
        return rowContacts.get(i);
    }

    @Override
    public long getItemId(int i) {
        return rowContacts.indexOf(getItem(i));
    }


    // Private view holder class
    private class ViewHolder {
        ImageView imageView;
        TextView contact;
    }


    @Override
    public View getView(int i, View convertView, ViewGroup parent) {


        View v = convertView;

        if (v == null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(context);
            v = vi.inflate(R.layout.list_contacts, null);
        }

        //Item p = getItem(position);
        RowContact p = (RowContact) getItem(i);

        if (p != null) {
            TextView tt1 = (TextView) v.findViewById(R.id.contactName);
            ImageView tt4 = (ImageView) v.findViewById(R.id.imageContact);

            if (tt1 != null) {
                tt1.setText(p.getName());
            }

            if (tt4 != null) {
                tt4.setImageResource(p.getImageContact());
            }
        }

        return v;


    }
}
