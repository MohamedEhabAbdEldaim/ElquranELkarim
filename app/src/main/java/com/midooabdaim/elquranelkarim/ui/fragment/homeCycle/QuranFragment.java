package com.midooabdaim.elquranelkarim.ui.fragment.homeCycle;

import android.annotation.SuppressLint;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.midooabdaim.elquranelkarim.R;
import com.midooabdaim.elquranelkarim.adapter.QuranAdapter;
import com.midooabdaim.elquranelkarim.data.model.surah;
import com.midooabdaim.elquranelkarim.helper.CustomDailog;
import com.midooabdaim.elquranelkarim.ui.fragment.BaseFragment;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.midooabdaim.elquranelkarim.helper.HelperMethod.AssetJSONFile;
import static com.midooabdaim.elquranelkarim.helper.HelperMethod.customToast;


public class QuranFragment extends BaseFragment {

    Unbinder unbinder;
    @BindView(R.id.fragment_quran_and_details_rv_id)
    RecyclerView fragmentQuranAndDetailsRvId;
    @BindView(R.id.fragment_quran_txt_surah_name)
    TextView fragmentQuranTxtSurahName;
    @BindView(R.id.fragment_quran_play)
    Button fragmentQuranPlay;
    @BindView(R.id.fragment_quran_current_time)
    TextView fragmentQuranCurrentTime;
    @BindView(R.id.fragment_quran_seek_bar)
    SeekBar fragmentQuranSeekBar;
    @BindView(R.id.fragment_quran_all_time)
    TextView fragmentQuranAllTime;
    @BindView(R.id.fragment_quran_ll_sound)
    LinearLayout fragmentQuranLlSound;
    private LinearLayoutManager linearLayoutManager;
    private QuranAdapter quranAdapter;
    private List<surah> surahList = new ArrayList<>();
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private CustomDailog dailog;

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
        mediaPlayer = new MediaPlayer();
        handler = new Handler();
        if (mediaPlayer.isPlaying()) {
            fragmentQuranPlay.setBackgroundResource(R.drawable.pause);
            updateSeekBar();
        }
        return view;
    }

    private void initRecyclerView() {
        linearLayoutManager = new LinearLayoutManager(getActivity());
        fragmentQuranAndDetailsRvId.setLayoutManager(linearLayoutManager);
        getData();
        quranAdapter = new QuranAdapter(getActivity(), surahList, QuranFragment.this);
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

    public void setMediaPlayer(int index, String surahName) {
        try {
            fragmentQuranLlSound.setVisibility(View.VISIBLE);
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            String url = "";
            if (index < 10) {
                url = "https://server10.mp3quran.net/ibrahim_dosri_hafs/Rewayat-Warsh-A-n-Nafi/00" + index + ".mp3";
            } else if (index < 100) {
                url = "https://server10.mp3quran.net/ibrahim_dosri_hafs/Rewayat-Warsh-A-n-Nafi/0" + index + ".mp3";

            } else {
                url = "https://server10.mp3quran.net/ibrahim_dosri_hafs/Rewayat-Warsh-A-n-Nafi/" + index + ".mp3";

            }

            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mp.start();
                    fragmentQuranPlay.setBackgroundResource(R.drawable.pause);
                    updateSeekBar();
                    fragmentQuranPlay.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mediaPlayer.isPlaying()) {
                                handler.removeCallbacks(updater);
                                mediaPlayer.pause();
                                fragmentQuranPlay.setBackgroundResource(R.drawable.play);
                            } else {
                                mediaPlayer.start();
                                fragmentQuranPlay.setBackgroundResource(R.drawable.pause);
                                updateSeekBar();
                            }
                        }
                    });
                    fragmentQuranSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                        @Override
                        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                            if (fromUser) {
                                mediaPlayer.seekTo(progress);
                                fragmentQuranCurrentTime.setText(getTime(mediaPlayer.getCurrentPosition()));
                            }
                        }

                        @Override
                        public void onStartTrackingTouch(SeekBar seekBar) {

                        }

                        @Override
                        public void onStopTrackingTouch(SeekBar seekBar) {

                        }
                    });
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            fragmentQuranPlay.setBackgroundResource(R.drawable.play);
                            fragmentQuranCurrentTime.setText(getString(R.string.zerotime));
                            fragmentQuranSeekBar.setProgress(0);
                        }
                    });
                }
            });
            fragmentQuranAllTime.setText(getTime(mediaPlayer.getDuration()));
            fragmentQuranSeekBar.setMax(mediaPlayer.getDuration());
            fragmentQuranTxtSurahName.setText(surahName);
        } catch (IOException e) {
            customToast(getActivity(), e.getMessage(), true);
        }

    }

    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            updateSeekBar();
            fragmentQuranCurrentTime.setText(getTime(mediaPlayer.getCurrentPosition()));
            fragmentQuranSeekBar.setProgress(mediaPlayer.getCurrentPosition());
        }
    };

    private void updateSeekBar() {
        if (mediaPlayer.isPlaying()) {
            handler.postDelayed(updater, 100);
        }
    }

    private String getTime(long milliseconds) {
        String time = "";
        String second = "";
        String minute = "";
        int hours = (int) (milliseconds / (1000 * 60 * 60));
        int minutes = (int) ((milliseconds % (1000 * 60 * 60)) / (1000 * 60));
        int seconds = (int) (((milliseconds % (1000 * 60 * 60)) % (1000 * 60)) / 1000);
        if (hours > 0) {
            time = hours + " : ";
        }
        if (minutes < 10) {
            minute = "0" + minutes;
        } else {
            minute = "" + minutes;
        }
        if (seconds < 10) {
            second = "0" + seconds;
        } else {
            second = "" + seconds;
        }
        time += minute + " : " + second;
        return time;
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mediaPlayer.isPlaying()) {
            Button.OnClickListener listeneryes = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dailog.dismiss();
                }
            };
            Button.OnClickListener listenerno = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    dailog.dismiss();
                }
            };
            dailog = new CustomDailog(getActivity(), listeneryes, listenerno);
            dailog.show();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mediaPlayer.isPlaying()) {
            fragmentQuranPlay.setBackgroundResource(R.drawable.pause);
            updateSeekBar();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mediaPlayer.isPlaying()) {
            fragmentQuranPlay.setBackgroundResource(R.drawable.pause);
            updateSeekBar();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mediaPlayer.isPlaying()) {
            Button.OnClickListener listeneryes = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dailog.dismiss();

                }
            };
            Button.OnClickListener listenerno = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    dailog.dismiss();

                }
            };
            dailog = new CustomDailog(getActivity(), listeneryes, listenerno);
            dailog.show();
        }
    }

    @Override
    public void BackPressed() {
        getActivity().finish();
    }
}