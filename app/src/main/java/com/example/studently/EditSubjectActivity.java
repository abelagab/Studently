package com.example.studently;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditSubjectActivity extends AppCompatActivity {

    SpectrumPalette palette;
    int color;
    String subjectText;
    String classroomText;
    String detailsText;
    long id;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextInputLayout subject = findViewById(R.id.subject);
        TextInputEditText subjectHint = subject.findViewById(R.id.subject_hint);

        final TimePicker timePicker = findViewById(R.id.timePicker1);

        final TextInputLayout classroom = findViewById(R.id.classroom);
        TextInputEditText classroomHint = classroom.findViewById(R.id.class_hint);

        palette = findViewById(R.id.palette);

        final EditText details = findViewById(R.id.details);

        TableRow buttonRow = findViewById(R.id.tableRow);

        //get id from Details Activity
        Bundle b = getIntent().getExtras();
        long value = -1; // or other values
        if(b != null) {
            value = b.getLong("key");
        }
        id = value;

        final SubjectModel SM = SubjectModel.getObjectById(value);

        subjectHint.setHint(SM.subject);

        timePicker.setIs24HourView(true);
        timePicker.setHour(SM.hour);
        timePicker.setMinute(SM.min);

        classroomHint.setHint(SM.classroom);


        //default color
        palette.setSelectedColor(SM.color);
        color = SM.color;

        //set color to color selected
        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                                               @Override
                                               public void onColorSelected(int colorSelected) {
                                                   color = colorSelected;
                                               }
                                           }
        );

        details.setHint(SM.details);

        Button cancelButton = buttonRow.findViewById(R.id.cancel_button);

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Button editButton = buttonRow.findViewById(R.id.save_button);



        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(subject.getEditText().getText().toString() == null){
                    subjectText = SM.subject;
                }else{
                    subjectText = subject.getEditText().getText().toString();
                }

                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();

                if(classroom.getEditText().getText().toString() == null){
                    classroomText = SM.classroom;
                }else{
                    classroomText = classroom.getEditText().getText().toString();
                }

                if(details.getText().toString() == null){
                    detailsText = SM.details;
                }else{
                    detailsText = details.getText().toString();
                }
                int day = SM.day;

                SubjectModel.updateRow(id, classroomText, color, day, detailsText, hour, min, subjectText);

                startMain();
            }
        });


    }
    public void startMain(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
