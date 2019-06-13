package com.bankmandiri.helloandroid2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bankmandiri.helloandroid2.R;
import com.bankmandiri.helloandroid2.databinding.ItemTodoDoneBinding;
import com.bankmandiri.helloandroid2.model.Todo;

public class DoneViewHolder extends RecyclerView.ViewHolder {

    private ItemTodoDoneBinding binding;

    public DoneViewHolder(@NonNull ItemTodoDoneBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Todo todo) {
        binding.setTodo(todo);
    }
}