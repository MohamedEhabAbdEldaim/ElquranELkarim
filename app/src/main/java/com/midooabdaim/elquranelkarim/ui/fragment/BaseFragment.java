package com.midooabdaim.elquranelkarim.ui.fragment;

import androidx.fragment.app.Fragment;

import com.midooabdaim.elquranelkarim.ui.activity.BaseActivity;


public class BaseFragment extends Fragment {
    public BaseActivity baseActivity;

    public void BackPressed() {
        baseActivity.superOnBackPressed();
    }

    public void intialFragment() {
        baseActivity = (BaseActivity) getActivity();
        baseActivity.baseFragment = this;

    }




}
