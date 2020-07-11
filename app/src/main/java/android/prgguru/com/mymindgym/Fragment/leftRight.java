package android.prgguru.com.mymindgym.Fragment;


import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.prgguru.com.mymindgym.HomePageListener;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.prgguru.com.mymindgym.R;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

/**
 * A simple {@link Fragment} subclass.
 */
public class leftRight extends Fragment implements View.OnClickListener {
    ProgressBar mProgressBar;
    HomePageListener mListener;
    AlertDialog dialog;
    Button right, wrong;
    boolean  startThread=false;
    boolean matching = false;
    TextView mColorName;
    int points = 0;
    private Handler handler = new Handler();
    View mainView;
    int exceed;
    TextView myPoints;
    String colorName[] = {"RED", "YELLOW", "MAROON", "GRAY", "BLACK", "PURPLE", "BLUE", "GREEN", "PINK"};
    String colorCode[] = {"#FF0000", "#FFFF00", "#800000", "#808080", "#000000", "#800080", "#0000FF", "#008000", "#FF1493"};
    private int randomColor;
    private int randomText;


    public leftRight() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.fragment_left_right, container, false);
        mProgressBar = mainView.findViewById(R.id.circle_progress_bar);
        myPoints = mainView.findViewById(R.id.MyPoint);
        right = mainView.findViewById(R.id.right);
        wrong = mainView.findViewById(R.id.cross);
        mColorName = mainView.findViewById(R.id.colorName);
        right.setOnClickListener(this);
        wrong.setOnClickListener(this);
        setColor();
        startThread();
        return mainView;
    }

    private void startThread() {

        Log.d("VT", "startThread() called");
        new Thread(new Runnable() {
            int i = 0;
            int progressStatus = 0;

            @Override
            public void run() {
                Log.d("VT", "run() called " + progressStatus);
                while (progressStatus < 100) {

                    progressStatus += 5;
                    try {
                        Thread.sleep(100);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Log.d("VT", "sttaus " + progressStatus);
                            mProgressBar.setProgress(progressStatus);
                            myPoints.setText(String.valueOf(points));

                        }
                    });
                }
                Message msg = new Message();
                msg.obj = "pooja";
                responseHandler.sendMessage(msg);
            }
        }).start();
    }

    final Handler responseHandler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            setColor();
        }
    };

    private boolean checkmatchingornot() {
        Log.d("MyCOLOR", "random color " + randomColor + " random text " + randomText);
        if (randomColor == randomText) {
            points += 1;
            Toast.makeText(getActivity(), "you r genious", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (randomColor != randomText) {
            Toast.makeText(getActivity(), "wrong choice", Toast.LENGTH_SHORT).show();


        }
        return false;
    }

    private boolean notMatching() {
        Log.d("MyCOLOR", "random color " + randomColor + " random text " + randomText);
        if (randomColor != randomText) {
            points += 1;
            Toast.makeText(getActivity(), "you r genious", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (randomColor == randomText) {
            Toast.makeText(getActivity(), "wrong choice", Toast.LENGTH_SHORT).show();


        }
        return false;
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
        switch (v.getId()) {
            case R.id.right:
                matching = checkmatchingornot();

                setColor();
                startThread();


                break;
            case R.id.cross:
                matching = notMatching();

                setColor();
                startThread();


                break;
        }
    }

    public void setColor() {
        if (exceed <= 10) {
            Random r = new Random();
            randomColor = r.nextInt(colorCode.length);
            randomText = r.nextInt(colorName.length);
            mColorName.setText(colorName[randomText]);
            Log.d("MYCOLOR", "color " + colorCode[randomColor] + " number " + randomColor + " " + "text " + colorName[randomText] + " number " + randomText);
            mColorName.setTextColor(Color.parseColor(colorCode[randomColor]));
            startThread();
            exceed++;
        }
//        if (exceed > 10) {
//            timeExcced();
//
//        }


    }

    public void timeExcced() {
//        startRun=false;
        //customHandler.removeCallbacks(updateTimerThread);

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.time_exced, null);
        Button timeover = view.findViewById(R.id.limitexceed);
        timeover.setText("OK");


        timeover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setAdapter(adapter);

                //   myCountDownTimer.start();
                dialog.dismiss();
                getActivity().onBackPressed();

            }
        });
        builder.setView(view);
        dialog = builder.create();

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }
}


