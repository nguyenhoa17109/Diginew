package com.nguyenhoa.diginew.news;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioAttributes;
import android.media.MediaPlayer;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.nguyenhoa.diginew.R;
import com.nguyenhoa.diginew.model.News;

import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AudioNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AudioNewsFragment extends Fragment {
    private News news;
    private TextView tvTitleNews, tvSource, tvTime;
    private ImageView ivPlayPause;
    private View thumbView;
    private String url;

    private MediaPlayer mediaPlayer;
    private SeekBar seekBar;
    private Handler handler = new Handler();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AudioNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AudioNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AudioNewsFragment newInstance(String param1, String param2) {
        AudioNewsFragment fragment = new AudioNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_audio_news, container, false);

        init(view);

        Bundle bundle = getArguments();
        news = (News) bundle.getSerializable("audio");

        tvTitleNews.setText(news.getTitle());
        tvSource.setText(news.getSource());
        tvTime.setText(news.getTimes()+" "+getResources().getString(R.string.time));

        url = news.getUrl(); // your URL here


        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioAttributes(
                new AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .setUsage(AudioAttributes.USAGE_MEDIA)
                        .build()
        );
        mediaPlayer.setLooping(true);

        prepareMediaPlayer();

        ivPlayPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    handler.removeCallbacks(updater);
                    mediaPlayer.pause();
                    ivPlayPause.setImageResource(R.drawable.ic_play_circle);
                }
                else{
                    mediaPlayer.start();
                    ivPlayPause.setImageResource(R.drawable.ic_pause_circle);
                    updateSeekBar();
                }
            }
        });


        seekBar.setOnTouchListener(new View.OnTouchListener() {
            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                SeekBar seekBar = (SeekBar) v;
                int playPostion = (mediaPlayer.getDuration() / 100) * seekBar.getProgress();
                mediaPlayer.seekTo(playPostion);

                seekBar.setThumb(getThumb(milliSecondsToTimer(mediaPlayer.getCurrentPosition()), milliSecondsToTimer(mediaPlayer.getDuration())));

                return false;
            }
        });

//        mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
//            @Override
//            public void onBufferingUpdate(MediaPlayer mp, int percent) {
//                seekBar.setSecondaryProgress(percent);
//            }
//        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                seekBar.setProgress(0);
                ivPlayPause.setImageResource(R.drawable.ic_play_circle);
                mediaPlayer.reset();
                prepareMediaPlayer();
            }
        });

        return view;
    }

    private void prepareMediaPlayer(){
        try {
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepare();
            seekBar.setThumb(getThumb("0:00", milliSecondsToTimer(mediaPlayer.getDuration())));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Runnable updater = new Runnable() {
        @Override
        public void run() {
            try {
                if(mediaPlayer.isLooping() || mediaPlayer.isPlaying()){
                    updateSeekBar();
                    long currentDuration = mediaPlayer.getCurrentPosition();
                    seekBar.setThumb(getThumb(milliSecondsToTimer(currentDuration), milliSecondsToTimer(mediaPlayer.getDuration())));
                }
                else{
                    mediaPlayer.stop();
                    mediaPlayer.release();
                }

            } catch (IllegalStateException e) {
                e.printStackTrace();
            }

        }
    };


    @Override
    public void onPause() {
        super.onPause();

        mediaPlayer.pause();
        ivPlayPause.setImageResource(R.drawable.ic_play_circle);

    }

    private void updateSeekBar(){
        if(mediaPlayer.isPlaying()){
            seekBar.setProgress((int) (((float) mediaPlayer.getCurrentPosition() / mediaPlayer.getDuration()) *100));
            handler.postDelayed(updater, 500);
        }
    }

    public String milliSecondsToTimer(long milliseconds){
        String finalTimerString = "";
        String secondsString = "";

        int hours = (int)( milliseconds / (1000*60*60));
        int minutes = (int)(milliseconds % (1000*60*60)) / (1000*60);
        int seconds = (int) ((milliseconds % (1000*60*60)) % (1000*60) / 1000);
        if(hours > 0){
            finalTimerString = hours + ":";
        }
        if(seconds < 10){
            secondsString = "0" + seconds;
        }else{
            secondsString = "" + seconds;}
        finalTimerString = finalTimerString + minutes + ":" + secondsString;

        return finalTimerString;
    }

    public Drawable getThumb(String t1, String t2) {
        ((TextView) thumbView.findViewById(R.id.tvProgress)).setText(t1 + "/"+ t2);

        thumbView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        thumbView.layout(0, 0, thumbView.getMeasuredWidth(), thumbView.getMeasuredHeight());
        thumbView.draw(canvas);

        return new BitmapDrawable(getResources(), bitmap);
    }

    public void init(View view){
        thumbView = LayoutInflater.from(getContext()).inflate(R.layout.seekbar_thumb, null, false);

        tvTitleNews = view.findViewById(R.id.tvAudioTitle);
        tvSource = view.findViewById(R.id.tvSource);
        tvTime = view.findViewById(R.id.tvTimes);

        ivPlayPause = view.findViewById(R.id.ivPlayPause);
        seekBar = view.findViewById(R.id.seekBar);
    }
}