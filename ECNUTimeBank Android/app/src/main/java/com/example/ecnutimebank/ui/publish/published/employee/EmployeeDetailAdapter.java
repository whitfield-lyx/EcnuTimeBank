/**
 * @author K.makise on 2020/5/23
 */

package com.example.ecnutimebank.ui.publish.published.employee;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ecnutimebank.R;
import com.example.ecnutimebank.entity.User;

import java.util.List;

public class EmployeeDetailAdapter extends RecyclerView.Adapter<EmployeeDetailAdapter.ViewHolder> {
    private List<User> volunteers;
    private OnItemClickListener onItemClickListener;

    public void setVolunteers(List<User> volunteers) {
        this.volunteers = volunteers;
    }
    public EmployeeDetailAdapter(List<User> volunteers, OnItemClickListener onItemClickListener) {
        this.volunteers = volunteers;
        this.onItemClickListener = onItemClickListener;
    }

    public List<User> getData() {
        return volunteers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_employee_detail, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        if (volunteers!=null) {
            User employee = volunteers.get(position);
            holder.employeeName.setText(employee.getUserName());
            holder.employeeGender.setText(employee.getUserGender());
            holder.employeePhone.setText(employee.getUserTelephone());
            holder.userId = employee.getUserId();
            holder.onItemClickListener = onItemClickListener;
//            holder.acceptBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickListener.onAccept(holder.userId);
//                }
//            });
//            holder.refuseBtn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    onItemClickListener.onRefuse(holder.userId);
//                }
//            });
        }
    }

    @Override
    public int getItemCount() {
        return volunteers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private Integer userId;
        private OnItemClickListener onItemClickListener;
        private TextView employeeName;
        private TextView employeeGender;
        private TextView employeePhone;
        private ImageButton acceptBtn;
        private ImageButton refuseBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            employeeName = itemView.findViewById(R.id.employee_name);
            employeeGender = itemView.findViewById(R.id.employee_gender);
            employeePhone = itemView.findViewById(R.id.employee_phone);
            acceptBtn = itemView.findViewById(R.id.accept_employee_btn);
            refuseBtn = itemView.findViewById(R.id.refuse_employee_btn);
            acceptBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onAccept(userId);
                }
            });
            refuseBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onItemClickListener.onRefuse(userId);
                }
            });
        }
    }

    interface OnItemClickListener {
        void onAccept(Integer id);
        void onRefuse(Integer id);
    }

}
