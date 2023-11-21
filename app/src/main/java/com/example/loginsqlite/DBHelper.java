package com.example.loginsqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final String nombreDB = "registro.db";

    public DBHelper(@Nullable Context context) {
        super(context, nombreDB, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table usuarios(nombre TEXT primary key, contrasenia TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists usuarios");
    }

    public boolean insertarUsuario(String nombre, String contrasenia){
        SQLiteDatabase miDB = this.getWritableDatabase();
        ContentValues valoresContenido = new ContentValues();
        valoresContenido.put("nombre", nombre);
        valoresContenido.put("contrasenia", contrasenia);
        long resultado = miDB.insert("usuarios", null, valoresContenido);
        if(resultado == -1) return false;
        else return true;
    }

    //comprobar si el usuario ya existe en la base de datos
    public boolean comprobarUsuario(String usuario){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usuarios where nombre = ?", new String[]{usuario});
        if(cursor.getCount() > 0) return true;
        else return false;
    }

    public boolean usuarioExiste(String usuario, String contrasenia){
        SQLiteDatabase myDB = this.getWritableDatabase();
        Cursor cursor = myDB.rawQuery("select * from usuarios where nombre = ? and contrasenia = ?", new String[]{usuario, contrasenia});
        if(cursor.getCount() > 0) return true;
        else return false;
    }
}
