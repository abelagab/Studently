package com.example.studently;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TableRow;
import android.widget.TimePicker;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditExamActivity extends AppCompatActivity {

    SpectrumPalette palette;
    int color;

    String titleText;
    long id;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_exam);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final TextInputLayout title = findViewById(R.id.title);
        TextInputEditText titleHint = title.findViewById(R.id.title_hint);

        final DatePicker datePicker = findViewById(R.id.datePicker1);

        final TimePicker timePicker = findViewById(R.id.timePicker1);


        palette = findViewById(R.id.palette);


        TableRow buttonRow = findViewById(R.id.tableRow);

        //get id from Details Activity
        Bundle b = getIntent().getExtras();
        long value = -1; // or other values
        if(b != null) {
            value = b.getLong("key");
        }

        id = value;

        final ExamModel EM = ExamModel.getObjectById(value);

        titleHint.setHint(EM.title);


        timePicker.setIs24HourView(true);
        timePicker.setHour(EM.hour);
        timePicker.setMinute(EM.min);



        //default color
        palette.setSelectedColor(EM.color);
        color = EM.color;

        //set color to color selected
        palette.setOnColorSelectedListener(new SpectrumPalette.OnColorSelectedListener() {
                                               @Override
                                               public void onColorSelected(int colorSelected) {
                                                   color = colorSelected;
                                               }
                                           }
        );


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
                if(title.getEditText().getText().toString() == null){
                    titleText = EM.title;
                }else{
                    titleText = title.getEditText().getText().toString();
                }

                int year = datePicker.getYear();
                int month = datePicker.getMonth();
                int day = datePicker.getDayOfMonth();

                int hour = timePicker.getCurrentHour();
                int min = timePicker.getCurrentMinute();





                ExamModel.updateRow(id, color, day, hour, min, month, titleText, year);

                startTools();
            }
        });
    }

    public void startTools(){
        startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
    }
}
