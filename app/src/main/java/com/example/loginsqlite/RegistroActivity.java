package com.example.loginsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegistroActivity extends AppCompatActivity {

    EditText nombre, contrasenia, confirmarContrasenia;
    Button btnRegistrar, btnLogin;

    DBHelper dbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro_activity);

        nombre = findViewById(R.id.txtNombreUsuario);
        contrasenia = findViewById(R.id.txtContrasenia);
        confirmarContrasenia = findViewById(R.id.txtContraseniaConfirmar);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistrar = findViewById(R.id.btnRegistro);

        dbHelper = new DBHelper(this);

        btnLogin.setOnClickListener(view -> startActivity(new Intent(RegistroActivity.this, LoginActivity.class)));

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nom, pwd, rePwd;
                nom = nombre.getText().toString();
                pwd = contrasenia.getText().toString();
                rePwd = contrasenia.getText().toString();

                if(nom.equals("") || pwd.equals("") || rePwd.equals("")){
                    Toast.makeText(RegistroActivity.this, "Llena todos los campos", Toast.LENGTH_SHORT).show();
                }
                else{
                    if(pwd.equals(rePwd)){
                        if(!dbHelper.comprobarUsuario(nom)){
                            //registrar usuario en la base de datos
                            boolean res = dbHelper.insertarUsuario(nom, pwd);
                            if(res){
                                Toast.makeText(RegistroActivity.this, "Usuario registrado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(RegistroActivity.this, "Error", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else{
                            Toast.makeText(RegistroActivity.this, "Nombre de usuario no disponible.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                    }
                    else{
                        Toast.makeText(RegistroActivity.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}
