package com.example.h0per.rwtest.list;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.h0per.rwtest.R;

class MyVH extends RecyclerView.ViewHolder {
    private TextView textView;
    private ViewGroup parent;

    MyVH(@NonNull ViewGroup viewGroup) {
        super(viewGroup);
        this.parent = viewGroup;
        this.textView = viewGroup.findViewById(R.id.textview);
    }

    void bind(String fruitName) {
        textView.setText(fruitName);
    }

    public TextView getTextView() {
        return textView;
    }

    public ViewGroup getParent() {
        return parent;
    }
}