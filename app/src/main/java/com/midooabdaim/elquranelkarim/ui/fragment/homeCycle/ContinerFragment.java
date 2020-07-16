package com.midooabdaim.elquranelkarim.ui.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.adapter.ViewPagerWithFragmentAdapter;
import com.midooabdaim.elquranelkarim.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class ContinerFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_container_tab_layout_id)
    TabLayout fragmentContainerTabLayoutId;
    @BindView(R.id.fragment_container_view_pager)
    ViewPager fragmentContainerViewPager;

    private ViewPagerWithFragmentAdapter fragmentAdapter;

    public ContinerFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intialFragment();
        View view = inflater.inflate(R.layout.fragment_continer, container, false);
        unbinder = ButterKnife.bind(this, view);
        fragmentAdapter = new ViewPagerWithFragmentAdapter(getChildFragmentManager(), 0);
        fragmentAdapter.addPager(new QuranFragment(),getString(R.string.ElquranELkarim));
        fragmentAdapter.addPager(new SebhaFragment(),getString(R.string.Sebha));
        fragmentContainerViewPager.setAdapter(fragmentAdapter);
        fragmentContainerTabLayoutId.setupWithViewPager(fragmentContainerViewPager);

        return view;
    }

    @Override
    public void onStart() {
        intialFragment();
        super.onStart();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void BackPressed() {
        getActivity().finish();
    }
}