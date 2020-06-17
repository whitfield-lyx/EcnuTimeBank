package com.example.ecnutimebank.ui.requirements;

import com.example.ecnutimebank.entity.Order;

import java.io.Serializable;
import java.util.List;

public class BundleData implements Serializable {
    private List<Order> data;

    public BundleData(List<Order> data) {
        this.data = data;
    }

    public List<Order> getData() {
        return data;
    }

    public void setData(List<Order> data) {
        this.data = data;
    }
}
