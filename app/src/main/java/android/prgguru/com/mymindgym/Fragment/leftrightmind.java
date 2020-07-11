package android.prgguru.com.mymindgym.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.prgguru.com.mymindgym.HomePageListener;
import android.prgguru.com.mymindgym.R;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class leftrightmind extends Fragment implements View.OnClickListener {
    HomePageListener mListener;

    int i = -1;
    ProgressBar mProgressBar, mProgressBar1;
    private TextView textViewShowTime,myt;

    private Button buttonStartTime, buttonStopTime;
    private EditText edtTimerValue;

    private CountDownTimer countDownTimer;
    private long totalTimeCountInMilliseconds;
    View mainView;
    Button r, c;
    int randomColor;
    int randomText;
    Chronometer chronometer;
    String[] color = {"#000000", "#FF0000", "#800000", "#FFFF00", "#808000", "#00FF00", "#008000", "#00FFFF", "#0000FF", "#800080"};
    String[] text = {"BLACK", "RED", "MAROON", "YELLOW", "OLIVE", "LIME", "GREEN", "AQUA", "BLUE", "PURPLE"};

    public leftrightmind() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_leftrightmind, container, false);


        buttonStartTime = mainView.findViewById(R.id.button_timerview_start);
        buttonStopTime = mainView.findViewById(R.id.button_timerview_stop);
        r = mainView.findViewById(R.id.right);
        c = mainView.findViewById(R.id.cross);

        textViewShowTime = mainView.findViewById(R.id.textView_timerview_time);
        myt=mainView.findViewById(R.id.mytime);
        edtTimerValue = mainView.findViewById(R.id.textview_timerview_back);

        r.setOnClickListener(this);
        c.setOnClickListener(this);
        buttonStartTime.setOnClickListener(this);
        buttonStopTime.setOnClickListener(this);

        mProgressBar = mainView.findViewById(R.id.progressbar_timerview);
        mProgressBar1 = mainView.findViewById(R.id.progressbar1_timerview);
        chronometer = mainView.findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometerChanged) {
                chronometer = chronometerChanged;
                String currentTime = chronometer.getText().toString();
                if (currentTime.equals("02:00")) //put time according to you
                {
                    Log.d("TAG", "chronometer stop");
                    chronometer.stop();
                    timeExcced();
                }
            }
        });


        chooseRandom();
        callTimer();

        return mainView;
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

AlertDialog dialog;
    public void timeExcced() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.time_exced, null);
        Button timeover = view.findViewById(R.id.limitexceed);


        timeover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setAdapter(adapter);
                chronometer.setText("00:00");


                dialog.dismiss();

            }
        });
        builder.setView(view);
        dialog = builder.create();

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.button_timerview_start:


                setTimer();

                buttonStartTime.setVisibility(View.INVISIBLE);
                buttonStopTime.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.INVISIBLE);

                startTimer();
                mProgressBar1.setVisibility(View.VISIBLE);
                break;
            case R.id.button_timerview_stop:

                countDownTimer.cancel();
                countDownTimer.onFinish();
                mProgressBar1.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.VISIBLE);
                edtTimerValue.setVisibility(View.VISIBLE);
                buttonStartTime.setVisibility(View.VISIBLE);
                buttonStopTime.setVisibility(View.INVISIBLE);
                break;
            case R.id.right:
//                chooseRandom();
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

    private void startTimer() {
        countDownTimer = new CountDownTimer(totalTimeCountInMilliseconds, 1) {
            @Override
            public void onTick(long leftTimeInMilliseconds) {
                long seconds = leftTimeInMilliseconds / 1000;
                mProgressBar1.setProgress((int) (leftTimeInMilliseconds));

                myt.setText(String.format("%02d", seconds / 60)
                        + ":" + String.format("%02d", seconds % 60));
            }

            @Override
            public void onFinish() {
//                textViewShowTime.setText("00:00");
                textViewShowTime.setVisibility(View.VISIBLE);
//                buttonStartTime.setVisibility(View.VISIBLE);
//                buttonStopTime.setVisibility(View.VISIBLE);
                mProgressBar.setVisibility(View.VISIBLE);
                mProgressBar1.setVisibility(View.GONE);

            }
        }.start();
    }

    public void chooseRandom() {

        Random r = new Random();
        randomColor = r.nextInt(color.length);
        randomText = r.nextInt(text.length);
        textViewShowTime.setText(text[randomText]);
        Log.d("MYCOLOR", "color " + color[randomColor] + " number " + randomColor + " " + "text " + text[randomText] + " number " + randomText);
        textViewShowTime.setTextColor(Color.parseColor(color[randomColor]));
        callTimer();

    }
    public void callTimer()
    {
        setTimer();
        mProgressBar.setVisibility(View.INVISIBLE);

        startTimer();
        mProgressBar1.setVisibility(View.VISIBLE);
    }
}
