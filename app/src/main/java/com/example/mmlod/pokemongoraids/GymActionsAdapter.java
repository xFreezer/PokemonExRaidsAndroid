package com.example.mmlod.pokemongoraids;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by MMlod on 03.02.2018.
 */

class GymActionsAdapter extends ArrayAdapter<GymAction> {

    private static final String TAG = "GymActionsAdapter";
    private Context mContext;
    private int mResource;

    /**
     * Default constructor for the GymActionsAdapter
     * @param context
     * @param resource
     * @param objects
     */

    public GymActionsAdapter(Context context, int resource, ArrayList<GymAction> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String name = getItem(position).getName();
        String startDate = getItem(position).getStartDate();
        String endDate = getItem(position).getEndDate();

        GymAction gymAction = new GymAction(name, startDate, endDate);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.textView1);
        TextView tvStartDate = (TextView) convertView.findViewById(R.id.textView2);
        TextView tvEndDate = (TextView) convertView.findViewById(R.id.textView3);
        tvName.setText(name);
        tvStartDate.setText(startDate);
        tvEndDate.setText(endDate);

        return convertView;
    }
}
