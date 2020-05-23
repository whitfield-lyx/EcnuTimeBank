/**
 * @author K.makise on 2020/5/23
 */

package com.example.ecnutimebank.ui.publish;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.Employee;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class PublishedDetailAdapter extends RecyclerView.Adapter<PublishedDetailAdapter.ViewHolder> {
    private List<Employee> employees;
    private OnItemClickListener onItemClickListener;

    public PublishedDetailAdapter(List<Employee> employees, OnItemClickListener onItemClickListener) {
        this.employees = employees;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_published_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.employeeName.setText("Lisi");
        holder.employeeGender.setText("Female");
        holder.employeePhone.setText("5678984651");
        holder.userId = "123";
        holder.onItemClickListener = onItemClickListener;
        holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onAccept(holder.userId);
            }
        });
        holder.refuseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onRefuse(holder.userId);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private String userId;
        private OnItemClickListener onItemClickListener;
        private TextView employeeName;
        private TextView employeeGender;
        private TextView employeePhone;
        private Button acceptBtn;
        private Button refuseBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.employee_name);
            employeeGender = itemView.findViewById(R.id.employee_gender);
            employeePhone = itemView.findViewById(R.id.employee_phone);
            acceptBtn = itemView.findViewById(R.id.accept_employee_btn);
            refuseBtn = itemView.findViewById(R.id.refuse_employee_btn);
        }
    }

    interface OnItemClickListener {
        void onAccept(String id);

        void onRefuse(String id);
    }

}
