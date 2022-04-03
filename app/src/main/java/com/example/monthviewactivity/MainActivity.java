package com.example.monthviewactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    int year, month;
    private Object AdapterView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("hey", "OnCreate MainActivity");
        Button btnLast = (Button) findViewById(R.id.last);
        Button btnNext = (Button) findViewById(R.id.next);
        txt = (TextView) findViewById(R.id.prsent);

        Calendar calendar = Calendar.getInstance();

        Intent getIntent = getIntent();
        month = getIntent.getIntExtra("monthInfo", 0);
        year = getIntent.getIntExtra("yearInfo", 0);
        Log.i("hey", "getnIntent()"+month+year);

        String[] days = makeCal();
        Log.i("hey", "days Size: "+days.length+"and... "+year+month);


        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                days);

        /*adapter = new MyAdapter(this, R.layout.dayInfo, days);*/
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);


        btnNext.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                if(month==11) {
                    year++;
                    month = -1;
                }
                intent.putExtra("monthInfo", month + 1);
                intent.putExtra("yearInfo", year);
                startActivity(intent);
                finish();
            }
        });

    }

    public String[] makeCal(){
        Calendar calendar = Calendar.getInstance();

        Log.i("hey", "makeCal()... now "+year+month);
        if(year==0 && month==0){
            year = calendar.get(calendar.YEAR);
            month = calendar.get(calendar.MONTH);
            Log.i("hey", "00 -> "+year+month);
        }


        calendar.set(year, month, 1);
        int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        int startday = calendar.get(calendar.DAY_OF_WEEK);
        int daySize = lastday + startday - 1;

        String[] days = new String[daySize];
        for(int i=0; i<startday; i++)
            days[i] = "";

        for(int i=startday-1; i<daySize; i++)
            days[i] = "" + (i - startday + 2);

        txt.setText(String.format("%d년 %d월", year, month+1));
        return days;
    }
}