package com.example.ecnutimebank.ui.home;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    private List<Requirement> requirements;
    private OnItemClickListener onItemClickListener;

    public HomeAdapter(List<Requirement> requirements, OnItemClickListener onItemClickListener) {
        this.requirements = requirements;
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
        holder.requirementId = "123";
        holder.onItemClickListener = onItemClickListener;
        holder.requirementPlace.setText("华东师范大学");
        holder.requirementType_of_place.setText("学校");
        holder.requirementDistance.setText("500m");
        holder.requirementArea.setText("普陀区");
        /*int width = ((Activity)holder.requirementImage.getContext()).getWindowManager().getDefaultDisplay().getWidth();
        ViewGroup.LayoutParams params = holder.requirementImage.getLayoutParams();
        params.height =  (int) (200 + Math.random() * 400) ;
        holder.requirementImage.setLayoutParams(params);
        */
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String requirementId;
        private OnItemClickListener onItemClickListener;
        private TextView requirementPlace;
        private TextView requirementType_of_place;
        private TextView requirementDistance;
        private TextView requirementArea;
        private ImageView requirementImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requirementPlace = itemView.findViewById(R.id.home_item_place);
            requirementType_of_place = itemView.findViewById(R.id.home_item_type_of_place);
            requirementDistance = itemView.findViewById(R.id.home_item_distance);
            requirementArea = itemView.findViewById(R.id.home_item_area);
            requirementImage = itemView.findViewById(R.id.home_cardImage);
            ViewGroup.LayoutParams params = requirementImage.getLayoutParams();
            params.height =  (int) (500 + Math.random() * 500) ;
            requirementImage.setLayoutParams(params);
            itemView.setOnClickListener(view -> onItemClickListener.onItemClicked(requirementId));
        }
    }

    interface OnItemClickListener {
        void onItemClicked(String id);
    }

}