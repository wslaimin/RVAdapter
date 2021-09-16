package com.lm.rvadapter;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class RVViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
    public T binding;
    public RVViewHolder(@NonNull View itemView) {
        super(itemView);
        binding=DataBindingUtil.bind(itemView);
    }
}
