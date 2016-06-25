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

public class Beaches extends AppCompatActivity {
    GridView myGrid1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_beaches);
        myGrid1  = (GridView) findViewById(R.id.gridView2);
        myGrid1.setAdapter(new Adapter(this));
        myGrid1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                try
                {
                    Beach temp1 = (Beach) adapterView.getItemAtPosition(i);
                    Intent intent1 = new Intent(Beaches.this, Fort.class);
                    intent1.putExtra("image1", temp1.imageId1);
                    startActivity(intent1);
                } catch (ClassCastException cce)
                {
                    cce.printStackTrace();
                }
            }
        });
    }
}
class Beach
{
    int imageId1;
    String name1;

    Beach(int imageId1, String name1)
    {
        this.imageId1=imageId1;
        this.name1=name1;
    }
}
class BeachAdapter extends BaseAdapter {
    ArrayList<Beach> list;
    Context context;

    BeachAdapter(Context context) {
        this.context = context;
        list = new ArrayList<Beach>();
        Resources res = context.getResources();
        String[] tempBeachName = res.getStringArray(R.array.beach_in_karnataka);
        int[] beachImages = {R.drawable.gokarna_beach, R.drawable.karwar_beach, R.drawable.kaup_beach, R.drawable.malpe_beach, R.drawable.marawanthe_beach, R.drawable.murudeshwar_beach, R.drawable.surathkal_beach};
        for (int i = 0; i < 7; i++) {
            Beach b = new Beach(beachImages[i], tempBeachName[i]);
            list.add(b);
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
        ImageView myBeach;
        ViewHolder(View v)
        {
            myBeach = (ImageView) v.findViewById(R.id.imageView2);
        }
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ImageView imageView;
        View row1 = view;
        ViewHolder holder = null;
        if(row1==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row1 = inflater.inflate(R.layout.single_item, viewGroup, false);
            /*imageView = new ImageView(context);
            imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(2, 2, 2, 2);*/
            holder = new ViewHolder(row1);
            row1.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) row1.getTag();
        }
        Beach temp1 = list.get(i);
        holder.myBeach.setImageResource(temp1.imageId1);
        return row1;
    }
}