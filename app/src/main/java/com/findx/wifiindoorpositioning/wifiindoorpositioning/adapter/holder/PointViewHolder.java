package com.findx.wifiindoorpositioning.wifiindoorpositioning.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;

/**
 * Created by sankalp on 13/04/19.
 */

public class PointViewHolder extends RecyclerView.ViewHolder {
    final TextView tvIdentifier, tvIdentifier2, tvPointX, tvPointY;

    public PointViewHolder(View itemView) {
        super(itemView);
        tvIdentifier = itemView.findViewById(R.id.point_identifier);
        tvIdentifier2 = itemView.findViewById(R.id.point_identifier2);
        tvPointX = itemView.findViewById(R.id.point_x);
        tvPointY = itemView.findViewById(R.id.point_y);

    }
}
