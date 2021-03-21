package com.example.regional_information;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionViewHolder> {

    private List<RegionInfo> list;

    public RegionAdapter(List<RegionInfo> list){
        this.list = list;
    }

    @NonNull
    @Override
    public RegionViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.region_layout, parent, false);
        return new RegionAdapter.RegionViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        holder.region.setText(list.get(position).getRegionName());
        holder.sickForDay.setText(""+list.get(position).getSick());
        Intent intent = new Intent(context,RegionDetail.class);
        intent.putExtra("index",position);
        holder.itemView.setOnClickListener(e->context.startActivity(intent));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class RegionViewHolder extends RecyclerView.ViewHolder {
        TextView region;
        TextView sickForDay;
        public RegionViewHolder(@NonNull View itemView) {
            super(itemView);
            region = itemView.findViewById(R.id.tvRegion);
            sickForDay = itemView.findViewById(R.id.tvSick);
        }
    }
}
