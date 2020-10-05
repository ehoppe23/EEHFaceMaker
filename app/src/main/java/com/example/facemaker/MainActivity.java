/*
* FaceMaker Activity
* @author Emily Hoppe
*
* 9/8/20
* CS301
*
*/
package com.example.facemaker;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.SurfaceView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {

    String[] hairStyles = {"Bald", "Short", "Long"};
    private Spinner hairSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Hair styles added to spinner
        hairSpinner = findViewById(R.id.hairStyleSpinner);
        ArrayAdapter<String> hairAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                this.hairStyles);
        hairSpinner.setAdapter(hairAdapter);

        //Widgets and listeners set up
        Face faceView = findViewById(R.id.faceView1);
        FaceController faceContr = new FaceController(faceView);

        Button randomFaceB = findViewById(R.id.randomFaceButton);
        randomFaceB.setOnClickListener(faceContr);

        SeekBar redSB = findViewById(R.id.redSeekBar);
        redSB.setOnSeekBarChangeListener(faceContr);

        SeekBar greenSB = findViewById(R.id.greenSeekBar);
        greenSB.setOnSeekBarChangeListener(faceContr);

        SeekBar blueSB = findViewById(R.id.blueSeekBar);
        blueSB.setOnSeekBarChangeListener(faceContr);

        RadioGroup HESradioGroup = findViewById(R.id.HESRadioGroup);
        HESradioGroup.setOnCheckedChangeListener(faceContr);

        Spinner hairStyleSpinner = findViewById(R.id.hairStyleSpinner);
        hairStyleSpinner.setOnItemSelectedListener(faceContr);

        //Gives face access to widgets that require internal updates
        faceView.setWidgets(redSB,greenSB,blueSB,hairStyleSpinner);

    }
}

