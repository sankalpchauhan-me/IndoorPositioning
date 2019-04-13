package com.findx.wifiindoorpositioning.wifiindoorpositioning.adapter.holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.findx.wifiindoorpositioning.wifiindoorpositioning.R;

/**
 * Created by sankalp on 13/04/19.
 */

public class SectionHeaderViewHolder extends RecyclerView.ViewHolder {
    final TextView tvTitle;


    public SectionHeaderViewHolder(View headerView) {
        super(headerView);
        tvTitle = (TextView) headerView.findViewById(R.id.tv_section_name);
    }
}
