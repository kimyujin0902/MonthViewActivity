package com.example.monthviewactivity;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.GridView;
import android.widget.ArrayAdapter;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnLast = (Button)findViewById(R.id.last);
        Button btnNext = (Button)findViewById(R.id.next);
        txt = (TextView)findViewById(R.id.prsent);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int lastday = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        String[] days = new String[lastday];
        for(int i=0; i<lastday; i++)
            days[i] = "" + (i+1);

        ArrayAdapter<String> adapter
                = new ArrayAdapter<String>(
                        this,
                android.R.layout.simple_list_item_1,
                days);
        GridView gridview = (GridView) findViewById(R.id.gridview);
        gridview.setAdapter(adapter);

        txt.setText(String.format("%d년 %d월", year, month+1));

    }
}