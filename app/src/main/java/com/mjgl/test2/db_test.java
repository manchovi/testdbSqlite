package com.mjgl.test2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class db_test extends SQLiteOpenHelper {

    /*public db_test(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "db_test.db", null, 1);
    }*/

    public db_test(Context context){
        super(context, "dbtest.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        /*Definiendo la estrucutura de la base de datos*/
        db.execSQL("create table tb_categorias (idcategoria integer(11) not null primary key, nombrecategoria varchar(50) not null, estadocategoria integer(11) not null, fecharegistro datetime not null)");
        db.execSQL("create table tb_productos (codigo integer(11) not null primary key, descripcion varchar(100) not null, precio real not null, idcategoria integer(11) not null, FOREIGN KEY(idcategoria) REFERENCES tb_categorias(idcategoria))");

        //Almacenamiento de 3 categorias para efectos de prueba y ejemplo.
        db.execSQL("insert into tb_categorias values(1, 'Smartphone', 1, datetime('now','localtime')), (2, 'Tablets', 1, datetime('now','localtime')), (3, 'Computadora', 1, datetime('now','localtime')) ");

        //Insertando registro, 6 por vez.
        //db.execSQL("insert into tb_productos values(1, 'Samsung Galaxy S6+', 255.0, 1), (2, 'Galaxy Tab S7+', 300.10, 2), (3, 'Laptop Toshiba Satellite A135-s2276', 599, 3), (4, 'Samsung Galaxy S10+', 300.3, 1), (5, 'Galaxy Tab S12+', 500.0, 2), (6, 'Laptop HP Pavilion 13-bb0501la ', 1100.0, 3)");

        //Insertando registros de 3 en 3.
        db.execSQL("insert into tb_productos values(1, 'Samsung Galaxy S6+', 255.0, 1), (2, 'Galaxy Tab S7+', 300.10, 2), (3, 'Laptop Toshiba Satellite A135-s2276', 599, 3)");
        db.execSQL("insert into tb_productos values(4, 'Samsung Galaxy S10+', 300.3, 1), (5, 'Galaxy Tab S12+', 500.0, 2), (6, 'Laptop HP Pavilion 13-bb0501la ', 1100.0, 3)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists tb_productos");
        db.execSQL("drop table if exists tb_categorias ");
        onCreate(db);
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }


    public boolean register_producto(dto_productos datos){
        boolean estado = true;
        int resultado;

        //SQLiteDatabase bd = this.getWritableDatabase();
        //SQLiteDatabase bd = this.getReadableDatabase();

        try {
            int codigo = datos.getCodigo();
            String descripcion = datos.getDescripcion();
            double precio = datos.getPrecio();
            int id = datos.getIdcategoria();

            Cursor fila = this.getWritableDatabase().rawQuery("select codigo from tb_productos where codigo='"+codigo+"'", null);
            if(fila.moveToFirst()==true){
                estado = false;
            }else {
                //estado = (boolean)this.getWritableDatabase().insert("datos","nombre, correo, telefono",registro);
                //resultado = (int) this.getWritableDatabase().insert("usuarios", "nombres,apellidos,usuario,clave,pregunta,respuesta", registro);
                String SQL = "INSERT INTO tb_productos \n" +
                        "(codigo,descripcion,precio,idcategoria)\n" +
                        "VALUES \n" +
                        "('"+ String.valueOf(codigo) +"', '" + descripcion + "', '" + String.valueOf(precio) + "', '" + String.valueOf(id) +"');";

                this.getWritableDatabase().execSQL(SQL);
                this.getWritableDatabase().close();
                estado = true;
            }

        }catch (Exception e){
            estado = false;
            Log.e("error.",e.toString());
        }

        return estado;
    }


    public boolean register_categoria(dto_categorias datos){
        boolean estado = true;
        int resultado;

        //SQLiteDatabase bd = this.getWritableDatabase();
        //SQLiteDatabase bd = this.getReadableDatabase();

        try {
            int id = datos.getIdcategoria();
            String nombre = datos.getNombrecategoria();
            int estadocat = datos.getEstadocategoria();
            String fecha = datos.getFecharegistro();

            Cursor fila = this.getWritableDatabase().rawQuery("select idcategoria from tb_categorias where idcategoria='"+id+"'", null);
            if(fila.moveToFirst()==true){
                estado = false;
            }else {
                //estado = (boolean)this.getWritableDatabase().insert("datos","nombre, correo, telefono",registro);
                //resultado = (int) this.getWritableDatabase().insert("usuarios", "nombres,apellidos,usuario,clave,pregunta,respuesta", registro);
                String SQL = "INSERT INTO tb_categorias \n" +
                        "(idcategoria,nombrecategoria,estadocategoria, fecharegistro)\n" +
                        "VALUES \n" +
                        "('"+ String.valueOf(id) +"', '" + nombre + "', '" + String.valueOf(estadocat) + "', '" + fecha + "');";

                this.getWritableDatabase().execSQL(SQL);
                this.getWritableDatabase().close();
                estado = true;
            }

        }catch (Exception e){
            estado = false;
            Log.e("error.",e.toString());
        }

        return estado;
    }



}
