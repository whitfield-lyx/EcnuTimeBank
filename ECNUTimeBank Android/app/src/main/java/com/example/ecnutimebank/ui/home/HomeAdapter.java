package com.example.ecnutimebank.ui.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Facility;

import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private List<Facility> facilityList = new ArrayList<>();
    private OnItemClickListener onItemClickListener;

    public void setFacilityList(List<Facility> facilityList) {
        this.facilityList = facilityList;
    }
    public HomeAdapter(List<Facility> facilityList, OnItemClickListener onItemClickListener) {
        this.facilityList = facilityList;
        this.onItemClickListener = onItemClickListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (facilityList != null&&facilityList.size()!=0) {
            Facility curFacility = facilityList.get(position);
            holder.facility_id = String.valueOf(position+1);
            holder.facility_name.setText(curFacility.getFacilityName());
            holder.facility_type.setText("学校");
            holder.facility_distance.setText("500m");
            holder.facility_address.setText(curFacility.getFacilityAddress());
            holder.onItemClickListener = onItemClickListener;
        } else {
            holder.facility_id = String.valueOf(position+1);
            holder.onItemClickListener = onItemClickListener;
            holder.facility_name.setText("华东师范大学");
            holder.facility_type.setText("学校");
            holder.facility_distance.setText("500m");
            holder.facility_address.setText("普陀区");
        }


        /*int width = ((Activity)holder.requirementImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = holder.requirementImage.getLayoutParams();
        params.height =  (int) (200 + Math.random() * 400) ;
        holder.requirementImage.setLayoutParams(params);
        */
    }

    @Override
    public int getItemCount() {
        if (facilityList.size()==0)
            return 20;
        else
            return facilityList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String facility_id;
        private OnItemClickListener onItemClickListener;
        private TextView facility_name;
        private TextView facility_type;
        private TextView facility_distance;
        private TextView facility_address;
        private ImageView facility_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            facility_name = itemView.findViewById(R.id.home_item_place);
            facility_type = itemView.findViewById(R.id.home_item_type_of_place);
            facility_distance = itemView.findViewById(R.id.home_item_distance);
            facility_address = itemView.findViewById(R.id.home_item_area);
            facility_image = itemView.findViewById(R.id.home_cardImage);
            ViewGroup.LayoutParams params = facility_image.getLayoutParams();
            params.height =  (int) (500 + Math.random() * 500) ;
            facility_image.setLayoutParams(params);
            itemView.setOnClickListener(view -> onItemClickListener.onItemClicked(facility_id));
        }
    }

    interface OnItemClickListener {
        void onItemClicked(String id);
    }


}