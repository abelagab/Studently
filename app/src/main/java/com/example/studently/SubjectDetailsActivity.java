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

public class SubjectDetailsActivity extends AppCompatActivity {



    public SubjectDetailsActivity(){

    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

       Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TextView subject = findViewById(R.id.subject);
        TextView time = findViewById(R.id.time);
        TextView classroom = findViewById(R.id.classroom);
        TableRow colorTR = findViewById(R.id.tableRow_color);
        TextView color = colorTR.findViewById(R.id.showColor);
        TextView details =findViewById(R.id.details);

        //get id from fragments
        Bundle b = getIntent().getExtras();
        long value = -1; // or other values
        if(b != null) {
            value = b.getLong("key");
        }

        SubjectModel SM = SubjectModel.getObjectById(value);

        subject.setText(SM.subject);
        subject.setTextSize(50);
        time.setText(SubjectModel.getTime(SM));
        time.setTextSize(50);
        classroom.setText(SM.classroom);
        classroom.setTextSize(50);
        color.setBackgroundColor(SM.color);
        details.setText(SM.details);
        subject.setTextSize(30);


        TableRow buttonsTR = findViewById(R.id.tableRow_buttons);

        final long id = value;

        //edit button
        Button editButton = buttonsTR.findViewById(R.id.edit_button);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SubjectDetailsActivity.this, EditSubjectActivity.class);

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
                new Delete().from(SubjectModel.class).where("id = ?",id).execute();
                startMain();
            }
        });




    }
    public void startMain(){
        startActivity(new Intent(getApplicationContext(), MainActivity.class));
    }

}
