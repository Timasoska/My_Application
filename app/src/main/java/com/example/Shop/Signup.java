package com.example.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Signup extends AppCompatActivity {

    ImageView image;
    EditText name, mail, password;
    Button signup;
    TextView login;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        init();

        mAuth = FirebaseAuth.getInstance(); // Инициализация Firebase аутентификации
    }

    void init(){

        image = findViewById(R.id.image);
        name = findViewById(R.id.name);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);
        signup = findViewById(R.id.signup);
        login = findViewById(R.id.login);

        // Обработчик нажатия на кнопку регистрации
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signup();

            }
        });

        // Обработчик нажатия на кнопку входа
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Signup.this,Login.class));

            }
        });

    }
    private void signup(){
        String names = name.getText().toString().trim();
        String mails = mail.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        if(names.isEmpty()){
            name.setError("Name is required!");
            name.requestFocus();
            return;
        }

        if(mails.isEmpty()){
            mail.setError("Mail is required!");
            mail.requestFocus();
            return;
        }

        if(passwords.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        if(passwords.length() <6){
            password.setError("Min Password length 6.");
            password.requestFocus();
            return;
        }
        // Создание нового пользователя с помощью Firebase
        mAuth.createUserWithEmailAndPassword(mails,passwords)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            // Успешная регистрация
                            User user = new User(names,mails);
                            // Сохранение данных пользователя в Firebase Realtime Database
                            FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()){
                                                Toast.makeText(Signup.this,"Successfully!", Toast.LENGTH_LONG).show();
                                                startActivity(new Intent(Signup.this,MainActivity.class));

                                            } else {
                                                Toast.makeText(Signup.this,"Failed!", Toast.LENGTH_LONG).show();

                                            }
                                        }

                                    });
                        }
                        else {
                            Toast.makeText(Signup.this,"Failed!", Toast.LENGTH_LONG).show();

                        }
                    }
                });



    }
}
