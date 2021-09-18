# RVAdapter
Simply defining adapters of RecyclerView in Android.



## Request feature

Databinding is available.

Set up databinding in build.gradle.

```
android {
	buildFeatures {
  	dataBinding true
  }
}
```



## Dependency

```
implementation 'io.github.wslaimin:RVAdapter:1.0.0'
```

## Usage

Classes extend RVAdapter.

```
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
```

Set item click listener.

```
textAdapter.setItemClickListener((view, position, viewType) -> {
		Toast.makeText(this, textAdapter.getData().get(position), Toast.LENGTH_LONG).show();
});
```

Add click listener to children of item view.

```
imageAdapter.addViewClickListener((view, position, viewType) -> {
		Toast.makeText(this, "click image", Toast.LENGTH_LONG).show();
}, R.id.image);
```

