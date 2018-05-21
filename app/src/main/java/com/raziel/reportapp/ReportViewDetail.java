package com.raziel.reportapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ReportViewDetail extends AppCompatActivity {

    @BindView(R.id.lblVDevice)
    TextView lblVDevice;
    @BindView(R.id.lblVDescription)
    TextView lblVDescription;
    @BindView(R.id.lblVDate)
    TextView lblVDate;
    @BindView(R.id.lblVState)
    TextView lblVState;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ButterKnife.bind(this);

        try{
            Bundle bundle = getIntent().getExtras();
            String device = bundle.getString("device");
            String description = bundle.getString("description");
            String date = bundle.getString("date");
            String state = bundle.getString("state");

            lblVDevice.setText(device);
            lblVDescription.setText(description);
            lblVDate.setText(date);
            lblVState.setText(state);

            //if(device != null && description != null)
                //presenter.addReport(device,description,state);

        }catch (Exception e){
            e.getStackTrace();
        }
    }


}
