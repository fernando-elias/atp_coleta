package com.example.coletalimpa_araucaria.controller;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Farmacias.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "farmacias";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_BAIRRO = "bairro";
    private static final String COLUMN_FARMACIA = "farmacia";
    private static final String COLUMN_ENDERECO = "endereco";
    private static final String COLUMN_HORARIO = "horario";
    private static final String COLUMN_COLETA = "coleta";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_BAIRRO + " TEXT, " +
                COLUMN_FARMACIA + " TEXT, " +
                COLUMN_ENDERECO + " TEXT, " +
                COLUMN_HORARIO + " TEXT, " +
                COLUMN_COLETA + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String bairro, String farmacia, String endereco, String horario, String coleta) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_BAIRRO, bairro);
        contentValues.put(COLUMN_FARMACIA, farmacia);
        contentValues.put(COLUMN_ENDERECO, endereco);
        contentValues.put(COLUMN_HORARIO, horario);
        contentValues.put(COLUMN_COLETA, coleta);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }

    public Cursor getColeta(String bairro) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_COLETA + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_BAIRRO + " = ?";
        return db.rawQuery(query, new String[] {bairro});
    }

    public Cursor getFarmacia(String bairro) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_FARMACIA + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_BAIRRO + " = ?";
        return db.rawQuery(query, new String[] {bairro});
    }

    public Cursor getEndereco(String bairro) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_ENDERECO + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_BAIRRO + " = ?";
        return db.rawQuery(query, new String[] {bairro});
    }

    public Cursor getHorario(String bairro) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT " + COLUMN_HORARIO + " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_BAIRRO + " = ?";
        return db.rawQuery(query, new String[] {bairro});
    }
}
