package com.example.studently;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.Collections;
import java.util.List;

public class AssignmentAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<AssignmentModel> posts;
    private Context context;

    AssignmentAdapter(Context context) {
        this.context = context;
        posts = AssignmentModel.getAllAssignments();

        //sort list by time
        Collections.sort(posts, new AssignmentModel.CustomComparator());
        inflater = LayoutInflater.from(this.context);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int position) {
        return posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(convertView == null){
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_item,null);
        }

        CardView cardView = view.findViewById(R.id.cardview);
        TextView title = cardView.findViewById(R.id.title);
        TextView time = cardView.findViewById(R.id.time);
        String date = AssignmentModel.getDate(posts.get(position));
        String time1 = AssignmentModel.getTime(posts.get(position));
        String titleText = posts.get(position).title;
        String timeText = date+" "+time1;

        cardView.setCardBackgroundColor(posts.get(position).color);
        title.setText(titleText);
        title.setTextSize(30);
        time.setText(timeText);
        time.setTextSize(30);





        return view;
    }
}
