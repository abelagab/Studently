package com.example.studently;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableRow;
import android.widget.TextView;

import com.activeandroid.query.Delete;

public class ExamDetailsActivity extends AppCompatActivity {

    long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam_details);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView title = findViewById(R.id.title);
        TextView date = findViewById(R.id.date);
        TextView time = findViewById(R.id.time);
        TableRow colorTR = findViewById(R.id.tableRow_color);
        TextView color = colorTR.findViewById(R.id.showColor);

        //get id from fragments
        Bundle b = getIntent().getExtras();
        long value = -1; // or other values
        if(b != null) {
            value = b.getLong("key");
        }

        id = value;

        ExamModel EM = ExamModel.getObjectById(value);

        title.setText(EM.title);
        title.setTextSize(50);
        date.setText(ExamModel.getDate(EM));
        date.setTextSize(50);
        time.setText(ExamModel.getTime(EM));
        time.setTextSize(50);
        color.setBackgroundColor(EM.color);



        TableRow buttonsTR = findViewById(R.id.tableRow_buttons);

        final long id = value;

        //edit button
        Button editButton = buttonsTR.findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamDetailsActivity.this, EditExamActivity.class);

                Bundle b = new Bundle();
                b.putLong("key", id); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        //back button
        Button backButton = buttonsTR.findViewById(R.id.back_button);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        //delete button
        Button delButton = buttonsTR.findViewById(R.id.delete_button);

        delButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Delete().from(ExamModel.class).where("id = ?",id).execute();
                startTools();
            }
        });
    }

    public void startTools(){
        startActivity(new Intent(getApplicationContext(), ToolsActivity.class));
    }
}
