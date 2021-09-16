package com.lm.rvadapter.demo;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.ConcatAdapter;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.lm.rvadapter.demo.databinding.ActivityMainBinding;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    private TextAdapter textAdapter;
    private ImageAdapter imageAdapter;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.rv.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        divider.setDrawable(new ColorDrawable(Color.parseColor("#0000FF")));
        binding.rv.addItemDecoration(divider);
        textAdapter = new TextAdapter();
        textAdapter.setData(WordGenerator.generate(20));
        textAdapter.setItemClickListener((view, position, viewType) -> {
            Toast.makeText(this, textAdapter.getData().get(position), Toast.LENGTH_LONG).show();
        });
        imageAdapter = new ImageAdapter();
        imageAdapter.addViewClickListener((view, position, viewType) -> {
            Toast.makeText(this, "click image", Toast.LENGTH_LONG).show();
        }, R.id.image);
        imageAdapter.setData(Collections.singletonList(R.drawable.img_moon));
        ConcatAdapter adapter = new ConcatAdapter();
        adapter.addAdapter(textAdapter);
        adapter.addAdapter(imageAdapter);
        binding.rv.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        int position;
        if (id == R.id.append) {
            position = textAdapter.getItemCount() - 1;
            binding.rv.scrollToPosition(position);
            textAdapter.append(WordGenerator.generate(5));
        } else if (id == R.id.prepend) {
            binding.rv.scrollToPosition(0);
            textAdapter.prepend(WordGenerator.generate(5));
        } else if (id == R.id.add) {
            position = textAdapter.getItemCount() - 1;
            binding.rv.scrollToPosition(position);
            imageAdapter.add(R.drawable.img_moon, 0);
        } else if (id == R.id.delete) {
            position = textAdapter.getItemCount() + imageAdapter.getItemCount() - 1;
            binding.rv.scrollToPosition(position);
            if (imageAdapter.getItemCount() > 0) {
                imageAdapter.delete(imageAdapter.getItemCount() - 1);
            }
        }
        return super.onOptionsItemSelected(item);
    }
}