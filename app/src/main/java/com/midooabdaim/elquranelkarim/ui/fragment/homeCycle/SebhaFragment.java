package com.midooabdaim.elquranelkarim.ui.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.ui.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class SebhaFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_sebha_txt_counter)
    TextView fragmentSebhaTxtCounter;

    public SebhaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intialFragment();
        View view = inflater.inflate(R.layout.fragment_sebha, container, false);
        unbinder = ButterKnife.bind(this, view);

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

    @OnClick({R.id.fragment_sebha_btn_clear, R.id.fragment_sebha_btn_counter})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.fragment_sebha_btn_clear:
                fragmentSebhaTxtCounter.setText(getString(R.string.zero));
                break;
            case R.id.fragment_sebha_btn_counter:
                int i = Integer.parseInt(fragmentSebhaTxtCounter.getText().toString().trim());
                i++;
                fragmentSebhaTxtCounter.setText(String.valueOf(i));
                break;
        }
    }
}