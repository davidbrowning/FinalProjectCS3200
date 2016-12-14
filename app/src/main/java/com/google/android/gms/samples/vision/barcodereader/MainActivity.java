/*
 * Copyright (C) The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.vision.barcodereader;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

/**
 * Main activity demonstrating how to pass extra parameters to an activity that
 * reads barcodes.
 */
public class MainActivity extends Activity implements View.OnClickListener {

    // use a compound button so either checkbox or switch widgets work.
    private Button inventory;
    private Button newItem;
    private Button dinner;

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getApplicationContext().deleteDatabase("database");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.copy_of_activity_main);

        inventory = (Button) findViewById(R.id.inv);
        newItem = (Button) findViewById(R.id.newitem);
        dinner = (Button) findViewById(R.id.dinner);

        findViewById(R.id.inv).setOnClickListener(this);
        findViewById(R.id.newitem).setOnClickListener(this);
        findViewById(R.id.dinner).setOnClickListener(this);
    }

    /**
     * Called when a view has been clicked.
     *
     * @param v The view that was clicked.
     */
    @Override
    public void onClick(View v) {
        System.out.println("button clicked");
        if(v.getId() == R.id.newitem){
            Toast.makeText(getApplicationContext(),"new item clicked", Toast.LENGTH_SHORT).show();
            System.out.println("New Item");
            Intent intent = new Intent(this, BarcodeSetup.class);
            startActivity(intent);
        }
        if(v.getId() == R.id.inv){
            Toast.makeText(getApplicationContext(),"inventory clicked", Toast.LENGTH_SHORT).show();
            System.out.println("inventory");
            Intent intent = new Intent(this, Inventory_Splash.class);
            startActivity(intent);
        }

    }

    /**
     * Called when an activity you launched exits, giving you the requestCode
     * you started it with, the resultCode it returned, and any additional
     * data from it.  The <var>resultCode</var> will be
     * {@link #RESULT_CANCELED} if the activity explicitly returned that,
     * didn't return any result, or crashed during its operation.
     * <p/>
     * <p>You will receive this call immediately before onResume() when your
     * activity is re-starting.
     * <p/>
     *
     * @param requestCode The integer request code originally supplied to
     *                    startActivityForResult(), allowing you to identify who this
     *                    result came from.
     * @param resultCode  The integer result code returned by the child activity
     *                    through its setResult().
     * @param data        An Intent, which can return result data to the caller
     *                    (various data can be attached to Intent "extras").
     * @see #startActivityForResult
     * @see #createPendingResult
     * @see #setResult(int)
     */
    /*@Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    statusMessage.setText(R.string.barcode_success);
                    barcodeValue.setText(barcode.displayValue);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                } else {
                    statusMessage.setText(R.string.barcode_failure);
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
                statusMessage.setText(String.format(getString(R.string.barcode_error),
                        CommonStatusCodes.getStatusCodeString(resultCode)));
            }
        }
        else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }*/
}
