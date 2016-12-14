package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by David on 12/11/2016.
 */

public class Inventory_Splash_NewItem extends Activity implements View.OnClickListener {

    public static final String EXTRA_UPC = "upc";

    private Button fruits;
    private Button vegetables;
    private Button grains;
    private Button protein;
    private Button dairy;
    private String itemName;
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_main_add);

        fruits = (Button) findViewById(R.id.fruits);
        vegetables = (Button) findViewById(R.id.vegetables);
        grains = (Button) findViewById(R.id.grains);
        protein = (Button) findViewById(R.id.protein);
        dairy = (Button) findViewById(R.id.dairy);

        findViewById(R.id.fruits).setOnClickListener(this);
        findViewById(R.id.vegetables).setOnClickListener(this);
        findViewById(R.id.grains).setOnClickListener(this);
        findViewById(R.id.protein).setOnClickListener(this);
        findViewById(R.id.dairy).setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.fruits){
            //do fruits stuff
            // Credit to Aaron on stackoverflow.com for the dialogue
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Item Name");
            final EditText in = new EditText(this);
            in.setInputType(InputType.TYPE_CLASS_TEXT);
            build.setView(in);
            build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemName = in.getText().toString();
                    callInsert("FRUIT");
                }
            });
            build.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            build.show();
        }
        if(view.getId() == R.id.vegetables){
            //do vegetable stuff
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Item Name");
            final EditText in = new EditText(this);
            in.setInputType(InputType.TYPE_CLASS_TEXT);
            build.setView(in);
            build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemName = in.getText().toString();
                    callInsert("VEG");
                }
            });
            build.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            build.show();
            Intent intent = new Intent(Inventory_Splash_NewItem.this, VegetableActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.grains){
            //do grains stuff
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Item Name");
            final EditText in = new EditText(this);
            in.setInputType(InputType.TYPE_CLASS_TEXT);
            build.setView(in);
            build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemName = in.getText().toString();
                    callInsert("GRAIN");
                }
            });
            build.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            build.show();
        }
        if(view.getId() == R.id.protein){
            //do protein stuff
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Item Name");
            final EditText in = new EditText(this);
            in.setInputType(InputType.TYPE_CLASS_TEXT);
            build.setView(in);
            build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemName = in.getText().toString();
                    callInsert("PROTEIN");
                }
            });
            build.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            build.show();
        }
        if(view.getId() == R.id.dairy){
            //do dairy stuff
            AlertDialog.Builder build = new AlertDialog.Builder(this);
            build.setTitle("Item Name");
            final EditText in = new EditText(this);
            in.setInputType(InputType.TYPE_CLASS_TEXT);
            build.setView(in);
            build.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    itemName = in.getText().toString();
                    callInsert("DAIRY");
                }
            });
            build.setNegativeButton("Cancel",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which){
                    dialog.cancel();
                }
            });
            build.show();
        }
    }

    private void callInsert(String s){
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            int flag = 0;
            if(flag == 0) {
                // replace the add new item entry with the new entry.
                db.delete(s,"NAME=?",new String[]{"NEW"});
                int upc = Integer.parseInt(getIntent().getExtras().getString(EXTRA_UPC));
                System.out.println("UPC Read: "+upc);
                insertThing(db, itemName, "Doesn't Matter", R.drawable.icon, 1, upc, s);
                insertThing(db, "NEW", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark, 0, 0000001, s);
            }
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        Intent intent = new Intent(Inventory_Splash_NewItem.this, MainActivity.class);
        startActivity(intent);
    }

    private static void insertThing(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc, String table) {
        ContentValues drinkValues = new ContentValues();
        drinkValues.put("NAME", name);
        drinkValues.put("DESCRIPTION", description);
        drinkValues.put("IMAGE_RESOURCE_ID", resourceId);
        drinkValues.put("QUANTITY", quantity);
        drinkValues.put("UPC", upc);
        db.insert(table, null, drinkValues);
    }
}