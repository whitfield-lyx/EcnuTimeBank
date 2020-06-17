package com.example.ecnutimebank.ui.publish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;
import com.example.ecnutimebank.ui.requirements.RequirementAdapter;

import java.util.List;

public class PublishedAdapter extends RecyclerView.Adapter<PublishedAdapter.ViewHolder> {
    private List<Requirement> requirements;
    private OnItemClickListener onItemClickListener;
    public PublishedAdapter (List<Requirement> requirements, OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
        this.requirements = requirements;
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
        holder.requirementStatus.setText("已接受");

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String requirementId;
        private OnItemClickListener onItemClickListener;
        private TextView requirementName;
        private TextView requirementTime;
        private TextView requirementStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requirementName = itemView.findViewById(R.id.requirement_name);
            requirementTime = itemView.findViewById(R.id.requirement_time);
            requirementStatus = itemView.findViewById(R.id.requirement_status);
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
