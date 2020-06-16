package com.example.ecnutimebank.ui.publish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Requirement;
import java.util.List;

public class AcceptedAdapter extends RecyclerView.Adapter<AcceptedAdapter.ViewHolder> {
    private List<Requirement> requirements;
    private OnItemClickListener onItemClickListener;

    public AcceptedAdapter (List<Requirement> requirements,OnItemClickListener onItemClickListener) {
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
//        holder.requirementName.setText("Name");
//        holder.requirementPlace.setText("School");
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requirementName = itemView.findViewById(R.id.requirement_name);
            requirementPlace = itemView.findViewById(R.id.requirement_place);
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
