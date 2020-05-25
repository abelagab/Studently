package com.example.studently;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class MainActivityPageAdapter extends FragmentStateAdapter {


    public MainActivityPageAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return new MondayFragment();
            case 1:
                return new TuesFragment();
            case 2:
                return new WedFragment();
            case 3:
                return new ThuFragment();
            case 4:
                return new FridayFragment();

            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return 5;

    }




}
