package com.example.Shop;


import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    Button b1,b2,b3,b4,add;
    ListView list_item;
    // Создание списка для хранения продуктов
    final ArrayList<HomeProductList> productLists = new ArrayList<>();
    // Аннотация, указывающая, что метод требует определенной версии API
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void init(){
        // Инициализация кнопок и установка для них прозрачного фона
        b1 = findViewById(R.id.b1);
        b1.setBackgroundTintList(null);

        b2 = findViewById(R.id.b2);
        b2.setBackgroundTintList(null);

        b3 = findViewById(R.id.b3);
        b3.setBackgroundTintList(null);

        b4 = findViewById(R.id.b4);
        b4.setBackgroundTintList(null);

        add = findViewById(R.id.add);
        add.setOnClickListener(view -> startActivity(new Intent(MainActivity.this,ProductActivity.class)));

        list_item = findViewById(R.id.list_item);

        productLists.add(new HomeProductList("Iphone 15","150000 RUB.",R.drawable.iphone15));
        productLists.add(new HomeProductList("MacBook pro","180000 RUB.",R.drawable.macbook));
        productLists.add(new HomeProductList("MacBook air","100000 RUB.",R.drawable.macbookair));
        productLists.add(new HomeProductList("Samsung S20","130000 RUB.",R.drawable.samsung));
        productLists.add(new HomeProductList("Samsung A25","25000 RUB.",R.drawable.samsunga));
        productLists.add(new HomeProductList("Samsung Note 10","120000 RUB.",R.drawable.samsungnote));
        productLists.add(new HomeProductList("Asus zenbook 15pro","130000 RUB.",R.drawable.asus));
        productLists.add(new HomeProductList("Asus vivobook 15","105000 RUB.",R.drawable.asusvivo));

        // Создание адаптера для отображения продуктов в списке и установка его для ListView
        HomeProductAdapter mailAdapter = new HomeProductAdapter(this, productLists);
        if (list_item != null) {
            list_item.setAdapter(mailAdapter);
        }
    }
}