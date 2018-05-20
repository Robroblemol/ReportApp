package com.raziel.reportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.raziel.reportapp.models.IMainActivity;
import com.raziel.reportapp.presenter.Presenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    @BindView(R.id.rvCardViews)
    RecyclerView rvReport;

    private RecyclerViewAdapter rvAdapter;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        presenter = new Presenter(this);
        rvAdapter = new RecyclerViewAdapter();

        presenter.initLstReport();
        rvAdapter.addLstReport(presenter.getLst());

        rvReport.setLayoutManager(new LinearLayoutManager(this));
        rvReport.addItemDecoration(new android.support.v7.widget.
                DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        rvReport.setAdapter(rvAdapter);

        try{
            Bundle bundle = getIntent().getExtras();
            String device = bundle.getString("device");
            String description = bundle.getString("description");
            if(device != null && description != null)
                presenter.addReport(device,description);

        }catch (Exception e){
            e.getStackTrace();
        }


    }
    @OnClick(R.id.fActionButton)
    @Override
    public void addReport() {
       Intent addReport = new Intent(getApplicationContext(),AddReport.class);
       startActivity(addReport);
    }

    @Override
    public void addReport(ReportViewModel t) {
        rvAdapter.addReport(t);
    }

    @Override
    public void showReports(List<ReportViewModel> taskViewModelList) {
        rvAdapter.addLstReport(taskViewModelList);
        rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void lodingData() {
        Toast.makeText(this, "Cargando datos", Toast.LENGTH_LONG).show();
    }
}