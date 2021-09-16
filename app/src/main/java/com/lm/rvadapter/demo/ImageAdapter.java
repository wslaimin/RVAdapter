package com.lm.rvadapter.demo;

import androidx.annotation.NonNull;
import com.lm.rvadapter.RVAdapter;
import com.lm.rvadapter.RVViewHolder;
import com.lm.rvadapter.demo.databinding.ItemImageBinding;

class ImageAdapter extends RVAdapter<Integer, ItemImageBinding> {
    @Override
    public int getItemLayout() {
        return R.layout.item_image;
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder<ItemImageBinding> holder, int position) {
        holder.binding.image.setImageResource(data.get(position));
    }
}
