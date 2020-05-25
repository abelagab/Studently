package com.example.studently;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.palette.graphics.Palette;

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

import java.sql.Time;
import java.util.Calendar;




public class AddSubject extends AppCompatActivity {

    public String subject;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    SpectrumPalette palette;
    int color;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_addsubject);

        ActiveAndroid.initialize(this);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextInputLayout subject = findViewById(R.id.subject);

        final TimePicker timePicker = findViewById(R.id.timePicker1);

        final TextInputLayout classroom = findViewById(R.id.classroom);

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
                startMain();
            }
        });

        Button addButton = buttonRow.findViewById(R.id.add_button);

        //get attributes values to save them in database after add button is clicked
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String subjectText = subject.getEditText().getText().toString();
                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();
                String classroomText = classroom.getEditText().getText().toString();
                String detailsText = details.getText().toString();
                int day = MainActivity.LastTabId;

                saveSubject(subjectText, hour, min, classroomText, color, detailsText, day);

                startMain();

            }
        });



    }


    // used to save subjects in the database
    public void saveSubject(String subject, int hour, int min, String classroom, int color, String details, int day){
        SubjectModel newSM = new SubjectModel();
        newSM.subject = subject;
        newSM.hour = hour;
        newSM.min = min;
        newSM.classroom = classroom;
        newSM.color = color;
        newSM.details = details;
        newSM.day = day;
        newSM.save();
        Log.d("EA", "All done!");
    }

    // used to start MainActivity
    public void startMain(){
        startActivity(new Intent(AddSubject.this,MainActivity.class));
    }




}
