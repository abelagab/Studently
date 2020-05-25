package com.example.studently;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;
import com.activeandroid.query.Select;
import com.activeandroid.query.Update;

import java.util.Comparator;
import java.util.List;

@Table(name = "Exams")
public class ExamModel extends Model {

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





    //get all assignments from database
    public static List<ExamModel> getAllExams(){
        return new Select().from(ExamModel.class).execute();
    }




    //compares two times with each other

    public static class CustomComparator implements Comparator<ExamModel> {
        @Override
        public int compare(ExamModel o1, ExamModel o2) {
            return getDate(o1).compareTo(getDate(o2));
        }
    }

    //get time in string form to compare
    public static String getTime(ExamModel em) {
        String hour = Integer.toString(em.hour);
        String min = Integer.toString(em.min);

        String timeText = hour+":"+min;

        return timeText;
    }

    public static String getDate(ExamModel em) {
        String year = Integer.toString(em.year);
        String month = Integer.toString(em.month);
        String day = Integer.toString(em.day);

        String dateText = day+"/"+month+"/"+year;

        return dateText;
    }

    public static ExamModel getObjectById(long id){
        List<ExamModel> exams = getAllExams();
        ExamModel EM = null;
        int position = 0;
        boolean found = false;
        do{
            if(exams.get(position).getId() == id){
                EM = exams.get(position);
                found = true;
            }
            else{
                position = position + 1;
            }
        }while(found != true);

        return EM;
    }

    public static void updateRow(long id, int color, int day, int hour, int min, int month, String title, int year){

        String updateSet = " color = ? ," + "day = ?,"+ "hour = ?,"+ "min = ?," + "month = ?," +" title = ?,"+" year = ?";

        new Update(ExamModel.class).set(updateSet, color, day, hour, min, month, title, year)
                .where("Id = ?", id).execute();


    }


}
