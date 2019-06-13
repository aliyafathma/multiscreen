package com.bankmandiri.helloandroid2.adapter;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bankmandiri.helloandroid2.R;
import com.bankmandiri.helloandroid2.databinding.ItemTodoBinding;
import com.bankmandiri.helloandroid2.databinding.ItemTodoDoneBinding;
import com.bankmandiri.helloandroid2.model.Todo;

import java.util.ArrayList;
import java.util.List;


public class TodoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    private List<Todo> todos = new ArrayList<>();
    OnItemClickListener myListener;

    public  TodoAdapter(List<Todo> todos, OnItemClickListener myListener)
    {
        this.myListener = myListener;
        this.todos = todos;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        if (viewType == 1) {
            ItemTodoDoneBinding binding = DataBindingUtil.inflate(LayoutInflater
                            .from(viewGroup.getContext()), R.layout.item_todo_done, viewGroup,
                    false);
            return new DoneViewHolder(binding);
        } else {
            ItemTodoBinding binding = DataBindingUtil.inflate(LayoutInflater
                            .from(viewGroup.getContext()), R.layout.item_todo, viewGroup,
                    false);
            return new TodoViewHolder(binding);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewHolder.getItemViewType()) {
            case 1:
                DoneViewHolder doneViewHolder = (DoneViewHolder) viewHolder;
                doneViewHolder.bind(todos.get(position));
                break;
            default:
                TodoViewHolder todoViewHolder = (TodoViewHolder) viewHolder;
                todoViewHolder.bind(todos.get(position), myListener);
        }
    }

    @Override
    public int getItemCount() {
        return todos.size();
    }

    @Override
    public int getItemViewType(int position) {
        return todos.get(position).isDone() ? 1 : 0;
    }

    public void add(Todo todo) {
        todos.add(todo);
        notifyItemInserted(todos.size()-1);
    }
}
