package com.mjgl.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class CrearBaseDato extends AppCompatActivity implements View.OnClickListener{

    private Button btncrearbd;
    db_test database = new db_test(this);
    dto_productos datos = new dto_productos();
    //db_test database;

    int codigo = 100;
    String descripcion = "Cell LG W41 Pro";
    double precio = 18.99;
    int idcategoria = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crear_base_dato);
        btncrearbd = findViewById(R.id.btncrearbd);
        btncrearbd.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btncrearbd:
                Toast.makeText(this, "Base de datos creada.", Toast.LENGTH_SHORT).show();
                //database = new db_test(this);
                datos.setCodigo(codigo);
                datos.setDescripcion(descripcion);
                datos.setPrecio(precio);
                datos.setIdcategoria(idcategoria);
                if(database.register_producto(datos)){
                    Toast.makeText(this, "Registro guardado", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(this, "Error. No se ha podido registrar el producto.", Toast.LENGTH_SHORT).show();
                }
                break;

            default:

        }
    }
}