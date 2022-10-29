package com.example.googlemap1;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentInfoPageAdapter extends FragmentStateAdapter {
    public FragmentInfoPageAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return(PageInfoFragment.newInstance(position));
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
