package com.google.android.gms.samples.vision.barcodereader;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class StarbuzzDatabaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "databaseFinal"; // the name of our database
    private static final int DB_VERSION = 2; // the version of the database
    StarbuzzDatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE FRUIT (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER, "
                + "QUANTITY INTEGER, "
                + "UPC INTEGER);");
        db.execSQL("CREATE TABLE VEG (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER, "
                + "QUANTITY INTEGER, "
                + "UPC INTEGER);");
        db.execSQL("CREATE TABLE GRAIN (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER, "
                + "QUANTITY INTEGER, "
                + "UPC INTEGER);");
        db.execSQL("CREATE TABLE DAIRY (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER, "
                + "QUANTITY INTEGER, "
                + "UPC INTEGER);");
        db.execSQL("CREATE TABLE PROTEIN (_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "NAME TEXT, "
                + "DESCRIPTION TEXT, "
                + "IMAGE_RESOURCE_ID INTEGER, "
                + "QUANTITY INTEGER, "
                + "UPC INTEGER);");
        insertFruit(db, "Orange", "an orange", R.drawable.orange, 0,00000001);
        insertFruit(db, "Banana", "a banana",
                R.drawable.banana, 0, 00000002);
        insertFruit(db, "Apple", "an apple", R.drawable.apple, 0, 00000003);
        insertFruit(db, "Pear", "a pear", R.drawable.pear, 0, 00000004);
        insertFruit(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark,0,00000005);
        insertVeg(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark,0,00000005);
        insertGrain(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark,0,00000005);
        insertDairy(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark,0,00000005);
        insertProtein(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark,0,00000005);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
    private static void insertFruit(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC",upc);
        db.insert("FRUIT", null, tableValues);
    }
    private static void insertVeg(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC",upc);
        db.insert("VEG", null, tableValues);
    }
    private static void insertGrain(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC",upc);
        db.insert("GRAIN", null, tableValues);
    }
    private static void insertDairy(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC",upc);
        db.insert("DAIRY", null, tableValues);
    }
    private static void insertProtein(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC",upc);
        db.insert("PROTEIN", null, tableValues);
    }
}