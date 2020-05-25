package com.example.studently;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;



public class ExamFragment extends Fragment {

    public static long itemID;

    public ExamFragment() {
        // Required empty public constructor
    }


    public static ExamFragment newInstance(String param1, String param2) {
        ExamFragment fragment = new ExamFragment();
        ;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignment_manager, container, false);



        GridView gridView = (GridView) view.findViewById(R.id.gridView);
        ExamAdapter adapter = new ExamAdapter(getContext());
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), ExamDetailsActivity.class);
                ExamModel EM = (ExamModel) parent.getItemAtPosition(position);
                itemID = EM.getId();
                Bundle b = new Bundle();
                b.putLong("key", itemID); //Your id
                intent.putExtras(b); //Put your id to your next Intent
                startActivity(intent);
            }
        });

        //when the button is clicked
        FloatingActionButton add = view.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity((new Intent(getContext(), AddExamActivity.class)));

            }
        });
        // Inflate the layout for this fragment
        return view;
    }
}
