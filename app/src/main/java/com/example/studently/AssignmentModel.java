package com.example.studently;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.From;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.Comparator;
import java.util.List;

@Table(name = "Assignments")
public class AssignmentModel extends Model {

    @Column(name = "title")
    String title;
    @Column(name = "year")
    int year;
    @Column(name = "month")
    int month;
    @Column(name = "day")
    int day;
    @Column(name = "hour")
    int hour;
    @Column(name = "min")
    int min;
    @Column(name = "color")
    int color;
    @Column(name = "details")
    String details;




    //get all assignments from database
    public static List<AssignmentModel> getAllAssignments(){
        return new Select().from(AssignmentModel.class).execute();
    }




    //compares two times with each other

    public static class CustomComparator implements Comparator<AssignmentModel> {
        @Override
        public int compare(AssignmentModel o1, AssignmentModel o2) {
            return getDate(o1).compareTo(getDate(o2));
        }
    }

    //get time in string form to compare
    public static String getTime(AssignmentModel am) {
        String hour = Integer.toString(am.hour);
        String min = Integer.toString(am.min);

        String timeText = hour+":"+min;

        return timeText;
    }

    public static String getDate(AssignmentModel am) {
        String year = Integer.toString(am.year);
        String month = Integer.toString(am.month);
        String day = Integer.toString(am.day);

        String dateText = day+"/"+month+"/"+year;

        return dateText;
    }

    public static AssignmentModel getObjectById(long id){
        List<AssignmentModel> assignments = getAllAssignments();
        AssignmentModel AM = null;
        int position = 0;
        boolean found = false;
        do{
            if(assignments.get(position).getId() == id){
                AM = assignments.get(position);
                found = true;
            }
            else{
                position = position + 1;
            }
        }while(found != true);

        return AM;
    }

    public static void updateRow(long id, int color, int day, String details, int hour, int min, int month, String title, int year){

        String updateSet = " color = ? ," + "day = ?,"+ " details = ?,"+ "hour = ?,"+ "min = ?," + "month = ?," +" title = ?,"+" year = ?";

        new Update(AssignmentModel.class).set(updateSet, color, day, details, hour, min, month, title, year)
                .where("Id = ?", id).execute();


    }


}