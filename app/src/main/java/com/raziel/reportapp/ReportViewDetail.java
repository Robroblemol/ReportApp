package com.raziel.reportapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.raziel.reportapp.models.IRViewDetailPresenter;
import com.raziel.reportapp.models.IReportViewDetail;
import com.raziel.reportapp.presenter.RViewDetailPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ReportViewDetail extends AppCompatActivity implements IReportViewDetail {

    @BindView(R.id.lblVDevice)
    TextView lblVDevice;
    @BindView(R.id.lblVDescription)
    TextView lblVDescription;
    @BindView(R.id.lblVDate)
    TextView lblVDate;
    @BindView(R.id.lblVState)
    TextView lblVState;
    @BindView(R.id.txtVFix)
    TextView txtVfix;
    @BindView(R.id.bttnFix)
    Button bttnFix;

    IRViewDetailPresenter presenter;
    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        presenter = new RViewDetailPresenter(this);

        ButterKnife.bind(this);

        try{
            Bundle bundle = getIntent().getExtras();
            String device = bundle.getString("device");
            String description = bundle.getString("description");
            String date = bundle.getString("date");
            String state = bundle.getString("state");
            String fix = bundle.getString("fix");
            id = bundle.getString("id");

            lblVDevice.setText(device);
            lblVDescription.setText(description);
            lblVDate.setText(date);
            lblVState.setText(state);

            if(state.equals("Novedad")){
                //
                txtVfix.setEnabled(true);
                txtVfix.setVisibility(View.VISIBLE);
                txtVfix.setText(fix);
                bttnFix.setVisibility(View.VISIBLE);
                bttnFix.setEnabled(true);
            }else{
                txtVfix.setEnabled(false);
                txtVfix.setVisibility(View.INVISIBLE);
                bttnFix.setVisibility(View.INVISIBLE);
                bttnFix.setEnabled(false);
            }


            //if(device != null && description != null)
                //presenter.addReport(device,description,state);

        }catch (Exception e){
            e.getStackTrace();
        }
    }

    @OnClick(R.id.bttnFix)
    @Override
    public void addFix(final View v) {
        MaterialDialog dialog = new MaterialDialog.Builder(this)
                .title(R.string.add_fix)
                .customView(R.layout.fix_activity,true)
                .positiveText(R.string.add)
                .onPositive(new MaterialDialog.SingleButtonCallback(){

                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        View view = dialog.getCustomView();
                        try{
                            TextView fix = view.findViewById(R.id.etFix);
                            String f = fix.getText().toString();
                            //TextView txtVfix = (TextView) v;
                            //txtVfix.setText(f);
                            presenter.addFix(f,id);
                            //txtVfix.setVisibility(View.INVISIBLE);
                            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                            intent.putExtra("login",true);
                            startActivity(intent);
                        }catch (Exception e){

                        }
                    }
                }).show();

    }
}
