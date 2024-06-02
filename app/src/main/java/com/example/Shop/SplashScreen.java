package com.example.Shop;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import org.jetbrains.annotations.Nullable;


public class SplashScreen extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        // Запуск метода для ожидания и перехода на следующую активность
        timeOut();

    }
    void timeOut(){ Thread timerThread = new Thread(){
        public void run(){
            try{

                synchronized(this){
                    wait(2000); // Ожидание 2 секунды
                }

            }catch(InterruptedException e){
                e.printStackTrace();
            }finally{
                // Переход на Login активность после ожидания
                Intent i=new Intent(getApplicationContext(), Login.class);
                startActivity(i);
                finish();
            }
        }
    };
        timerThread.start();
    }
}