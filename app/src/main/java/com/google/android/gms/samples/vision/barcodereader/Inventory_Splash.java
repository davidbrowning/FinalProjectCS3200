package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.sql.BatchUpdateException;

/**
 * Created by David on 12/11/2016.
 */

public class Inventory_Splash extends Activity implements View.OnClickListener {

    private Button fruits;
    private Button vegetables;
    private Button grains;
    private Button protein;
    private Button dairy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventory_main);

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
            Intent intent = new Intent(Inventory_Splash.this, FruitActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.vegetables){
            //do vegetable stuff
            Intent intent = new Intent(Inventory_Splash.this, VegetableActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.grains){
            //do grains stuff
            Intent intent = new Intent(Inventory_Splash.this, GrainActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.protein){
            //do protein stuff
            Intent intent = new Intent(Inventory_Splash.this, ProteinActivity.class);
            startActivity(intent);
        }
        if(view.getId() == R.id.dairy){
            //do dairy stuff
            Intent intent = new Intent(Inventory_Splash.this, DairyActivity.class);
            startActivity(intent);
        }
    }
}