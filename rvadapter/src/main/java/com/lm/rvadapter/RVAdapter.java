package com.lm.rvadapter;

import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public abstract class RVAdapter<T, R extends ViewDataBinding> extends RecyclerView.Adapter<RVViewHolder<R>> {
    protected List<T> data = new ArrayList<>();
    private RVIClickListener itemClickListener;
    private final SparseArray<RVIClickListener> viewClickListeners = new SparseArray<>();

    public void setData(List<T> data) {
        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return data;
    }

    public void setItemClickListener(RVIClickListener listener) {
        this.itemClickListener = listener;
    }

    public void addViewClickListener(RVIClickListener childListener, int... childIds) {
        for (int childId : childIds) {
            addViewClickListener(childId, childListener);
        }
    }

    public RVIClickListener getViewClickListener(int viewId) {
        return viewClickListeners.get(viewId);
    }

    public void append(List<T> items) {
        int start = data.size();
        data.addAll(items);
        notifyItemRangeInserted(start, items.size());
    }

    public void prepend(List<T> items) {
        data.addAll(0, items);
        notifyDataSetChanged();
    }

    public void add(T item, int position) {
        data.add(position, item);
        notifyItemInserted(position);
    }

    public void delete(int position) {
        data.remove(position);
        notifyItemRemoved(position);
    }

    @NonNull
    @Override
    public RVViewHolder<R> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RVViewHolder<R> vh = new RVViewHolder<R>(inflater.inflate(getItemLayout(), parent, false));
        if (itemClickListener != null) {
            vh.itemView.setOnClickListener((view) -> {
                itemClickListener.onClick(view, vh.getLayoutPosition(), vh.getItemViewType());
            });
        }
        for (int i = 0; i < viewClickListeners.size(); i++) {
            int key = viewClickListeners.keyAt(i);
            View view = vh.itemView.findViewById(key);
            if (view != null) {
                view.setOnClickListener((v) -> {
                    viewClickListeners.get(key).onClick(v, vh.getLayoutPosition(), vh.getItemViewType());
                });
            }
        }
        return vh;
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public abstract int getItemLayout();

    private void addViewClickListener(int viewId, RVIClickListener childListener) {
        viewClickListeners.put(viewId, childListener);
    }
}
