package com.example.Shop;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class HomeProductAdapter extends BaseAdapter {

    private LayoutInflater mInflater;
    private ArrayList<HomeProductList> mailArrayList; // Поле для хранения списка продуктов, которые будут отображаться в списке

    public HomeProductAdapter(Activity activity, ArrayList<HomeProductList> gmailArrayList) {

        this.mInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE); // Инициализация LayoutInflater с использованием контекста активности
        this.mailArrayList = gmailArrayList; // Инициализация списка продуктов
    }

    @Override
    public int getCount() {
        return mailArrayList.size();
    } // Метод для получения количества элементов в списке

    @Override
    public Object getItem(int position) {
        return mailArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // Метод для создания и заполнения представления элемента списка
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Заполнение представления с использованием макета product_item_layout
        convertView = mInflater.inflate(R.layout.product_item_layout, null);

        // Получение ссылок на текстовые поля и изображение в представлении
        TextView tvProductName =  convertView.findViewById(R.id.tvProductName);
        TextView tvProductPrice = convertView.findViewById(R.id.tvProductPrice);
        ImageView imageView = convertView.findViewById(R.id.ivProductImage);

        // Получение текущего продукта из списка по позиции
        HomeProductList mail = mailArrayList.get(position);

        // Установка текста и изображения для текущего продукта
        tvProductName.setText(mail.getName());
        tvProductPrice.setText(mail.getPrice());
        imageView.setImageResource(mail.getImage());

        // Возврат заполненного представления
        return convertView;


    }
}