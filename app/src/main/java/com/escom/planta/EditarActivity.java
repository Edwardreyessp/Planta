package com.escom.planta;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.escom.planta.db.DbPlantas;
import com.escom.planta.entidades.Plantas;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import escom.planta.R;

public class EditarActivity extends AppCompatActivity {

    EditText txtNombre, txtRiego, txtTamano, txtImagen;
    Button btnGuarda;
    FloatingActionButton fabEditar, fabEliminar;
    boolean correcto = false;
    Plantas planta;
    int id = 0;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver);

        txtNombre = findViewById(R.id.txtNombre);
        txtRiego = findViewById(R.id.txtDiasRiego);
        txtTamano = findViewById(R.id.txtTamano);
        txtImagen = findViewById(R.id.txtImagen);
        btnGuarda = findViewById(R.id.btnGuarda);
        fabEditar = findViewById(R.id.fabEditar);
        fabEditar.setVisibility(View.INVISIBLE);
        fabEliminar = findViewById(R.id.fabEliminar);
        fabEliminar.setVisibility(View.INVISIBLE);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }

        final DbPlantas dbPlantas = new DbPlantas(EditarActivity.this);
        planta = dbPlantas.verPlanta(id);

        if (planta != null) {
            txtNombre.setText(planta.getNombre());
            txtRiego.setText(planta.getDias_riego());
            txtTamano.setText(planta.getTamano());
            txtImagen.setText(planta.getImagen());
        }

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtNombre.getText().toString().equals("") && !txtRiego.getText().toString().equals("")) {
                    correcto = dbPlantas.editarPlanta(id, txtNombre.getText().toString(), txtRiego.getText().toString(), txtTamano.getText().toString(), txtImagen.getText().toString());

                    if(correcto){
                        Toast.makeText(EditarActivity.this, "REGISTRO MODIFICADO", Toast.LENGTH_LONG).show();
                        verRegistro();
                    } else {
                        Toast.makeText(EditarActivity.this, "ERROR AL MODIFICAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(EditarActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void verRegistro(){
        Intent intent = new Intent(this, VerActivity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
    }
}