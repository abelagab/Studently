package com.example.studently;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TimePicker;

import com.activeandroid.ActiveAndroid;
import com.google.android.material.textfield.TextInputLayout;
import com.thebluealliance.spectrum.SpectrumPalette;

import java.util.Calendar;

public class AddAssignmentActivity extends AppCompatActivity {

    SpectrumPalette palette;
    int color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addassignment);

        ActiveAndroid.initialize(this);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextInputLayout title = findViewById(R.id.title);

        final DatePicker datePicker = findViewById(R.id.datePicker1);

        final TimePicker timePicker = findViewById(R.id.timePicker1);

        palette = findViewById(R.id.palette);
        //default color
        palette.setSelectedColor(getResources().getColor(R.color.white));
        color = getResources().getColor(R.color.white);

        //set color to color selected
        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                                               @Override
                                               public void onColorSelected(int colorSelected) {
                                                   color = colorSelected;
                                               }
                                           }
        );


        final EditText details = findViewById(R.id.details);


        TableRow buttonRow = findViewById(R.id.tableRow);

        Button cancelButton = buttonRow.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTools();
            }
        });

        Button addButton = buttonRow.findViewById(R.id.add_button);

        //get attributes values to save them in database after add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String titleText = title.getEditText().getText().toString();
                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                String detailsText = details.getText().toString();

                saveAssignment(titleText, year, month, day, hour, min, color, detailsText);

                startTools();

            }
        });



    }

    public void saveAssignment(String title, int year, int month, int day, int hour, int min, int color, String details){
        AssignmentModel newAM = new AssignmentModel();
        newAM.title = title;
        newAM.year = year;
        newAM.month = month;
        newAM.day = day;
        newAM.hour = hour;
        newAM.min = min;
        newAM.color = color;
        newAM.details = details;
        newAM.save();
        Log.d("EA", "All done!");
    }

    public void startTools(){
        startActivity(new Intent(AddAssignmentActivity.this,ToolsActivity.class));
    }
}
