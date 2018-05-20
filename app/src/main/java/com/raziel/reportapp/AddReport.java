package com.raziel.reportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.TextView;

import com.raziel.reportapp.models.IAddReport;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddReport extends AppCompatActivity implements IAddReport {

    @BindView(R.id.etDevice)
    TextView etDevice;
    @BindView(R.id.etDescription)
    TextView etDescription;
    @BindView(R.id.cbIssue)
    CheckBox cdIsuue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_report);

        ButterKnife.bind(this);
    }
    @OnClick(R.id.btnAddReport)
    @Override
    public void addReport() {
        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
        intent.putExtra("device",etDevice.getText().toString());
        intent.putExtra("description",etDescription.getText().toString());
        startActivity(intent);

    }
}
