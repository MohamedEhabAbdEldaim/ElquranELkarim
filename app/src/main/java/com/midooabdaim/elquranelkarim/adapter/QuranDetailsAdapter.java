package com.midooabdaim.elquranelkarim.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.data.model.surah;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class QuranDetailsAdapter extends RecyclerView.Adapter<QuranDetailsAdapter.ViewHolder> {


    private Context context;
    private Activity activity;
    private List<surah> surahList = new ArrayList<>();

    public QuranDetailsAdapter(Context context, List<surah> surahList) {
        this.context = context;
        this.surahList = surahList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_quran_details,
                parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        setData(holder, position);
        setAction(holder, position);
    }

    private void setData(ViewHolder holder, int position) {
        holder.itemQuranDetailsTxtSurahName.setText(surahList.get(position).getName());
        holder.itemQuranDetailsTxtBesmallah.setText(surahList.get(position).getBesmallah());
        holder.itemQuranDetailsTxtContent.setText(surahList.get(position).getContent());
    }

    private void setAction(ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return surahList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View view;
        @BindView(R.id.item_quran_details_txt_surah_name)
        TextView itemQuranDetailsTxtSurahName;
        @BindView(R.id.item_quran_details_txt_besmallah)
        TextView itemQuranDetailsTxtBesmallah;
        @BindView(R.id.item_quran_details_txt_content)
        TextView itemQuranDetailsTxtContent;

        public ViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            ButterKnife.bind(this, view);
        }
    }
}
