package com.raziel.reportapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.raziel.reportapp.models.IMainActivity;
import com.raziel.reportapp.presenter.Presenter;
import com.raziel.reportapp.views.EmailPasswordActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements IMainActivity {
    @BindView(R.id.rvCardViews)
    RecyclerView rvReport;

    private RecyclerViewAdapter rvAdapter;
    private RViewAdapter rViewAdapter = new RViewAdapter();
    private Presenter presenter;

    //private boolean flagLoing = false;


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
        rvReport.setAdapter(rViewAdapter);


        //if (!flagLoing){

        //}else {
            try{
                Bundle bundle = getIntent().getExtras();
                String device = bundle.getString("device");
                String description = bundle.getString("description");
                Boolean state = bundle.getBoolean("state");
                //Boolean login = bundle.getBoolean("login");
                if(device != null && description != null)
                    presenter.addReport(device,description,state);
                //if (login){
                //    Intent logIn = new Intent(getApplicationContext(),EmailPasswordActivity.class);
                //    startActivity(logIn);
                //}
          //          flagLoing = bundle.getBoolean("login");


            }catch (Exception e){
           //     Bundle bundle = getIntent().getExtras();
           //     flagLoing = bundle.getBoolean("login");

                e.getStackTrace();
            }

        //}

        rViewAdapter.setOnItemClickListener(new RViewAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {
               // Toast.makeText
               //         (v.getContext(),"Position card = "+position,
               //                 Toast.LENGTH_SHORT).show();
                ReportViewModel rpt = rViewAdapter.getReportByIndex(position);
                Intent viewDetail = new Intent(getApplicationContext(),ReportViewDetail.class);
                viewDetail.putExtra("device",rpt.getDevice());
                viewDetail.putExtra("description",rpt.getDescription());
                viewDetail.putExtra("state",rpt.getState());
                viewDetail.putExtra("date",rpt.getDate());
                startActivity(viewDetail);

            }
        });


    }
    @OnClick(R.id.fActionButton)
    @Override
    public void addReport() {
       Intent addReport = new Intent(getApplicationContext(),AddReport.class);
       startActivity(addReport);
    }

    @Override
    public void addReport(ReportViewModel t) {
        //rvAdapter.addReport(t);
        rViewAdapter.addReport(t);
    }

    @Override
    public void showReports(List<ReportViewModel> taskViewModelList) {
        rViewAdapter.addLstReport(taskViewModelList);
        rViewAdapter.notifyDataSetChanged();
        //rvAdapter.addLstReport(taskViewModelList);
        //rvAdapter.notifyDataSetChanged();
    }

    @Override
    public void lodingData() {
        Toast.makeText(this, "Cargando datos", Toast.LENGTH_LONG).show();
    }

}
