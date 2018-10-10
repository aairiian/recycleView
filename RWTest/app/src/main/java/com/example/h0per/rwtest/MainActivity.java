package com.example.h0per.rwtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.example.h0per.rwtest.list.MyAdapter;
import com.example.h0per.rwtest.list.OnItemSelectListener;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    //https://developer.android.com/guide/topics/ui/layout/recyclerview
    EditText input;
    Button button;
    RecyclerView recyclerView;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        input = findViewById(R.id.input);
        button = findViewById(R.id.add);
        recyclerView = findViewById(R.id.list);


        adapter = new MyAdapter(
                Arrays.asList(
                        "apple", "banana", "strawberry", "blueberry", "papaya", "mango", "chery",
                        "apple2", "banana2", "strawberry2", "blueberry2", "papaya2", "mango2", "chery2")
        );

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
        //Лайаут менеджер указывает как должны отображаться данные, базовые менеджеры - Linear, Grid и Staggered
//        recyclerView.addItemDecoration();  - декоратор, который указывает на сепараторы между элементами , к примеру белые полоски между элементами в гриде.
//        example: DividerItemDecoration

//        recyclerView.addOnScrollListener(); --> можем отслеживать движения вьюшки, скролл. К примеру догружать данные при скролле на последний элемент.

        adapter.setOnItemSelectListener(new OnItemSelectListener() {
            @Override
            public void onItemSelect(int position, String fruitName) {
                Log.e(MainActivity.class.getSimpleName(), "Item clicked: " + fruitName);
                adapter.remove(position);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.addElement(input.getText().toString());
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.registerAdapterDataObserver(observer);
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.unregisterAdapterDataObserver(observer);
    }

    RecyclerView.AdapterDataObserver observer = new RecyclerView.AdapterDataObserver() {
        @Override
        public void onChanged() {
            super.onChanged();
//           if(adapter.getItemCount() == 0) showEmptyView(); else hideEmptyView();
            /*
             * Обсервер для отслеживания изменений в данных в адаптере, можем использовать для показа или скрытия emptyView.
             * */
            Log.e(MainActivity.class.getSimpleName(), "Data changed!");
        }
    };


}
