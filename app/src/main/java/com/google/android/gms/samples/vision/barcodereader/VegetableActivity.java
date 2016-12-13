package com.google.android.gms.samples.vision.barcodereader;

import android.app.ListActivity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

/**
 * Created by David on 12/12/2016.
 */

public class VegetableActivity extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listDrinks = getListView();
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();

            int flag = 2;
            if(flag == 0) {
                insertThing(db, "Green Pepper", "a pepper", R.drawable.pepper, 0, 0000010);
                insertThing(db, "Carrot", "a carrot",
                        R.drawable.carrot, 0, 0000020);
                insertThing(db, "Tomato", "a tomato", R.drawable.tomato, 0, 0000030);
                insertThing(db, "Add new item", "if desired item doesn't exit", R.drawable.common_google_signin_btn_icon_dark, 0, 0000040);
            }
            if(flag == 1){
                db.delete("VEG",null,null);
            }

            cursor = db.query("VEG",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);
            listDrinks.setAdapter(listAdapter);
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
        /*ListView listItems = getListView();
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            db = starbuzzDatabaseHelper.getReadableDatabase();
            cursor = db.query("FRUIT",
                    new String[]{"_id", "NAME"},
                    null, null, null, null, null);
            CursorAdapter listAdapter = new SimpleCursorAdapter(this,
                    android.R.layout.simple_list_item_1,
                    cursor,
                    new String[]{"NAME"},
                    new int[]{android.R.id.text1},
                    0);

            System.out.println(cursor.getColumnName(1));
            System.out.println(cursor.toString());
            listItems.setAdapter(listAdapter);
            Toast toast = Toast.makeText(this, "Stuff" ,Toast.LENGTH_SHORT);
            toast.show();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }*/
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        cursor.close();
        db.close();
    }
    @Override
    public void onListItemClick(ListView listView,
                                View itemView,
                                int position,
                                long id) {
        System.out.println(position);
        System.out.println(id);
        //System.out.println(listView.getLastVisiblePosition());
        if(position != listView.getLastVisiblePosition()) {
            Intent intent = new Intent(VegetableActivity.this, InventoryActivity.class);
            Bundle bundle = new Bundle();
            bundle.putInt(InventoryActivity.EXTRA_DRINKNO, (int) id);
            bundle.putInt(InventoryActivity.EXTRA_TID, 1);
            intent.putExtras(bundle);
            startActivity(intent);
        }
        else{
            final int tid = 1;
            Intent intent = new Intent(VegetableActivity.this, BarcodeSetup.class);
            Bundle bundle = new Bundle();
            bundle.putInt(InventoryActivity.EXTRA_DRINKNO, (int) id);
            bundle.putInt(InventoryActivity.EXTRA_TID, tid);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
    private static void insertThing(SQLiteDatabase db, String name,
                                    String description, int resourceId, int quantity, int upc) {
        ContentValues tableValues = new ContentValues();
        tableValues.put("NAME", name);
        tableValues.put("DESCRIPTION", description);
        tableValues.put("IMAGE_RESOURCE_ID", resourceId);
        tableValues.put("QUANTITY", quantity);
        tableValues.put("UPC", upc);
        db.insert("VEG", null, tableValues);
    }
}
