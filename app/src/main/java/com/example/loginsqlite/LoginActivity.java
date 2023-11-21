package com.example.loginsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText nombre, contrasenia;
    Button btnLogin, btnRegistro;

    DBHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        nombre = findViewById(R.id.txtUsuario);
        contrasenia = findViewById(R.id.txtContrasenia);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistro = findViewById(R.id.btnRegistro);

        btnRegistro.setOnClickListener(view -> startActivity(new Intent(LoginActivity.this, RegistroActivity.class)));

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean res = dbHelper.usuarioExiste(nombre.getText().toString(), contrasenia.getText().toString());
                if(res){
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(LoginActivity.this, "El usuario no y la contraseña no coinciden.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}