package com.example.ecnutimebank.ui.requirements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequirementAdapter extends RecyclerView.Adapter<RequirementAdapter.ViewHolder> {
    private List<Requirement> requirements;
    private OnItemClickListener onItemClickListener;

    public RequirementAdapter(List<Requirement> requirements, OnItemClickListener onItemClickListener) {
        this.requirements = requirements;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requirement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.requirementId = "123";
        holder.onItemClickListener = onItemClickListener;
        holder.requirementName.setText("Name");
        holder.requirementTime.setText("Tomorrow");
        holder.requirementPlace.setText("School");
        holder.requirementBonus.setText("Money: " + 50);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String requirementId;
        private OnItemClickListener onItemClickListener;
        private TextView requirementName;
        private TextView requirementPlace;
        private TextView requirementTime;
        private TextView requirementBonus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requirementName = itemView.findViewById(R.id.requirement_name);
            requirementPlace = itemView.findViewById(R.id.requirement_place);
            requirementTime = itemView.findViewById(R.id.requirement_time);
            requirementBonus = itemView.findViewById(R.id.requirement_bonus);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onItemClicked(requirementId);
                }
            });
        }
    }

    interface OnItemClickListener {
        void onItemClicked(String id);
    }

}
