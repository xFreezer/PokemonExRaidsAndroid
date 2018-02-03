package com.example.mmlod.pokemongoraids;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;


/**
 * Created by MMlod on 03.02.2018.
 */

class GymActionsAdapter extends ArrayAdapter<GymAction> {

    private static final String TAG = "GymActionsAdapter";
    private Context mContext;
    private int mResource;
    private int lastPosition = -1;

    static class ViewHolder {
        TextView name;
        TextView startDate;
        TextView endDate;

    }

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

        final View result;

        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = LayoutInflater.from(mContext);
            convertView = inflater.inflate(mResource, parent, false);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView1);
            holder.startDate = (TextView) convertView.findViewById(R.id.textView2);
            holder.endDate = (TextView) convertView.findViewById(R.id.textView3);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        result = convertView;


        Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.load_down_anim : R.anim.load_up_anim);
        result.startAnimation(animation);
        lastPosition = position;

        holder.name.setText(name);
        holder.startDate.setText(startDate);
        holder.endDate.setText(endDate);

        return convertView;
    }
}
