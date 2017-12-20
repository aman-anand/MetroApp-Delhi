package com.vgroupinc.metroapp.showRoute.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vgroupinc.metroapp.R;
import com.vgroupinc.metroapp.base.bean.Station;

import java.util.ArrayList;

/**
 * Created by DELL on 11/14/2017.
 */

public class RouteAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<Station> journeyArrayList;
    private LayoutInflater inflater;

    public RouteAdapter(Context context, ArrayList<Station> journeyArrayList) {
        this.context = context;
        this.journeyArrayList = journeyArrayList;
        this.inflater = (LayoutInflater.from(context));
        this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return journeyArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return journeyArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Station station = journeyArrayList.get(position);

        if (checkAheadForLineChange(position)) {
            convertView = inflater.inflate(R.layout.route_item_line_change, null);
            Station station1 = journeyArrayList.get(position + 1);
            ViewHolder holder = new ViewHolder();
            if (holder != null) {
                holder.lineNo = convertView.findViewById(R.id.lineNumber);
                holder.image = convertView.findViewById(R.id.position);
                holder.name = convertView.findViewById(R.id.stationName);
                holder.lineName = convertView.findViewById(R.id.lineName);
                holder.label = convertView.findViewById(R.id.smallLabel);
                switch (station.getLine_id()) {
                    case 1:

                        holder.name.setTextColor(context.getResources().getColor(R.color.RedLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.RedLine));
                        break;
                    case 2:

                        holder.name.setTextColor(context.getResources().getColor(R.color.GreenLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.GreenLine));
                        break;
                    case 3:

                        holder.name.setTextColor(context.getResources().getColor(R.color.YellowLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.YellowLine));
                        break;
                    case 4:
                        holder.name.setTextColor(context.getResources().getColor(R.color.BlueLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.BlueLine));
                        break;
                    case 5:
                        holder.name.setTextColor(context.getResources().getColor(R.color.VioletLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.VioletLine));
                        break;
                    case 6:
                        holder.name.setTextColor(context.getResources().getColor(R.color.LightBlueLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.LightBlueLine));
                        break;
                    case 7:
                        holder.name.setTextColor(context.getResources().getColor(R.color.OrangeLine));
                        holder.label.setTextColor(context.getResources().getColor(R.color.OrangeLine));
                        break;

                    default:

                        break;
                }

                switch (station1.getLine_id()) {
                    case 1:
                        holder.lineName.setText(R.string.redLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.RedLine));

                        holder.image.setColorFilter(context.getResources().getColor(R.color.RedLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 2:

                        holder.lineName.setText(R.string.greenLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.GreenLine));

                        holder.image.setColorFilter(context.getResources().getColor(R.color.GreenLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 3:
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.YellowLine));
                        holder.lineName.setText(R.string.yellowLine);
                        holder.image.setColorFilter(context.getResources().getColor(R.color.YellowLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 4:
                        holder.lineName.setText(R.string.blueLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.BlueLine));
                        holder.image.setColorFilter(context.getResources().getColor(R.color.BlueLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 5:
                        holder.lineName.setText(R.string.bioletLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.VioletLine));

                        holder.image.setColorFilter(context.getResources().getColor(R.color.VioletLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 6:
                        holder.lineName.setText(R.string.lbLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.LightBlueLine));
                        holder.image.setColorFilter(context.getResources().getColor(R.color.LightBlueLine), PorterDuff.Mode.SRC_ATOP);
                        break;
                    case 7:
                        holder.lineName.setText(R.string.orangeLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.OrangeLine));
                        holder.image.setColorFilter(context.getResources().getColor(R.color.OrangeLine), PorterDuff.Mode.SRC_ATOP);
                        break;

                    default:

                        break;
                }
            }
            holder.lineNo.setText("" + station1.getLine_id());
            holder.name.setText("" + station.getStation_name());


        } else {
            convertView = inflater.inflate(R.layout.route_item, null);
            ViewHolder holder = new ViewHolder();
            if (holder != null) {
                holder.lineNo = convertView.findViewById(R.id.lineNumber);
                holder.position = convertView.findViewById(R.id.position);
                holder.name = convertView.findViewById(R.id.stationName);
                holder.lineName = convertView.findViewById(R.id.lineName);
                switch (station.getLine_id()) {
                    case 1:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.red_line));
                        holder.lineName.setText(R.string.redLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.RedLine));
                        break;
                    case 2:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.green_line));
                        holder.lineName.setText(R.string.greenLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.GreenLine));
                        break;
                    case 3:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.yellow_line));
                        holder.lineName.setText(R.string.yellowLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.YellowLine));
                        break;
                    case 4:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.blue_line));
                        holder.lineName.setText(R.string.blueLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.BlueLine));
                        break;
                    case 5:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.violet_line));
                        holder.lineName.setText(R.string.bioletLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.VioletLine));
                        break;
                    case 6:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.light_blue_line));
                        holder.lineName.setText(R.string.lbLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.LightBlueLine));
                        break;
                    case 7:
                        holder.position.setBackground(context.getResources().getDrawable(R.drawable.orange_line));
                        holder.lineName.setText(R.string.orangeLine);
                        holder.lineName.setTextColor(context.getResources().getColor(R.color.OrangeLine));
                        break;

                    default:

                        break;
                }
            }
            holder.lineNo.setText("" + station.getLine_id());


            holder.name.setText("" + station.getStation_name());
            holder.position.setText("" + (position + 1));

        }


        return convertView;
    }

    private boolean checkAheadForLineChange(int position) {
        Boolean flag = false;
        int line_id = journeyArrayList.get(position).getLine_id();
        if (journeyArrayList.size() > (position + 1) && line_id != journeyArrayList.get(position + 1).getLine_id()) {
            flag = true;
        }
        return flag;

    }

    private class ViewHolder {
        private TextView position = null, name = null, lineNo = null, lineName = null, label = null;
        private ImageView image = null;
        private LinearLayout linearLayout = null;
    }
}
