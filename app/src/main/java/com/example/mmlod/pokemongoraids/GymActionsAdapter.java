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
    private ArrayList<GymAction> allGyms;
    private ArrayList<GymAction> filteredGyms = null;

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
        filteredGyms = objects;
        this.allGyms = new ArrayList<GymAction>();
        this.allGyms.addAll(objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

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

        holder.name.setText(filteredGyms.get(position).getName());
        holder.startDate.setText(filteredGyms.get(position).getStartDate());
        holder.endDate.setText(filteredGyms.get(position).getEndDate());

        return convertView;
    }

    public void filter(String text){
        text = text.toLowerCase();
        filteredGyms.clear();
        if(text.length() == 0){
            filteredGyms.addAll(allGyms);
        } else if(text.matches("\\d{2}-\\d{2}-\\d{4}")) {
            for (GymAction ga : allGyms){
                if(ga.getEndDate().equals(text)) filteredGyms.add(ga);
            }
        } else {
            for (GymAction ga : allGyms){
                if(ga.getName().toLowerCase().contains(text)) filteredGyms.add(ga);
            }
        }

        notifyDataSetChanged();
    }
}
