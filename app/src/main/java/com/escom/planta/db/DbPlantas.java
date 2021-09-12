package com.escom.planta.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.escom.planta.entidades.Plantas;

import java.util.ArrayList;

public class DbPlantas extends DbHelper {

    Context context;

    public DbPlantas(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public long insertarPlanta(String nombre, String dias_riego, String tamano, String imagen) {

        long id = 0;

        try {
            DbHelper dbHelper = new DbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put("nombre", nombre);
            values.put("dias_riego", dias_riego);
            values.put("tamano", tamano);
            values.put("imagen", imagen);

            id = db.insert(TABLE_PLANTAS, null, values);
        } catch (Exception ex) {
            ex.toString();
        }

        return id;
    }

    public ArrayList<Plantas> mostrarPlantas() {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Plantas> listaPlantas = new ArrayList<>();
        Plantas planta;
        Cursor cursorPlantas;

        cursorPlantas = db.rawQuery("SELECT * FROM " + TABLE_PLANTAS, null);

        if (cursorPlantas.moveToFirst()) {
            do {
                planta = new Plantas();
                planta.setId(cursorPlantas.getInt(0));
                planta.setNombre(cursorPlantas.getString(1));
                planta.setDias_riego(cursorPlantas.getString(2));
                planta.setTamano(cursorPlantas.getString(3));
                listaPlantas.add(planta);
            } while (cursorPlantas.moveToNext());
        }

        cursorPlantas.close();

        return listaPlantas;
    }

    public Plantas verPlanta(int id) {

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Plantas planta = null;
        Cursor cursorPlantas;

        cursorPlantas = db.rawQuery("SELECT * FROM " + TABLE_PLANTAS + " WHERE id = " + id + " LIMIT 1", null);

        if (cursorPlantas.moveToFirst()) {
            planta = new Plantas();
            planta.setId(cursorPlantas.getInt(0));
            planta.setNombre(cursorPlantas.getString(1));
            planta.setDias_riego(cursorPlantas.getString(2));
            planta.setTamano(cursorPlantas.getString(3));
            planta.setImagen(cursorPlantas.getString(4));
        }

        cursorPlantas.close();

        return planta;
    }

    public boolean editarPlanta(int id, String nombre, String dias_riego, String tamano, String imagen) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("UPDATE " + TABLE_PLANTAS + " SET nombre = '" + nombre + "', dias_riego = '" + dias_riego + "', tamano = '" + tamano + "', imagen = '" + imagen + "' WHERE id='" + id + "' ");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }

    public boolean eliminarPlanta(int id) {

        boolean correcto = false;

        DbHelper dbHelper = new DbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        try {
            db.execSQL("DELETE FROM " + TABLE_PLANTAS + " WHERE id = '" + id + "'");
            correcto = true;
        } catch (Exception ex) {
            ex.toString();
            correcto = false;
        } finally {
            db.close();
        }

        return correcto;
    }
}
