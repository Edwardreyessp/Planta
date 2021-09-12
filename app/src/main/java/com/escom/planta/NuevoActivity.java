package com.escom.planta;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.escom.planta.db.DbPlantas;

import escom.planta.R;

public class NuevoActivity extends AppCompatActivity {

    EditText txtNombre, txtDiasRiego, txtTamano;
    Button btnGuarda;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        txtNombre = findViewById(R.id.txtNombre);
        txtDiasRiego = findViewById(R.id.txtDiasRiego);
        txtTamano = findViewById(R.id.txtTamano);
        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(!txtNombre.getText().toString().equals("") && !txtDiasRiego.getText().toString().equals("")) {

                    DbPlantas dbPlantas = new DbPlantas(NuevoActivity.this);
                    long id = dbPlantas.insertarPlanta(txtNombre.getText().toString(), txtDiasRiego.getText().toString(), txtTamano.getText().toString());

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void limpiar() {
        txtNombre.setText("");
        txtDiasRiego.setText("");
        txtTamano.setText("");
    }
}