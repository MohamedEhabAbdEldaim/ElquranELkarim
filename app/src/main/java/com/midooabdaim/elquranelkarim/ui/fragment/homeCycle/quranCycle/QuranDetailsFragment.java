package com.midooabdaim.elquranelkarim.ui.fragment.homeCycle.quranCycle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.adapter.QuranAdapter;
import com.midooabdaim.elquranelkarim.adapter.QuranDetailsAdapter;
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
import static com.midooabdaim.elquranelkarim.helper.HelperMethod.dismissProgressDialog;
import static com.midooabdaim.elquranelkarim.helper.HelperMethod.showProgressDialog;


public class QuranDetailsFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_quran_and_details_rv_id)
    RecyclerView fragmentQuranAndDetailsRvId;
    public int index = 0;
    private LinearLayoutManager linearLayoutManager;
    private QuranDetailsAdapter quranDetailsAdapter;
    private List<surah> surahList = new ArrayList<>();

    public QuranDetailsFragment() {
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
        quranDetailsAdapter = new QuranDetailsAdapter(getActivity(), surahList);
        fragmentQuranAndDetailsRvId.setAdapter(quranDetailsAdapter);
        fragmentQuranAndDetailsRvId.scrollToPosition(index - 1);

    }

    private void getData() {
        try {
            JSONObject obj = new JSONObject(AssetJSONFile("QuranDetails.json", getActivity()));
            JSONArray surah = obj.getJSONArray("surah");
            for (int i = 0; i < surah.length(); i++) {
                JSONObject jsonObject = surah.getJSONObject(i);
                int num = jsonObject.getInt("index");
                String name = jsonObject.getString("name");
                JSONArray aya = jsonObject.getJSONArray("aya");
                String content = "";
                String basmallh = "";
                for (int a = 0; a < aya.length(); a++) {
                    JSONObject jsonAya = aya.getJSONObject(a);
                    if (a == 0 && i != 0) {
                        basmallh = jsonAya.getString("bismillah");
                    }
                    String ayaContent = jsonAya.getString("text");
                    int ayaNum = jsonAya.getInt("index");
                    content += ayaContent + " {" + ayaNum + "} ";
                }
                surahList.add(new surah(num, name, basmallh, content));
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
        super.BackPressed();
    }
}