package com.example.dell.rocketlauncher;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RocketLaunchAdapter extends RecyclerView.Adapter<RocketLaunchAdapter.MyViewHolder> {

    private List<Launch> launchList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, net;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.launchTitleTextView);
            net = (TextView) view.findViewById(R.id.launchTimeLocationTextView);

        }
    }


    public RocketLaunchAdapter(List<Launch> launchList) {
        this.launchList = launchList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.launch_shedule_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Launch launch = launchList.get(position);
        holder.name.setText(launch.getName());
        holder.net.setText(launch.getNet());
    }

    @Override
    public int getItemCount() {
        return launchList.size();
    }
}