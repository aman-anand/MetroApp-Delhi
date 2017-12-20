package com.vgroupinc.metroapp.base.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.vgroupinc.metroapp.R;
import com.vgroupinc.metroapp.base.bean.Station;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DELL on 11/13/2017.
 */

public class StationAdapter extends ArrayAdapter<Station> {
    private final Context mContext;
    private final List<Station> m_stations;
    private final List<Station> m_stations_All;
    private final List<Station> m_stations_suggestions;
    private final int mLayoutResourceId;

    public StationAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Station> objects) {
        super(context, resource, objects);
        this.mContext = context;
        this.mLayoutResourceId = resource;
        this.m_stations = new ArrayList<>(objects);
        this.m_stations_All = new ArrayList<>(objects);
        this.m_stations_suggestions = new ArrayList<>();
    }

    public int getCount() {
        return m_stations.size();
    }

    public Station getItem(int position) {
        return m_stations.get(position);
    }

    public long getItemId(int position) {
        return position;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        try {
            if (convertView == null) {
                LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
                convertView = inflater.inflate(mLayoutResourceId, parent, false);
            }
            Station station = getItem(position);
            TextView name = (TextView) convertView.findViewById(R.id.textView);
            name.setText(station.getStation_name());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return convertView;
//        return super.getView(position, convertView, parent);
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            public CharSequence convertResultToString(Object resultValue) {
                return ((Station) resultValue).getStation_name();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                if (constraint != null) {
                    m_stations_suggestions.clear();
                    for (Station station : m_stations_All) {
                        if (station.getStation_name().toLowerCase().contains(constraint.toString().toLowerCase())) {
                            m_stations_suggestions.add(station);
                        }
                    }
                    FilterResults filterResults = new FilterResults();
                    filterResults.values = m_stations_suggestions;
                    filterResults.count = m_stations_suggestions.size();
                    return filterResults;
                } else {
                    return new FilterResults();
                }
            }


            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                m_stations.clear();
                if (results != null && results.count > 0) {
                    // avoids unchecked cast warning when using mDepartments.addAll((ArrayList<Department>) results.values);
                    List<?> result = (List<?>) results.values;
                    for (Object object : result) {
                        if (object instanceof Station) {
                            m_stations.add((Station) object);
                        }
                    }
                } else if (constraint == null) {
                    // no filter, add entire original list back in
                    m_stations.addAll(m_stations_All);
                }
                notifyDataSetChanged();
            }
        };
    }
}
