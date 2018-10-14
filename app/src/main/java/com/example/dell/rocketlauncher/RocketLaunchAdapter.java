package com.example.dell.rocketlauncher;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class RocketLaunchAdapter extends RecyclerView.Adapter<RocketLaunchAdapter.MyViewHolder> {

    private List<LaunchRocket> rocketList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView rocketName, launchTimeLocation, launchCountry, launchingMission, descriptionTV;
        public ImageView launchImageView;

        public MyViewHolder(View view) {
            super(view);
            rocketName = view.findViewById(R.id.launchTitleTextView);
            launchTimeLocation = view.findViewById(R.id.launchTimeLocationTextView);
            launchImageView = view.findViewById(R.id.launchImageView);
            launchCountry = view.findViewById(R.id.launchCountryTextView);
            launchingMission = view.findViewById(R.id.missionTextView);
            descriptionTV = view.findViewById(R.id.launchSummaryTextView);

        }
    }


    public RocketLaunchAdapter(List<LaunchRocket> rocketList) {
        this.rocketList = rocketList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.launch_shedule_layout, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        LaunchRocket rocket = rocketList.get(position);
        //get data from api and set here
        //please set here image tooo
        holder.rocketName.setText(rocket.getName());
        holder.launchCountry.setText(rocket.getCountry());
        holder.launchingMission.setText(rocket.getMission());
        holder.launchTimeLocation.setText(rocket.getTime()+" "+rocket.getLocation());
        holder.descriptionTV.setText(rocket.getDescription());

    }

    @Override
    public int getItemCount() {
        return rocketList.size();
    }
}
