package com.example.session5.Adapters;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.session5.Fragments.Camera_fragment;
import com.example.session5.Fragments.calls;
import com.example.session5.Fragments.chat;
import com.example.session5.Fragments.status;

public class pageAdapter extends FragmentPagerAdapter {
    private byte num_of_tabs;

    public pageAdapter(FragmentManager fm, byte num_of_tabs) {
        super(fm);
        this.num_of_tabs = num_of_tabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new Camera_fragment();
            case 1:

                return new chat();
            case 2:

                return new status();
            case 3:

                return new calls();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num_of_tabs;
    }
}
