package com.example.googlemap1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class InfoPageFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.infohome_fragment, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager2 pager = view.findViewById(R.id.pager);
        FragmentInfoPageAdapter pageAdapter = new FragmentInfoPageAdapter(getActivity());
        pager.setAdapter(pageAdapter);

        TabLayout tabLayout = view.findViewById(R.id.tab_layout);
        TabLayoutMediator tabLayoutMediator= new TabLayoutMediator(tabLayout, pager, new TabLayoutMediator.TabConfigurationStrategy(){
            @Override
            public void onConfigureTab(TabLayout.Tab tab, int position) {
                tab.setText("Страница " + (position + 1));
                if(position == 2){
                    tab.setText("AQI");
                }
                if(position == 0){
                    tab.setText("Температура");
                }
                if(position == 1){
                    tab.setText("Влажность");
                }
                if(position == 3){
                    tab.setText("Давление");
                }
            }
        });
        tabLayoutMediator.attach();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
