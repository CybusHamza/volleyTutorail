package com.app.volleytutorail;


import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ListAddapter  extends ArrayAdapter<POJO>{


    Activity activity;
    ArrayList<POJO> pojos;
    private LayoutInflater inflater;


    public ListAddapter(Activity activity , int resource,ArrayList<POJO> pojos) {
        super(activity,resource);
        this.activity = activity;
        this.pojos = pojos;

    }


    @Override
    public int getCount() {
        return pojos.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = null;
        inflater = (LayoutInflater) activity
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            v = inflater.inflate(R.layout.list_row, null);


        ImageView profilepic = (ImageView) v.findViewById(R.id.profile_pic);
        TextView id = (TextView) v.findViewById(R.id.id);
        TextView first = (TextView) v.findViewById(R.id.firstt_name);
        TextView last = (TextView) v.findViewById(R.id.last_name);


        POJO pojo= pojos.get(position);

        id.setText(pojo.getId());
        first.setText(pojo.getFirstName());
        last.setText(pojo.getLastName());


        Picasso.with(activity).
                load("http://epay.cybussolutions.com/epay/"+pojo.getProfile().trim()).into(profilepic);




        return v;
    }
}
