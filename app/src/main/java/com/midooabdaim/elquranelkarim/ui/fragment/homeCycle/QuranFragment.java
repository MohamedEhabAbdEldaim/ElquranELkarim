package com.midooabdaim.elquranelkarim.ui.fragment.homeCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.adapter.QuranAdapter;
import com.midooabdaim.elquranelkarim.data.model.surah;
import com.midooabdaim.elquranelkarim.ui.fragment.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.midooabdaim.elquranelkarim.helper.HelperMethod.AssetJSONFile;


public class QuranFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_quran_and_details_rv_id)
    RecyclerView fragmentQuranAndDetailsRvId;
    private LinearLayoutManager linearLayoutManager;
    private QuranAdapter quranAdapter;
    private List<surah> surahList = new ArrayList<>();


    public QuranFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        intialFragment();
        View view = inflater.inflate(R.layout.fragment_quran, container, false);
        unbinder = ButterKnife.bind(this, view);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentQuranAndDetailsRvId.setLayoutManager(linearLayoutManager);
        getData();
        quranAdapter = new QuranAdapter(getActivity(), surahList);
        fragmentQuranAndDetailsRvId.setAdapter(quranAdapter);
    }

    private void getData() {
        try {
            JSONObject obj = new JSONObject(AssetJSONFile("Quran.json", getActivity()));
            JSONArray surah = obj.getJSONArray("surah");
            for (int i = 0; i < surah.length(); i++) {
                JSONObject jsonObject = surah.getJSONObject(i);
                int num = jsonObject.getInt("index");
                String name = jsonObject.getString("name");
                surahList.add(new surah(num, name));
            }
        } catch (Exception e) {
        }
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