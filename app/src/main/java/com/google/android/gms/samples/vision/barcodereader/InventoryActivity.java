package com.google.android.gms.samples.vision.barcodereader;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
public class InventoryActivity extends Activity {
    public static final String EXTRA_DRINKNO = "drinkNo";
    public static final String EXTRA_TID = "tableNo";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_table);
        //Get the drink from the intent
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int tableNo = bundle.getInt(EXTRA_TID);
        int drinkNo = bundle.getInt(EXTRA_DRINKNO);
        System.out.println("Table Number :"+tableNo);
        //Create a cursor
        try {
            SQLiteOpenHelper starbuzzDatabaseHelper = new StarbuzzDatabaseHelper(this);
            SQLiteDatabase db = starbuzzDatabaseHelper.getReadableDatabase();
            String tableName = "FRUIT";
            if(tableNo == 0) {
                tableName = "FRUIT";
            }
            else if(tableNo == 1){
                tableName = "VEG";
            }
            else if(tableNo == 2){
                tableName = "GRAIN";
            }
            else if(tableNo == 3){
                tableName = "PROTEIN";
            }
            else if(tableNo == 4){
                tableName = "DAIRY";
            }
            Cursor cursor = db.query(tableName,
                   new String[]{"NAME", "DESCRIPTION", "IMAGE_RESOURCE_ID", "QUANTITY", "UPC"},
                   "_id = ?",
                   new String[]{Integer.toString(drinkNo)},
                   null, null, null);

            //Move to the first record in the Cursor
            if (cursor.moveToFirst()) {
                //Get the drink details from the cursor
                String nameText = cursor.getString(0);
                String descriptionText = cursor.getString(3);
                String upcText = cursor.getString(4);

                int photoId = cursor.getInt(2);
                //Populate the drink name
                TextView name = (TextView)findViewById(R.id.name);
                name.setText(nameText);
                //Populate the drink description
                TextView description = (TextView)findViewById(R.id.description);
                description.setText("Quantity:" + descriptionText);

                TextView upc = (TextView) findViewById(R.id.upc);
                upc.setText("UPC: "+ upcText);
                //Populate the drink image
                ImageView photo = (ImageView)findViewById(R.id.photo);
                photo.setImageResource(photoId);
                photo.setContentDescription(nameText);
            }
            cursor.close();
            db.close();
        } catch(SQLiteException e) {
            Toast toast = Toast.makeText(this, "Database unavailable", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
}