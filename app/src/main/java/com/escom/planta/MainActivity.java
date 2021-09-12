package com.escom.planta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.escom.planta.adaptadores.ListaPlantasAdapter;
import com.escom.planta.db.DbPlantas;
import com.escom.planta.entidades.Plantas;

import java.util.ArrayList;

import escom.planta.R;

public class MainActivity extends AppCompatActivity {

    RecyclerView listaPlantas;
    ArrayList<Plantas> listaArrayPlantas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listaPlantas = findViewById(R.id.listaPlantas);
        listaPlantas.setLayoutManager(new LinearLayoutManager(this));

        DbPlantas dbPlantas = new DbPlantas(MainActivity.this);

        listaArrayPlantas = new ArrayList<>();

        ListaPlantasAdapter adapter = new ListaPlantasAdapter(dbPlantas.mostrarPlantas());
        listaPlantas.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_principal, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.menuNuevo:
                nuevoRegistro();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void nuevoRegistro(){
        Intent intent = new Intent(this, NuevoActivity.class);
        startActivity(intent);
    }
}