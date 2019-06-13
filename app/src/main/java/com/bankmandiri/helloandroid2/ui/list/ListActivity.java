package com.bankmandiri.helloandroid2.ui.list;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.bankmandiri.helloandroid2.R;
import com.bankmandiri.helloandroid2.adapter.OnItemClickListener;
import com.bankmandiri.helloandroid2.adapter.TodoAdapter;
import com.bankmandiri.helloandroid2.databinding.ActivityListBinding;
import com.bankmandiri.helloandroid2.dialog.AddDialog;
import com.bankmandiri.helloandroid2.dialog.AddDialogListener;
import com.bankmandiri.helloandroid2.model.Todo;
import com.bankmandiri.helloandroid2.ui.detail.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AddDialogListener, View.OnClickListener, OnItemClickListener {

    TodoAdapter adapter;
    ActivityListBinding binding;

    final int EDIT_REQUEST_CODE = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_list);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_list);
        setSupportActionBar(binding.toolbar);

        initList();

        /*add = id Button ADD. Jadi line ini intinya nge-binding button itu*/
        binding.add.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.menu_add:
                add();
                return true;
            case R.id.menu_setting:
                setting();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void add(){
        Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
        AddDialog dialog = new AddDialog(this);
        dialog.show();
    }

    private void setting(){
        Toast.makeText(this,"Setting", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAdd(String name){
        adapter.add(new Todo(name, true));
    }

    private void initList() {
        /*KALO MAU LIST ANKA DAN ALIYA DARI AWAL GAADA, DIHAPUS AJA*/
//        List<Todo> users = new ArrayList<>();
//        users.add(new Todo("anka", false));
//        users.add(new Todo("aliya", true));
//         adapter = new TodoAdapter(users);

        /*Line 87 dipake KALO line 82-84 dihapus, meaning listnya nanti dari awal kosong. TAPI HARUS masukkin line 87*/
        adapter = new TodoAdapter(new ArrayList<Todo>(), this);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        this.binding.mainRcvTodo.setLayoutManager(layoutManager);
        this.binding.mainRcvTodo.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.add) {
            adapter.add(new Todo(binding.todo.getText().toString(),binding.done.isChecked()));
            binding.todo.setText("");
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK && requestCode == EDIT_REQUEST_CODE) {
            Todo todo = data.getExtras().getParcelable("todo");
            //binding.setTodo(todo);
        }
    }

    @Override
    public void onItemClick(Todo item) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("todo" , item);

        /*saat startActivityResult dipake, ada method opsional yang harus kita override yaitu : onActivityResult*/
        startActivityForResult(intent, EDIT_REQUEST_CODE);
    }
}
