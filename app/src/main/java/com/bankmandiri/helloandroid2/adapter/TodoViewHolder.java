package com.bankmandiri.helloandroid2.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bankmandiri.helloandroid2.R;
import com.bankmandiri.helloandroid2.databinding.ItemTodoBinding;
import com.bankmandiri.helloandroid2.model.Todo;

public class TodoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ItemTodoBinding binding;
    OnItemClickListener myListener;
    Todo todo;

    public TodoViewHolder(@NonNull ItemTodoBinding binding){
        super(binding.getRoot());
        this.binding = binding;
    }

    public void bind(Todo todo, OnItemClickListener myListener) {
        this.todo = todo;
        binding.setTodo(todo);
        this.myListener = myListener;
        binding.getRoot().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        myListener.onItemClick(todo);
    }
}