package android.prgguru.com.mymindgym.Fragment;


import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.prgguru.com.mymindgym.HomePageListener;
import android.prgguru.com.mymindgym.R;
import android.prgguru.com.mymindgym.adapter.MyRecyclerViewAdapter;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumberFragmnet extends Fragment implements MyRecyclerViewAdapter.ItemClickListener {

    private HomePageListener mListener;
    MyRecyclerViewAdapter adapter;
    private Chronometer chronometer;

    TextView Number, tm;
    int a = 1;
    String[] parts;
    MediaPlayer f;
    int item;
    int match = 1;
    AlertDialog dialog;
    String time;
    String[] data = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y"};
    private int seconds = 0;
    private boolean startRun;
    TextView timeView;
    MediaPlayer rs, ws;
    RecyclerView recyclerView;
    int start = 1;
    int end = 25;
    TextView level1, level2, level3, level4;
    boolean l1, l2, l3;
    ProgressBar progressBar;
    boolean ishard = false;
    MyCountDownTimer myCountDownTimer;
    Switch switchOne;
    Bundle bundle;
    boolean sound = true;

    public NumberFragmnet() {
        // Required empty public constructor
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.sound);
        MenuItem item1 = menu.findItem(R.id.rateus);
        MenuItem item2 = menu.findItem(R.id.some);
        MenuItem item3 = menu.findItem(R.id.some1);
        item.setVisible(false);
        item1.setVisible(false);
        item2.setVisible(false);
        item3.setVisible(false);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // TODO your code to hide item here
//        MenuItem item = (MenuItem) menu.findItem(R.id.sound);
//        item.setActionView(R.layout.soundonoff);
//        ToggleButton switchAB = item
//                .getActionView().findViewById(R.id.togglesound);
//        switchAB.setChecked(true);
//
//        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView,
//                                         boolean isChecked) {
//                if (isChecked) {
//                    Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT)
//                            .show();
//                    sound = true;
//                } else {
//                    Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT)
//                            .show();
//                    sound = false;
//                }
//            }
//        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_number_fragmnet, container, false);
        ToggleButton switchAB = v.findViewById(R.id.togglesound);
        switchAB.setChecked(true);

        switchAB.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(getActivity(), "ON", Toast.LENGTH_SHORT)
                            .show();
                    sound = true;
                } else {
                    Toast.makeText(getActivity(), "OFF", Toast.LENGTH_SHORT)
                            .show();
                    sound = false;
                }
            }
        });
        switchOne = (Switch) v.findViewById(R.id.switch_one);

        switchOne.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
