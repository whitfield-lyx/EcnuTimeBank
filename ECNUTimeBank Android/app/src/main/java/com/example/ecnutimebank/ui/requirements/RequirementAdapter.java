package com.example.ecnutimebank.ui.requirements;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Order;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RequirementAdapter extends RecyclerView.Adapter<RequirementAdapter.ViewHolder> {
    private List<Order> orders;
    private OnItemClickListener onItemClickListener;

    public RequirementAdapter(List<Order> orders, OnItemClickListener onItemClickListener) {
        this.orders = orders;
        this.onItemClickListener = onItemClickListener;
    }

    public void setData(List<Order> orders) {
        this.orders = orders;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_requirement, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Order order = orders.get(position);
        holder.requirementId = String.valueOf(order.getOrderId());
        holder.onItemClickListener = onItemClickListener;
        holder.requirementName.setText(order.getOrderTitle());
        holder.requirementPlace.setText(order.getOrderAddress());
        holder.requirementDate.setText(order.getOrderTime());
    }

    @Override
    public int getItemCount() {
        return orders.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String requirementId;
        private OnItemClickListener onItemClickListener;
        private TextView requirementName;
        private TextView requirementPlace;
        private TextView requirementDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            requirementName = itemView.findViewById(R.id.requirement_name);
            requirementPlace = itemView.findViewById(R.id.requirement_place);
            requirementDate = itemView.findViewById(R.id.requirement_date);
            itemView.setOnClickListener(view -> onItemClickListener.onItemClicked(requirementId));
        }
    }

    interface OnItemClickListener {
        void onItemClicked(String id);
    }

}