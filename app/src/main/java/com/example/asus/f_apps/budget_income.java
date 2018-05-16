package com.example.asus.f_apps;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class budget_income extends AppCompatActivity {

    private Button incomepadaincome;
    private Button outcomepadaincome;
    private BarChart chart;
//    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_budget_income);
//        auth = FirebaseAuth.getInstance();
//        incomepadaincome = (Button) findViewById(R.id.btn_incomediincome);
//        outcomepadaincome = (Button) findViewById(R.id.btn_outcomediincome);
//        chart = (BarChart)findViewById(R.id.chart);
//
//        ArrayList<String> xAxis = new ArrayList<>();
//        ArrayList<IBarDataSet> dataSets = null;
//        ArrayList<BarEntry> valueSet = new ArrayList<>();
//
//        xAxis.add("Januari");
//        xAxis.add("Februari");
//        xAxis.add("Maret");
//        xAxis.add("April");
//        xAxis.add("Mei");
//        xAxis.add("Juni");
//        xAxis.add("Juli");
//        xAxis.add("Agustus");
//        xAxis.add("September");
//        xAxis.add("Oktober");
//        xAxis.add("November");
//        xAxis.add("Desember");
//
//
//        valueSet.add(new BarEntry(10,10));
//        valueSet.add(new BarEntry(20,20));
//        valueSet.add(new BarEntry(30,30));
//        valueSet.add(new BarEntry(40,40));
//        valueSet.add(new BarEntry(50,50));
//        valueSet.add(new BarEntry(60,60));
//        valueSet.add(new BarEntry(70,70));
//        valueSet.add(new BarEntry(80,80));
//        valueSet.add(new BarEntry(90,90));
//        valueSet.add(new BarEntry(100,100));
//        valueSet.add(new BarEntry(110,110));
//        valueSet.add(new BarEntry(120,120));
//
//        BarDataSet barDataSet = new BarDataSet(valueSet,"browser");
//        barDataSet.setColors(new int[]{Color.RED, Color.MAGENTA, Color.BLUE, Color.RED, Color.MAGENTA, Color.BLUE, Color.RED, Color.MAGENTA, Color.BLUE, Color.RED, Color.MAGENTA, Color.BLUE});
//
//        dataSets = new ArrayList<>();
//        dataSets.add(barDataSet);
//
//        YAxis yAxisRight = chart.getAxisRight();
//        yAxisRight.setEnabled(false);
//
//        BarData data = new BarData(xAxis, dataSets);
//        chart.setExtraOffsets(0,0,0,20);
//        chart.setData(data);
//        chart.animateXY(2000,2000);
//        chart.invalidate();
//
//
//        incomepadaincome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(budget_income.this, budget_outcome.class);
//                startActivity(intent);
//           }
//        });
//
//        outcomepadaincome.setOnClickListener(new View.OnClickListener() {
//            @Override
//           public void onClick(View view) {
//                Intent intent = new Intent(budget_income.this, budget_outcome.class);
//                startActivity(intent);
//            }
//        });
    }
}
