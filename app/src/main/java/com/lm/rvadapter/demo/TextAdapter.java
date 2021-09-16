package com.lm.rvadapter.demo;

import androidx.annotation.NonNull;
import com.lm.rvadapter.RVAdapter;
import com.lm.rvadapter.RVViewHolder;
import com.lm.rvadapter.demo.databinding.ItemTextBinding;

class TextAdapter extends RVAdapter<String, ItemTextBinding> {

    @Override
    public int getItemLayout() {
        return R.layout.item_text;
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder<ItemTextBinding> holder, int position) {
        holder.binding.text.setText(data.get(position));
    }
}
