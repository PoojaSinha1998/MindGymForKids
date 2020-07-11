package android.prgguru.com.mymindgym.adapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.prgguru.com.mymindgym.R;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MyRecyclerViewAlphabateAdapter extends RecyclerView.Adapter<MyRecyclerViewAlphabateAdapter.ViewHolder> {

    private String[] mData = new String[0];
    private String[] numbers;
    private int start;
    private int end;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;
    boolean hard=false;

    public String[] mColours_25={"#F9EBEA","#CD6155","#641E16","#FDEDEC","#EC7063","#78281F",
            "#F5EEF8",
            "#AF7AC5",
            "#512E5F",
            "#5B2C6F",
            "#7FB3D5",
            "#154360",
            "#A3E4D7",
            "#0B5345",
            "#27AE60",
            "#186A3B",
            "#F9E79F",
            "#7D6608",
            "#F39C12",
            "#784212",
            "#D35400",
            "#FBEEE6",
            "#ECF0F1",
            "#7B7D7D",
            "#D7DBDD",
            "#F2F3F4",
            "#4D5656",
            "#34495E",
            "#212F3D",
            "#FF00FF",
            "#00FFFF",
            "#808000",
            "#212F3D",
            "#FF00FF",
            "#00FFFF",
            "#F5EEF8",
            "#AF7AC5",
            "#512E5F",
            "#5B2C6F",
            "#7FB3D5",
            "#154360",
            "#808000"};

    ArrayList<String> number = new ArrayList<String>();
    LinkedList<String> wordlist;


    public MyRecyclerViewAlphabateAdapter(Context context, String[] data,String[] numbers)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.numbers = numbers;

        shufflemydata();
    }

    // data is passed into the constructor
 public MyRecyclerViewAlphabateAdapter(Context context, String[] data, int start, int end) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
     this.start = start;
     this.end = end;
     shufflemydata();
//     for (int i = start; i <=end; ++i) {
//         number.add(String.valueOf(i));
//         Log.d("TAG","value of number "+i);
//     }
//     Collections.shuffle(number);
//     wordlist = new LinkedList<String>();
//     for (String i : mColours_25)
//         wordlist.add(i);
//     Collections.shuffle(wordlist);




//     Random rand = new Random();
//     while (true) {
//         int number = rand.nextInt(24) + 1;
//         if (!myNumbers.contains(number)) {
//             myNumbers.add(number);
//
//             if (myNumbers.size() == 5)
//                 break;
//         }
//     }
    }





    // inflates the cell layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.grid_alphabate_adapter_view, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the textview in each cell
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int i = new Random().nextInt(254);
        Log.d("TAG","values randomly "+ number);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setStroke(2,Color.BLACK);
//        shape.setColor(Color.parseColor ("#"+mColors[new Random().nextInt(254)]));
//        shape.setColor(Color.parseColor (wordlist.get(new Random().nextInt(mColours_25.length))));
        shape.setColor(Color.parseColor (wordlist.get(position)));
        holder.myTextView.setBackground(shape);
        Log.d("TAG","ON position" +position+"   value  "+number.get(position));
        Log.d("TAG"," position" +position);
        holder.myTextView.setText(number.get(position));

//        holder.myTextView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                GradientDrawable shape = new GradientDrawable();
//                shape.setShape(GradientDrawable.OVAL);
//                shape.setStroke(10,Color.BLACK);
//
//                v.setBackground(shape);
//
//                Log.d("ADAPTER","v");
//            }
//        });


    }

    // total number of cells
    @Override
    public int getItemCount() {
        return numbers.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        LinearLayout linText;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            linText=itemView.findViewById(R.id.linearText);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

//            view.setBackgroundResource(R.drawable.custom_ripple);


            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());


            Log.d("TAG","adapter position "+getAdapterPosition() );
            if(hard)
            {

                shufflemydata();
            }
            notifyDataSetChanged();
        }
    }

    // convenience method for getting data at click position
    public String getItem(int id) {
     Log.d("TAG","Inside adapter "+  mData[id] + " id "+number.get(id));
        return number.get(id);
    }

    public  String getCount(int id)
    {
        return mData[id];
    }

    // allows clicks events to be caught
    public void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }
    public void shufflemydata()
    {
        number.clear();
        for (int i = 0; i <numbers.length; ++i) {
            number.add(numbers[i]);
            Log.d("TAG","value of number "+i);
        }
        Collections.shuffle(number);
        Log.d("TAG","value of number after shyffling "+number);

        wordlist = new LinkedList<String>();
        for (String i : mColours_25)
            wordlist.add(i);
        Collections.shuffle(wordlist);

    }
    public void sethard(boolean vale)
    {
        hard=vale;

    }





}