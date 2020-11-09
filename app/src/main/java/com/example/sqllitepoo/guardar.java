package com.example.sqllitepoo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import entidades.ConexionSQLite;
import entidades.DTO;

public class guardar extends AppCompatActivity {
    private EditText etcodg,etdescg,etpzg;
    private Button btngg,btnbcodg,btndesg;

    ConexionSQLite conexion = new ConexionSQLite(this);
    DTO dato = new DTO();

    boolean inputETg = false;
    boolean inputEDg = false;
    boolean input1g = false;
    int resultInsert = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guardar);

        Toolbar toolbarg = findViewById(R.id.toolbarg);
        toolbarg.setNavigationIcon(getResources().getDrawable(R.drawable.bacl));
        toolbarg.setTitleTextColor(getResources().getColor(R.color.white));
        toolbarg.setTitleMargin(0,0,0,0);
        toolbarg.setSubtitle("CRUD SQLite");
        toolbarg.setSubtitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbarg);

        toolbarg.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);

        etcodg = (EditText) findViewById(R.id.et1g);
        etdescg = (EditText) findViewById(R.id.et2g);
        etpzg = (EditText) findViewById(R.id.et3g);
    }

    public void guardarg (View view) {
        if (etcodg.getText().toString().length() == 0) {
            etcodg.setError("Campo obligatorio");
            inputETg = false;
        } else {
            inputETg = true;
        }

        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        if (etdescg.getText().toString().length() == 0) {
            etdescg.setError("Campo obligatorio");
            inputEDg = false;
        } else {
            inputEDg = true;
        }

        //aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
        if (etpzg.getText().toString().length() == 0) {
            etpzg.setError("Campo obligatorio");
            input1g = false;
        } else {
            input1g = true;
        }

        if (inputETg && inputEDg && input1g){
            try {
                dato.setCodigo(Integer.parseInt(etcodg.getText().toString()));
                dato.setDescripcion(etdescg.getText().toString());
                dato.setPrecio(Double.parseDouble(etpzg.getText().toString()));

                if (conexion.InsertTradicional(dato)){
                    Toast.makeText(this,"Registro Agregado OwO",Toast.LENGTH_SHORT).show();
                    limpiardatg();
                }else {
                    Toast.makeText(this,"Ya existe el registro"+etcodg.getText().toString(),Toast.LENGTH_LONG).show();
                }
            }catch (Exception o){

                Toast.makeText(this,"Hubo un Error el algo",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public  void consultcodg (View view){
        if (etcodg.getText().toString().length()== 0){
            etcodg.setError("Campo obligatorio");
            inputETg = false;
        }else {
            inputETg = true;
        }

        if (inputETg){
            String codigo = etcodg.getText().toString();
            dato.setCodigo(Integer.parseInt(codigo));

            if (conexion.consultArt(dato)){
                etdescg.setText(dato.getDescripcion());
                etpzg.setText(""+dato.getPrecio());
            }else{

                Toast.makeText(this,"No existe el articulo ese",Toast.LENGTH_SHORT).show();
                limpiardatg();
            }
        }else{
            Toast.makeText(this,"Ingrese el articulo por favor",Toast.LENGTH_SHORT).show();

        }

    }

    public  void consuldescg (View view){
        if (etdescg.getText().toString().length()== 0){
            etdescg.setError("Campo obligatorio");
            inputEDg = false;
        }else {
            inputEDg = true;
        }

        if (inputEDg){
            String desc = etdescg.getText().toString();
            dato.setDescripcion(desc);
            if (conexion.cosultDesc(dato)){
                etcodg.setText(""+dato.getCodigo());
                etdescg.setText(dato.getDescripcion());
                etpzg.setText(""+dato.getPrecio());

            }else {
                Toast.makeText(this,"No existe tal articulo",Toast.LENGTH_SHORT).show();
                limpiardatg();

            }
        }else {
            Toast.makeText(this,"Ingrese el articulo por descripcion por favor",Toast.LENGTH_SHORT).show();
        }


    }

    public void limpiardatg(){

        etcodg.setText(null);
        etdescg.setText(null);
        etpzg.setText(null);
    }

    public void limpiardat2g(View view){

        etcodg.setText(null);
        etdescg.setText(null);
        etpzg.setText(null);
    }

}