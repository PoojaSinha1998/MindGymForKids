package android.prgguru.com.mymindgym.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.prgguru.com.mymindgym.HomePageListener;
import android.prgguru.com.mymindgym.R;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class LeftRightMindNew extends Fragment implements View.OnClickListener{
View mainView;

    ProgressBar mProgressBar, mProgressBar1;
    Button right,wrong;
    Chronometer chronometer;
    HomePageListener mListener;
    int randomColor;
    private long totalTimeCountInMilliseconds;
    int randomText;
    TextView textViewShowTime,myt;
    String colorName[]={"RED","YELLOW","MAROON","GRAY","BLACK","PURPLE","BLUE","GREEN","PINK"};
    String colorCode[]={"#FF0000","#FFFF00","#800000","#808080","#000000","#800080","#0000FF","#008000","#FF1493"};

    public LeftRightMindNew() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView= inflater.inflate(R.layout.fragment_left_right_mind_new, container, false);
        mProgressBar=mainView.findViewById(R.id.progressbar_timerview);
        mProgressBar1=mainView.findViewById(R.id.progressbar1_timerview);
        chronometer=mainView.findViewById(R.id.chronometer);
        textViewShowTime=mainView.findViewById(R.id.textView_timerview_text);
        right=mainView.findViewById(R.id.right);
        wrong=mainView.findViewById(R.id.cross);
        right.setOnClickListener(this);
        wrong.setOnClickListener(this);
        mProgressBar.setVisibility(View.VISIBLE);
        mProgressBar.setProgress(60);
//        chronometer.setOnChronometerTickListener(this)
//            @Override
//            public void onChronometerTick(Chronometer chronometerChanged) {
//                chronometer = chronometerChanged;
//                String currentTime = chronometer.getText().toString();
//                if (currentTime.equals("01:00")) //put time according to you
//                {
//                    Log.d("TAG", "chronometer stop");
//                    chronometer.stop();
////                    timeExcced();
//                }
//            }
//        });

        chooseRandom();
        callTimer();
        return  mainView;
    }
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof HomePageListener) {
            mListener = (HomePageListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement HomePageListener");
        }
        mListener.resetHomePage();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.right:

                chronometer.stop();
                checkmatchingornot();

                break;
            case R.id.cross:


                break;
        }
    }
    private void checkmatchingornot() {
        Log.d("MyCOLOR", "random color "+randomColor+" random text "+randomText );
        if(randomColor==randomText)
        {
            Toast.makeText(getActivity(),"you r genious",Toast.LENGTH_SHORT).show();
            chronometer.start();

        }
        if(randomColor!=randomText) {
            Toast.makeText(getActivity(), "wrong choice", Toast.LENGTH_SHORT).show();

        }
        chooseRandom();
    }
    public void chooseRandom() {

        Random r = new Random();
        randomColor = r.nextInt(colorCode.length);
        randomText = r.nextInt(colorName.length);
        textViewShowTime.setText(colorName[randomText]);
        Log.d("MYCOLOR", "color " + colorCode[randomColor] + " number " + randomColor + " " + "text " + colorName[randomText] + " number " + randomText);
        textViewShowTime.setTextColor(Color.parseColor(colorCode[randomColor]));
        callTimer();

    }
    public void callTimer()
    {
        setTimer();
        mProgressBar.setVisibility(View.INVISIBLE);

        startTimer();
        mProgressBar1.setVisibility(View.VISIBLE);
        mProgressBar1.setProgress(60);
    }

    private void startTimer() {
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer c) {
                String s= (String) chronometer.getText();
                long seconds = (Long.parseLong(s)) / 1000;
              //  mProgressBar1.setProgress((int) (seconds));

                myt.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));


            }
        });

    }

    private void setTimer() {
        int time = 2;
//        if (!edtTimerValue.getText().toString().equals("")) {
//            time = Integer.parseInt(edtTimerValue.getText().toString());
//        } else
//            Toast.makeText(getActivity(), "Please Enter Minutes...",
//                    Toast.LENGTH_LONG).show();
        totalTimeCountInMilliseconds = time * 1000;
        mProgressBar1.setMax(time * 1000);
    }


}
