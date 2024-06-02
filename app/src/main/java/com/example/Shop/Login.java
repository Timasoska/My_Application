package com.example.Shop;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
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
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {
    ImageView image;
    Button login;
    TextView signup;
    EditText mail,password;
    private FirebaseAuth mAuth; // Экземпляр FirebaseAuth для выполнения аутентификации
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login)      ;

        init(); // Инициализация элементов интерфейса
    }

    // Метод для инициализации элементов интерфейса и установки обработчиков событий
    void init(){
        login = findViewById(R.id.login);
        signup = findViewById(R.id.signup);
        image = findViewById(R.id.image);
        mail = findViewById(R.id.mail);
        password = findViewById(R.id.password);

        mAuth = FirebaseAuth.getInstance();


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this,Signup.class));

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

    }

    // Метод для выполнения входа
    private void login(){

        String mails = mail.getText().toString().trim();
        String passwords = password.getText().toString().trim();

        // Проверка, что поле электронной почты не пустое
        if(mails.isEmpty()){
            mail.setError("Mail is required!");
            mail.requestFocus();
            return;
        }

        // Проверка, что введенный адрес электронной почты имеет правильный формат
        if(!Patterns.EMAIL_ADDRESS.matcher(mails).matches()){
            mail.setError("Please enter a valid mail.");
            mail.requestFocus();
            return;
        }


        if(passwords.isEmpty()){
            password.setError("Password is required!");
            password.requestFocus();
            return;
        }

        // Выполнение входа с помощью FirebaseAuth
        mAuth.signInWithEmailAndPassword(mails,passwords).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); // Получение текущего пользователя

                // Если вход выполнен успешно, переход на главный экран
                if(task.isSuccessful()){
                    startActivity(new Intent(Login.this,MainActivity.class));
                }
                else {
                    Toast.makeText(Login.this,"Failed to login!", Toast.LENGTH_LONG).show();

                }
            }
        });

    }
}
