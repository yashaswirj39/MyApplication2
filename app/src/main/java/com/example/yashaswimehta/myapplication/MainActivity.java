package com.example.yashaswimehta.myapplication;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    GridView myGrid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myGrid  = (GridView) findViewById(R.id.gridView);
        myGrid.setAdapter(new Adapter(this));
        myGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Tourist temp = (Tourist) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(MainActivity.this, Beaches.class);
                intent.putExtra("image", temp.imageId);
                startActivity(intent);
            }
        });
    }
}
class Tourist
{
    int imageId;
    String name;
    Tourist(int imageId, String name)
    {
        this.imageId=imageId;
        this.name=name;
    }
}
class Adapter extends BaseAdapter
{
    ArrayList<Tourist> list;
    Context context;
    Adapter(Context context)
    {
        this.context = context;
        list = new ArrayList<Tourist>();
        Resources res = context.getResources();
        String[] tempTouristName = res.getStringArray(R.array.tourist_place);
        int[] touristImages = {R.drawable.beach, R.drawable.heritage, R.drawable.nature, R.drawable.temple, R.drawable.wildlife};
        for(int i=0;i<5;i++)
        {
            Tourist tourist = new Tourist(touristImages[i], tempTouristName[i]);
            list.add(tourist);
        }
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    class ViewHolder
    {
        ImageView myTourist;
        ViewHolder(View v)
        {
            myTourist = (ImageView) v.findViewById(R.id.imageView);
        }
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        View row = view;
        ViewHolder holder = null;
        if(row==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.single_item, viewGroup, false);
            holder = new ViewHolder(row);
            row.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row.getTag();
        }
        Tourist temp = list.get(i);
        holder.myTourist.setImageResource(temp.imageId);
            return row;
    }
}