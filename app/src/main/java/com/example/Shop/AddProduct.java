package com.example.Shop;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class AddProduct extends AppCompatActivity
{
    EditText name,desc,price;
    Button submit,back;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addproduct);

        name=findViewById(R.id.add_name);
        desc=findViewById(R.id.add_desc);
        price=findViewById(R.id.add_price);

        back=findViewById(R.id.add_back);
        back.setOnClickListener(view -> {
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        });

        submit=findViewById(R.id.add_submit);
        submit.setOnClickListener(view -> {
            processinsert();
            Toast.makeText(this,"Successfully",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),ProductActivity.class));

        });
    }


    private void processinsert() {
        // Получение данных о продукте из EditText
        String productName = name.getText().toString();
        String productDesc = desc.getText().toString();
        String productPrice = price.getText().toString();

        // Создание объекта данных о продукте
        Map<String, Object> map = new HashMap<>();
        map.put("name", productName);
        map.put("desc", productDesc);
        map.put("price", productPrice);

        // Получение ссылки на базу данных Firebase и добавление данных о продукте
        FirebaseDatabase.getInstance().getReference().child("Products").push()
                .setValue(map)
                .addOnSuccessListener(aVoid -> {
                    // Очистка полей EditText после успешного добавления
                    name.setText("");
                    desc.setText("");
                    price.setText("");
                    Toast.makeText(getApplicationContext(), "Product added successfully", Toast.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> Toast.makeText(getApplicationContext(), "Failed to add product", Toast.LENGTH_LONG).show());
    }

}