//                            Toast.makeText(getActivity(),
//                                    "Switch hard", Toast.LENGTH_SHORT).show();
                            ishard = true;
                        } else {
//                            Toast.makeText(getActivity(),
//                                    "Switch easy", Toast.LENGTH_SHORT).show();
                            ishard = false;

                        }
                    }
                });

        level1 = v.findViewById(R.id.l1);
        level2 = v.findViewById(R.id.l2);
        level3 = v.findViewById(R.id.l3);
        level4 = v.findViewById(R.id.l4);
        rs = MediaPlayer.create(getActivity(), R.raw.click);
        ws = MediaPlayer.create(getActivity(), R.raw.wrongsong);
        Number = (TextView) v.findViewById(R.id.number);
        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        final int twoMin = 2 * 60 * 1000; // 2 minutes in milli seconds
        progressBar.setProgress(120);
        myCountDownTimer = new MyCountDownTimer(twoMin, 1000);


        chronometer = v.findViewById(R.id.chronometer);
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


        // set up the RecyclerView
        recyclerView = (RecyclerView) v.findViewById(R.id.rvNumbers);

        int numberOfColumns = 5;
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), numberOfColumns));
        adapter = new MyRecyclerViewAdapter(getActivity(), data, start, end);
        adapter.setClickListener(this);
        recyclerView.hasFixedSize();
        recyclerView.setAdapter(adapter);
        level1.setBackgroundResource(R.drawable.buttonbgselected);

        return v;
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
    public void onItemClick(View view, int position) {
        Log.d("TAG", "value of ishard " + ishard);

        if (ishard) {

            adapter.sethard(true);
            Log.i("TAG", "You clicked number " + adapter.getItem(position) + ", which is at cell position " + position);
            item = Integer.parseInt(adapter.getItem(position));
//            adapter = new MyRecyclerViewAdapter(getActivity(), data, start, end);
//            recyclerView.setAdapter(adapter);
//          //  adapter.notifyDataSetChanged();


        } else if (!ishard) {
            adapter.sethard(false);
            Log.i("TAG", "You clicked number in else part" + adapter.getItem(position) + ", which is at cell position " + position);
            item = Integer.parseInt(adapter.getItem(position));
        }
        boolean f = increseNumber(item);
//        Toast.makeText(getApplicationContext(),adapter.getItem(position),Toast.LENGTH_LONG).show();
        if (f) {
            if (match == 25) {

                myCountDownTimer.cancel();
                showElapsedTime();
            } else {
                if (sound) {
                    rs.start();
                }
                match++;
                if (ishard) {
                    //  refreshAdapter();
                }
                Number.setText(String.valueOf(match));
                if (match == 2) {
                    switchOne.setEnabled(false);
                    myCountDownTimer.start();

                    chronometer.setBase(SystemClock.elapsedRealtime());
                    chronometer.start();

                }
            }
        } else {
            if (sound) {
                ws.start();
            }
            Toast.makeText(getActivity(), "Press: " + match, Toast.LENGTH_LONG).show();
        }
    }

    public boolean increseNumber(int m) {

        Log.d("TAG", "value of match " + match);
        if (match == m) {
            Log.d("TAG", "value of m " + m);
            return true;
        }
        return false;


    }

    private void showElapsedTime() {

        Log.d("TAG", "Before getting value from chronometer");
        time = chronometer.getText().toString();
        Log.d("TAG", "after getting value from chronometer " + time);

        chronometer.stop();
        /*********************************************************make fragment */
        parts = time.split(":");
        Log.d("TAG", " Minute " + parts[0] + " Second " + parts[1]);

        callAlert();
        //    int time=getSecondsFromDurationString(String.valueOf(elapsedMillis));


//        Toast.makeText(getApplicationContext(), "Elapsed milliseconds: " + time,
//                Toast.LENGTH_SHORT).show();
    }


    public void timeExcced() {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getLayoutInflater().inflate(R.layout.time_exced, null);
        Button timeover = view.findViewById(R.id.limitexceed);


        timeover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                recyclerView.setAdapter(adapter);
                chronometer.setText("00:00");

                adapter.shufflemydata();
                adapter.notifyDataSetChanged();
                switchOne.setEnabled(true);
                switchOne.setChecked(false);
                ishard = false;
                match = 1;
                progressBar.setProgress(120);
//                myCountDownTimer.start();
                Number.setText("1");
                dialog.dismiss();

            }
        });
        builder.setView(view);
        dialog = builder.create();

        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    public void callAlert() {
        Log.d("db", "logout");
        f = MediaPlayer.create(getActivity(), R.raw.finaltone);
        if (sound) {
            f.start();
        }
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(getActivity());
        View mView = getLayoutInflater().inflate(R.layout.alert_final, null);
        Button i_yes = (Button) mView.findViewById(R.id.close);
        Button i_next = mView.findViewById(R.id.next);
        TextView m = (TextView) mView.findViewById(R.id.minute);

        int minute = Integer.parseInt(parts[0]);
        int second = Integer.parseInt(parts[1]);

        StringBuilder sb = new StringBuilder();

        if (minute > 0) {
            sb.append(minute + " Minute ");
            sb.append(second + " Seconds. ");
        } else if (second > 0) {
            sb.append(second + " Seconds. ");
        }

        m.setText(String.valueOf(sb));

        i_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                recyclerView.setAdapter(adapter);

                adapter.shufflemydata();
                adapter.notifyDataSetChanged();
                chronometer.setText("00:00");
                switchOne.setEnabled(true);
                switchOne.setChecked(false);
                ishard = false;
                progressBar.setProgress(120);
                match = 1;
                Number.setText("1");
                dialog.dismiss();

            }
        });
        i_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fm = getActivity().getSupportFragmentManager();
                Fragment homeFragment;
                bundle = new Bundle();
                bundle.putBoolean("HARD", ishard);
                bundle.putBoolean("SOUND",sound);

                FragmentTransaction ft = fm.beginTransaction();
                homeFragment = new NumberLevel2();
                homeFragment.setArguments(bundle);
                ft.add(R.id.am_full_container, homeFragment);
                ft.commit();
                dialog.dismiss();
            }
        });
        mBuilder.setView(mView);
        dialog = mBuilder.create();
        dialog.show();
        dialog.setCanceledOnTouchOutside(false);


    }

    public class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            Log.d("VT", (String.valueOf((millisUntilFinished / 1000))));
//                int progress = (int) (millisUntilFinished/100);
            int secondsRemaining = (int) millisUntilFinished / 1000;
            Log.d("VT", ">>>>>>>>> secondsRemaining: " + secondsRemaining);

            float fraction = millisUntilFinished / (float) (10 * 1000);
            Log.d("VT", ">>>>>>>>> fraction: " + fraction);
//                progressBar.setProgress(progress);
            int total = (int) ((millisUntilFinished / 1000));
            progressBar.setProgress(total);
        }

        @Override
        public void onFinish() {
            Log.d("VT", "TASK Completed");
            progressBar.setProgress(0);
            // callAlert();
        }


    }

    public void refreshAdapter() {
        adapter = new MyRecyclerViewAdapter(getActivity(), data, start, end);
        recyclerView.setAdapter(adapter);

    }


}
