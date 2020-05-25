package com.example.studently;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class SubjectsAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private List<SubjectModel> posts;
    private Context context;

    SubjectsAdapter(Context context, int day) {
        this.context = context;
        posts = SubjectModel.getSubjectByDay(day);

        //sort list by time
        Collections.sort(posts, new SubjectModel.CustomComparator());
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

        //View layout = LayoutInflater.from(context).inflate(R.layout.fragment_world,null);
        if(convertView == null){
            //convertView = inflater.inflate(R.layout.grid_item, parent, false);
            //imageView = new ImageView(context);
            //imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            //imageView.setLayoutParams(new GridView.LayoutParams(340,350));
            inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.view_item,null);
        }

        CardView cardView = view.findViewById(R.id.cardview);
        TextView title = cardView.findViewById(R.id.title);
        TextView time = cardView.findViewById(R.id.time);
        String hour = Integer.toString(posts.get(position).hour);
        String min = Integer.toString(posts.get(position).min);
        String titleText = posts.get(position).subject;
        String timeText = hour+":"+min;

        cardView.setCardBackgroundColor(posts.get(position).color);
        title.setText(titleText);
        title.setTextSize(50);
        time.setText(timeText);
        time.setTextSize(50);





        return view;
    }


}
