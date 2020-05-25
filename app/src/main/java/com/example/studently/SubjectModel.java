package com.example.studently;

import android.content.Intent;
import android.database.Cursor;
import android.icu.lang.UScript;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.sql.Time;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

@Table(name = "Items")
public class SubjectModel extends Model {

    @Column(name = "subject")
    String subject;
    @Column(name = "hour")
    int hour;
    @Column(name = "min")
    int min;
    @Column(name = "classroom")
    String classroom;
    @Column(name = "color")
    int color;
    @Column(name = "details")
    String details;
    @Column(name = "day")
    int day;




    //get all subjects from database
    public static List<SubjectModel> getAllSubjects(){
        return new Select().from(SubjectModel.class).execute();
    }

    public static List<SubjectModel> getSubjectByDay(int day){
        From from = new Select().from(SubjectModel.class).where("day = ?", day);

        

        List<SubjectModel> subjects = from.execute();

        return subjects;
    }


    //compares two times with each other

    public static class CustomComparator implements Comparator<SubjectModel> {
        @Override
        public int compare(SubjectModel o1, SubjectModel o2) {
            return getTime(o2).compareTo(getTime(o1));
        }
    }

    //get time in string form to compare
    public static String getTime(SubjectModel sm) {
        SubjectModel SM = sm;
        String hour = Integer.toString(SM.hour);
        String min = Integer.toString(SM.min);

        String timeText = hour+":"+min;

        return timeText;
    }

    public static SubjectModel getObjectById(long id){
        List<SubjectModel> subjects = getAllSubjects();
        SubjectModel SM = null;
        int position = 0;
        boolean found = false;
        do{
            if(subjects.get(position).getId() == id){
                SM = subjects.get(position);
                found = true;
            }
            else{
                position = position + 1;
            }
        }while(found != true);

        return SM;
    }

    public static void updateRow(long id, String classroom, int color, int day, String details, int hour, int min, String subject){

        String updateSet = " classroom = ?, " + " color = ? ," +
                "day = ?,"+ " details = ?,"+ "hour = ?,"+ "min = ?," + "subject = ?";

        new Update(SubjectModel.class).set(updateSet, classroom, color, day, details, hour, min, subject)
                .where("Id = ?", id).execute();


    }


}
