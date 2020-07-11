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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private String[] mData = new String[0];
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
            "#808000"};

    public String[] mColors = {
            "FFEBEE", "FFCDD2", "EF9A9A", "E57373", "EF5350", "F44336", "E53935",        //reds
            "D32F2F", "C62828", "B71C1C", "FF8A80", "FF5252", "FF1744", "D50000",
            "FCE4EC", "F8BBD0", "F48FB1", "F06292", "EC407A", "E91E63", "D81B60",        //pinks
            "C2185B", "AD1457", "880E4F", "FF80AB", "FF4081", "F50057", "C51162",
            "F3E5F5", "E1BEE7", "CE93D8", "BA68C8", "AB47BC", "9C27B0", "8E24AA",        //purples
            "7B1FA2", "6A1B9A", "4A148C", "EA80FC", "E040FB", "D500F9", "AA00FF",
            "EDE7F6", "D1C4E9", "B39DDB", "9575CD", "7E57C2", "673AB7", "5E35B1",        //deep purples
            "512DA8", "4527A0", "311B92", "B388FF", "7C4DFF", "651FFF", "6200EA",
            "E8EAF6", "C5CAE9", "9FA8DA", "7986CB", "5C6BC0", "3F51B5", "3949AB",        //indigo
            "303F9F", "283593", "1A237E", "8C9EFF", "536DFE", "3D5AFE", "304FFE",
            "E3F2FD", "BBDEFB", "90CAF9", "64B5F6", "42A5F5", "2196F3", "1E88E5",        //blue
            "1976D2", "1565C0", "0D47A1", "82B1FF", "448AFF", "2979FF", "2962FF",
            "E1F5FE", "B3E5FC", "81D4fA", "4fC3F7", "29B6FC", "03A9F4", "039BE5",        //light blue
            "0288D1", "0277BD", "01579B", "80D8FF", "40C4FF", "00B0FF", "0091EA",
            "E0F7FA", "B2EBF2", "80DEEA", "4DD0E1", "26C6DA", "00BCD4", "00ACC1",        //cyan
            "0097A7", "00838F", "006064", "84FFFF", "18FFFF", "00E5FF", "00B8D4",
            "E0F2F1", "B2DFDB", "80CBC4", "4DB6AC", "26A69A", "009688", "00897B",        //teal
            "00796B", "00695C", "004D40", "A7FFEB", "64FFDA", "1DE9B6", "00BFA5",
            "E8F5E9", "C8E6C9", "A5D6A7", "81C784", "66BB6A", "4CAF50", "43A047",        //green
            "388E3C", "2E7D32", "1B5E20", "B9F6CA", "69F0AE", "00E676", "00C853",
            "F1F8E9", "DCEDC8", "C5E1A5", "AED581", "9CCC65", "8BC34A", "7CB342",        //light green
            "689F38", "558B2F", "33691E", "CCFF90", "B2FF59", "76FF03", "64DD17",
            "F9FBE7", "F0F4C3", "E6EE9C", "DCE775", "D4E157", "CDDC39", "C0CA33",        //lime
            "A4B42B", "9E9D24", "827717", "F4FF81", "EEFF41", "C6FF00", "AEEA00",
            "FFFDE7", "FFF9C4", "FFF590", "FFF176", "FFEE58", "FFEB3B", "FDD835",        //yellow
            "FBC02D", "F9A825", "F57F17", "FFFF82", "FFFF00", "FFEA00", "FFD600",
            "FFF8E1", "FFECB3", "FFE082", "FFD54F", "FFCA28", "FFC107", "FFB300",        //amber
            "FFA000", "FF8F00", "FF6F00", "FFE57F", "FFD740", "FFC400", "FFAB00",
            "FFF3E0", "FFE0B2", "FFCC80", "FFB74D", "FFA726", "FF9800", "FB8C00",        //orange
            "F57C00", "EF6C00", "E65100", "FFD180", "FFAB40", "FF9100", "FF6D00",
            "FBE9A7", "FFCCBC", "FFAB91", "FF8A65", "FF7043", "FF5722", "F4511E",        //deep orange
            "E64A19", "D84315", "BF360C", "FF9E80", "FF6E40", "FF3D00", "DD2600",
            "EFEBE9", "D7CCC8", "BCAAA4", "A1887F", "8D6E63", "795548", "6D4C41",        //brown
            "5D4037", "4E342E", "3E2723",
            "FAFAFA", "F5F5F5", "EEEEEE", "E0E0E0", "BDBDBD", "9E9E9E", "757575",        //grey
            "616161", "424242", "212121",
            "ECEFF1", "CFD8DC", "B0BBC5", "90A4AE", "78909C", "607D8B", "546E7A",        //blue grey
            "455A64", "37474F", "263238"};
    ArrayList<String> number = new ArrayList<String>();
    int workingornot=1;
    LinkedList<String> wordlist;
    int randomNumber=2;

    public MyRecyclerViewAdapter(Context context,String[] data)
    {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;

        shufflemydata();
    }

    // data is passed into the constructor
 public  MyRecyclerViewAdapter(Context context, String[] data,int start, int end) {
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
        View view = mInflater.inflate(R.layout.grid_adapter_view, parent, false);
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

    }

    // total number of cells
    @Override
    public int getItemCount() {
        return mData.length;
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = (TextView) itemView.findViewById(R.id.info_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            Log.d("TAG","adapter position "+getAdapterPosition() );
            if(hard)
            {number.clear();
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
        for (int i = start; i <=end; ++i) {
            number.add(String.valueOf(i));
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