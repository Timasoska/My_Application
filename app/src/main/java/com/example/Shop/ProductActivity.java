package com.example.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Collections;

public class ProductActivity extends AppCompatActivity
{
    RecyclerView recview;
    AddProductAdapter adapter;
    FloatingActionButton fb;
    LinearLayout linear;
    TextView textadd;
    ImageView list;

    LinearLayoutManager lmAsc, lmDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        // Инициализация RecyclerView и других компонентов
        recview=findViewById(R.id.recview);
        lmAsc = new LinearLayoutManager(this);
        lmDesc = new LinearLayoutManager(this);
        lmDesc.setReverseLayout(true); // Установка реверсивного расположения для lmDesc
        lmDesc.setStackFromEnd(true);// Установка заполнения списка с конца для lmDesc
        recview.setLayoutManager(lmAsc); // Установка начального менеджера компоновки для RecyclerView

        list = findViewById(R.id.list);
        // Обработчик нажатия на изображение для изменения порядка сортировки
        list.setOnClickListener(view -> {
            LinearLayoutManager lm = (LinearLayoutManager) recview.getLayoutManager();
            assert lm != null;
            if (lm.getStackFromEnd()) {
                recview.setLayoutManager(lmAsc);
            }
            else {
                recview.setLayoutManager(lmDesc);
            }
        });
        linear = findViewById(R.id.linear);
        textadd = findViewById(R.id.textadd);
        // Настройка опций FirebaseRecyclerOptions для получения данных из Firebase
        FirebaseRecyclerOptions<Model> options =
                new FirebaseRecyclerOptions.Builder<Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Products").orderByChild("name"), Model.class)
                        .build();
        // Инициализация адаптера и установка его для RecyclerView
        adapter=new AddProductAdapter(options, textadd, linear);
        recview.setAdapter(adapter);


        fb=findViewById(R.id.fadd);
        fb.setOnClickListener(view -> {

            startActivity(new Intent(getApplicationContext(), AddProduct.class));



        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}